package by.pharmsystem.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class BeanConfig {

    @Bean
    public SimpleMailMessage getMailMessenger() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("E-pharm service");
        return message;
    }
}
