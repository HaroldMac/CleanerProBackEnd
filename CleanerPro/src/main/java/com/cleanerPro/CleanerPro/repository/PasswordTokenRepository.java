package com.cleanerPro.CleanerPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanerPro.CleanerPro.model.PasswordToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {

}
