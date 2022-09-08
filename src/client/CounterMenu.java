package client;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;
import entity.Dosage;
import entity.Patient;
import entity.waitingQueue;
import utility.Font;
import utility.Validation;
import utility.ValidationException;

import java.util.*;

public class CounterMenu {
    private static QueueInterface<entity.waitingQueue> waitingQueue = new ArrayQueue<>(100);
    private static ListInterface<Patient> patientList = new ArrayList<>(100);


    private static boolean roomFree1 = false;
    private static boolean roomFree2 = false;
    private static Queue<Patient> room1 = new LinkedList<>();
    private static Queue<Patient> room2 = new LinkedList<>();

    private static Queue<Patient> room3 = new LinkedList<>();

    private static Scanner input = new Scanner(System.in);

    //add/search patient only can register
    public static void searchPatient() {
        boolean validICNO = true;
        String patientIC = input.next();
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

            String y = input.next();

            if (y == "Y") {
                System.out.println("Patient registered into queue");
                CounterDriver.waitingNo++;
                registerPatient(new Patient(patientIC),   CounterDriver.waitingNo);
            } else {
                System.out.println("Patient not registered into queue");
            }
        } else {
            System.out.println("Record not found! Want to add patient?");
            addPatient();
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
                /*if (a[index].compareTo(a[index + 1]) > 0) {
                    swap(a, index, index + 1); // swap adjacent elements
                    sorted = false;  // array not sorted because a swap was performed
                }*/
            }
        }
    }

    private static <T> void swap(ListInterface<T> a, int i, int j) {
        T temp = a.getEntry(i);
        a.replace(i, a.getEntry(j));
        a.replace(j, temp);

        /*T temp = a[i];
        a[i] = a[j];
        a[j] = temp;*/
    }

    public static void addPatient() {
        boolean validICNO = true;
        String patientIC = input.next();
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

        System.out.println(patientIC);

            if (patientList.toString().contains(new Patient(patientIC).getIcNo())) {
                System.out.println("Patient has been registered before!");
                CounterDriver.counterMenu();
            } else {
                Patient patientNew = new Patient(patientName, patientIC, patientPhoneNo, patientAddress, patientDOB);
                patientList.add(patientNew);
                System.out.println("Patient added successfully!");
                System.out.println("Register patient?");
                String y = input.next();

                if (Objects.equals(y, "Y")) {
                    System.out.println("Patient registered into queue");
                    CounterDriver.waitingNo++;
                    registerPatient(new Patient(patientIC),   CounterDriver.waitingNo);

                } else {
                    System.out.println("Patient not registered into queue");
                }
                CounterDriver.counterMenu();
            }
        }

    public static void editPatient() {
        boolean validICNO = true;
        String patientIC = input.next();
        do{
            try{
                System.out.print("IC No: ");
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
            System.out.println("Confirm edit?");
            String n = input.next();
            if (n == "Y") {
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
                        new Patient(patientIC).setPatientName(nameEdit);
                        break;
                    case 2:
                        System.out.println("Phone Number: ");
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

            } else {
                System.out.println("Patient details not edited");
            }
        }
        else {
            System.out.println("Record not found! Want to add patient?");
            addPatient();
        }

    }

    public static void deletePatient() {
        boolean validICNO = true;
        String patientIC = input.next();
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
            for (int i = 0; i < patientList.getNumberOfEntries(); i++) {
                if (new Patient(patientIC) == patientList.getEntry(i)) {
                    patientList.remove(i);
                }
                System.out.println("Patient Deleted!");
                CounterDriver.counterMenu();
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

        for(int i = 1; i<patientList.getNumberOfEntries()+1; i++){
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

    public static ListInterface<Patient> getpatientList(){
        return patientList;
    }

    public static QueueInterface<entity.waitingQueue> getwaitingQueue (){
        return waitingQueue;
    }

}
