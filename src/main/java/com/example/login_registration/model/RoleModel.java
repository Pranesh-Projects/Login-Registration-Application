package com.example.login_registration.model;


import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long role_id;
    private String role_name;

//    All Constructor
    public RoleModel(Long role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

//    No Constructor
    public RoleModel() {

    }

//  Getter and setter
    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
