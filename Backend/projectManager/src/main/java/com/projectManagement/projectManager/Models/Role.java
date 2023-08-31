package com.projectManagement.projectManager.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {

    @Id
    private int roleId;
    private String name;
}
