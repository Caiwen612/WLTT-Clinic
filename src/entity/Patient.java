package entity;

public class Patient {
    private String patientName;
    private String icNo;
    private String phoneNo;
    private String address;
    private String DOB;

    /*public Patient(String name, String icNo, String phoneNo, String address, String DOB ){
        this.patientName = name;
        this.icNo = icNo;
        this.phoneNo = phoneNo;
        this.address = address;
        this.DOB = DOB;
    }*/

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

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientName='" + patientName + '\'' +
                ", icNo='" + icNo + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", DOB='" + DOB + '\'' +
                '}';
    }
}
