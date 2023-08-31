package com.projectManagement.projectManager.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@Entity
public class Project implements Serializable {
    @Id
    @GeneratedValue(generator = "FourDigitIdGenerator")
    @GenericGenerator(name = "FourDigitIdGenerator",strategy = "com.projectManagement.projectManager.CustomGenerator.FourDigitIdGenerator")
    private BigInteger projectId;
    private int projectNumber;
    private String projectTitle;
    private String projectContent;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
