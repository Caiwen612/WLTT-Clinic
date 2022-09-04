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
    private static boolean roomFree1= false;
    private static boolean roomFree2= false;
    private static Queue<Patient> room1 = new LinkedList<>();
    private static Queue<Patient> room2 = new LinkedList<>();
    private static Queue<Patient> room3 = new LinkedList<>();

    private static Scanner input = new Scanner(System.in);

    //add/search patient only can register
    public static void main(String[] args){

        System.out.println("Counter: ");
        System.out.println("[1] Search for Patient");
        System.out.println("[2] Add New Patient");
        System.out.println("[3] Edit Existing Patient Details");
        System.out.println("[4] Delete Existing Patient Details");
        System.out.println("[5] Queue Number");
        System.out.println("Enter your option: ");
        int option = input.nextInt();

        switch (option){
            case 1:
                if(!(waitingQueue.isEmpty())){
                    searchPatient(patientList);
                } else{
                    System.out.println("Patient List is empty");
                }
                break;
            case 2:
                addPatient(patientList);
                break;
            case 3:
                if(!(waitingQueue.isEmpty())){
                    editPatient(patientList);
                } else{
                    System.out.println("Patient List is empty");
                }
                break;
            case 4:
                if(!(waitingQueue.isEmpty())){
                    deletePatient(patientList);
                } else{
                    System.out.println("Patient List is empty");
                }
                break;
            case 5:
                showQueue();
                break;
        }
    }

    public static void searchPatient(ListInterface<Patient> pL) {
        //sorting
        boolean exist = false;
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        exist = pL.toString().contains(new Patient(patientIC).getIcNo());
        if(!exist){
            System.out.println(new Patient(patientIC));
            System.out.println("Register patient?");

            String y = input.next();

            if (y=="Y") {
                System.out.println("Patient registered into queue");
                waitingNo++;
                registerPatient(new Patient(patientIC), waitingNo);
            }
            else {
                System.out.println("Patient not registered into queue");
            }
        }
        else{
            System.out.println("Record not found! Want to add patient?");
            addPatient(pL);
        }


    }

    public static void addPatient(ListInterface<Patient> pL) {
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
        pL.add(patientNew);
        System.out.println("Patient added successfully!");
        System.out.println("Register patient?");
        String y = input.next();

        if (Objects.equals(y, "Y ")) {
            System.out.println("Patient registered into queue");
            waitingNo++;
            registerPatient(new Patient(patientIC), waitingNo);
        }
        else {
            System.out.println("Patient not registered into queue");
        }

    }

    public static void editPatient(ListInterface<Patient> pL) {
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        pL.contains(new Patient(patientIC));
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

    public static void deletePatient(ListInterface<Patient> pL) {
        boolean exist = false;
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        exist = pL.contains(new Patient(patientIC));
        if (!exist) {
            for (int i = 0; i < pL.getNumberOfEntries()+1; i++) {
                if (new Patient(patientIC) == pL.getEntry(i)) {
                    pL.remove(i);
                }
            }
        } else {
            System.out.println("Record not found! Want to add patient?");
            addPatient(pL);
        }
    }

    public static void registerPatient(Patient registerPatientIC, int waitingNo){


        String n = input.next();

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
