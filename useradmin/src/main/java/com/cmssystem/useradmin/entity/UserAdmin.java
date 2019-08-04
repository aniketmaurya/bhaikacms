package com.cmssystem.useradmin.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = UserAdmin.TABLE_NAME)
public class UserAdmin implements Serializable {

    public static final String TABLE_NAME = "USERADMIN";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;
    private String email;
    private String name;
    private String password;
    private int roleId;
    private boolean isActive;


}
