package com.coviam.metadata.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
@Table(name = Program.TABLE_NAME)
public class Program {

    public static final String TABLE_NAME="Program";
    public static final String ID_COLUMN="ID";

    @Id
    @GeneratedValue(generator = "prog_id")
    @GenericGenerator(name = "prog_id",strategy = "increment")
    @Column(name=Program.ID_COLUMN)
    String id;

    String programType;

    String description;

    String programName;

    String parentalRating;

    List<String> keywords;

    List<Language> languageId;

    Long startDate;

    String programImgUrl;
    Long expiryDate;
    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<Crew, String> crewRoles = new HashMap<>();

    Boolean isAlive;
    // to store which user has uploaded this file
    private String userId;

    private String userName;
}
