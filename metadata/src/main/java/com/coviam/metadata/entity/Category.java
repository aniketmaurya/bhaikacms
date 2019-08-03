package com.coviam.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    private String name;

    //copy
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Program> program;

    @OneToMany(mappedBy = "parent")
    private Set<Category> childCategories;

    public Set<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }

}
