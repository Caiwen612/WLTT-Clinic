package entity;

public class Payment {

    private String payerName;
    private String cardID;
    private String expDate;
    private String cvv;
    private double amountPaid;

    public Payment() {
    }

    //Cash payer
    public Payment(String payerName, double amountPaid) {
        this.payerName = payerName;
        this.amountPaid = amountPaid;
    }

    //Credit card payer
    public Payment(String payerName, String cardID, String expDate, String cvv, double amountPaid) {
        this.payerName = payerName;
        this.cardID = cardID;
        this.expDate = expDate;
        this.cvv = cvv;
        this.amountPaid = amountPaid;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
}