package com.gznznzjsn.dailycodebuffertutorials.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
//todo maybe abstract class for tokens
public class PasswordResetToken {

    //EXPIRATION_TIME is 10 minutes
    private static final int EXPIRATION_TIME = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN")
    )
    private User user;


    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime = calculateExpirationTime();
    }


    public PasswordResetToken(String token) {
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }


    //todo: improve work with date and time
    private Date calculateExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, PasswordResetToken.EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
