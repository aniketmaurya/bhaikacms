/*package com.cmssystem.useradmin.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserPasswordToken {


    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String token;

    @OneToOne(targetEntity = UserAdmin.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "id")
    private UserAdmin userAdmin;

    private Date expiryDate;

}*/
