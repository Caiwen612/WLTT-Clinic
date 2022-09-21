package entity;

public class Dosage implements Comparable, Cloneable{
    private String dosageForm;
    private String dose;
    private int dosageQuantity;
    private double dosageCost;
    private double dosagePrice;
    private MedicineDosageRecord record;

    public Dosage(){}

    public Dosage(String dosageForm, String dose, int dosageQuantity, double dosageCost, double dosagePrice) {
        this.dosageForm = dosageForm;
        this.dose = dose;
        this.dosageQuantity = dosageQuantity;
        this.dosageCost = dosageCost;
        this.dosagePrice = dosagePrice;
    }

    public Dosage(String dosageForm, String dose, int dosageQuantity, double dosageCost, double dosagePrice, MedicineDosageRecord record) {
        this.dosageForm = dosageForm;
        this.dose = dose;
        this.dosageQuantity = dosageQuantity;
        this.dosageCost = dosageCost;
        this.dosagePrice = dosagePrice;
        this.record = record;
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

    public MedicineDosageRecord getRecord() {
        return record;
    }

    public void setRecord(MedicineDosageRecord record) {
        this.record = record;
    }

    public void reduceStock(int qty){
        this.dosageQuantity -= qty;
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

    public int compareDosageQuantity(Object o){
        if (o instanceof Dosage){
            return Integer.compare(this.dosageQuantity, ((Dosage) o).dosageQuantity);
        }
        else {
            return 0;
        }
    }

    public int compareAllocatedQuantity(Object o){
        if (o instanceof Dosage){
            return Integer.compare(this.record.getAllocateQuantity(), ((Dosage) o).record.getAllocateQuantity());
        }
        else {
            return 0;
        }
    }

    public int compareRestockQuantity(Object o){
        if (o instanceof Dosage){
            return Integer.compare(this.record.getRestockQuantity(), ((Dosage) o).record.getRestockQuantity());
        }
        else {
            return 0;
        }
    }



    @Override
    public Dosage clone() {
        try {
            Dosage clone = (Dosage) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
