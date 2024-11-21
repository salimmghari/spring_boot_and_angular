package com.software.spring_boot_and_angular.dao;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String username;
    private String password;

    User() {}

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (!(object instanceof User)) return false;
        
        User user = (User) object;

        return (
            Objects.equals(this.id, user.id) 
            && Objects.equals(this.username, user.username)
            && Objects.equals(this.password, user.password)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.password);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", username='" + this.username + "\'}";
    }
}
