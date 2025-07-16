package com.example.Leave.management.system.Repositery;

import com.example.Leave.management.system.Model.LeaveReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Employee_LeaveRpo extends JpaRepository<LeaveReport,Integer> {
    Optional<LeaveReport> findByEmailId(String emailId);  // ✅ Used for both get & update
    List<LeaveReport> findAllByEmailId(String emailId);
}
