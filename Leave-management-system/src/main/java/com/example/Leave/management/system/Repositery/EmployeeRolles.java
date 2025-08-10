package com.example.Leave.management.system.Repositery;

import com.example.Leave.management.system.Model.RoleBasedAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRolles extends JpaRepository<RoleBasedAccess,Integer> {
    Optional<RoleBasedAccess> findByemailIdIgnoreCase(String emailId);
}
