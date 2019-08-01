/*
package com.coviam.metadata.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = Crew.TABLE_NAME)
public class Crew {

    public static final String TABLE_NAME="Crew";
    public static final String ID_COLUMN="ID";

    @Id
    @GeneratedValue(generator = "crew_id")
    @GenericGenerator(name = "crew_id",strategy = "increment")
    @Column(name=Crew.ID_COLUMN)
    private String id;

    private String role;
}
*/
