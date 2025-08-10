package com.example.Leave.management.system.Repositery;

import com.example.Leave.management.system.Model.LeaveReportAverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LeaveReportaverage extends JpaRepository<LeaveReportAverage,Integer> {
}
