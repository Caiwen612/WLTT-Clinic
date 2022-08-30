package entity;

import jdk.incubator.foreign.MemoryLayout;

public class waitingQueue {
    private int waitingNo;
    private Patient patientName;
    private int roomNo;

    public waitingQueue(){

    }

    public waitingQueue(Patient pN, int rN, int wN){
        this.patientName = pN;
        this.roomNo = rN;
        this.waitingNo = wN;
    }

    public int getWaitingNo() {
        return waitingNo;
    }

    public Patient getPatientList() {
        return patientName;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setPatientList(Patient pN) {
        this.patientName= pN;
    }

    public void setWaitingNo(int wN) {
        this.waitingNo = wN;
    }

    public void setRoomNO(int rN) {
        this.roomNo = rN;
    }

    @Override
    public String toString() {
        return "waitingList{" +
                "waitingNo=" + waitingNo +
                ", patientName=" + patientName +
                ", roomNo=" + roomNo +
                '}';
    }
}
