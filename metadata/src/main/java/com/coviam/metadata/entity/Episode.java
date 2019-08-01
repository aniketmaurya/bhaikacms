package com.coviam.metadata.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = Episode.TABLE_NAME)
public class Episode {

    public static final String TABLE_NAME="Episode";
    public static final String ID_COLUMN="ID";

    @Id
    @GeneratedValue(generator = "ep_generator")
    @GenericGenerator(name = "ep_generator", strategy = "increment")
    String id;

    @ManyToOne
    @JoinColumn(name = "season_id")
    Season seasonId;

    Integer episodeNumber;

    String episodeDescription;

    String episodeUrl;

    String episodeImageUrl;
}
