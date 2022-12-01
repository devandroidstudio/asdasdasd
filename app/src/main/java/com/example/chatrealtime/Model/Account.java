package com.example.chatrealtime.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Account implements Serializable {
    String fullName,email,password;
    LocalDateTime dateJoin;
    boolean status;

    public Account() {
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account(String fullName, String email, String password, LocalDateTime dateJoin, boolean status) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.dateJoin = dateJoin;
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(LocalDateTime dateJoin) {
        this.dateJoin = dateJoin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
