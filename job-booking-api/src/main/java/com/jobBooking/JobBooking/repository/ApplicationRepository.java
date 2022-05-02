package com.jobBooking.JobBooking.repository;

import com.jobBooking.JobBooking.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
