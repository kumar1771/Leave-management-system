package com.example.Leave.management.system.Service;

import com.example.Leave.management.system.Model.LeaveReport;
import com.example.Leave.management.system.Model.LeaveSignup;
//import com.example.Leave.management.system.Model.Leavelog;
import com.example.Leave.management.system.Repositery.Employee_LeaveRpo;
import com.example.Leave.management.system.Repositery.LeaveRepositery;
//import com.example.Leave.management.system.Repositery.LeaveSignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveService {
    @Autowired
    LeaveRepositery leaverepositery;
    @Autowired
    Employee_LeaveRpo employeeleaverpo;
//    @Autowired
//    LeaveSignUpRepo leavesignuprepo;
//    public List<Leavelog> getlogin() {
//        return leaverepositery.findAll();
//    }

    public boolean validate(String emailId, String password) {
        return leaverepositery.findByEmailIdAndPassword(emailId, password).isPresent();
    }

    public void SignUp(String name, String emailId, String password) {
        LeaveSignup leavesignup = new LeaveSignup(name, emailId, password);
        leaverepositery.save(leavesignup);
    }

    public List<LeaveReport> findbyemailid(String emailId) {
        return employeeleaverpo.findAllByEmailId(emailId);
    }

    public LeaveReport updateleave(String emailId,String leaveType,int days) {
        LeaveReport report = employeeleaverpo.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        switch (leaveType) {
            case ("sick_leave"):
                if ((report.getSick_leave() <= 0) && ((report.getSick_leave() - days)<=0)) throw new RuntimeException("No sick Leave Balance");
                report.setSick_leave(report.getSick_leave() - days);
            case ("casual_leave"):
                if ((report.getCasual_leave() <= 0) && ((report.getCasual_leave()-days)<=0)) throw new RuntimeException("No casual Leave Balance");
                report.setCasual_leave(report.getCasual_leave() - days);
            case ("earned_leave"):
                if ((report.getEarned_leave() <= 0)&& ((report.getEarned_leave()-days)<=0)) throw new RuntimeException("No Earned Leave Balance");
                report.setEarned_leave(report.getEarned_leave() - days);
                ;
        }

//        report.setSick_leave(report.getCasual_leave()-1);
//        report.setCasual_leave(report.getEarned_leave()-1);
//        report.setEarned_leave(report.getCasual_leave()-1);

        return employeeleaverpo.save(report);
    }
}