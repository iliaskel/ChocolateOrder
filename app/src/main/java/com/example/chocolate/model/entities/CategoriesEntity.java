package com.example.chocolate.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class CategoriesEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public CategoriesEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CategoriesEntity[] populateCategories(){

        return new CategoriesEntity[]{
                new CategoriesEntity("Καφέδες"),
                new CategoriesEntity("Αναψυκτικά-Χυμοί"),
                new CategoriesEntity("Μπύρες"),
                new CategoriesEntity("Κρασιά"),
                new CategoriesEntity("Ποτά"),
                new CategoriesEntity("Milkshakes"),
                new CategoriesEntity("Πάστες"),
                new CategoriesEntity("Σιροπιαστά"),
                new CategoriesEntity("Μικρά σιροπιαστά"),
                new CategoriesEntity("Παγωτά"),
                new CategoriesEntity("Πρωινά"),
                new CategoriesEntity("Γάλατα"),
                new CategoriesEntity("Πίτσες"),
                new CategoriesEntity("Σαλάτες"),
                new CategoriesEntity("Burger - Club"),
        };
    }

    @Override
    public String toString() {
        return "CategoriesEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
