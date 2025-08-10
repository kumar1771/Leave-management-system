package com.example.Leave.management.system.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveReportAverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String emailId;
    private double average;
    private String status;

    public LeaveReportAverage(String name, String emailId, double average, String status) {
        this.name = name;
        this.emailId = emailId;
        this.average = average;
        this.status = status;
    }
}
