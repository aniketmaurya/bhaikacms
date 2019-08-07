package com.coviam.metadata.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    // 1->single video, 2->multi 3->seasonal
    @NotNull
    private String type;

    @NotNull(message = "description may not be null")
    //@Size(min = 10)
    private String description;

    @NotNull(message = "name may not be null")
    // @Size(min = 2)
    private String name;

    @NotNull(message = "parentalRating may not be null")
    private String parentalRating;

    // we will store keywords as space separated values
    @NotNull
    private String keywords;

    // We will languages as space separated values
    @NotNull(message = "languages may not be null")
    private String languages;

    @NotNull
    private Long startDate;

    @NotNull
    private Long expiryDate;

    private Boolean isAlive;

    // to store which user has uploaded this file
    @NotNull
    private String userId;

    //    private String author;
    @OneToOne
    @JoinColumn(nullable = false)
    private Category category;

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> imgUrls = new HashMap<>();


    private Long creationDate;


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
}
