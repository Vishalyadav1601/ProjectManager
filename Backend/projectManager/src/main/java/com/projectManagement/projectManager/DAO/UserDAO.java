package com.projectManagement.projectManager.DAO;

import com.projectManagement.projectManager.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, BigInteger> {

    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);
}
