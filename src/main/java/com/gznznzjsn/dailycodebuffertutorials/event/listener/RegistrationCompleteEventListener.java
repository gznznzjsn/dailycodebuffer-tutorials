package com.gznznzjsn.dailycodebuffertutorials.event.listener;

import com.gznznzjsn.dailycodebuffertutorials.entity.User;
import com.gznznzjsn.dailycodebuffertutorials.event.RegistrationCompleteEvent;
import com.gznznzjsn.dailycodebuffertutorials.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final UserService userService;

    @Autowired
    public RegistrationCompleteEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the verification token for the User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        //send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

        //sendVerificationEmail(), know it  is just mocking
        log.info("click the link to verify your account: {}",url);
    }
}
