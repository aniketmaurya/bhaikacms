package com.coviam.metadata.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
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

    @OneToMany
    @JoinColumn(name = "languageId")
    List<Language> languageId;

    String programImgUrl;

    Date startDate;

    Date expiryDate;

    Boolean isAlive;
}
