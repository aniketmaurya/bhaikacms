package com.coviam.metadata.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Builder;
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
@Table(name = SingleVideo.TABLE_NAME)
@TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
@Builder
public class SingleVideo {

    public static final String TABLE_NAME = "single_video";
    public static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @NotNull
    private String videoTitle;

    @NotNull
    private String videoUrl;

    @NotNull
    private String description;

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> videoImgUrls = new HashMap<>();

    @Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    private Map<String, String> crewList = new HashMap<>();
}
