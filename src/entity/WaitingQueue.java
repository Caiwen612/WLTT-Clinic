package entity;


import java.io.Serializable;

public class WaitingQueue implements Serializable {
    private Patient patient;
    private int roomNo;
    private String waitingNo;
    private static int waitingNoRoom1 = 100;
    private static int waitingNoRoom2 = 100;
    private static int waitingNoRoom3 = 100;

    public WaitingQueue(){
        this(null,0);
    }

    public WaitingQueue(Patient p,int rN){
        this.patient = p;
        this.roomNo = rN;
        this.waitingNo = generateWaitingNo(rN);
        switch (rN){
            case 1:
                waitingNoRoom1++;
                break;
            case 2:
                waitingNoRoom2++;
                break;
            case 3:
                waitingNoRoom3++;
                break;
        }
    }

    public String generateWaitingNo(int rN){
        switch (rN){
            case 1:
                return "A" + waitingNoRoom1;
            case 2:
                return "B" + waitingNoRoom2;
            case 3:
                return "C" + waitingNoRoom3;
        }

        return null;
    }

    public String getWaitingNo() {
        return waitingNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNO(int rN) {
        this.roomNo = rN;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPatientName(){
        return patient.getPatientName();
    }

    @Override
    public String toString() {
        return
                "WaitingNo: " + waitingNo +
                "   RoomNo: " + roomNo;
    }
}
