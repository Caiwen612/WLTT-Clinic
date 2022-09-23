package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction{

    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private String transactionID;
    private String payDate = LocalDate.now().format(dateFormat);
    private String payTime = LocalDateTime.now().format(timeFormat);
    private Invoice invoice;
    private Payment payment;
    private String method;
    private static int numOfTransaction;

    public Transaction() {
    }

    public Transaction(Invoice invoice, Payment payment, String method) {
        this.transactionID = "T" + String.format("%04d", (Transaction.numOfTransaction + 1));
        this.invoice = invoice;
        this.payment = payment;
        this.method = method;
        numOfTransaction++;
    }

    public Transaction(String payDate, String payTime, Invoice invoice, Payment payment, String method) {
        this.transactionID = "T" + String.format("%04d", (Transaction.numOfTransaction + 1));
        this.payDate = payDate;
        this.payTime = payTime;
        this.invoice = invoice;
        this.payment = payment;
        this.method = method;
        numOfTransaction++;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public static int getNumOfTransaction() {
        return numOfTransaction;
    }

    public static void setNumOfTransaction(int numOfTransaction) {
        Transaction.numOfTransaction = numOfTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", payDate='" + payDate + '\'' +
                ", payTime='" + payTime + '\'' +
                ", invoice=" + invoice +
                ", payment=" + payment +
                ", method='" + method + '\'' +
                '}';
    }
}