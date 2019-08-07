package com.coviam.metadata.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "program may not be null")
    private Program program;

    @NotNull(message = "seasonName may not be null")
    private String seasonName;

    @NotNull(message = "seasonNumber may not be null")
    private Integer seasonNumber;

    @NotNull(message = "seasonDescription may not be null")
    private String seasonDescription;

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> seasonImgUrls = new HashMap<>();

    private Long creationDate;


    @Override
    public String toString() {
        return "Season{" +
                "id='" + id + '\'' +
                ", program=" + program +
                ", seasonName='" + seasonName + '\'' +
                ", seasonNumber=" + seasonNumber +
                ", seasonDescription='" + seasonDescription + '\'' +
                ", seasonImgUrls=" + seasonImgUrls +
                '}';
    }
}
