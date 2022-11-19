package com.gznznzjsn.dailycodebuffertutorials.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class VerificationToken {

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
            foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN")
    )
    private User user;


    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }


    public VerificationToken(String token) {
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }


    //todo: improve work with date and time
    private Date calculateExpirationTime(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
