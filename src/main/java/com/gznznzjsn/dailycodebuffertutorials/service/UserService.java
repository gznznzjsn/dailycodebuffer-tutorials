package com.gznznzjsn.dailycodebuffertutorials.service;

import com.gznznzjsn.dailycodebuffertutorials.entity.User;
import com.gznznzjsn.dailycodebuffertutorials.entity.VerificationToken;
import com.gznznzjsn.dailycodebuffertutorials.model.UserModel;

import java.util.Optional;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);
}
