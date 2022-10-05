package client;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;
import driver.Driver;
import entity.MedicalRecord;
import entity.Patient;
import entity.WaitingQueue;
import utility.Validation;
import utility.ValidationException;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

/*
 * @Author: Lan Ke En
 * @Group: RSF2S1G1
 * */

public class CounterManager {
    private static ListInterface<Patient> patientList = new ArrayList<>(100);
    //private static QueueInterface<WaitingQueue> waitingQueuePatient = new ArrayQueue<>(100);
    private static QueueInterface<WaitingQueue> room1Queue = new ArrayQueue<>(20);
    private static QueueInterface<WaitingQueue> room2Queue = new ArrayQueue<>(20);
    private static QueueInterface<WaitingQueue> room3Queue = new ArrayQueue<>(20);


    static Scanner input = new Scanner(System.in);
    private static String y;

    //add/search patient only can register

    public CounterManager() {


        //Default data
        Patient p1 = new Patient("Low Zhi Yi", "021205101234", "011-88578857", "KL", "05-12-2002");
        Patient p2 = new Patient("Tay Chai Boon", "020612101661", "011-28227757", "KL", "12-06-2002");
        Patient p3 = new Patient("Lan Ke En", "020323104567", "011-21913130", "KL", "23-03-2002");
        Patient p4 = new Patient("Lee Chun Kai", "020907018912", "011-1071 5933", "JB", "07-09-2002");
        Patient p5 = new Patient("Wilson Yau Kai Chun", "020218011661", "010-529 0905", "JB", "18-02-2002");
        patientList.add(p1);
        patientList.add(p2);
        patientList.add(p3);
        patientList.add(p4);
        patientList.add(p5);


        MedicalRecord mR1 = new MedicalRecord("", "AA", "AAA");
        MedicalRecord mR2 = new MedicalRecord("B", "BB", "BBB");
        MedicalRecord mR3 = new MedicalRecord("C", "CC", "CCC");
        p1.getHistory().add(mR1);
        p1.getHistory().add(mR2);
        p1.getHistory().add(mR3);


    }

