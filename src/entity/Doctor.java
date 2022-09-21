package entity;

import adt.SortedDoublyLinkList;
import adt.SortedListInterface;

public class Doctor {
    private final String ID;
    private final String name;
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

    public SortedListInterface<Appointment> getAppointmentList() {
        return appointmentList;
    }


}
