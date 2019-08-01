package com.coviam.metadata.entity;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Program {

    String id;
    String programType;
    String description;
    String programName;
    String parentalRating;
    List<String> keywords;
    List<Language> languageId;
    String programImgUrl;
    Date startDate;
    Date expiryDate;
    Boolean isAlive;

    // to store which user has uploaded this file
    private String userId;
    private String userName;
}