    public static void searchPatient() throws InterruptedException {
        //input.nextLine();
        boolean validICNO = true;
        String patientIC = " ";
        do {
            try {
                System.out.print("IC Number(Number only without space): ");
                patientIC = input.next();
                Validation.validIC(patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            }
        } while (validICNO);

        Patient patient = null;
        if (patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            for (int i = 1; i < patientList.getNumberOfEntries() + 1; i++) {
                if (Objects.equals(new Patient(patientIC).getIcNo(), patientList.getEntry(i).getIcNo())) {
                    patient = patientList.getEntry(i);
                    System.out.println(patientList.getEntry(i).toString());
                }
            }
            System.out.println("Register patient?");
            y = input.next().toUpperCase();
            if (Objects.equals(y, "Y")) {
                registerPatient(patient);
                System.out.println("Patient registered into queue");
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();
            } else {
                System.out.println("Patient not registered into queue");
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();
            }
        } else {
            System.out.println("Record not found! Want to add patient?");
            y = input.next();
            if (Objects.equals(y, "Y")) {
                addPatient();
            } else {
                Driver.counterMenu();
            }
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(ListInterface<Patient> a, int n) {
        boolean sorted = false;
        for (int pass = 1; pass <= n && !sorted; pass++) {
            sorted = true;
            for (int index = 1; index <= n - pass; index++) {
                // swap adjacent elements if first is greater than second
                if (a.getEntry(index).compareTo(a.getEntry(index + 1)) > 0) {
                    swap(a, index, index + 1);
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
        input.nextLine();
        System.out.print("Name: ");
        String patientName = input.nextLine();

        do {
            try {
                System.out.print("IC Number(Number only without space): ");
                patientIC = input.nextLine();
                Validation.validIC(patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
            }
        } while (validICNO);

        System.out.print("Phone Number: ");
        String patientPhoneNo = input.nextLine();
        System.out.print("Address: ");
        String patientAddress = input.nextLine();
        System.out.print("Date of Birth: ");
        String patientDOB = input.nextLine();


        if (patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            System.out.println("Patient has been added before!");
            Driver.pressAnyKeyToContinueWithPrompt();
            Driver.counterMenu();
        } else {
            Patient patientNew = new Patient(patientName, patientIC, patientPhoneNo, patientAddress, patientDOB);
            patientList.add(patientNew);
            System.out.println("Patient added successfully!");
            System.out.println("Register patient?");
            String y = input.next().toUpperCase();

            if (Objects.equals(y, "Y")) {
                registerPatient(patientNew);
                System.out.println("Patient registered into queue");
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();

            } else {
                System.out.println("Patient not registered into queue");
            }
            Driver.pressAnyKeyToContinueWithPrompt();
            Driver.counterMenu();
        }
    }

    public static void editPatient() {
        int i = 0;
        boolean validICNO = true;
        String patientIC = " ";
        do {
            try {
                System.out.print("IC Number(Number only without space): ");
                patientIC = input.next();
                Validation.validIC(patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
            }

        } while (validICNO);
        if (patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            for (i = 1; i < patientList.getNumberOfEntries() + 1; i++) {
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
                input.nextLine();
                switch (detailsNo) {
                    case 1:
                        System.out.println("Name: ");
                        String nameEdit = input.nextLine();
                        patientList.getEntry(i).setPatientName(nameEdit);
                        break;
                    case 2:
                        System.out.println("Phone Number: ");
                        String phoneNoEdit = input.nextLine();
                        patientList.getEntry(i).setPhoneNo(phoneNoEdit);
                        break;
                    case 3:
                        System.out.println("Address: ");
                        String addressNoEdit = input.nextLine();
                        patientList.getEntry(i).setAddress(addressNoEdit);
                        break;
                    case 4:
                        System.out.println("DOB: ");
                        String dobNoEdit = input.nextLine();
                        patientList.getEntry(i).setDOB(dobNoEdit);
                        break;

                }
                System.out.println("Patient details edited successfully!");
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();
            } else {
                System.out.println("Patient details not edited");
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();
            }
        } else {
            System.out.println("Record not found! Want to add patient?");
            y = input.next().toUpperCase();
            if (Objects.equals(y, "Y")) {
                addPatient();
            } else {
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();
            }
        }
    }

    public static void deletePatient() {
        boolean validICNO = true;
        String patientIC = " ";
        do {
            try {
                System.out.print("IC Number(Number only without space): ");
                patientIC = input.next();
                Validation.validIC(patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
            }
        } while (validICNO);

        Patient patient = null;
        if (patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            for (int i = 1; i < patientList.getNumberOfEntries() + 1; i++) {
                if (Objects.equals(new Patient(patientIC).getIcNo(), patientList.getEntry(i).getIcNo())) {
                    patient = patientList.getEntry(i);
                    patientList.remove(i);
                    System.out.println("Patient Data Deleted!");
                    //Remove patient from queue
                    findRegisterPatient(patient);
                    break;
                }
            }
        } else{
            System.out.println("The record not found");
            Driver.pressAnyKeyToContinueWithPrompt();
            Driver.counterMenu();
        }
    }

    public static void findRegisterPatient(Patient patient) {
        //Find patient
        Patient targetPatient = null;
        Iterator room1Iterator = room1Queue.getIterator();
        Iterator room2Iterator = room2Queue.getIterator();
        Iterator room3Iterator = room3Queue.getIterator();

        //Search for patient in room1
        while (room1Iterator.hasNext()){
            WaitingQueue target = (WaitingQueue) room1Iterator.next();
            if(Objects.equals(target.getPatient().getIcNo(), patient.getIcNo())){
                System.out.println("The patient found in the room 1");
                targetPatient = target.getPatient();
                removeRegisterPatient(targetPatient,room1Queue);
                break;
            }
        }
        //Search for patient in room2
        while (room2Iterator.hasNext()){
            WaitingQueue target = (WaitingQueue) room2Iterator.next();
            if(Objects.equals(target.getPatient().getIcNo(), patient.getIcNo())){
                System.out.println("The patient found in the room 2");
                targetPatient = target.getPatient();
                removeRegisterPatient(targetPatient,room2Queue);
                break;
            }
        }
        //Search for patient in room3
        while (room3Iterator.hasNext()){
            WaitingQueue target = (WaitingQueue) room3Iterator.next();
            if(Objects.equals(target.getPatient().getIcNo(), patient.getIcNo())){
                System.out.println("The patient found in the room 3");
                targetPatient = target.getPatient();
                removeRegisterPatient(targetPatient,room3Queue);
                break;
            }
        }
    }

    public static void removeRegisterPatient(Patient patient,QueueInterface<WaitingQueue> waitingQueuePatient){
        ListInterface<WaitingQueue> tempList = new ArrayList<>(20);

        //Add original queue into list
        while(!(waitingQueuePatient.isEmpty())){
            tempList.add(waitingQueuePatient.dequeue());
        }

        //Remove the queue from tempList
        for(int i = 1;i <= tempList.getNumberOfEntries();i++){
            if(Objects.equals(patient.getIcNo(), tempList.getEntry(i).getPatient().getIcNo())) {
                System.out.println("Patient data removed from queue!");
                tempList.remove(i);
                break;
            }
        }

        //Add the list into queue
        for(int i = 1;i <= tempList.getNumberOfEntries();i++){
            waitingQueuePatient.enqueue(tempList.getEntry(i));
        }
        Driver.pressAnyKeyToContinueWithPrompt();
        Driver.counterMenu();
    }

    public static void registerPatient(Patient patient) {
        System.out.println("Enter your room no(1/2/3): ");
        int roomNo = input.nextInt();
        WaitingQueue registerPatient = new WaitingQueue(patient, roomNo);
        switch (roomNo) {
            case 1:
                room1Queue.enqueue(registerPatient);
                break;
            case 2:
                room2Queue.enqueue(registerPatient);
                break;
            case 3:
                room3Queue.enqueue(registerPatient);
                break;
        }
    }

    public static void showQueue() {
        if(room1Queue.isEmpty()){
            System.out.println("Room 1: " + "-");
        } else{
            System.out.println("Room 1: " + room1Queue.getFront().getWaitingNo() + "\tName: " + room1Queue.getFront().getPatientName());
        }

        if(room2Queue.isEmpty()){
            System.out.println("Room 2: " + "-");
        } else{
            System.out.println("Room 2: " + room2Queue.getFront().getWaitingNo() + "\tName: " + room2Queue.getFront().getPatientName());
        }

        if(room3Queue.isEmpty()){
            System.out.println("Room 3: " + "-");
        } else{
            System.out.println("Room 3: " + room3Queue.getFront().getWaitingNo() + "\tName: " + room3Queue.getFront().getPatientName());
        }
    }

    public static ListInterface<Patient> getPatientList() {
        return patientList;
    }

    public static QueueInterface<WaitingQueue> getRoom1Queue() {
        return room1Queue;
    }

    public static QueueInterface<WaitingQueue> getRoom2Queue() {
        return room2Queue;
    }

    public static QueueInterface<WaitingQueue> getRoom3Queue() {
        return room3Queue;
    }
}