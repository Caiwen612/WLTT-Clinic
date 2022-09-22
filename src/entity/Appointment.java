package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/** Appointment - An entity class of appointment list.
 *  @author Tay Chai Boon
 */

public class Appointment implements Comparable<Appointment>, Serializable {
    //Data member
    private String ID;
    private Patient patient;
    private Date date;
    private String appointmentDescription;
    private static int count = 1;
    //Date format
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    //Constructor
    public Appointment(){
        this(null,null,"");
    }

    public Appointment(Patient patient, Date date, String appointmentDescription) {
        this.ID = generateAppointmentID();
        this.patient = patient;
        this.date = date;
        this.appointmentDescription = appointmentDescription;
        count++;
    }

    //Getter
    public String getID() {
        return this.ID;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public String getDate() {
        return sdf.format(date);
    }

    public String getAppointmentDescription() {
        return this.appointmentDescription;
    }

    public static int getCount() {
        return Appointment.count;
    }

    //Setter
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    //Method of Entity
    public String generateAppointmentID(){
        return "A" + count;
    }

    @Override
    public int compareTo(Appointment o) {
        return date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj == null){
            return false;
        }

        if(getClass() != obj.getClass()){
            return false;
        }

        Appointment other = (Appointment) obj;
        if(!Objects.equals(this.ID, other.ID)){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return ID + "\t" + patient.getPatientName() + "\t" + sdf.format(date) + "\t" + appointmentDescription;
    }
}
