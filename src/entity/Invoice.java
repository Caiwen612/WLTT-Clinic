package entity;

import adt.ArrayList;
import adt.ListInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {

    //Formatting
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final double SERVICE_TAX = 0.06;

    private String invoiceID;
    private String printDate = LocalDate.now().format(dateFormat);
    private String printTime = LocalDateTime.now().format(timeFormat);
    private ListInterface<PrescriptionList> prescriptionList;
    private Patient patient;
    private double subTotal;
    private double total;
    private double amount;
    private static int numOfInvoice = 0;

    public Invoice() {
    }

    //Initialize Purpose
    public Invoice(String printDate, String printTime, ArrayList<PrescriptionList> prescriptionList, Patient patient) {
        this.invoiceID = "I" + String.format("%04d", (Invoice.numOfInvoice + 1));
        this.printDate = printDate;
        this.printTime = printTime;
        this.prescriptionList = prescriptionList;
        this.patient = patient;
        Invoice.numOfInvoice++;
    }

    public Invoice(ArrayList<PrescriptionList> prescriptionList, Patient patient) {
        this.invoiceID = "I" + String.format("%04d", (Invoice.numOfInvoice + 1));
        this.printDate = getPrintDate();
        this.printTime = getPrintTime();
        this.prescriptionList = prescriptionList;
        this.patient = patient;
        Invoice.numOfInvoice++;
    }

    public Invoice(String printDate, String printTime, ArrayList<PrescriptionList> prescriptionList, Patient patient, double subTotal, double total, double amount) {
        this.invoiceID = "I" + String.format("%04d", (Invoice.numOfInvoice + 1));
        this.printDate = getPrintDate();
        this.printTime = getPrintTime();
        this.prescriptionList = prescriptionList;
        this.patient = patient;
        this.subTotal = subTotal;
        this.total = total;
        this.amount = amount;
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

    public ListInterface<PrescriptionList> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(ListInterface<PrescriptionList> prescriptionList) {
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
        return getPrescriptionList().getEntry(i + 1).getDosage().getDosagePrice() * getPrescriptionList().getEntry(i + 1).getQuantity();
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
