package com.cleanerPro.CleanerPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanerPro.CleanerPro.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
