package dev.notification.query;

import dev.common_service.queries.SendNotiQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotiQuery {
    @QueryHandler
    public String sendNoti(SendNotiQuery query){
        String[] emails = query.getEmails().toArray(String[]::new);

        try {
            // Tạo đối tượng Email
            HtmlEmail email = new HtmlEmail();

            // Cấu hình thông tin email server
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthentication("ak12@gmail.com", "123456");
            email.setStartTLSEnabled(true);

            // Thiết lập người gửi
            email.setFrom("ak12@gmail.com", "Quiz lab");

            // Thiết lập người nhận
            email.addTo(emails);

            // Thiết lập chủ đề và nội dung email
            email.setSubject("Invite to play quiz");
            email.setHtmlMsg("You are invited to play this quiz, click here to play: ");

            // Gửi email
            email.send();

            System.out.println("Email sent successfully!");
        } catch (EmailException e) {
            System.out.println("Gửi thư thành công");
        }

        return "ok";
    }
}