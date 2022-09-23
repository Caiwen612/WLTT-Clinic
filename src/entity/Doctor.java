package entity;

import adt.SortedDoublyLinkList;
import adt.SortedListInterface;

import java.util.Objects;

/** Appointment - An entity class of doctor.
 *  @author Tay Chai Boon
 */

public class Doctor {
    //Data member
    private String ID;
    private String name;
    private SortedListInterface<Appointment> appointmentList;

    //Constructor
    public Doctor(){
        this("","");
    }

    public Doctor(String id, String name){
        this.ID = id;
        this.name = name;
        this.appointmentList = new SortedDoublyLinkList<>();
    }

    //Getter
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public SortedListInterface<Appointment> getAppointmentList() {
        return appointmentList;
    }

    //Setter
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppointmentList(SortedListInterface<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    //Method of Entity
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

        Doctor other = (Doctor) obj;
        if(!Objects.equals(this.ID, other.ID)){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", appointmentList=" + appointmentList +
                '}';
    }
}
