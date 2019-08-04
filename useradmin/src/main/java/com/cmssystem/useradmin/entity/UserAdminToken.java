package com.cmssystem.useradmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = UserAdminToken.TABLE_NAME)
public class UserAdminToken implements Serializable {

    public static final String TABLE_NAME = "USERADMINTOKEN";

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserAdmin userAdmin;


    @Id
    private UUID token;*/

    @Id
    private String id;
    private UUID token;

}
