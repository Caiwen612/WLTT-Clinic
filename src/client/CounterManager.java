package client;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;
import driver.Driver;
import entity.MedicalRecord;
import entity.Patient;
import entity.WaitingQueue;
import utility.Font;
import utility.Validation;
import utility.ValidationException;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class CounterManager {
    private static ListInterface<Patient> patientList = new ArrayList<>(100);
    private static QueueInterface<WaitingQueue> waitingQueuePatient = new ArrayQueue<>(100);
    private static QueueInterface<WaitingQueue> room1Queue = new ArrayQueue<>(20);
    private static QueueInterface<WaitingQueue> room2Queue = new ArrayQueue<>(20);
    private static QueueInterface<WaitingQueue> room3Queue = new ArrayQueue<>(20);



    static Scanner input = new Scanner(System.in);
    private static String y;

    //add/search patient only can register

    public CounterManager(){
        databaseInit();
        loadData();
        if(patientList.getNumberOfEntries() == 0){
            //Default data
            Patient p1 = new Patient("Low Zhi Yi","021205011234","011-88578857","KL","05-12-2002");
            Patient p2 = new Patient("Tay Chai Boon","020612101661","011-28227757","KL","12-06-2002");
            Patient p3 = new Patient("Lan Ke En","020323104567","011-21913130","KL","23-03-2002");
            Patient p4 = new Patient("Lee Chun Kai","020907018912","011-1071 5933","JB","07-09-2002");
            Patient p5 = new Patient("Wilson Yau Kai Chun","020218011661","010-529 0905","JB","18-02-2002");
            patientList.add(p1);
            patientList.add(p2);
            patientList.add(p3);
            patientList.add(p4);
            patientList.add(p5);


            MedicalRecord mR1 = new MedicalRecord("A","AA","AAA");
            MedicalRecord mR2 = new MedicalRecord("B","BB","BBB");
            MedicalRecord mR3 = new MedicalRecord("C","CC","CCC");
            p1.getHistory().add(mR1);
            p1.getHistory().add(mR2);
            p1.getHistory().add(mR3);

            WaitingQueue w1 = new WaitingQueue(p1,1);
            WaitingQueue w2 = new WaitingQueue(p2,2);
            WaitingQueue w3 = new WaitingQueue(p3,3);
            WaitingQueue w4 = new WaitingQueue(p4,1);
            WaitingQueue w5 = new WaitingQueue(p5,1);
            //Default data manual add in, Code will do auto
            room1Queue.enqueue(w1);
            room2Queue.enqueue(w2);
            room3Queue.enqueue(w3);
            room1Queue.enqueue(w4);
            room1Queue.enqueue(w5);
        }
    }

    private static void databaseInit(){
        try {
            File file = new File("database/patient.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            File file2 = new File("database/waitingQueue.txt");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            File file3 = new File("database/room1Queue.txt");
            if (!file3.exists()) {
                file3.createNewFile();
            }
            File file4 = new File("database/room2Queue.txt");
            if (!file4.exists()) {
                file4.createNewFile();
            }
            File file5 = new File("database/room3Queue.txt");
            if (!file5.exists()) {
                file5.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        File[] files = new File("database").listFiles();
        for (File file : files) {
            System.out.println(file.getName());
            if (file.exists()) { //If the files exists, load all data into driver program
                try {
                    FileInputStream fis = new FileInputStream("database/" + file.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    switch (file.getName()) {
                        case "patient.txt" -> patientList = (ArrayList<Patient>) ois.readObject();
                        case "waitingQueue.txt" -> waitingQueuePatient = (ArrayQueue<WaitingQueue>) ois.readObject();
                        case "room1Queue.txt" -> room1Queue = (ArrayQueue<WaitingQueue>) ois.readObject();
                        case "room2Queue.txt" -> room2Queue = (ArrayQueue<WaitingQueue>) ois.readObject();
                        case "room3Queue.txt" -> room3Queue = (ArrayQueue<WaitingQueue>) ois.readObject();
                    }
                    ois.close();
                    System.out.print(Font.TEXT_YELLOW);
                    System.out.println("The progress of the programs is loaded from the database.");
                    System.out.print(Font.RESET);
                }  catch (EOFException e){
                    //First time use this systems
                    System.out.print(Font.TEXT_YELLOW);
                    System.out.println("The database is empty");
                    System.out.println("Your progress will store in database.");
                    System.out.print(Font.RESET);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void storeData() {
        File[] files = new File("database").listFiles();

        for (File file : files) {
            if (file.exists()) { //If the files exists, load all data into driver program
                try {
                    FileOutputStream fos = new FileOutputStream("database/" + file.getName());
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    switch (file.getName()) {
                        case "patient.txt" -> oos.writeObject(patientList);
                        case "waitingQueue.txt" -> oos.writeObject(waitingQueuePatient);
                        case "room1Queue.txt" -> oos.writeObject(room1Queue);
                        case "room2Queue.txt" -> oos.writeObject(room2Queue);
                        case "room3Queue.txt" -> oos.writeObject(room3Queue);
                    }
                    oos.close();
//                    System.out.println("Your progress has been store in database.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void searchPatient() throws InterruptedException {
        boolean validICNO = true;
        String patientIC =" ";
        do{
            try{
                System.out.print("IC Number(Number only without space): ");
                patientIC = input.next();
                Validation.validIC(patientIC);
                validICNO = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            }
        }while(validICNO);

        Patient patient = null;
        if (patientList.toString().contains(new Patient(patientIC).getIcNo())) {
            for (int i = 1; i < patientList.getNumberOfEntries()+1; i++) {
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
            }
            else{
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
                System.out.print("IC Number(Number only without space): ");
                patientIC = input.next();
                Validation.validIC(patientIC);
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
        int i =0;
        boolean validICNO = true;
        String patientIC =" ";
        do{
            try{
                System.out.print("IC Number(Number only without space): ");
                patientIC = input.next();
                Validation.validIC(patientIC);
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
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();
            } else {
                System.out.println("Patient details not edited");
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();
            }
        }
        else {
            System.out.println("Record not found! Want to add patient?");
            y = input.next().toUpperCase();
            if (Objects.equals(y, "Y")) {
                addPatient();
            }
            else{
                Driver.pressAnyKeyToContinueWithPrompt();
                Driver.counterMenu();
            }
        }
    }

    public static void deletePatient() {
        boolean validICNO = true;
        String patientIC = " ";
        do{
            try{
                System.out.print("IC Number(Number only without space): ");
                patientIC = input.next();
                Validation.validIC(patientIC);
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
            QueueInterface<WaitingQueue> queue = new ArrayQueue<WaitingQueue>();
            while (!checkPatient){
                queue.enqueue(waitingQueuePatient.getFront());
                waitingQueuePatient.dequeue();
                if(waitingQueuePatient.getSize() == loopSize) {
                    checkPatient =true;
                    waitingQueuePatient= queue;
                    System.out.println("Patient data removed from queue!");
                    Driver.pressAnyKeyToContinueWithPrompt();
                    Driver.counterMenu();
                }
                else if(waitingQueuePatient.getFront()==null){
                    checkPatient =true;
                    waitingQueuePatient= queue;
                    Driver.pressAnyKeyToContinueWithPrompt();
                    Driver.counterMenu();
                }
                else {
                    loopSize++;
                    if (!(Objects.equals(waitingQueuePatient.getFront().getPatient().getIcNo(), new Patient(patientIC).getIcNo()))) {
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
                Driver.counterMenu();
            }
        }
    }

    public static void registerPatient(Patient patient){
        System.out.println("Enter your room no(1/2/3): ");
        int roomNo = input.nextInt();
        WaitingQueue registerPatient = new WaitingQueue(patient,roomNo);
        switch(roomNo) {
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

    public static void showQueue(){
        System.out.println("Room 1: " + room1Queue.getFront());//1001
        System.out.println("Room 2: " + room2Queue.getFront());//1002
        System.out.println("Room 3: " + room3Queue.getFront());//1003
    }

    public static ListInterface <Patient> getPatientList(){
        return patientList;
    }

    public static QueueInterface<WaitingQueue> getWaitingQueuePatient() {
        return waitingQueuePatient;
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