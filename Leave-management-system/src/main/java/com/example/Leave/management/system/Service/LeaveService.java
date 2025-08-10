package com.example.Leave.management.system.Service;

import com.example.Leave.management.system.Model.LeaveReport;
import com.example.Leave.management.system.Model.LeaveReportAverage;
import com.example.Leave.management.system.Model.LeaveSignup;
//import com.example.Leave.management.system.Model.Leavelog;
import com.example.Leave.management.system.Model.RoleBasedAccess;
import com.example.Leave.management.system.Repositery.EmployeeRolles;
import com.example.Leave.management.system.Repositery.Employee_LeaveRpo;
import com.example.Leave.management.system.Repositery.LeaveReportaverage;
import com.example.Leave.management.system.Repositery.LeaveRepositery;
//import com.example.Leave.management.system.Repositery.LeaveSignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    @Autowired
    LeaveRepositery leaverepositery;
    @Autowired
    Employee_LeaveRpo employeeleaverpo;
    @Autowired
    EmployeeRolles employeerolles;
    @Autowired
    LeaveReportaverage leavereportaverage;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
        leavesignup.setPassword(encoder.encode(leavesignup.getPassword()));
        leaverepositery.save(leavesignup);
    }

    public List<LeaveReport> findbyemailid(String emailId) {
        return employeeleaverpo.findAllByEmailId(emailId);
    }

    public LeaveReport updateleave(String emailId, String leaveType, int days) {
        LeaveReport report = employeeleaverpo.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        switch (leaveType) {
            case ("sick_leave"):
                if ((report.getSick_leave() <= 0) && ((report.getSick_leave() - days) <= 0))
                    throw new RuntimeException("No sick Leave Balance");
                report.setSick_leave(report.getSick_leave() - days);
            case ("casual_leave"):
                if ((report.getCasual_leave() <= 0) && ((report.getCasual_leave() - days) <= 0))
                    throw new RuntimeException("No casual Leave Balance");
                report.setCasual_leave(report.getCasual_leave() - days);
            case ("earned_leave"):
                if ((report.getEarned_leave() <= 0) && ((report.getEarned_leave() - days) <= 0))
                    throw new RuntimeException("No Earned Leave Balance");
                report.setEarned_leave(report.getEarned_leave() - days);
                ;
        }

//        report.setSick_leave(report.getCasual_leave()-1);
//        report.setCasual_leave(report.getEarned_leave()-1);
//        report.setEarned_leave(report.getCasual_leave()-1);

        return employeeleaverpo.save(report);
    }

    public List<RoleBasedAccess> RollAssignment() {
        List<LeaveReport> employees = employeeleaverpo.findAll();
        List<RoleBasedAccess> rollassi = employees.stream().map(entity -> new RoleBasedAccess(entity.getName(),entity.getEmailId(),(entity.getEmailId().equals("apavanpavan961@gmail.com"))?"Admin":"employee")).toList();
        employeerolles.saveAll(rollassi);
        return rollassi;
    }

    public List<LeaveReportAverage> AccessBasedOnRoll(String emailId) {
        RoleBasedAccess RollType = employeerolles.findByemailIdIgnoreCase(emailId.toLowerCase())
                .orElseThrow(() -> new RuntimeException("Roll not found"));
        System.out.println(RollType.getRoll());
        if (RollType.getRoll().equals("Admin")) {
            int threshold = 30;
            List<LeaveReport> all = employeeleaverpo.findAll();
            List<LeaveReportAverage> Leavereport= all.stream().map(entity -> new LeaveReportAverage(entity.getName(), entity.getEmailId(), ((entity.getCasual_leave() + entity.getEarned_leave() + entity.getSick_leave()) / 3.0), (((entity.getCasual_leave() + entity.getEarned_leave() + entity.getSick_leave()) / 3.0) > threshold) ? "above threshold" : "below threshold")).toList();
            System.out.println(Leavereport);
            leavereportaverage.saveAll(Leavereport);
            return Leavereport;
        }
        else{
            throw new RuntimeException("Access denied, only admin can access this page");
        }
    }

    public String Verify(LeaveSignup leaveSignup) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(leaveSignup.getEmailId(),leaveSignup.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(leaveSignup.getEmailId());
        }
        return "Fail";
    }


//    public String AccessBasedOnRoll(String emailId, String roll) {
//        RoleBasedAccess RollType = employeerolles.findByRoll(roll)
//                .orElseThrow(() -> new RuntimeException("Roll not found"));
//        if (RollType.getRoll() == "Admin") {
//            public List<LeaveReportAverage> findbynoofleaves () {
//                int threshold = 30;
//                List<LeaveReport> all = employeeleaverpo.findAll();
//                return all.stream().map(entity -> new LeaveReportAverage(entity.getName(), entity.getEmailId(), ((entity.getCasual_leave() + entity.getEarned_leave() + entity.getSick_leave()) / 3.0), (((entity.getCasual_leave() + entity.getEarned_leave() + entity.getSick_leave()) / 3.0) > threshold) ? "above threshold" : "below threshold")).toList();
//            }
//        }
//    }
}