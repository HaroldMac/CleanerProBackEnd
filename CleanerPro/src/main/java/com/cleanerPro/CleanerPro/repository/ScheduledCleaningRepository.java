package com.cleanerPro.CleanerPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanerPro.CleanerPro.model.ScheduledCleaning;

public interface ScheduledCleaningRepository extends JpaRepository<ScheduledCleaning, Long>{

}
