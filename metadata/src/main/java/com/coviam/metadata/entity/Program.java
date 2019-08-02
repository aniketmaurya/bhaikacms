package com.coviam.metadata.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = Program.TABLE_NAME)
@TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
public class Program implements Serializable {

    public static final String TABLE_NAME = "Program";
    public static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = Program.ID_COLUMN)
    private String id;

    private String programType;
    private String description;
    private String programName;
    private String parentalRating;

    // we will store keywords as space separated values
    private String keywords;

    // We will languages as space separated values
    private String languageId;
    private Long startDate;
    private Long expiryDate;
    private Boolean isAlive;

    // to store which user has uploaded this file
    private String userId;

    //    private String author;
    @OneToOne
    @JoinColumn(nullable = false)
    private Category category;

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> programImgUrl = new HashMap<>();


}
