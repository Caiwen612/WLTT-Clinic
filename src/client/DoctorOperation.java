package client;

import adt.ArrayList;
import adt.ListInterface;
import adt.SortedListInterface;
import driver.Driver;
import entity.*;
import utility.Font;
import utility.Validation;
import utility.ValidationException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Doctor operation - Client class to implement operation on entity and ADT
 *
 * @author Tay Chai Boon
 */
public class DoctorOperation {
    private static Scanner input = new Scanner(System.in);
    private static ListInterface<Doctor> doctorList = new ArrayList<>(5);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public DoctorOperation(){
        Doctor doctor1 = new Doctor("D1","Caiwen");
        Doctor doctor2 = new Doctor("D2","Zhi Yi");
        Doctor doctor3 = new Doctor("D3","Da Fei");
        sdf.setLenient(false);

        databaseInit();
        loadData();

        // for the first time running program
        if(doctorList.getNumberOfEntries() == 0){
            doctorList.add(doctor1);
            doctorList.add(doctor2);
            doctorList.add(doctor3);


            try{
                //Run at Empty
                Appointment a1 = new Appointment(new Patient(),sdf.parse("04-12-2020 11:30"),"a1");//12:00
                //Run at Beginning
                Appointment a2 = new Appointment(new Patient(),sdf.parse("04-12-2020 11:00"),"a2");
                Appointment a3 = new Appointment(new Patient(),sdf.parse("04-12-2020 10:00"),"a3");
                //Run at Behind
                Appointment a4 = new Appointment(new Patient(),sdf.parse("05-12-2020 15:00"),"a4");
                Appointment a5 = new Appointment(new Patient(),sdf.parse("05-12-2020 16:00"),"a5");
                //Run at Center
                Appointment a6 = new Appointment(new Patient(),sdf.parse("05-12-2020 13:00"),"a6");
                Appointment a7 = new Appointment(new Patient(),sdf.parse("05-12-2020 14:00"),"a7");
                Appointment a8 = new Appointment(new Patient(),sdf.parse("05-12-2020 12:30"),"a8");
                Appointment a9 = new Appointment(new Patient(),sdf.parse("05-12-2020 14:30"),"a9");
                Appointment a10 = new Appointment(new Patient(),sdf.parse("05-12-2020 10:30"),"a10");
                doctor1.getAppointmentList().add(a1);
                doctor1.getAppointmentList().add(a1);
                doctor1.getAppointmentList().add(a2);
                doctor1.getAppointmentList().add(a3);
                doctor1.getAppointmentList().add(a4);
                doctor1.getAppointmentList().add(a5);
                doctor1.getAppointmentList().add(a6);
                doctor1.getAppointmentList().add(a7);
                doctor1.getAppointmentList().add(a8);
                doctor1.getAppointmentList().add(a9);
                doctor1.getAppointmentList().add(a10);
            }catch (ParseException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    //Create database
    private static void databaseInit(){
        try {
            File file = new File("database/doctor.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Load data from files
    private static void loadData() {
        File[] files = new File("database").listFiles();
        for (File file : files) {
            System.out.println(file.getName());
            if (file.exists()) { //If the files exists, load all data into driver program
                try {
                    FileInputStream fis = new FileInputStream("database/" + file.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    switch (file.getName()) {
                        case "doctor.txt" -> doctorList = (ArrayList<Doctor>) ois.readObject();
                        default -> System.out.println("Unknown file to load into arraylist " + file.getName());
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

    //Store data
    public void storeData() {
        File[] files = new File("database").listFiles();

        for (File file : files) {
            if (file.exists()) { //If the files exists, load all data into driver program
                try {
                    FileOutputStream fileOutput = new FileOutputStream("database/" + file.getName());
                    ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
                    switch (file.getName()) {
                        case "doctor.txt" ->
                                objOutput.writeObject(doctorList);
                        default ->
                                System.out.println("Unknown file stored in database to be serialized in " + file.getName());
                    }
                    objOutput.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ListInterface<Doctor> getDoctorList() {
        return doctorList;
    }

    public static void checkPatientRecord(Patient patient){
        //Default data
        ListInterface<MedicalRecord> medicalRecord = patient.getHistory();
        if(medicalRecord.isEmpty()){
            System.out.println("This patient did not have any record.");
            Driver.pressAnyKeyToContinueWithPrompt();
        } else{
            for(int i = 1; i <= medicalRecord.getNumberOfEntries();){
                System.out.println(medicalRecord.getEntry(i));
                char viewMore = validCharYN("Do you want to view more record?(Y/N) ");
                if(i == medicalRecord.getNumberOfEntries()){
                    System.out.println("This patient has no more record.");
                    Driver.pressAnyKeyToContinueWithPrompt();
                }
                if(viewMore == 'Y'){
                    i++;
                } else{
                    break;
                }
            }
        }
    }

    public static void addPatientRecord(Patient patient,ListInterface<Medicine> medicineStock)  {
        Driver.clearScreen();
        input.nextLine();//clear buffer
        System.out.print(Font.TEXT_BLUE);
        System.out.print("Enter medical Problems: ");
        String mProblems = input.nextLine();
        System.out.print("Enter medical Symptoms: ");
        String mSymptoms = input.nextLine();
        System.out.print("Enter medical Diagnosis: ");
        String mDiagnosis = input.nextLine();
        System.out.print(Font.RESET);

        //Cart medicine
        ListInterface<Medicine> patientMedicineList = new ArrayList<>();
        char addMore = 0;
        do {
            printMedicineStock(medicineStock);
            int option = validOption("Enter the no: ",1, 100);
            int quantity = validQuantity("Enter the quantity: ",1000);
            //get the target medicine
            Medicine medicine = findMedicine(medicineStock,option, quantity);
            patientMedicineList.add(medicine);
            displayMedicalCart(patientMedicineList);
            addMore = validCharYN("Do you wish to add more? ('Y' for yes/ 'N' for no):");
        } while (addMore == 'Y');

        //Create a record  and save all the input from user
        MedicalRecord record = new MedicalRecord(mProblems, mSymptoms, mDiagnosis,patientMedicineList);
        patient.getHistory().add(record);
        Driver.pressAnyKeyToContinueWithPrompt();
    }

    public static void editPatientRecord(Patient patient,ListInterface<Medicine> medicineStock)  {
        //Get the latest record
        MedicalRecord record = patient.getHistory().getEntry(patient.getHistory().getNumberOfEntries());
        if(record != null){
            displayPatientRecord(patient,record);
            System.out.println("[1] Medical Problems");
            System.out.println("[2] Medical Symptoms");
            System.out.println("[3] Medical Diagnosis");
            System.out.println("[4] Medicine List");
            System.out.println("[5] Back");

            int index = validOption("Enter the option: ",1,5);
            input.nextLine();
            String newData = null;
            if (index != 5 && index != 4) {
                System.out.print(Font.TEXT_BLUE);
                System.out.print("Enter new data: ");
                newData = input.nextLine();
                System.out.print(Font.RESET);
            }

            switch (index){
                case 1:
                    record.setMedicalProblems(newData);
                    break;
                case 2:
                    record.setMedicalSymptoms(newData);
                    break;
                case 3:
                    record.setMedicalDiagnosis(newData);
                    break;
                case 4:
                    operationMedicalCart(patient,record,medicineStock);
                    break;
                case 5:
                    break;
            }
        } else {
            System.out.println("You did not add the record yet.");
            Driver.pressAnyKeyToContinue();
        }
    }

    public static void displayPatientRecord(Patient patient,MedicalRecord record)  {
        Driver.clearScreen();
        System.out.println("========================================================");
        System.out.println("|Medical record " + "of patient " + patient.getPatientName() + "|");
        System.out.println("=========================================================");
        System.out.println(record);
        if(record.getMedicineCart() != null){
            displayMedicalCart(record.getMedicineCart());
        }
        System.out.println("");
    }

    public static void displayMedicalCart(ListInterface<Medicine> medicineCart){
        System.out.println("Medicine Cart");
        System.out.print(Font.UNDERLINE_BLUE);
        System.out.printf("%-4s %-15s %-15s %10s %10s\n", "No", "Name", "Dosage Form", "Dose", "Quantity");
        System.out.print(Font.RESET);

        for(int i=1;i <= medicineCart.getNumberOfEntries();i++){
            Medicine medicine = medicineCart.getEntry(i);
            Dosage dosage = medicine.getDosage().getEntry(1);
            System.out.printf("%-4s %-15s %-15s %10s %10s\n",i,medicine.getName(),dosage.getDosageForm(),dosage.getDose(),dosage.getDosageQuantity());
        }
    }

    public static void addAppointment(Patient patient,Doctor doctor) {
        input.nextLine();
        boolean reEnter = true;
        Date date = null;
        do{
            try{
                System.out.print(Font.TEXT_BLUE);
                System.out.print("Enter the appointment date (dd-MM-yyyy HH:MM(24HOUR FORMAT): ");
                String userInput = input.nextLine();
                System.out.print(Font.RESET);
                date = sdf.parse(userInput);
                reEnter = false;
            }catch (ParseException ex){
                System.out.println("Invalid date format: " + ex.getMessage());
            }
        }while (reEnter);
        System.out.print(Font.TEXT_BLUE);
        System.out.print("Enter the appointment description: ");
        String description = input.nextLine();
        System.out.print(Font.RESET);
        Appointment appointment = new Appointment(patient,date,description);
        doctor.getAppointmentList().add(appointment);
    }

    public static void appointmentManagement(Doctor doctor)  {
        //Default data
        sdf.setLenient(false);//to make sure date flw the format
        Driver.clearScreen();
        System.out.println("Appointment Management");
        System.out.println("[1] Search appointment");
        System.out.println("[2] Display appointment");
        System.out.println("[3] Update appointment");
        System.out.println("[4] Delete appointment");
        System.out.println("[5] Back");

        int option = validOption("Enter a option: ",1,5);
        switch (option){
            case 1:
                input.nextLine();
                System.out.print(Font.TEXT_BLUE);
                System.out.print("Enter the date(dd-mm-yyyy): ");
                System.out.print(Font.RESET);
                String searchDate = input.nextLine();
                SortedListInterface appointmentList = doctor.getAppointmentList();
                boolean found = false;
                try{
                    for (Object object: appointmentList){
                        Appointment appointment = (Appointment) object;
                        if(appointment.getDate().contains(searchDate)){
                            found = true;
                            System.out.println(appointment);
                        }
                    }
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                if(found){
                    Driver.pressAnyKeyToContinueWithPrompt();
                    break;
                } else {
                    System.out.println("You did not have any appointment at this date " + searchDate);
                    Driver.pressAnyKeyToContinueWithPrompt();
                    break;
                }
            case 2:
                Driver.clearScreen();
                System.out.println("[1] Display from latest");
                System.out.println("[2] Display from previous");
                int displayOption = validOption("Enter the option: ",1,2);
                switch (displayOption){
                    case 1:
                        System.out.println("Index" + "\t" + "ID" + "\t" + "Name" + "\t" + "Date" + "\t" + "description");
                        System.out.println(doctor.getAppointmentList().printDescending());
                        System.out.println("Total appointment: " + doctor.getAppointmentList().getNumberOfEntries());
                        Driver.pressAnyKeyToContinueWithPrompt();
                        break;
                    case 2:
                        System.out.println("ID" + "\t" + "Name" + "\t" + "Date" + "\t" + "description");
                        System.out.println(doctor.getAppointmentList().printAscending());
                        System.out.println("Total appointment: " + doctor.getAppointmentList().getNumberOfEntries());
                        Driver.pressAnyKeyToContinueWithPrompt();
                        break;
                }
                break;
            case 3:
                Driver.clearScreen();
                System.out.println("ID" + "\t" + "Name" + "\t" + "Date" + "\t" + "description");
                System.out.println(doctor.getAppointmentList().printAscending());
                int editIndex = validOption("Enter the index: ",1,doctor.getAppointmentList().getNumberOfEntries());
                Appointment appointment = doctor.getAppointmentList().getEntry(editIndex);
                Driver.clearScreen();
                System.out.println(appointment);
                System.out.println("[1] Edit date");
                System.out.println("[2] Edit description");
                int editOption = validOption("Enter the option that you want to edit: ",1,2);
                switch (editOption){
                    case 1:
                        input.nextLine();
                        boolean reEnter = false;
                        Date date = null;
                        do{
                            try{
                                System.out.print(Font.TEXT_BLUE);
                                System.out.print("Enter the new appointment date (dd-MM-yyyy HH:MM(24HOUR FORMAT): ");
                                String userInput = input.nextLine();
                                System.out.print(Font.RESET);
                                date = sdf.parse(userInput);
                                reEnter = false;
                            }catch (ParseException ex){
                                System.out.println("Invalid date format: " + ex.getMessage());
                                reEnter = true;
                            }
                        }while (reEnter);
                        appointment.setDate(date);
                        //Remove the old data because the data did not sort after update the details
                        doctor.getAppointmentList().remove(appointment);
                        //Add back new data, so it will sort
                        doctor.getAppointmentList().add(appointment);
                        break;
                    case 2:
                        input.nextLine();
                        System.out.print(Font.TEXT_BLUE);
                        System.out.print("Enter the new description");
                        String newDescription = input.nextLine();
                        System.out.print(Font.RESET);
                        appointment.setAppointmentDescription(newDescription);
                        break;
                }
                break;

            case 4:
                Driver.clearScreen();
                System.out.println("ID" + "\t" + "Name" + "\t" + "Date" + "\t" + "description");
                System.out.println(doctor.getAppointmentList().printAscending());
                int deleteIndex = validOption("Enter the index: ",1,doctor.getAppointmentList().getNumberOfEntries());
                Appointment deleteAppointment = doctor.getAppointmentList().getEntry(deleteIndex);
                System.out.println("You are trying to remove this Medicine = " + deleteAppointment);
                char confirmRemove = validCharYN("Do you sure you want to remove? (Y/N) ");
                if(confirmRemove == 'Y'){
                    doctor.getAppointmentList().remove(deleteAppointment);
                } else{
                    break;
                }
                break;
            case 5:
                break;
        }
    }

    //Module for medicine
    public static void printMedicineStock(ListInterface<Medicine> medicineStock) {
        Driver.clearScreen();
        int i = 1;
        System.out.println("===========================================================");
        System.out.printf("%36s\n", "Medicine List");
        System.out.println("===========================================================");
        System.out.printf("%-4s %-15s %-15s %10s %10s\n", "No", "Name", "Dosage Form", "Dose", "Quantity");
        System.out.println("-----------------------------------------------------------");
        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                String output = String.format("%-4s %-15s %-15s %10s %8s", i,
                        medicineStock.getEntry(j).getName(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageForm(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDose(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageQuantity());

                if (medicineStock.getEntry(j).getDosage().getEntry(k).getDosageQuantity() <= 20) {
                    System.out.println(output);
                } else {
                    System.out.println(output);
                }
                i++;
            }
            System.out.println("-----------------------------------------------------------");
        }
    }

    public static Medicine findMedicine(ListInterface<Medicine> medicineStock,int num, int quantity){
        int index = 0, medicineNo = 0, dosageNo = 0;
        //Find the index of medicine and dosage
        Medicine medicineMenu = null;
        Dosage dosageMenu = null;
        ListInterface<Dosage> dosageListMenu;

        //Get a copy of medicine and dosage for patient
        ListInterface<Dosage> patientListDosage = new ArrayList<>();
        Medicine patientMedicine = null;
        Dosage patientDosage = null;

        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                index++;
                if (index == num) {
                    medicineNo = j;
                    dosageNo = k;

                    //Find the target of medicine and dosage
                    dosageMenu = medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo);//copy dosage
                    medicineMenu = medicineStock.getEntry(medicineNo);//copy medicine
                    dosageListMenu = medicineMenu.getDosage();//copy arraylist

                    //Make sure  the data of menu will not be affect
                    patientMedicine = medicineMenu.clone();

                    patientDosage = medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).clone();
                    patientDosage.setDosageQuantity(quantity);
                    patientListDosage.add(patientDosage);
                    patientMedicine.setDosage(patientListDosage);

                }

            }
        }
        return patientMedicine;
    }

    public static void operationMedicalCart(Patient patient,MedicalRecord record,ListInterface<Medicine> medicineStock)  {
        Driver.clearScreen();
        ListInterface<Medicine> medicineCart = record.getMedicineCart();
        displayMedicalCart(medicineCart);
        System.out.println("[1] Add Medicine       ");
        System.out.println("[2] Edit Medicine     ");
        System.out.println("[3] Remove Medicine    ");
        System.out.println("[4] Back   ");

        int optionMedical = validOption("Enter the option : ",1,4);
        switch (optionMedical){
            case 1:
                printMedicineStock(medicineStock);
                //Todo: BUGGGGGGGGG for  input validation
                int option = validOption("Enter the no: ",1, 100);
                int quantity = validQuantity("Enter the quantity: ",50);
                //get the target medicine
                Medicine medicine = findMedicine(medicineStock,option, quantity);
                medicineCart.add(medicine);
                Driver.clearScreen();
                displayMedicalCart(medicineCart);
                break;
            case 2:
                //Get the target medicine
                int medicineNo = validOption("Enter the no you wish to edit: ",1,medicineCart.getNumberOfEntries());
                System.out.println("Medicine = " + medicineCart.getEntry(medicineNo));
                int newQuantity = validQuantity("Enter new quantity: ",100);
                medicineCart.getEntry(medicineNo).getDosage().getEntry(1).setDosageQuantity(newQuantity);
                Driver.clearScreen();
                displayMedicalCart(medicineCart);
                break;
            case 3:
                int medicineNo2 = validOption("Enter the no you wish to remove: ",1,medicineCart.getNumberOfEntries());
                System.out.println("You are trying to remove this Medicine = " + medicineCart.getEntry(medicineNo2));
                char confirmRemove = validCharYN("Do you sure you want to remove? (Y/N) ");
                if(confirmRemove == 'Y'){
                    medicineCart.remove(medicineNo2);
                    Driver.clearScreen();
                    displayMedicalCart(medicineCart);
                } else{
                    displayMedicalCart(medicineCart);
                }
                break;
        }
    }

    //Validation for input
    public static char validCharYN(String message){
        boolean reEnter = true;
        char inputCharacter = 'Z';
        do{
            try{
                System.out.print(Font.TEXT_BLUE);
                System.out.print(message);
                inputCharacter = Character.toUpperCase(input.next().charAt(0));
                System.out.print(Font.RESET);
                Validation.validCharYN(inputCharacter);
                reEnter = false;
            } catch (ValidationException e){
                System.err.println(e.getMessage());
                input.nextLine();
                try{
                    Thread.sleep(1000);
                }catch (Exception ex){
                    System.err.println(e.getMessage());
                }
            }
        }while (reEnter);

        return inputCharacter;
    }

    public static int validOption(String message,int lowerOption,int upperOption){
        boolean reEnter = true;
        int option = 0;
        do{
            try{
                System.out.print(Font.TEXT_BLUE);
                System.out.print(message);
                option = input.nextInt();
                System.out.print(Font.RESET);
                Validation.validOption(option,lowerOption,upperOption);
                reEnter = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                try{
                    Thread.sleep(1000);
                }catch (Exception ex){
                    System.err.println(e.getMessage());
                }
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                try{
                    Thread.sleep(1000);
                }catch (Exception ex){
                    System.err.println(e.getMessage());
                }
            }
        }while(reEnter);

        return option;
    }

    public static int validQuantity(String message, int currentStock) {
        boolean reEnter = true;
        int quantity = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.print(message);
                quantity = input.nextInt();
                System.out.print(Font.RESET);
                Validation.validProductQuantity(quantity, currentStock);
                reEnter = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                try{
                    Thread.sleep(1000);
                }catch (Exception ex){
                    System.err.println(e.getMessage());
                }
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                try{
                    Thread.sleep(1000);
                }catch (Exception ex){
                    System.err.println(e.getMessage());
                }
            }
        } while (reEnter);

        return quantity;
    }

}
