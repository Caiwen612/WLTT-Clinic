package entity;

import jdk.incubator.foreign.MemoryLayout;

public class waitingList {
    private Patient patientList;
    private int roomNo;

    public waitingList(){

    }

    public waitingList(Patient pL, int rN){
        this.patientList = pL;
        this.roomNo = rN;
    }

    public Patient getPatientList() {
        return patientList;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setPatientList(Patient pL) {
        this.patientList= pL;
    }

    public void setRoomNO(int rN) {
        this.roomNo = rN;
    }

    @Override
    public String toString() {
        return "waitingList{" +
                "patientList=" + patientList +
                ", roomNo=" + roomNo +
                '}';
    }
}
