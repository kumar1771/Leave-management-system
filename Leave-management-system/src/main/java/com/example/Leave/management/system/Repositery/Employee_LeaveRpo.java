package com.example.Leave.management.system.Repositery;

import com.example.Leave.management.system.Model.LeaveReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface Employee_LeaveRpo extends JpaRepository<LeaveReport,Integer> {
    Optional<LeaveReport> findByEmailId(String emailId);  // ✅ Used for both get & update
    List<LeaveReport> findAllByEmailId(String emailId);
//    @Query(nativeQuery = true,value="Select * from Leave_report")
//    List<LeaveReport> findAllByNoLeave();
}
