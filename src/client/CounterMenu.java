package client;

import entity.Patient;
import adt.ArrayQueue;
import adt.ArrayList;
import adt.ListInterface;
import adt.QueueInterface;
import entity.waitingQueue;

import java.util.Arrays;
import java.util.Scanner;

public class CounterMenu {
    private ListInterface<Patient> patientList = new ArrayList<>();
    private QueueInterface<waitingQueue> waitingQueue = new ArrayQueue<>();
    int waitingNo = 100;
     boolean roomFree1= false, roomFree2= false;

    Scanner input = new Scanner(System.in);

    public ListInterface<Patient> sortPatientList(ListInterface<Patient>   pL ){
        String[] sortPatient = new String[  pL .getNumberOfEntries()];
        for (int i = 0; i <  pL .getNumberOfEntries(); i++) {
            sortPatient[i] =   pL .getEntry(i).getPatientName();
        }

        Arrays.sort(sortPatient);
        pL  = (ListInterface<Patient>) Arrays.asList(sortPatient);
        return pL;

    }

    //add/search patient only can register

    public void menu(){
        patientList = sortPatientList(patientList);
        System.out.println("Counter: ");
        System.out.println("[1] Search for Patient");
        System.out.println("[2] Add New Patient");
        System.out.println("[3] Edit Existing Patient Details");
        System.out.println("Enter your option: ");
        int option = input.nextInt();

        switch (option){
            case 1:
                searchPatient(patientList);
                break;
            case 2:
                addPatient(patientList);
                break;
            case 3:
                editPatient(patientList);
                break;
            case 4:
                deletePatient(patientList);
                break;
        }

    }

    public void searchPatient(ListInterface<Patient> pL) {
        //sorting
        boolean exist = false;
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        exist = pL.contains(new Patient(patientIC));
        if(!exist){
            System.out.println(new Patient(patientIC).toString());
            System.out.println("Register patient?");
            waitingNo++;
            registerPatient(new Patient(patientIC), waitingNo);
        }
        else{
            System.out.println("Record not found! Want to add patient?");
            addPatient(pL);
        }

    }

    public void addPatient(ListInterface<Patient> pL) {
        System.out.println("Enter Patient Details ");
        System.out.println("Name: ");
        String patientName = input.next();
        System.out.println("IC No: ");
        String patientIC = input.next();
        System.out.println("Phone No: ");
        String patientPhoneNo = input.next();
        System.out.println("Address: ");
        String patientAddress= input.next();
        System.out.println("Date of Birth: ");
        String patientDOB = input.next();

        Patient patientNew= new Patient(patientName,patientIC,patientPhoneNo,patientAddress,patientDOB);
        pL.add(patientNew);
        System.out.println("Patient added successfully!");
        System.out.println("Register patient?");
        waitingNo++;
        registerPatient(new Patient(patientIC), waitingNo);
    }

    public void editPatient(ListInterface<Patient> pL) {
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        pL.contains(new Patient(patientIC));
        System.out.println(new Patient(patientIC).toString());
        System.out.println("Want to edit patient details?");
        //yes or yes
        System.out.println("Which details do u wanna edit?");
        //choose number 1-5, representing name, ic etc
        //Patient.set
        System.out.println("Confirm edit?");
        //yes or yes
        System.out.println("Patient details edited successfully!");
    }

    public void deletePatient(ListInterface<Patient> pL) {
        boolean exist = false;
        System.out.println("Enter Patient's IC No: ");
        String patientIC = input.next();
        exist = pL.contains(new Patient(patientIC));
        if (!exist) {
            for (int i = 0; i < pL.getNumberOfEntries(); i++) {
                if (new Patient(patientIC) == pL.getEntry(i)) {
                    pL.remove(i);
                }
            }
        } else {
            System.out.println("Record not found! Want to add patient?");
            addPatient(pL);
        }
    }

    public void registerPatient(Patient registerPatientIC, int waitingNo){

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

        waitingQueue.enqueue(new waitingQueue(registerPatientIC, (int) roomNo, waitingNo));
    }


}
