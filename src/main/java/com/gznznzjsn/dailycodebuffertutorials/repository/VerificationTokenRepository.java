package com.gznznzjsn.dailycodebuffertutorials.repository;

import com.gznznzjsn.dailycodebuffertutorials.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
}
