package com.cleanerPro.CleanerPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanerPro.CleanerPro.model.Cleaner;


public interface CleanerRepository extends JpaRepository<Cleaner, Long>{
	
	
}
