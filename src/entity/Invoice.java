package entity;

import adt.ListInterface;

public class Invoice {

    private String id;
    private String date;
    private Patient patient;
    private ListInterface<Medicine> medicine;

    public Invoice(String id, String date, Patient patient, ListInterface<Medicine> medicine) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.medicine = medicine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ListInterface<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(ListInterface<Medicine> medicine) {
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", patient=" + patient +
                ", medicine=" + medicine +
                '}';
    }
}
