package com.coviam.metadata.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = Category.TABLE_NAME)
public class Category {

    public static final String TABLE_NAME = "Category";
    public static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "cat_id")
    @GenericGenerator(name = "cat_id", strategy = "increment")
    @Column(name = Category.ID_COLUMN)
    private String id;

    @OneToOne
    @JoinColumn(nullable = true)
    private Category parentId;
    private String categoryName;
}
