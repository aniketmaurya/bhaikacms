/*
package com.coviam.metadata.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = Language.TABLE_NAME)
public class Language {

    public static final String TABLE_NAME="Language";
    public static final String ID_COLUMN="ID";

    @Id
    @GeneratedValue(generator = "lang_id")
    @GenericGenerator(name = "lang_id",strategy = "increment")
    @Column(name=Language.ID_COLUMN)
    String id;

    String name;


}
*/
