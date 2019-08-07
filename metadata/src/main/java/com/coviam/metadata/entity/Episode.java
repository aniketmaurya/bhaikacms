package com.coviam.metadata.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Builder;
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
@Builder
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

    @NotNull
    private Integer episodeNumber;

    @NotNull
    private String episodeTitle;

    @NotNull
    private String episodeDescription;

    private String episodeVideoUrl;

    private Long creationDate;

    // todo multiple imgs: Done
    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    @NotNull
    private Map<String, String> episodeImgUrls = new HashMap<>();

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> crewList = new HashMap<>();

    @Override
    public String toString() {
        return "Episode{" +
                "id='" + id + '\'' +
                ", season=" + season +
                ", episodeNumber=" + episodeNumber +
                ", episodeTitle='" + episodeTitle + '\'' +
                ", episodeDescription='" + episodeDescription + '\'' +
                ", episodeVideoUrl='" + episodeVideoUrl + '\'' +
                ", episodeImageUrls=" + episodeImgUrls +
                ", crewList=" + crewList +
                '}';
    }
}
