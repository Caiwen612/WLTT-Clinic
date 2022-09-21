package entity;

import adt.ArrayList;
import adt.ListInterface;

public class Patient implements Comparable {
    private String patientName;
    private String icNo;
    private String phoneNo;
    private String address;
    private String DOB;
    private ListInterface<MedicalRecord> history;
    private Invoice tempInvoice;

    public Patient(){
        this("","","","","");
    }

    public Patient(String patientIC) {
        this("",patientIC,"","","");
    }

    public Patient(String name, String icNo, String phoneNo, String address, String DOB){
        this.patientName = name;
        this.icNo = icNo;
        this.phoneNo = phoneNo;
        this.address = address;
        this.DOB = DOB;
        this.history = new ArrayList<MedicalRecord>(5);
    }


    public String getAddress() {
        return address;
    }

    public String getDOB() {
        return DOB;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getIcNo() {
        return icNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Invoice getTempInvoice() {
        return tempInvoice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public  void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public void setTempInvoice(Invoice tempInvoice) {
        this.tempInvoice = tempInvoice;
    }

    public ListInterface<MedicalRecord> getHistory() {
        return history;
    }

    public void setHistory(ListInterface<MedicalRecord> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return
                "Name: " + patientName +
                "\nIC Number: " + icNo +
                "\nPhone Number: " + phoneNo  +
                "\nAddress: " + address  +
                "\nDOB: " + DOB;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Patient){
            return this.patientName.compareTo(((Patient) o).patientName);

        }
        else {
            return 0;
        }
    }
}
