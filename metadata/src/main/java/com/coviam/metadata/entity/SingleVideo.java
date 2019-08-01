package com.coviam.metadata.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = SingleVideo.TABLE_NAME)
public class SingleVideo {

    public static final String TABLE_NAME = "single_video";
    public static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "single_video_generator")
    @GenericGenerator(name = "single_video_generator", strategy = "increment")
    String id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    Program programId;

    String videoURL;

}
