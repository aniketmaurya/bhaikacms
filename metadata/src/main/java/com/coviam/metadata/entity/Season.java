package com.coviam.metadata.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = Season.TABLE_NAME)
public class Season {

    public static final String TABLE_NAME="Season";
    public static final String ID_COLUMN="ID";

    @Id
    @GeneratedValue(generator = "season_id")
    @GenericGenerator(name = "season_id",strategy = "increment")
    String id;

    @ManyToOne
    @JoinColumn(name = "programId")
    Program programId;

    Integer seasonNumber;

    String seasonDescription;

    String seasonImgUrl;

}
