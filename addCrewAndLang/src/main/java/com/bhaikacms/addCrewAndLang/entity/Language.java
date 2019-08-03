package com.bhaikacms.addCrewAndLang.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Language.TABLE_NAME)
@Data
public class Language {
    public static final String TABLE_NAME = "Language";
    private static final String ID_COLUMN = "languageId";
    @Id
    @Column(name = Language.ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer languageId;
    private String languageName;
}