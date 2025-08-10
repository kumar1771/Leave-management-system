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
public class RoleBasedAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String emailId;
    private String roll;

    public RoleBasedAccess(String name, String emailId, String roll) {
        this.name = name;
        this.emailId = emailId;
        this.roll = roll;
    }
}
