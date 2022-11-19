package com.gznznzjsn.dailycodebuffertutorials.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {


    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // you can add the logic to validate password and matchingPassword before saving the data. Do it by yourself
    private String matchingPassword;

}
