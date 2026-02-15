package com.spring.flight.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseClass{

    @Column(unique = true)
    private String userName;

    @Column(nullable = false)
    private String roles;

    private String password;

    @Column(unique = true)
    private String email;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoleList()
    {
        return Arrays.asList(roles.split(","));
    }
}
