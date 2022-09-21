package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment implements Comparable<Appointment> {
    //Data member
    private String ID;
    private Patient patient;
    private Date date;
    private String appointmentDescription;
    private static int count = 1;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public Appointment(Patient patient, Date date, String appointmentDescription) {
        this.ID = generateAppointmentID();
        this.patient = patient;
        this.date = date;
        this.appointmentDescription = appointmentDescription;
        count++;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String generateAppointmentID(){
        return "A" + count;
    }

    public String getDate() {
        return sdf.format(date);
    }

    @Override
    public int compareTo(Appointment o) {
        return date.compareTo(o.date);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return ID + "\t" + patient.getPatientName() + "\t" + sdf.format(date) + "\t" + appointmentDescription;

    }
}
