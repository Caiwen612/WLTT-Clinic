package driver;

import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;
import client.CounterManager;
import client.DoctorOperation;
import client.PaymentManager;
import client.PharmacistOperation;
import entity.*;
import utility.Font;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/** Driver - combine each of the module become 1 system
 * @author Team
 */

public class Driver {

    static Scanner input = new Scanner(System.in);

    //Client program
    private static CounterManager c = new CounterManager();
    private static PharmacistOperation pharmacistOperation = new PharmacistOperation();
    private static DoctorOperation d = new DoctorOperation();
    private static PaymentManager p = new PaymentManager(pharmacistOperation.getMedicineStock());

    //Queue for room
    private static QueueInterface<WaitingQueue> room1 = c.getRoom1Queue();
    private static QueueInterface<WaitingQueue> room2 = c.getRoom2Queue();
    private static QueueInterface<WaitingQueue> room3 = c.getRoom3Queue();

    private static int currentRoom = 0;

    //Queue for pharmacy
    private static QueueInterface<WaitingQueue> pharmacyQueue = new ArrayQueue<>(20);


    //Queue for payment
    private static QueueInterface<WaitingQueue> paymentQueue = new ArrayQueue<>(20);


    //Queue

    public static void main(String[] args){
        welcome();
        menu();
    }

    private static void menu() {

        clearScreen();
        board();
        System.out.println("WLTT CLINIC");
        System.out.println("[1] Counter");
        System.out.println("[2] Doctor");
        System.out.println("[3] Pharmacy");
        System.out.println("[4] Payment");
        System.out.println("[5] Exit");

        System.out.print("Enter the option: ");
        int option = input.nextInt();

        switch (option){
            case 1:
                counterMenu();
                break;
            case 2:
                doctorMenu();
                break;
            case 3:
                pharmacistMenu();
                break;
            case 4:
                paymentMenu();
                break;
            case 5:
                endProgram();
                break;
        }
    }


    //TODO: Counter @Author Lan Ke En
    public static void counterMenu()  {
        clearScreen();
        CounterManager.bubbleSort(CounterManager.getPatientList(), CounterManager.getPatientList().getNumberOfEntries());
        System.out.println("Counter: ");
        System.out.println("[1] Search for Patient");
        System.out.println("[2] Add New Patient");
        System.out.println("[3] Edit Existing Patient Details");
        System.out.println("[4] Delete Existing Patient Details");
        System.out.println("[5] Display queue");
        System.out.println("[6] Back");
        System.out.println("Enter your option: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                if (!(c.getPatientList().isEmpty())) {
                    try{
                        c.searchPatient();
                    }catch (Exception ex){

                    }
                    pressAnyKeyToContinueWithPrompt();
                } else {
                    System.out.println("Patient List is empty. Please add patient first!");
                    pressAnyKeyToContinueWithPrompt();
                    counterMenu();
                }
                break;
            case 2:
                c.addPatient();
                break;
            case 3:
                if (!(c.getPatientList().isEmpty())) {
                    c.editPatient();
                    pressAnyKeyToContinueWithPrompt();
                } else {
                    System.out.println("Patient List is empty. Please add patient first!");
                    pressAnyKeyToContinueWithPrompt();
                    counterMenu();
                }
                break;
            case 4:
                if (!(c.getPatientList().isEmpty())) {
                    c.deletePatient();
                    pressAnyKeyToContinueWithPrompt();
                } else {
                    System.out.println("Patient List is empty. Please add patient first!");
                    pressAnyKeyToContinueWithPrompt();
                    counterMenu();
                }
                break;
            case 5:
                c.showQueue();
                pressAnyKeyToContinueWithPrompt();
                counterMenu();
                break;
            case 6:
                try{
                    menu();
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
                break;
        }
    }
    //TODO: END Counter

    //TODO: Doctor @Author Tay Chai Boon
    public static void doctorMenu(){
        clearScreen();
        System.out.println("[1] Room 1");
        System.out.println("[2] Room 2");
        System.out.println("[3] Room 3");
        System.out.println("[4] Back");
        int option = d.validOption("Enter the option: ",1,4);
        switch (option){
            case 1:
                currentRoom = 1;
                break;
            case 2:
                currentRoom = 2;
                break;
            case 3:
                currentRoom = 3;
                break;
            case 4:
                menu();
                break;
        }
        room(currentRoom);
    }

