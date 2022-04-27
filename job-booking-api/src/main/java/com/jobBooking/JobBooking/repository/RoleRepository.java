package com.jobBooking.JobBooking.repository;

import com.jobBooking.JobBooking.enumeration.ERole;
import com.jobBooking.JobBooking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
