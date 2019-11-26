package com.cleanerPro.CleanerPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanerPro.CleanerPro.model.User;

public interface UserRepository extends JpaRepository<User, Long>   {

}