    public static void room(int currentRoom) {
        clearScreen();
        QueueInterface<WaitingQueue> currentQueue = null;
        Doctor currentDoctor = d.getDoctorList().getEntry(currentRoom);
        switch (currentRoom){
            case 1:
                currentQueue = room1;
                break;
            case 2:
                currentQueue = room2;
                break;
            case 3:
                currentQueue = room3;
                break;
        }
        WaitingQueue currentPatient = null;

        if(Objects.requireNonNull(currentQueue).getFront() != null){
            currentPatient = currentQueue.getFront();
            if(currentPatient != null){
                System.out.println(Font.useFont(Font.BOLD_BLUE, "Current patient: " + currentPatient.getPatientName()));
            }
            System.out.println("[1] Check patient record history");
            System.out.println("[2] Add patient record");
            System.out.println("[3] Edit patient record");
            System.out.println("[4] Add appointment to patient");
            System.out.println("[5] Appointment Management");
            System.out.println("[6] Next Customer");
            System.out.println("[7] Back");
        } else{
            System.out.println("Currently no patient yet.");
            System.out.println("[5] Appointment Management");
            System.out.println("[7] Back");
        }

        //Display current patient


        int optionDoctor = d.validOption("Enter your selection: ",1,7);
        //TODO : Fix option
        ListInterface<Medicine> medicineStock = PharmacistOperation.getMedicineStock();
        switch (optionDoctor){
            case 1:
                d.checkPatientRecord(Objects.requireNonNull(currentPatient).getPatient());
                break;
            case 2:
                d.addPatientRecord(Objects.requireNonNull(currentPatient).getPatient(),medicineStock);
                break;
            case 3:
                d.editPatientRecord(Objects.requireNonNull(currentPatient).getPatient(),medicineStock);
                break;
            case 4:
                d.addAppointment(Objects.requireNonNull(currentPatient).getPatient(),currentDoctor);
                break;
            case 5:
                d.appointmentManagement(currentDoctor);
                break;
            case 6:
                updateBoard(currentRoom);
                break;
            case 7:
                doctorMenu();
                break;

        }
        room(currentRoom);
    }

    //TODO: Pharmacy @Author Wilson Yau Kai Chun
    public static void pharmacistMenu()  {
        WaitingQueue currentPatient = pharmacyQueue.getFront();
        Pharmacist pharmacist = new Pharmacist("P001", "Kai Lee");
        clearScreen();
        int option;

        System.out.println(Font.useFont(Font.BOLD_BLUE, "Pharmacist ID: " + pharmacist.getID()
                + "     Pharmacist Name: " + pharmacist.getName() + "\n" + "-".repeat(50)));
        if(currentPatient != null){
            System.out.println(Font.useFont(Font.BOLD_BLUE, "Current patient: " + currentPatient.getPatientName()));
        } else {
            System.out.println(Font.useFont(Font.BOLD_RED,"Currently no patient yet."));
        }
        MedicalRecord latestMedicalRecord = null;
        if(currentPatient != null){
            latestMedicalRecord = currentPatient.getPatient().getHistory().getEntry(currentPatient.getPatient().getHistory().getNumberOfEntries());
        }

        System.out.println("[1] Allocate Medicine");
        System.out.println("[2] Next Patient");
        System.out.println("[3] Medicine Stock Management");
        System.out.println("[4] Medicine Management");
        System.out.println("[5] View Summary Report");
        System.out.println("[6] Back");

        System.out.print("Enter your option: ");
        option = input.nextInt();

        switch (option) {
            case 1 -> {
                if(currentPatient != null){
                    pharmacistOperation.allocateMedicine(currentPatient.getPatient(),latestMedicalRecord);
                } else{
                    System.out.println("Currently no patient yet.");
                    pressAnyKeyToContinueWithPrompt();
                }
                break;
            }
            case 2 -> {
                updatePharmacyBoard();
                break;
            }
            case 3 -> {
                medicineStockManagement();
                break;
            }
            case 4 -> {
                medicineManagement();
                break;
            }
            case 5 -> {
                pharmacistOperation.viewSummaryReport();
                break;
            }
            case 6 -> menu();
        }
            pharmacistMenu();
    }

