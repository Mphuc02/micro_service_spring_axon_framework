package dev.quiz_lab.common.rest;

import dev.common_service.exception.ObjectPropertiesException;
//import dev.quiz_lab.common.constant.Const.*;
//import dev.quiz_lab.common.dto.QuizDTO;
//import dev.quiz_lab.common.service.QuizService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping(QUIZ_API.URL)
//@RequiredArgsConstructor
//@Slf4j
//public class QuizRest {
//    private final QuizService quizService;
//
//    @GetMapping()
//    public ResponseEntity<List<QuizDTO>> getAll(){
//        return ResponseEntity.ok(quizService.getAll());
//    }
//
//    @GetMapping("/play/{id}")
//    public ResponseEntity<Object> play(@PathVariable UUID id){
//        return ResponseEntity.ok(quizService.findById(id));
//    }
//
//    @GetMapping("/download-template")
//    public ResponseEntity<Object> downloadTemplate(){
//        return ResponseEntity.ok("Download thanh cong");
//    }
//
//    @PostMapping()
//    public ResponseEntity<Object> save(@RequestPart MultipartFile file,
//                                       @Valid @RequestPart QuizDTO quiz,
//                                       BindingResult result){
//        if(result.hasErrors()){
//            throw new ObjectPropertiesException(result.getAllErrors());
//        }
//        return ResponseEntity.ok(quizService.save(file, quiz));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> delete(@PathVariable UUID id){
//        quizService.delete(id);
//        return ResponseEntity.ok("ok");
//    }
//}