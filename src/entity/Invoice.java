package entity;

import adt.ListInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final double SERVICE_TAX = 0.06;
    private String id;
    private String date = LocalDate.now().format(dateFormat);
    private String time = LocalDateTime.now().format(timeFormat);
    private Patient patient;
    private double subTotal;
    private double total;
    private ListInterface<Medicine> medicine; //Should be prescription list I think
    private static int numOfInvoice = 0;

    public Invoice(){
        numOfInvoice++;
    };

    public Invoice(String id, String date, String time, Patient patient, ListInterface<Medicine> medicine) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.medicine = medicine; //Have to change
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ListInterface<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(ListInterface<Medicine> medicine) {
        this.medicine = medicine;
    }

    public static double getServiceTax(){
        return Invoice.SERVICE_TAX;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", patient=" + patient +
                ", medicine=" + medicine +
                '}';
    }
}
