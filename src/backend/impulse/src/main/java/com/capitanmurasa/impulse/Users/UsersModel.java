package com.capitanmurasa.impulse.Users;

import jakarta.persistence.*;


@Entity
@Table(name = "USERS")
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    private String role;

    @Column(name = "username")
    private String username;

    @Column(name = "displayname")
    private String displayname;

    @Column(name = "password_hash")
    private String password;

    public UsersModel(){

    }
    public UsersModel(String userName, String password, String role, String displayName){
        this.username = userName;
        this.password = password;
        this.displayname = displayName;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {return role;};

    public void setRole(String role){ this.role = role; };

    public String getUserName() {
        return username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    public String getDisplayName() {
        return displayname;
    }

    public void setDisplayName(String name) {
        this.displayname = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
