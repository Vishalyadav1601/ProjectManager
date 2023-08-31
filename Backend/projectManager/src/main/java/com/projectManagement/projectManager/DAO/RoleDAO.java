package com.projectManagement.projectManager.DAO;

import com.projectManagement.projectManager.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role,Integer> {
}
