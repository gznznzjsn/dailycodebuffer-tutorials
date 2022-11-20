package com.gznznzjsn.dailycodebuffertutorials.controller;

import com.gznznzjsn.dailycodebuffertutorials.entity.User;
import com.gznznzjsn.dailycodebuffertutorials.entity.VerificationToken;
import com.gznznzjsn.dailycodebuffertutorials.event.RegistrationCompleteEvent;
import com.gznznzjsn.dailycodebuffertutorials.model.PasswordModel;
import com.gznznzjsn.dailycodebuffertutorials.model.UserModel;
import com.gznznzjsn.dailycodebuffertutorials.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {


    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public RegistrationController(UserService userService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }

    @GetMapping("/hello")
    public String greet() {
        return "Oh, hi!";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return List.of("success", user).toString();
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "user Verified Successfully";
        } else {
            return "Wrong User";
        }
    }


    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user,applicationUrl(request), verificationToken);
        return "Verification Link is sent";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel,HttpServletRequest request){
        User user = userService.findUserByEmail(passwordModel.getEmail());
       String url = "";
        if(user!=null){
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user,token);
             passwordResetTokenMail(user,applicationUrl(request),token);
        }
        return "Reset password link is sent";
    }

    private void passwordResetTokenMail(User user, String applicationUrl, String token) {
        //send mail to user
        String url = applicationUrl + "/savePassword?token=" + token;

        //sendVerificationEmail(), know it  is just mocking
        log.info("click the link to reset your password: {}",url);
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token")String token,@RequestBody PasswordModel passwordModel){
        String result = userService.validatePasswordResetToken(token);
        if(!result.equalsIgnoreCase("valid")){
            return "Invalid token";
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if(user.isPresent()){
            userService.changePassword(user.get(),passwordModel.getNewPassword());
            return  "Password Reset Successfully";
        }else{
            return "Invalid token";
        }
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        //send mail to user
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();

        //sendVerificationEmail(), know it  is just mocking
        log.info("click the link to verify your account: {}",url);
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" + request.getServerPort() +
                request.getContextPath();
    }
}
