package com.coviam.metadata.entity;


import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = Program.TABLE_NAME)
@TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
public class Language implements Serializable {

    public static final String TABLE_NAME = "Language";
    public static final String ID_COLUMN = "ID";

    @Id
    @Column(name = Program.ID_COLUMN)
    private String id;

    private String name;
}