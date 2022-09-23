package entity;

import adt.ListInterface;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice implements Serializable {

    //Formatting
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final double SERVICE_TAX = 0.06;

    private String invoiceID;
    private String printDate = LocalDate.now().format(dateFormat);
    private String printTime = LocalDateTime.now().format(timeFormat);
    private ListInterface<Medicine> prescriptionList;
    private Patient patient;
    private double subTotal;
    private double total;
    private double amount;
    private static int numOfInvoice = 0;

    public Invoice() {
        this(null,null);
    }

    //Initialize Purpose
    public Invoice(ListInterface<Medicine> prescriptionList, Patient patient, double subTotal, double total) {
        this.invoiceID = "I" + String.format("%04d", (Invoice.numOfInvoice + 1));
        this.prescriptionList = prescriptionList;
        this.patient = patient;
        this.subTotal = subTotal;
        this.total = total;
        Invoice.numOfInvoice++;
    }

    public Invoice(ListInterface<Medicine> prescriptionList, Patient patient) {
        this.invoiceID = "I" + String.format("%04d", (Invoice.numOfInvoice + 1));
        this.printDate = getPrintDate();
        this.printTime = getPrintTime();
        this.prescriptionList = prescriptionList;
        this.patient = patient;
        Invoice.numOfInvoice++;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getPrintDate() {
        return printDate;
    }

    public void setPrintDate(String printDate) {
        this.printDate = printDate;
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime;
    }

    public ListInterface<Medicine> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(ListInterface<Medicine> prescriptionList) {
        this.prescriptionList = prescriptionList;
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
        return getSubTotal() + calcTaxRate();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static int getNumOfInvoice() {
        return numOfInvoice;
    }

    public static void setNumOfInvoice(int numOfInvoice) {
        Invoice.numOfInvoice = numOfInvoice;
    }

    public double getAmount(int i) {
        Dosage dosage = getPrescriptionList().getEntry(i).getDosage().getEntry(1);
        return dosage.getDosagePrice() * dosage.getDosageQuantity();
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double calcTaxRate(){
        return getSubTotal() * Invoice.SERVICE_TAX;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID='" + invoiceID + '\'' +
                ", printDate='" + printDate + '\'' +
                ", printTime='" + printTime + '\'' +
                ", prescriptionList=" + prescriptionList +
                ", patient=" + patient +
                ", subTotal=" + subTotal +
                ", total=" + total +
                '}';
    }
}
