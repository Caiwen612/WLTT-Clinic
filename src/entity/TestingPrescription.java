package entity;

import adt.ArrayList;
import adt.ListInterface;

public class TestingPrescription {

    private int medicineNo;
    private int dosageNo;
    private int quantity;

    public TestingPrescription(int medicineNo, int dosageNo, int quantity) {
        this.medicineNo = medicineNo;
        this.dosageNo = dosageNo;
        this.quantity = quantity;
    }

    public int getMedicineNo() {
        return medicineNo;
    }

    public void setMedicineNo(int medicineNo) {
        this.medicineNo = medicineNo;
    }

    public int getDosageNo() {
        return dosageNo;
    }

    public void setDosageNo(int dosageNo) {
        this.dosageNo = dosageNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*  private String id;
    private String name;
    private Dosage dosage;
    private int quantity;

    public MedicineRecord(String id, String name, Dosage dosage, int quantity) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.quantity = quantity;
    }

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

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }*/
}
