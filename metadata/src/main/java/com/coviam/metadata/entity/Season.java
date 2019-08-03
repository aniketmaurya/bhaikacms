package com.coviam.metadata.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = Season.TABLE_NAME)
@TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
public class Season {

    public static final String TABLE_NAME = "Season";
    public static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "season_id")
    @GenericGenerator(name = "season_id", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    private Integer seasonNumber;

    private String seasonDescription;

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> seasonImgUrls = new HashMap<>();

/*
    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> crewList = new HashMap<>();
*/


}
