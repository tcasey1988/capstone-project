package com.capstone.capstoneproject.models;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String role;

    public Role(){}

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
