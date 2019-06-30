package com.example.chocolate.model.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ice_cream")
public class IceCreamEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private float price;

    public IceCreamEntity(String name, float price) {
        this.name = name;
        this.price= price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static IceCreamEntity[] populateIceCreams(){
        return new IceCreamEntity[]{
            new IceCreamEntity(  "Σοκολάτα",            1.50f),
            new IceCreamEntity(  "Βανίλια",             1.50f),
            new IceCreamEntity(  "Φράουλα",             1.50f),
            new IceCreamEntity(  "Σύκο",                1.50f),
            new IceCreamEntity(  "Καραμέλα",            1.50f),
            new IceCreamEntity(  "Cookies",             1.50f),
            new IceCreamEntity(  "Καϊμάκι",             1.50f),
            new IceCreamEntity(  "Τσιχλόφουσκα",        1.50f),
            new IceCreamEntity(  "Black forest",        1.50f),
            new IceCreamEntity(  "Στρατσιατέλα",        1.50f),
            new IceCreamEntity(  "Παρφέ βανίλια",       1.50f),
            new IceCreamEntity(  "Μπανάνα",             1.50f),
            new IceCreamEntity(  "Φυστίκι",             1.50f),
            new IceCreamEntity(  "Cheesecake",          1.50f),
            new IceCreamEntity(  "Πεπόνι",              1.50f),
            new IceCreamEntity(  "Λεμόνι",              1.50f),
        };
    }

    @Override
    public String toString() {
        return "IceCreamEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