    public static void medicineStockManagement() {
        int option = 1;
        pharmacistOperation.printMedicineStock();
        while (option != 0) {
            System.out.println("[1] Search");
            System.out.println("[2] Sort");
            System.out.println("[3] Restock");
            System.out.println("[4] Exit");
            System.out.print("Enter your option: ");
            option = input.nextInt();

            input.nextLine();
            switch (option) {
                case 1:
                    pharmacistOperation.searchMedicineStock();
                    break;
                case 2:
                    pharmacistOperation.sortMedicineStock();
                    break;
                case 3:
                    pharmacistOperation.addMedicineStock();
                    break;
                case 4:
                    pharmacistMenu();
                    break;
            }
        }
    }

    public static void medicineManagement() {
        int option = 1;
        while (option != 0) {
            pharmacistOperation.printMedicineList();
            System.out.println("[1] Add new medicine");
            System.out.println("[2] Update current medicine");
            System.out.println("[3] Delete old medicine");
            System.out.println("[4] Exit");
            System.out.print("Enter your option: ");
            option = input.nextInt();

            input.nextLine();
            switch (option) {
                case 1:
                    pharmacistOperation.addNewMedicine();
                    break;
                case 2:
                    pharmacistOperation.updateCurrentMedicine();
                    break;
                case 3:
                    pharmacistOperation.deleteMedicine();
                    break;
                case 4:
                    pharmacistMenu();
                    break;

            }
        }
    }
    //TODO: END Pharmacy

    //TODO: Payment @Author Lee Chun Kai
    public static void paymentMenu(){
        clearScreen();
        WaitingQueue currentPatient = paymentQueue.getFront();
        if(currentPatient != null){
            System.out.println(Font.useFont(Font.BOLD_BLUE, "Current patient: " + currentPatient.getPatientName()));
        } else {
            System.out.println(Font.useFont(Font.BOLD_RED,"Currently no patient yet."));
        }
        System.out.println("[1] Print Invoice");//Current patient invoice
        System.out.println("[2] Transaction History");//Clinic
        System.out.println("[3] Proceed Payment");//Make payment
        System.out.println("[4] Next Customer");
        System.out.println("[0] Back");

        System.out.print("Enter your option: ");
        int option = input.nextInt();


        switch (option){
            case 1:
                if(currentPatient != null){
                    p.printInvoice(currentPatient.getPatient());
                } else{
                    System.out.println("Currently no patient yet.");
                    pressAnyKeyToContinueWithPrompt();
                }

                break;
            case 2:
                p.transactionMenu();
                break;
            case 3:
                if(currentPatient != null){
                    p.paymentProceed(currentPatient.getPatient());
                } else{
                    System.out.println("\nCurrently no patient yet.");
                    pressAnyKeyToContinueWithPrompt();
                }
                break;
            case 4:
                updatePaymentBoard();
                break;
            case 0:
                menu();
                break;
        }
        paymentMenu();
    }

    //TODO: END PAYMENT

    private static void board(){
        if(room1.isEmpty()){
            System.out.println("Room 1: " + "-");
        } else{
            System.out.println("Room 1: " + room1.getFront().getWaitingNo());
        }

        if(room2.isEmpty()){
            System.out.println("Room 2: " + "-");
        } else{
            System.out.println("Room 2: " + room2.getFront().getWaitingNo());
        }

        if(room3.isEmpty()){
            System.out.println("Room 3: " + "-");
        } else{
            System.out.println("Room 3: " + room3.getFront().getWaitingNo());
        }
    }

    private static void updateBoard(int roomNumber){
        WaitingQueue patient = null;
        switch (roomNumber){
            case 1:
                patient = room1.dequeue();
                break;
            case 2:
                patient = room2.dequeue();
                break;
            case 3:
                patient = room3.dequeue();
                break;
        }
        pharmacyQueue.enqueue(patient);
    }

    private static void updatePharmacyBoard(){
        WaitingQueue patient = null;
        patient = pharmacyQueue.dequeue();
        paymentQueue.enqueue(patient);
    }

    private static void updatePaymentBoard(){
        paymentQueue.dequeue();
    }







