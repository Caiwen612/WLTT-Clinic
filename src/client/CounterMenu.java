package client;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;
import entity.Patient;
import entity.waitingQueue;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class CounterMenu {
    private static ListInterface<Patient> patientList = new ArrayList<>(100);

    private static QueueInterface<waitingQueue> waitingQueue = new ArrayQueue<>(100);
    private static int waitingNo = 100;
    private static boolean roomFree1 = false;
    private static boolean roomFree2 = false;
    private static Queue<Patient> room1 = new LinkedList<>();
    private static Queue<Patient> room2 = new LinkedList<>();
    private static Queue<Patient> room3 = new LinkedList<>();

    private static Scanner input = new Scanner(System.in);

    //add/search patient only can register
    public static void main(String[] args) {
        counterMenu();
    }

    public static void counterMenu(){

        patientList = sort(patientList);
        System.out.println("Counter: ");
        System.out.println("[1] Search for Patient");
        System.out.println("[2] Add New Patient");
        System.out.println("[3] Edit Existing Patient Details");
        System.out.println("[4] Delete Existing Patient Details");
        System.out.println("[5] Queue Number");
        System.out.println("Enter your option: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                if (!(waitingQueue.isEmpty())) {
                    searchPatient();
                } else {
                    System.out.println("Patient List is empty");
                }
                break;
            case 2:
                addPatient();
                break;
            case 3:
                if (!(waitingQueue.isEmpty())) {
                    editPatient();
                } else {
                    System.out.println("Patient List is empty");
                }
                break;
            case 4:
                if (!(waitingQueue.isEmpty())) {
                    deletePatient();
                } else {
                    System.out.println("Patient List is empty");
                }
                break;
            case 5:
                showQueue();
                break;
        }
    }

    public static void searchPatient() {
        //sorting
        boolean exist = false;
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        exist = patientList.toString().contains(new Patient(patientIC).getIcNo());
        if (!exist) {
            System.out.println(new Patient(patientIC));
            System.out.println("Register patient?");

            String y = input.next();

            if (y == "Y") {
                System.out.println("Patient registered into queue");
                waitingNo++;
                registerPatient(new Patient(patientIC), waitingNo);
            } else {
                System.out.println("Patient not registered into queue");
            }
        } else {
            System.out.println("Record not found! Want to add patient?");
            addPatient();
        }


    }

    public static ListInterface<Patient> sort( ListInterface<Patient> pL) {
        Patient array;
        for (int i = 1; i < pL.getNumberOfEntries(); i++) {
                if (pL.getEntry(i + 1).getPatientName().charAt(0) > pL.getEntry(i).getPatientName().charAt(0)) {
                    array = pL.getEntry(i);
                    pL.getEntry(i).equals(pL.getEntry(i + 1));
                    pL.getEntry(i + 1).equals(array);
                }
            }

        return pL;
    }

    public static void addPatient() {

        System.out.println("Enter Patient Details ");
        System.out.print("Name: ");
        String patientName = input.next();
        System.out.print("IC No: ");
        String patientIC = input.next();
        System.out.print("Phone No: ");
        String patientPhoneNo = input.next();
        System.out.print("Address: ");
        String patientAddress= input.next();
        System.out.print("Date of Birth: ");
        String patientDOB = input.next();

        Patient patientNew= new Patient(patientName,patientIC,patientPhoneNo,patientAddress,patientDOB);
        patientList.add(patientNew);
        System.out.println("Patient added successfully!");
        System.out.println("Register patient?");
        String y = input.next();

        if (Objects.equals(y, "Y")) {
            System.out.println("Patient registered into queue");
            waitingNo++;
            registerPatient(new Patient(patientIC), waitingNo);

        }
        else {
            System.out.println("Patient not registered into queue");
        }
        counterMenu();

    }

    public static void editPatient() {
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        patientList.contains(new Patient(patientIC));
        System.out.println(new Patient(patientIC));

        System.out.println("Confirm edit?");
        String n = input.next();
        if (n == "Y") {
            System.out.println("Which details do u wanna edit?");
            System.out.println("[1] Patient's Name");
            System.out.println("[2] Patient's Phone No");
            System.out.println("[3] Patient's Address");
            System.out.println("[4] Patient's DOB");
            System.out.println("Enter your option: ");

            int detailsNo = input.nextInt();
            switch (detailsNo){
                case 1:
                    System.out.println("Name: ");
                    String nameEdit = input.next();
                    new Patient(patientIC).setPatientName(nameEdit);
                    break;
                case 2:
                    System.out.println("Phone No: ");
                    String phoneNoEdit = input.next();
                    new Patient(patientIC).setPhoneNo(phoneNoEdit);
                    break;
                case 3:
                    System.out.println("Address: ");
                    String addressNoEdit = input.next();
                    new Patient(patientIC).setAddress(addressNoEdit);
                    break;
                case 4:
                    System.out.println("DOB: ");
                    String dobNoEdit = input.next();
                    new Patient(patientIC).setDOB(dobNoEdit);
                    break;

            }
            System.out.println("Patient details edited successfully!");

        }
        else{
            System.out.println("Patient details not edited");
        }

        }

    public static void deletePatient() {
        boolean exist = false;
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        exist = patientList.contains(new Patient(patientIC));
        if (!exist) {
            for (int i = 0; i < patientList.getNumberOfEntries()+1; i++) {
                if (new Patient(patientIC) == patientList.getEntry(i)) {
                    patientList.remove(i);
                }
            }
        } else {
            System.out.println("Record not found! Want to add patient?");
            addPatient();
        }
    }

    public static void registerPatient(Patient registerPatientIC, int waitingNo){

        double roomNo = 0;

        //to see which room is free

        if (roomFree1 == true){
            roomNo = 1;
        }
        else if(roomFree2 == true){
            roomNo = 2;
        }
        else{
            roomNo=3;
        }

        if (waitingQueue.isEmpty()){
            waitingQueue.enqueue(new waitingQueue(null, 0, 0));
        }

        waitingQueue.enqueue(new waitingQueue(registerPatientIC, (int) roomNo, waitingNo));
    }

    public static void showQueue(){
        String n;

        for(int i = 1; i<patientList.getNumberOfEntries()+1 ; i++){
            System.out.println(patientList.getEntry(i));
        }

        do {
            if (waitingQueue.isEmpty()) {
                System.out.println("No one in queue yet!");
                break;

            } else {
                //clear screen
                System.out.println("Previous Queue Number: ");
                if (waitingQueue.getFront().getPatientList() == null) {
                    System.out.println("waitingNo= " + 0 + ", roomNo= " + 0);
                } else {
                    System.out.println(waitingQueue.getFront());
                }

                System.out.println("Current Queue Number: ");
                waitingQueue.dequeue();
                if (waitingQueue.getFront() == null) {
                    System.out.println("No patients registered in queue");
                    break;
                }
                else {
                    System.out.println(waitingQueue.getFront());
                }

                //add patient details in the room queue before dequeuing
                if (waitingQueue.getFront().getRoomNo() == 1) {
                    room1.add(waitingQueue.getFront().getPatientList());
                } else if (waitingQueue.getFront().getRoomNo() == 2) {
                    room2.add(waitingQueue.getFront().getPatientList());
                } else {
                    room3.add(waitingQueue.getFront().getPatientList());
                }

                //how many ppl waiting
                System.out.println("In Queue: ");
                System.out.println(waitingQueue.getSize());
                System.out.println();
                System.out.print("Press n to call the next number: ");
                n = input.next();

            }
        }
        while (Objects.equals(n, "n")) ;
    }

}
