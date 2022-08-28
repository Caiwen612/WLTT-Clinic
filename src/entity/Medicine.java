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

    //Initialize medicine stock
    public <T> void initializeMedicineStock(){
        ArrayList<T> medicineStock = new ArrayList<>(10);
        medicineStock.add((T) new Medicine("M001", "Acetaminophen", new Dosage[]{
                new Dosage("Oral capsule", "325 mg",20,5,20),
                new Dosage("Oral liquid", "160mg/5mL", 30, 3,15),
                new Dosage("Oral tablet", "500 mg",15,8,25)}, "A pain reliever and fever reducer"));
        medicineStock.add((T) new Medicine("M002", "Oseltamivir", new Dosage[]{
                new Dosage("Oral capsule", "300 mg", 20, 5, 15),
                new Dosage("Oral capsule", "450 mg",8,8,20)}, "An antiviral medication that blocks the actions of influenza virus types A and B in the body"

        ));
        medicineStock.add((T) new Medicine("M003", "Loperamide", new Dosage[]{
                new Dosage("Oral capsule", "300 mg", 18, 4, 15),
                new Dosage("Oral tablet", "450 mg",5,8,20)}, "Used to treat diarrhea or to reduce the amount of stool(poop) in people who have an ileosomy"

        ));

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
