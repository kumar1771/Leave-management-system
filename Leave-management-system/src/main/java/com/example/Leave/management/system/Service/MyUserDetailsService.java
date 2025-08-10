package com.example.Leave.management.system.Service;

import com.example.Leave.management.system.Model.LeaveSignup;
import com.example.Leave.management.system.Model.UserPrincipals;
import com.example.Leave.management.system.Repositery.Employee_LeaveRpo;
import com.example.Leave.management.system.Repositery.LeaveRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    LeaveRepositery employeeLeaveRpo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LeaveSignup user = employeeLeaveRpo.findByEmailId(username);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipals(user);
    }
}
