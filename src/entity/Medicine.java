package entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Medicine {
    private String id;
    private String name;
    private Dosage[] dosage;
    private String description;

    public Medicine(){}

    public Medicine(String id, String name, Dosage[] dosage, String description) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.description = description;
    }



    //Setter and getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Dosage[] getDosage() {
        return dosage;
    }

    public void setDosage(Dosage[] dosage) {
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
