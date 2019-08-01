package com.cmssystem.useradmin.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = UserAdmin.TABLE_NAME)
public class UserAdmin {

    public static final String TABLE_NAME = "USERADMIN";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int roleId;
    private int videosUploaded;

    public UserAdmin() {
    }

    public UserAdmin(String id, String email, String firstName, String lastName, String password, int roleId, int videosUploaded) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roleId = roleId;
        this.videosUploaded = videosUploaded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getVideosUploaded() {
        return videosUploaded;
    }

    public void setVideosUploaded(int videosUploaded) {
        this.videosUploaded = videosUploaded;
    }
}
