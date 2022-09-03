package entity;

public class Dosage implements Comparable{
    private String dosageForm;
    private String dose;
    private int dosageQuantity;
    private double dosageCost;
    private double dosagePrice;

    public Dosage(){}

    public Dosage(String dosageForm, String dose, int dosageQuantity, double dosageCost, double dosagePrice) {
        this.dosageForm = dosageForm;
        this.dose = dose;
        this.dosageQuantity = dosageQuantity;
        this.dosageCost = dosageCost;
        this.dosagePrice = dosagePrice;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(int dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    public double getDosageCost() {
        return dosageCost;
    }

    public void setDosageCost(double dosageCost) {
        this.dosageCost = dosageCost;
    }

    public double getDosagePrice() {
        return dosagePrice;
    }

    public void setDosagePrice(double dosagePrice) {
        this.dosagePrice = dosagePrice;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Dosage){
            return Integer.compare(this.dosageQuantity, ((Dosage) o).dosageQuantity);
        }
        else {
            return 0;
        }
    }
}
