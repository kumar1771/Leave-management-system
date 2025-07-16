package com.example.Leave.management.system.Repositery;

import com.example.Leave.management.system.Model.LeaveSignup;
//import com.example.Leave.management.system.Model.Leavelog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveRepositery extends JpaRepository<LeaveSignup,Integer> {
    Optional<LeaveSignup> findByEmailIdAndPassword(String emailId, String password);

}
