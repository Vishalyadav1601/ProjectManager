package com.projectManagement.projectManager.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(generator = "SixDigitIdGenerator")
    @GenericGenerator(name = "SixDigitIdGenerator",strategy = "com.projectManagement.projectManager.CustomGenerator.SixDigitIdGenerator")
    private BigInteger userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private BigInteger userPhone;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//    joinColumns = @JoinColumn(name = "user",referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "roleId"))
    private Set<Role> roles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.roles.stream().map((roles)->new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
