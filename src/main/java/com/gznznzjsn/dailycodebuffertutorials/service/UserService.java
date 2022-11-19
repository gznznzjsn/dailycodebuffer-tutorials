package com.gznznzjsn.dailycodebuffertutorials.service;

import com.gznznzjsn.dailycodebuffertutorials.entity.User;
import com.gznznzjsn.dailycodebuffertutorials.entity.VerificationToken;
import com.gznznzjsn.dailycodebuffertutorials.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

}
