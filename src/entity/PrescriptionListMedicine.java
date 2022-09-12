package entity;

public class PrescriptionListMedicine {

    private Medicine medicine;
    private Dosage dosage;
    private int quantity;
    private double amount;
    private double subTotal;
    private double total;

    public PrescriptionListMedicine(Medicine medicine, Dosage dosage, int quantity) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.quantity = quantity;
        subTotal += dosage.getDosagePrice() * quantity;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public double getAmount() {
        return dosage.getDosagePrice() * quantity;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "PrescriptionListMedicine{" +
                "medicine=" + medicine +
                ", dosage=" + dosage +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", subTotal=" + subTotal +
                ", total=" + total +
                '}';
    }
}
