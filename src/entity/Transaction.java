package entity;

import adt.ListInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String transactionNo;
    private String date = LocalDate.now().format(dateFormat);
    private String time = LocalDateTime.now().format(timeFormat);
    private String invoiceID;
    private String payerName;
    private String method;
    private double amount;

    private static int numOfInvoice = 0;

    public Transaction() {
    }

    public Transaction(String transactionID, String date, String time, String invoiceID, String payerName, String method, double amount) {
        this.transactionNo = transactionID;
        this.date = date;
        this.time = time;
        this.invoiceID = invoiceID;
        this.payerName = payerName;
        this.method = method;
        this.amount = amount;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
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

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static int getNumOfInvoice() {
        return numOfInvoice;
    }

    public static void setNumOfInvoice(int numOfInvoice) {
        Transaction.numOfInvoice = numOfInvoice;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionNo='" + transactionNo + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", invoiceID='" + invoiceID + '\'' +
                ", payerName='" + payerName + '\'' +
                ", method='" + method + '\'' +
                ", amount=" + amount +
                '}';
    }
}
