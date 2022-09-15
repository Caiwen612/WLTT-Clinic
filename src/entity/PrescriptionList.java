package entity;

public class PrescriptionList {

    private Medicine medicine;
    private Dosage dosage;
    private int quantity;

    public PrescriptionList() {
    }

    public PrescriptionList(Medicine medicine, Dosage dosage, int quantity) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.quantity = quantity;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
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
    }

    @Override
    public String toString() {
        return "PrescriptionList{" +
                "medicine=" + medicine +
                ", dosage=" + dosage +
                ", quantity=" + quantity +
                '}';
    }
}
