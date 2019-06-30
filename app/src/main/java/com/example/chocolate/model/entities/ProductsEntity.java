package com.example.chocolate.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int category_id;
    private String name;
    private float price;

    public ProductsEntity(int category_id, String name, float price) {
        this.category_id = category_id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }


    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    public static ProductsEntity[] populateProducts(){

        return new ProductsEntity[]{

                //Καφέδες
                new ProductsEntity(1,   "Freddo espresso",     2.50f),
                new ProductsEntity(1,   "Freddo cappuccino",   3.00f),
                new ProductsEntity(1,   "Φραπές",              2.00f),
                new ProductsEntity(1,   "Espresso μονός",      2.00f),
                new ProductsEntity(1,   "Espresso διπλό",      2.00f),
                new ProductsEntity(1,   "Late macchiato",      3.00f),
                new ProductsEntity(1,   "Cappuccino",          3.00f),
                new ProductsEntity(1,   "Espresso macchiato",  2.00f),
                new ProductsEntity(1,   "Ελληνικός μονός",     1.50f),
                new ProductsEntity(1,   "Ελληνικός διπλός",    2.00f),
                new ProductsEntity(1,   "Φραπές με παγωτό",    3.20f),
                new ProductsEntity(1,   "Σοκολάτα ζεστή",      3.00f),
                new ProductsEntity(1,   "Σοκολάτα κρύα",       3.00f),

                //Αναψυκτικά - χυμοί
                new ProductsEntity(2,   "Cola",                 2.00f),
                new ProductsEntity(2,   "Fanta - πορτοκάλι",    2.00f),
                new ProductsEntity(2,   "Fanta - λεμόνι",       2.00f),
                new ProductsEntity(2,   "Sprite",               2.00f),
                new ProductsEntity(2,   "Σόδα",                 2.00f),
                new ProductsEntity(2,   "Σουρωτή",              2.00f),
                new ProductsEntity(2,   "Σόδα λεμόνι",          2.00f),
                new ProductsEntity(2,   "Ice tea Λεμόνι",       2.00f),
                new ProductsEntity(2,   "Ice tea ροδάκινο",     2.00f),
                new ProductsEntity(2,   "Ice tea πράσινο",      2.00f),
                new ProductsEntity(2,   "ΦΥΣΙΚΟΣ χυμός πορτοκάλι",2.00f),
                new ProductsEntity(2,   "Χυμός πορτοκάλι",      2.00f),
                new ProductsEntity(2,   "Χυμός ανάμεικτος",     2.00f),
                new ProductsEntity(2,   "Χυμός ρόδι",           2.00f),
                new ProductsEntity(2,   "Χυμός βύσσινο",        2.00f),
                new ProductsEntity(2,   "Χυμός μπανάνα-βύσσινο",2.00f),

                //Μπύρες
                new ProductsEntity(3,   "Μύθος",                3.00f),
                new ProductsEntity(3,   "Βεργίνα",              3.00f),
                new ProductsEntity(3,   "Fix",                  3.00f),
                new ProductsEntity(3,   "Amstel κουτάκι",       2.00f),
                new ProductsEntity(3,   "Μύθος κουτάκι",        2.00f),

                //Κρασιά
                new ProductsEntity(4,   "Λευκό",                4.00f),
                new ProductsEntity(4,   "Ροζέ",                 4.00f),
                new ProductsEntity(4,   "Ημίγλυκο",             4.00f),
                new ProductsEntity(4,   "Ξηρό",                 4.00f),

                //Ποτά
                new ProductsEntity(5,   "Haig",                 5.00f),
                new ProductsEntity(5,   "Chivas",               6.00f),
                new ProductsEntity(5,   "Johnny κόκκινο",       5.00f),
                new ProductsEntity(5,   "Johnny μαύρο",         6.00f),
                new ProductsEntity(5,   "Cutty sark",           5.00f),
                new ProductsEntity(5,   "Famous Grouse",        5.00f),
                new ProductsEntity(5,   "Dewar's",              5.00f),
                new ProductsEntity(5,   "Βότκα",                5.00f),
                new ProductsEntity(5,   "Gin",                  5.00f),
                new ProductsEntity(5,   "Bacardi",              5.00f),

                //Milkshake
                new ProductsEntity(6,   "Φτιάξε Milkshake",4.00f),

                //Πάστες
                new ProductsEntity(7,   "Σοκολατόπιτα",        2.80f),
                new ProductsEntity(7,   "Προφιτερόλ",          2.50f),
                new ProductsEntity(7,   "Κορνέ",               2.50f),
                new ProductsEntity(7,   "Μπαμπάς",             2.50f),
                new ProductsEntity(7,   "Χιονάτη",             2.50f),
                new ProductsEntity(7,   "Oreo",                2.50f),
                new ProductsEntity(7,   "Cheesecake",          2.50f),
                new ProductsEntity(7,   "Σάμπα",               2.50f),
                new ProductsEntity(7,   "Πραλίνα φουντούκι",   2.50f),
                new ProductsEntity(7,   "Σοκολατίνα",          2.50f),
                new ProductsEntity(7,   "Black forest",        2.50f),
                new ProductsEntity(7,   "Ferrero",             2.50f),
                new ProductsEntity(7,   "Εκμέκ κανταϊφ",       2.50f),

                //Σιροπιαστά
                new ProductsEntity(8,   "Κανταΐφ",             2.50f),
                new ProductsEntity(8,   "Μπακλαβάς",           2.50f),
                new ProductsEntity(8,   "Σαραγλί",             2.50f),
                new ProductsEntity(8,   "Γαλακτομπούρεκο",     2.50f),

                //Μικρά σιροπιαστά
                new ProductsEntity(9,   "Κανταϊφάκι",          1.20f),
                new ProductsEntity(9,   "Μπακλαβαδάκι",        1.20f),
                new ProductsEntity(9,   "Σαραγλάκι",           1.20f),
                new ProductsEntity(9,   "Γαλακτομπουρεκάκι",   1.20f),
                new ProductsEntity(9,   "Τουλουμπάκι",         1.20f),

                //Παγωτά
                new ProductsEntity(10,"Φτιάξε παγωτό",0.00f),
                //new ProductsEntity(10,   "Σοκολάτα",            1.50f),
                //new ProductsEntity(10,   "Βανίλια",             1.50f),
                //new ProductsEntity(10,   "Φράουλα",             1.50f),
                //new ProductsEntity(10,   "Σύκο",                1.50f),
                //new ProductsEntity(10,   "Καραμέλα",            1.50f),
                //new ProductsEntity(10,   "Cookies",             1.50f),
                //new ProductsEntity(10,   "Καϊμάκι",             1.50f),
                //new ProductsEntity(10,   "Τσιχλόφουσκα",        1.50f),
                //new ProductsEntity(10,   "Black forest",        1.50f),
                //new ProductsEntity(10,   "Στρατσιατέλα",        1.50f),
                //new ProductsEntity(10,   "Παρφέ βανίλια",       1.50f),
                //new ProductsEntity(10,   "Μπανάνα",             1.50f),
                //new ProductsEntity(10,   "Φυστίκι",             1.50f),
                //new ProductsEntity(10,   "Cheesecake",          1.50f),
                //new ProductsEntity(10,   "Πεπόνι",              1.50f),
                //new ProductsEntity(10,   "Λεμόνι",              1.50f),

                //Πρωινά
                new ProductsEntity(11,   "Μπουγάτσα κρέμα",       2.00f),
                new ProductsEntity(11,   "Μπουγάτσα τυρί",        2.00f),
                new ProductsEntity(11,   "Μπουγάτσα κιμά",        2.00f),

                //Γάλατα
                new ProductsEntity(12,   "Κακάο μικρό",          1.00f),
                new ProductsEntity(12,   "Κακάο μεγάλο",         1.30f),
                new ProductsEntity(12,   "Κακάο μικρό μπουκάλι", 1.30f),
                new ProductsEntity(12,   "Κακάο μεγάλο μπουκάλι",2.00f),
                new ProductsEntity(12,   "Milko",                2.00f),
                new ProductsEntity(12,   "Αριάνι μικρό",         1.00f),
                new ProductsEntity(12,   "Αριάνι μεγάλο",        1.30f),
                new ProductsEntity(12,   "Γάλα φρέσκο μικρό",    1.00f),
                new ProductsEntity(12,   "Γάλα φρέσκο μεγάλο",   1.30f),


                //Πίτσες
                new ProductsEntity(13,   "Σπέσιαλ",             8.00f),
                new ProductsEntity(13,   "Χωριάτικη",           8.00f),
                new ProductsEntity(13,   "Μαργαρίτα",           8.00f),
                new ProductsEntity(13,   "Σπέσιαλ ατομική",     4.00f),
                new ProductsEntity(13,   "Χωριάτικη ατομική",   4.00f),
                new ProductsEntity(13,   "Μαργαρίτα ατομική",   4.00f),

                //Σαλάτες
                new ProductsEntity(14,   "Caesars",             4.50f),
                new ProductsEntity(14,   "Chef",                4.50f),
                new ProductsEntity(14,   "Χωριάτικη",           4.50f),
                new ProductsEntity(14,   "Τονοσαλάτα",          4.50f),

                //Burger - Club
                new ProductsEntity(15,   "Club sandwich",       4.50f),
                new ProductsEntity(15,   "Burger",              3.50f),
                new ProductsEntity(15,   "Cheeseburger",        4.50f),

        };
    }

    @Override
    public String toString() {
        return "ProductsEntity{" +
                "id=" + id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
