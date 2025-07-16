package com.example.Leave.management.system.Controller;

//import com.example.Leave.management.system.Model.Leavelog;
import com.example.Leave.management.system.Model.LeaveReport;
import com.example.Leave.management.system.Service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveController {
    @Autowired
    LeaveService leaveservice;
//    @GetMapping("leave/login")
//    public List<Leavelog> GetLogin(){
//        return leaveservice.getlogin();
//    }
    @PostMapping("leave/login")
    public String logIn(@RequestParam("emailId") String emailId,@RequestParam("password") String password){
        boolean isValid = leaveservice.validate(emailId,password);
        return (isValid) ? "return success" : "Incorrect user Id or password";
    }
    @PostMapping("leave/signup")
    public String signUp(@RequestParam("name") String name,@RequestParam("emailId") String emailId,@RequestParam("password") String password){
        leaveservice.SignUp(name,emailId,password);
        return "You have successfully signup using "+emailId;
    }
    @GetMapping("leave/report/{emailId}")
    public List<LeaveReport> findByemailId(@PathVariable("emailId") String email){
        return leaveservice.findbyemailid(email);
    }
    @PutMapping("leave/Apply/{emailId}/{leaveType}/{Days}")
    public int UpdateLeave(@PathVariable("emailId") String emailId,@PathVariable("leaveType") String leaveType,@PathVariable("Days") int days){
        LeaveReport updated = leaveservice.updateleave(emailId,leaveType.toLowerCase(),days);
        int remainingLeave;
        switch(leaveType.toLowerCase()){
            case "sick_leave":
                remainingLeave = updated.getSick_leave();
                break;
            case "casual_leave":
                remainingLeave = updated.getCasual_leave();
                break;
            case "earned_leave":
                remainingLeave = updated.getEarned_leave();
                break;
            default:
                throw new IllegalArgumentException("Invalid leaveType: " + leaveType);
        }
        return remainingLeave;
    }



}
