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

    private String type;
    private String description;
    private String name;
    private String parentalRating;

    // we will store keywords as space separated values
    private String keywords;

    // We will languages as space separated values
    private String languages;
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
    private Map<String, String> imgUrls = new HashMap<>();


    @Override
    public String toString() {
        return "Program{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", parentalRating='" + parentalRating + '\'' +
                ", keywords='" + keywords + '\'' +
                ", languages='" + languages + '\'' +
                ", startDate=" + startDate +
                ", expiryDate=" + expiryDate +
                ", isAlive=" + isAlive +
                ", userId='" + userId + '\'' +
                ", category=" + category +
                ", imgUrls=" + imgUrls +
                '}';
    }
/*
    public Map<String, String> toMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", getName());
        hashMap.put("type", getType());
        hashMap.put("description", getDescription());
        hashMap.put("keywords", getKeywords());
        hashMap.put("languages", getLanguages());
        hashMap.put("startDate", getStartDate().toString());
        hashMap.put("expiryDate", getExpiryDate().toString());
        hashMap.put("parentalRating", getParentalRating());

        return hashMap;
    }*/
}
