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
@Table(name = Episode.TABLE_NAME)
@TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
public class Episode implements Serializable {

    public static final String TABLE_NAME = "Episode";
    public static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "ep_generator")
    @GenericGenerator(name = "ep_generator", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    private Integer episodeNumber;

    private String episodeTitle;

    private String episodeDescription;

    private String episodeVideoUrl;

    // todo multiple imgs: Done
    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> episodeImageUrls = new HashMap<>();

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> crewList = new HashMap<>();
}
