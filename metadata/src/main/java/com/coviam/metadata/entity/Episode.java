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
@Table(name = Episode.TABLE_NAME)
@TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
public class Episode {

    public static final String TABLE_NAME = "Episode";
    public static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "ep_generator")
    @GenericGenerator(name = "ep_generator", strategy = "increment")
    private String id;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season seasonId;

    private Integer episodeNumber;

    private String episodeDescription;

    private String episodeVideoUrl;

    // todo multiple imgs: Done
    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> episodeImageUrls = new HashMap<>();

}