    //TODO: TEAM Tools @Author Team
    private static void welcome(){
        logo();
        System.out.printf("%79s %s%n", "", "System Developer");
        System.out.printf("%75s %s%n", "", "+=====================+");
        System.out.printf("%75s %s%n", "", "|Application Developer|");
        System.out.printf("%75s %s%n", "", "+=====================+");
        System.out.printf("%77s %s%n", "", "Tay Chai Boon");
        System.out.printf("%77s %s%n", "", "Lan Ke En");
        System.out.printf("%77s %s%n", "", "Lee Chun Kai");
        System.out.printf("%77s %s%n", "", "Wilson Yau Kai Chun");



        //Display date and times
        System.out.println();
        System.out.printf("%75s%s%n", "", new Date());

        System.out.printf("%75s", "");
        pressAnyKeyToContinueWithPrompt();
    }

    private static void logo(){
        System.out.printf("%45s %s%n", "", Font.TEXT_CYAN + " .----------------.  .----------------.  .----------------.  .----------------.    ");
        System.out.printf("%45s %s%n", "", "");
        System.out.printf("%45s %s%n", "", " .----------------.  .----------------.  .----------------.  .----------------. ");
        System.out.printf("%45s %s%n", "", "| .--------------. || .--------------. || .--------------. || .--------------. |");
        System.out.printf("%45s %s%n", "", "| | _____  _____ | || |   _____      | || |  _________   | || |  _________   | |");
        System.out.printf("%45s %s%n", "", "| ||_   _||_   _|| || |  |_   _|     | || | |  _   _  |  | || | |  _   _  |  | |");
        System.out.printf("%45s %s%n", "", "| |  | | /\\ | |  | || |    | |       | || | |_/ | | \\_|  | || | |_/ | | \\_|  | |");
        System.out.printf("%45s %s%n", "", "| |  | |/  \\| |  | || |    | |   _   | || |     | |      | || |     | |      | |");
        System.out.printf("%45s %s%n", "", "| |  |   /\\   |  | || |   _| |__/ |  | || |    _| |_     | || |    _| |_     | |");
        System.out.printf("%45s %s%n", "", "| |  |__/  \\__|  | || |  |________|  | || |   |_____|    | || |   |_____|    | |");
        System.out.printf("%45s %s%n", "", "| |              | || |              | || |              | || |              | |");
        System.out.printf("%45s %s%n", "", "| '--------------' || '--------------' || '--------------' || '--------------' |");
        System.out.printf("%45s %s%n", "", " '----------------'  '----------------'  '----------------'  '----------------' ");


        System.out.print(Font.RESET);
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pressAnyKeyToContinue(){
        input.nextLine();
        input.nextLine();
    }

    public static void pressAnyKeyToContinueWithPrompt(){
        System.out.println(Font.TEXT_BRIGHT_MAGENTA + "Press any key to continue..." + Font.RESET);
        input.nextLine();
        input.nextLine();
    }

    private static void endProgram(){
        System.out.println(Font.TEXT_CYAN);
        System.out.println(" /$$$$$$$$ /$$   /$$  /$$$$$$  /$$   /$$ /$$   /$$       /$$     /$$ /$$$$$$  /$$   /$$               ");
        System.out.println("|__  $$__/| $$  | $$ /$$__  $$| $$$ | $$| $$  /$$/     |  $$   /$$//$$__  $$| $$  | $$               ");
        System.out.println("   | $$   | $$  | $$| $$  \\ $$| $$$$| $$| $$ /$$/      \\  $$ /$$/| $$    \\ $$| $$  | $$      ");
        System.out.println("   | $$   | $$$$$$$$| $$$$$$$$| $$ $$ $$| $$$$$/         \\  $$$$/ | $$  | $$| $$  | $$            ");
        System.out.println("   | $$   | $$__  $$| $$__  $$| $$  $$$$| $$  $$           \\  $$/  |$$  | $$| $$  | $$            ");
        System.out.println("   | $$   | $$  | $$| $$  | $$| $$ \\ $$$| $$ \\ $$           | $$   |$$  | $$| $$  | $$         ");
        System.out.println("   | $$   | $$  | $$| $$  | $$| $$  \\ $$| $$  \\ $$          | $$   |$$$$$$/|  $$$$$$/         ");
        System.out.println("   |__/   |__/  |__/|__/  |__/|__/   \\__/|__/  \\__/          |__/   \\______/    \\______/    ");
        System.out.println("                             Wish you have a nice day                                                         ");
        System.out.println("                                                                                                      ");

        System.exit(0);
    }
}
