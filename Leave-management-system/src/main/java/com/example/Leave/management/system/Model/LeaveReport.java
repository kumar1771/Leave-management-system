package com.example.Leave.management.system.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LeaveReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String emailId;
    private int sick_leave;
    private int casual_leave;
    private int earned_leave;

    public LeaveReport(String name, String emailId,int sick_leave, int casual_leave, int earned_leave) {
        this.name = name;
        this.sick_leave = sick_leave;
        this.casual_leave = casual_leave;
        this.earned_leave = earned_leave;
        this.emailId = emailId;
    }
}
