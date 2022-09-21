package client;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;
import entity.Patient;
import entity.waitingQueue;
import utility.Validation;
import utility.ValidationException;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class CounterMenu {
    private static ListInterface<Patient> patientList = new ArrayList<>(100);

    private static QueueInterface<waitingQueue> waitingQueuePatient = new ArrayQueue<>(100);
    private static int waitingNo = 100;
    private static boolean roomFree1 = false;
    private static boolean roomFree2 = false;
    private static Queue<Patient> room1 = new LinkedList<>();
    private static Queue<Patient> room2 = new LinkedList<>();

    private static Queue<Patient> room3 = new LinkedList<>();

    static Scanner input = new Scanner(System.in);
    private static String y;

    //add/search patient only can register

    public static void searchPatient() {
        boolean validICNO = true;
        String patientIC =" ";
        do{
            try{
                System.out.print("IC Number: ");
                patientIC = input.next();
                Validation.validIC(validICNO,patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
            }
        }while(validICNO);

        if ( patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            for (int i = 1; i < patientList.getNumberOfEntries()+1; i++) {
                if (Objects.equals(new Patient(patientIC).getIcNo(), patientList.getEntry(i).getIcNo())) {
                    System.out.println(patientList.getEntry(i).toString());
                }
            }
            System.out.println("Register patient?");
            y = input.next().toUpperCase();
            if (Objects.equals(y, "Y")) {
                System.out.println("Patient registered into queue");
                waitingNo++;
                registerPatient(new Patient(patientIC), waitingNo);
                CounterDriver.counterMenu();
            } else {
                System.out.println("Patient not registered into queue");
                CounterDriver.counterMenu();
            }
        } else {
            System.out.println("Record not found! Want to add patient?");
            y = input.next();
            if (Objects.equals(y, "Y")) {
                addPatient();
            }
            else{
                CounterDriver.counterMenu();
            }
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(ListInterface<Patient> a, int n) {
        boolean sorted = false;
        for (int pass = 1; pass <= n && !sorted; pass++) {
            sorted = true;
            for (int index = 1; index <= n - pass; index++) {
                // swap adjacent elements if first is greater than second
                if (a.getEntry(index).compareTo(a.getEntry(index+1)) > 0){
                    swap(a, index, index+1);
                    sorted = false;
                }

            }
        }
    }

    private static <T> void swap(ListInterface<T> a, int i, int j) {
        T temp = a.getEntry(i);
        a.replace(i, a.getEntry(j));
        a.replace(j, temp);

    }

    public static void addPatient() {

        boolean validICNO = true;
        String patientIC = "";
        System.out.println("Enter Patient Details ");
        System.out.print("Name: ");
        String patientName = input.next();

        do{
            try{
                System.out.print("IC Number: ");
                patientIC = input.next();
                Validation.validIC(validICNO,patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
            }
        }while(validICNO);

        System.out.print("Phone Number: ");
        String patientPhoneNo = input.next();
        System.out.print("Address: ");
        String patientAddress= input.next();
        System.out.print("Date of Birth: ");
        String patientDOB = input.next();


        if (patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            System.out.println("Patient has been added before!");
            CounterDriver.counterMenu();
        } else {
            Patient patientNew = new Patient(patientName, patientIC, patientPhoneNo, patientAddress, patientDOB);
            patientList.add(patientNew);
            System.out.println("Patient added successfully!");
            System.out.println("Register patient?");
            String y = input.next().toUpperCase();

            if (Objects.equals(y, "Y")) {
                System.out.println("Patient registered into queue");
                waitingNo++;
                registerPatient(new Patient(patientIC), waitingNo);

            } else {
                System.out.println("Patient not registered into queue");
            }
            CounterDriver.counterMenu();
        }
    }

    public static void editPatient() {
        int i =0;
        boolean validICNO = true;
        String patientIC =" ";
        do{
            try{
                System.out.print("IC Number: ");
                patientIC = input.next();
                Validation.validIC(validICNO,patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
            }

        }while(validICNO);
        if ( patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            for (i = 1; i < patientList.getNumberOfEntries()+1; i++) {
                if (Objects.equals(new Patient(patientIC).getIcNo(), patientList.getEntry(i).getIcNo())) {
                    System.out.println(patientList.getEntry(i).toString());
                    break;
                }
            }
            System.out.println("Confirm edit?");
            String n = input.next().toUpperCase();
            if (Objects.equals(n, "Y")) {
                System.out.println("Which details do u wanna edit?");
                System.out.println("[1] Patient's Name");
                System.out.println("[2] Patient's Phone Number");
                System.out.println("[3] Patient's Address");
                System.out.println("[4] Patient's DOB");
                System.out.println("Enter your option: ");

                int detailsNo = input.nextInt();
                switch (detailsNo) {
                    case 1:
                        System.out.println("Name: ");
                        String nameEdit = input.next();
                        patientList.getEntry(i).setPatientName(nameEdit);
                        break;
                    case 2:
                        System.out.println("Phone Number: ");
                        String phoneNoEdit = input.next();
                        patientList.getEntry(i).setPhoneNo(phoneNoEdit);
                        break;
                    case 3:
                        System.out.println("Address: ");
                        String addressNoEdit = input.next();
                        patientList.getEntry(i).setAddress(addressNoEdit);
                        break;
                    case 4:
                        System.out.println("DOB: ");
                        String dobNoEdit = input.next();
                        patientList.getEntry(i).setDOB(dobNoEdit);
                        break;

                }
                System.out.println("Patient details edited successfully!");
                CounterDriver.counterMenu();
            } else {
                System.out.println("Patient details not edited");
                CounterDriver.counterMenu();
            }
        }
        else {
            System.out.println("Record not found! Want to add patient?");
            y = input.next().toUpperCase();
            if (Objects.equals(y, "Y")) {
                addPatient();
            }
            else{
                CounterDriver.counterMenu();
            }
        }
    }

    public static void deletePatient() {
        boolean validICNO = true;
        String patientIC = " ";
        do{
            try{
                System.out.print("IC Number: ");
                patientIC = input.next();
                Validation.validIC(validICNO,patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
            }
        }while(validICNO);
        if ( patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            boolean checkPatient =false;
            int loopSize=-1;
            for (int i = 1; i < patientList.getNumberOfEntries() + 1; i++) {
                if (Objects.equals(new Patient(patientIC).getIcNo(), patientList.getEntry(i).getIcNo())) {
                    patientList.remove(i);
                }
                System.out.println("Patient Data Deleted!");

            }
            QueueInterface<waitingQueue> queue = new ArrayQueue<waitingQueue>();
            while (!checkPatient){
                queue.enqueue(waitingQueuePatient.getFront());
                waitingQueuePatient.dequeue();
                if(waitingQueuePatient.getSize() == loopSize) {
                    checkPatient =true;
                    waitingQueuePatient= queue;
                    System.out.println("Patient data removed from queue!");
                    CounterDriver.counterMenu();
                }
                else if(waitingQueuePatient.getFront()==null){
                    checkPatient =true;
                    waitingQueuePatient= queue;
                    CounterDriver.counterMenu();
                }
                else {
                    loopSize++;
                    if (!(Objects.equals(waitingQueuePatient.getFront().getPatientList().getIcNo(), new Patient(patientIC).getIcNo()))) {
                        queue.enqueue(waitingQueuePatient.getFront());
                        waitingQueuePatient.dequeue();

                    } else {
                        waitingQueuePatient.dequeue();
                    }
                }
            }

        } else {
            System.out.println("Record not found! Want to add patient?");
            y = input.next().toUpperCase();
            if (Objects.equals(y, "Y")) {
                addPatient();
            }
            else{
                CounterDriver.counterMenu();
            }
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
        if( waitingQueuePatient.isEmpty()) {
            waitingQueuePatient.enqueue(new waitingQueue(null,0,0));
        }
        waitingQueuePatient.enqueue(new waitingQueue(registerPatientIC, (int) roomNo, waitingNo));

    }

    public static void showQueue(){

        String n;
        for(int i = 1; i<patientList.getNumberOfEntries()+1; i++){
            System.out.println(patientList.getEntry(i));
        }

        do {
            if (waitingQueuePatient.isEmpty()) {
                System.out.println("No one in queue yet!");
                break;

            } else {
                //clear screen
                System.out.println("Previous Queue Number: ");
                if (waitingQueuePatient.getFront()== null) {
                    System.out.println("waitingNo= " + 0 + ", roomNo= " + 0);
                } else {
                    System.out.println(waitingQueuePatient.getFront());
                }

                System.out.println("Current Queue Number: ");
                waitingQueuePatient.dequeue();
                if (waitingQueuePatient.getFront() == null) {
                    System.out.println("No patients registered in queue");
                    CounterDriver.counterMenu();
                }
                else {
                    System.out.println(waitingQueuePatient.getFront());
                }

                //add patient details in the room queue before dequeuing
                if (waitingQueuePatient.getFront().getRoomNo() == 1) {
                    room1.add(waitingQueuePatient.getFront().getPatientList());
                } else if (waitingQueuePatient.getFront().getRoomNo() == 2) {
                    room2.add(waitingQueuePatient.getFront().getPatientList());
                } else {
                    room3.add(waitingQueuePatient.getFront().getPatientList());
                }

                //how many ppl waiting
                System.out.println("In Queue: ");
                System.out.println(waitingQueuePatient.getSize());
                System.out.println();
                System.out.print("Press n to call the next number: ");
                n = input.next().toLowerCase();

            }
        }
        while (Objects.equals(n, "n")) ;
    }

    public static ListInterface <Patient> getPatientList(){
        return patientList;
    }

}