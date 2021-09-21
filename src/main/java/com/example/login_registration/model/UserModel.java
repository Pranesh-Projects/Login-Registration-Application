package com.example.login_registration.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long user_id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
//    @Column(name = "email")
    private String email;
//    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
//            'name' = table name given
            name = "users_roles",
//            'name' = name of the column giving, 'referencedColumnName' = column name it refers to
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<RoleModel> roles;

//    ToString
    @Override
    public String toString() {
        return "UserModel{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    //  Getter and setter
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return firstname;
    }

    public void setFirst_name(String firstname) {
        this.firstname = firstname;
    }

    public String getLast_name() {
        return lastname;
    }

    public void setLast_name(String lastname) {
        this.lastname = lastname;
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

    public Collection<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleModel> roles) {
        this.roles = roles;
    }

    //    No Constructor
    public UserModel() {
    }

    //    All Constructor
    public UserModel(Long user_id, String firstname, String lastname, String email, String password, Collection<RoleModel> roles) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

//  Constructor without 'id'
    public UserModel(String firstname, String lastname, String email, String password, Collection<RoleModel> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
