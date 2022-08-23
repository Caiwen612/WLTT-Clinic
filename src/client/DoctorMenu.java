package client;

import entity.Doctor;
import entity.Patient;
import utility.Font;
import utility.Validation;
import utility.ValidationException;

import java.util.*;

public class DoctorMenu {
    //Declare a set of adt
    static Queue<Patient> room1 = new LinkedList<>();
    static Queue<Patient> room2 = new LinkedList<>();
    static Queue<Patient> room3 = new LinkedList<>();

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Doctor doctor1 = new Doctor("D1","Caiwen");
        Doctor doctor2 = new Doctor("D2","Zhi Yi");
        Doctor doctor3 = new Doctor("D3","Da Fei");

        Patient p1 = new Patient();
        Patient p2 = new Patient();
        Patient p3 = new Patient();
        Patient p4 = new Patient();

        room1.add(p1);

        System.out.println("[1] Room 1");
        System.out.println("[2] Room 2");
        System.out.println("[3] Room 3");

        boolean optionRoomError = true;
        int optionRoom = 0;
        do{
            try{
                System.out.print("Enter your selection: ");
                optionRoom = input.nextInt();
                Validation.validOption(optionRoom,1,3);
                optionRoomError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        }while(optionRoomError);

        switch (optionRoom){
            case 1:
                room(doctor1);
                break;
            case 2:
                room(doctor2);
                break;
            case 3:
                room(doctor3);
                break;
        }
    }

    public static void room(Doctor doctor) throws InterruptedException {

        Queue currentQueue = null;
        if(Objects.equals(doctor.getID(), "D1")){
            currentQueue = room1;
        } else if(Objects.equals(doctor.getID(), "D2")){
            currentQueue = room2;
        } else if(Objects.equals(doctor.getID(), "D3")){
            currentQueue = room3;
        }

        Patient currentPatient = (Patient) currentQueue.remove();

        //Display current patient
        System.out.println("Current patient: " + currentPatient);
        System.out.println("[1] Check patient record");
        System.out.println("[2] Record the patient ");
        System.out.println("[3] Add MC");
        System.out.println("[4] Next Customer");
        System.out.println("[5] Summary report");
        System.out.println("[6] Back");

        boolean optionDoctorError = true;
        int optionDoctor = 0;
        do{
            try{
                System.out.print("Enter your selection: ");
                optionDoctor = input.nextInt();
                Validation.validOption(optionDoctor,1,6);
                optionDoctorError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        }while(optionDoctorError);

        switch (optionDoctor){
            case 1:
        }

    }


}
