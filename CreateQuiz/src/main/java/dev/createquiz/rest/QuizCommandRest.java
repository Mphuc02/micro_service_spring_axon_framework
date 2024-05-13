package dev.createquiz.rest;

import com.google.gson.Gson;
import dev.common_service.command.CreateQuizCommonCommand;
import dev.common_service.exception.BadRequestException;
import dev.common_service.exception.ErrorMessages;
import dev.common_service.exception.ObjectPropertiesException;
import dev.common_service.model.UserCommon;
import dev.common_service.queries.AuthenticationCommonQuery;
import dev.common_service.queries.CheckUsersExistQuery;
import dev.common_service.queries.SendNotiQuery;
import dev.common_service.response.UsersExistResponse;
import dev.createquiz.dto.MessageDTO;
import dev.createquiz.dto.QuizDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/create-quiz")
@Slf4j
public class QuizCommandRest {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final WebsocketRest websocketRest;

    @PostMapping()
    public ResponseEntity<Object>save(@RequestPart(name = "file") MultipartFile file,
                                      @Valid @RequestPart(name = "quiz") QuizDTO quiz,
                                      BindingResult result,
                                      HttpServletRequest request) throws IOException, InterruptedException {
        if(result.hasErrors()){
            throw ObjectPropertiesException.build(result.getAllErrors());
        }

        //Kiểm tra thông tin đăng nhập
        UserCommon loggedUser;
        String jwt = getJwtFromHeader(request);
        if (jwt != null) {
            AuthenticationCommonQuery query = new AuthenticationCommonQuery(UUID.randomUUID(), jwt);
            loggedUser = this.queryGateway.query(query, ResponseTypes.instanceOf(UserCommon.class)).join();
            if (loggedUser == null) {
                websocketRest.sendServiceStatus(new MessageDTO(1, false));
                throw new BadRequestException(ErrorMessages.JWT_NOT_INCLUDE);
            }
        }
        else {
            websocketRest.sendServiceStatus(new MessageDTO(1, false));
            throw new BadRequestException(ErrorMessages.JWT_NOT_INCLUDE);
        }
        websocketRest.sendServiceStatus(new MessageDTO(1, true));
        Thread.sleep(1000);

        //Kiểm tra danh sách người chơi
        CheckUsersExistQuery query = new CheckUsersExistQuery(new ArrayList<>(quiz.getParticipants()));
        UsersExistResponse response = queryGateway.query(query, ResponseTypes.instanceOf(UsersExistResponse.class)).join();
        if(!response.isSuccess()){
            Map<String, Object> participants = Collections.singletonMap("participants", response.getListError());
            websocketRest.sendServiceStatus(new MessageDTO(2, false));
            throw new ObjectPropertiesException(participants);
        }
        websocketRest.sendServiceStatus(new MessageDTO(2, true));
        Thread.sleep(1000);

        //Lưu quiz
        quiz.setId(UUID.randomUUID());
        CreateQuizCommonCommand command = new CreateQuizCommonCommand(new Gson().toJson(quiz), file.getBytes(), loggedUser);
        try {
            commandGateway.sendAndWait(command);
        } catch (RuntimeException e) {
            websocketRest.sendServiceStatus(new MessageDTO(3, true));
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        websocketRest.sendServiceStatus(new MessageDTO(3, true));
        Thread.sleep(1000);

        //Gửi noti
        SendNotiQuery sendNoti = new SendNotiQuery(response.getListError());
        try {
            queryGateway.query(sendNoti, ResponseTypes.instanceOf(String.class)).join();
        } catch (RuntimeException e) {
            websocketRest.sendServiceStatus(new MessageDTO(4, false));
            e.printStackTrace();
            throw e;
        }
        websocketRest.sendServiceStatus(new MessageDTO(4, true));
        Thread.sleep(1000);

        return ResponseEntity.ok(quiz.getId());
    }

    private String getJwtFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String jwt = authHeader.substring(7);
            return jwt.equals("null") ? null : jwt;
        } else {
            return null;
        }
    }
}