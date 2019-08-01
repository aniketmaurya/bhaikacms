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
    @GeneratedValue(generator = "ep_id")
    @GenericGenerator(name = "ep_id",strategy = "increment")
    String id;

    @ManyToOne
    @JoinColumn(name = "seasonId")
    Season seasonId;

    Integer episodeNumber;

    String episodeDescription;

    String episodeUrl;
}
