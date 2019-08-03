package com.bhaikacms.addCrewAndLang.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Crew.TABLE_NAME)
@Data
public class Crew {
    public static final String TABLE_NAME = "Crew";
    private static final String ID_COLUMN = "CrewId";
    @Id
    @Column(name = Crew.ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer crewId;
    private String crewName;
}
