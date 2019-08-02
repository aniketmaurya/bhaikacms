package com.coviam.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = Category.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    public static final String TABLE_NAME = "Category";
    public static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = Category.ID_COLUMN)
    private String id;

    @OneToOne
    @JoinColumn(nullable = true)
    private Category parent;
    private String categoryName;
}
