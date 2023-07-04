package coachingmateanalytics.coachingmate.service;


import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
@SpringBootTest
public class email {
    @Autowired
    private SendEmail sendEmail;

    @Test
    void sendEmail() throws UnsupportedEncodingException {
        sendEmail.sendVerEmail("601952245@qq.com");
    }
}