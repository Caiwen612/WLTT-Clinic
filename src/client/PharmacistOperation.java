package client;

import adt.ArrayList;
import adt.ListInterface;
import driver.Driver;
import entity.*;
import utility.Font;
import utility.Validation;

import java.util.Iterator;
import java.util.Scanner;

public class PharmacistOperation {
    private static ListInterface<Medicine> medicineStock = new ArrayList<>(10);
    private static Scanner input = new Scanner(System.in);

    public PharmacistOperation(){
        medicineStock.add(new Medicine("M001", "Acetaminophen", new ArrayList<>() {
            {
                add(new Dosage("Oral capsule", "325mg", 40, 5, 20, new MedicineDosageRecord(20, 10)));
                add(new Dosage("Oral liquid", "160mg/5mL", 30, 3, 15, new MedicineDosageRecord(20, 40)));
                add(new Dosage("Oral tablet", "500mg", 15, 8, 25, new MedicineDosageRecord(50, 45)));
            }
        }, "A pain reliever and fever reducer"));

        medicineStock.add(new Medicine("M002", "Oseltamivir", new ArrayList<>() {
            {
                add(new Dosage("Oral capsule", "300mg", 20, 5, 15, new MedicineDosageRecord(24, 50)));
                add(new Dosage("Oral capsule", "450mg", 40, 8, 20, new MedicineDosageRecord(10, 30)));
                add(new Dosage("Oral capsule", "600mg", 34, 10, 25, new MedicineDosageRecord(5, 0)));
            }
        }, "An antiviral medication that blocks the actions of influenza virus types A and B in the body"));

        medicineStock.add(new Medicine("M003", "Loperamide", new ArrayList<>() {
            {
                add(new Dosage("Oral capsule", "300mg", 28, 4, 15, new MedicineDosageRecord(15, 30)));
                add(new Dosage("Oral tablet", "450mg", 15, 8, 20, new MedicineDosageRecord(20, 30)));
            }
        }, "Used to treat diarrhea or to reduce the amount of stool(poop) in people who have an ileosomy"));

        medicineStock.add(new Medicine("M004", "Atorvastatin", new ArrayList<>() {
            {
                add(new Dosage("Oral tablet", "10mg", 60, 5, 15, new MedicineDosageRecord(45, 32)));
                add(new Dosage("Oral tablet", "20mg", 54, 8, 25, new MedicineDosageRecord(30, 23)));
            }
        }, "Used to improve cholesterol levels in people with different types of cholesterol problems"));

        medicineStock.add(new Medicine("M005", "Lisinopril", new ArrayList<>() {
            {
                add(new Dosage("Oral tablet", "10mg", 50, 5, 12, new MedicineDosageRecord(45, 40)));
                add(new Dosage("Oral tablet", "25mg", 74, 10, 25, new MedicineDosageRecord(50, 30)));
            }
        }, "Used to improve cholesterol levels in people with different types of cholesterol problems"));
    }

    public void printMedicineStock() {
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
                    System.out.println(Font.useFont(Font.TEXT_RED, output));
                } else {
                    System.out.println(output);
                }
                i++;
            }
            System.out.println("-----------------------------------------------------------");
        }
        System.out.println("*Red color indicate the number of stock is less than the minimum number.");
        System.out.println("*Please restock it.");

    }

    public void searchMedicineStock() {
        Driver.clearScreen();
        String keywords;
        System.out.print("Enter the keywords: ");
        keywords = input.nextLine();

        int i = 1;
        System.out.println("===========================================================");
        System.out.printf("%36s\n", "Medicine List");
        System.out.println("===========================================================");
        System.out.printf("%-4s %-15s %-15s %10s %10s\n", "No", "Name", "Dosage Form", "Dose", "Quantity");
        System.out.println("-----------------------------------------------------------");
        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            if (medicineStock.getEntry(j).getName().toLowerCase().contains(keywords.toLowerCase())) {
                for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                    System.out.printf("%-4s %-15s %-15s %10s %8s\n", i,
                            medicineStock.getEntry(j).getName(),
                            medicineStock.getEntry(j).getDosage().getEntry(k).getDosageForm(),
                            medicineStock.getEntry(j).getDosage().getEntry(k).getDose(),
                            medicineStock.getEntry(j).getDosage().getEntry(k).getDosageQuantity());
                    i++;
                }
                System.out.println("-----------------------------------------------------------");
            }
        }
    }

    public  void sortMedicineStock() {
        ListInterface<Medicine> temp = new ArrayList<>();

        Iterator<Medicine> medicineIterator = medicineStock.getIterator();

        while (medicineIterator.hasNext()){
            temp.add(medicineIterator.next().clone());
        }

        //TODO: STILL CANNOT SORT THE ARRAYLIST WITHOUT CHANGING ANOTHER
        for (int index = 1; index <= temp.getNumberOfEntries(); index++) {
            ArrayList.bubbleSort(temp.getEntry(index).getDosage(), temp.getEntry(index).getDosage().getNumberOfEntries(), 3);
        }

        Driver.medicineStockManagement();

    }

    public void addMedicineStock()  {
        int num, quantity;
        char option;
        printMedicineStock();
        System.out.print("Enter the number of medicine that you want to restock: ");
        num = input.nextInt();

        int index = 0, medicineNo = 0, dosageNo = 0;
        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                index++;
                if (index == num) {
                    medicineNo = j;
                    dosageNo = k;
                }
            }
        }
        System.out.print("Enter the quantity you want to restock for \nmedicine " + medicineStock.getEntry(medicineNo).getName()
                + " - " + medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDosageForm() + " (" +
                medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDose() + "): ");


        quantity = input.nextInt();

        while (quantity <= 0){
            System.out.println("You are not allowed to enter negative or zero value. Please try again: ");
            quantity = input.nextInt();
        }

        System.out.println("Are you sure you want to restock the medicine with " +
                quantity + " quantity" +
                "? ('Y' for yes/'N' for no): ");
        option = input.next().charAt(0);

        try{
            Validation.validCharYN(option);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }



        if (Character.toUpperCase(option) == 'Y'){
            medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).setDosageQuantity(quantity +
                    medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDosageQuantity());

            System.out.println("Restock successfully.");
        }
        else{
            System.out.println("You have cancelled the restock medicine process");
        }

        System.out.println("Press Enter key to exit to medicine menu...");
        input.nextLine();
        input.nextLine();
        Driver.medicineStockManagement();

    }

    public void printMedicineList() {
        Driver.clearScreen();
        int i = 1;
        System.out.println("================================================================================");
        System.out.printf("%46s\n", "Medicine List");
        System.out.println("================================================================================");
        System.out.printf("%-4s %-15s %-15s %10s %10s %9s %10s\n", "No", "Name", "Dosage Form", "Dose", "Quantity", "Cost(RM）", "Price(RM)");
        System.out.println("--------------------------------------------------------------------------------");
        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                String output = String.format("%-4s %-15s %-15s %10s %8s %9.2f %10.2f", i,
                        medicineStock.getEntry(j).getName(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageForm(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDose(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageQuantity(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageCost(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosagePrice()
                );

                if (medicineStock.getEntry(j).getDosage().getEntry(k).getDosageQuantity() <= 20) {
                    System.out.println(Font.useFont(Font.TEXT_RED, output));
                } else {
                    System.out.println(output);
                }
                i++;
            }
            System.out.println("--------------------------------------------------------------------------------");
        }
        System.out.println("*Red color indicate the number of stock is less than the minimum number.");
        System.out.println("*Please restock it.");

    }

    public void addNewMedicine()  {
        char option;
        String mediName, mediFunc;
        int doseFormQuantity;

        input.nextLine();
        System.out.print("Enter the name of new medicine: ");
        mediName = input.nextLine();

        for (int index = 1; index <= medicineStock.getNumberOfEntries(); index++) {
            if (medicineStock.getEntry(index).getName().trim().toLowerCase().equals(mediName)){
                System.out.println("The medicine already exits.");
                System.out.println("Press enter to go back to medicine menu...");
                input.nextLine();
                Driver.medicineManagement();
            }
        }

        System.out.print("Enter the function of the medicine: ");
        mediFunc = input.nextLine();

        System.out.print("Enter the number of dosage form: ");
        doseFormQuantity = input.nextInt();

        while (doseFormQuantity <= 0){
            System.out.println("You are not allowed to enter negative or zero value. Please try again: ");
            doseFormQuantity = input.nextInt();
        }


        double[] doseCost = new double[doseFormQuantity], dosePrice = new double[doseFormQuantity];
        int[] dosageType = new int[doseFormQuantity], doseQuantity = new int[doseFormQuantity];
        String[] dosageForm = new String[doseFormQuantity];

        System.out.println("--------------------------------------------");
        for (int i = 0; i < doseFormQuantity; i++) {
            System.out.println("Dosage form " + (i+1));
            System.out.println("[1] Oral capsule");
            System.out.println("[2] Oral tablet");
            System.out.println("[3] Oral liquid");
            System.out.print("Enter the dosage form: ");
            dosageType[i] = input.nextInt();

            input.nextLine();
            if (dosageType[i] == 1 ||  dosageType[i] == 2){
                System.out.print("Enter the dose(e.g. 500mg): ");
            }
            else{
                System.out.print("Enter the dose(e.g. 150mg/mL): ");
            }
            dosageForm[i] = input.nextLine();

            System.out.print("Enter the dosage cost: RM");
            doseCost[i] = input.nextDouble();

            System.out.print("Enter the dosage price: RM");
            dosePrice[i] = input.nextDouble();

            System.out.print("Enter the initial quantity: ");
            doseQuantity[i] = input.nextInt();

            System.out.println("--------------------------------------------");
        }

        input.nextLine();
        System.out.print("Are you sure you want to add new medicine? ('Y' for yes/'N' for no): " );
        option = input.next().charAt(0);
        try{
            Validation.validCharYN(option);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        String[] dosageFormArray = new String[]{"Oral capsule", "Oral tablet", "Oral liquid"};
        if (Character.toUpperCase(option) == 'Y'){
            ArrayList<Dosage> tempDosage = new ArrayList<>();
            for (int i = 0; i < doseFormQuantity; i++) {
                tempDosage.add(new Dosage(dosageFormArray[dosageType[i]], dosageForm[i], doseQuantity[i], doseCost[i], dosePrice[i]));
            }
            String id = "M" + String.format("%03d", medicineStock.getNumberOfEntries()+1);
            medicineStock.add(new Medicine(id ,mediName, tempDosage, mediFunc));

            System.out.println("Add new medicine successfully!!!");
        }
        else {
            System.out.println("You have cancelled the add new medicine process");
        }
        input.nextLine();
        System.out.println("Press Enter key to go back to medicine management menu...");
        input.nextLine();
    }

    public void updateCurrentMedicine() {
        int num;
        double tempCost = 0, tempPrice = 0;
        char option;
        printMedicineList();
        System.out.print("Enter the number of medicine that you want to update: ");
        num = input.nextInt();

        int index = 0, medicineNo = 0, dosageNo = 0;
        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                index++;
                if (index == num) {
                    medicineNo = j;
                    dosageNo = k;
                }
            }
        }

        //Only can update medicine -> dosage ->, dosageCost, dosagePrice,
        System.out.println("--------------------------------------------");
        System.out.println("[1] Dosage Cost");
        System.out.println("[2] Dosage Price");
        System.out.print("Enter the item that you want to update: ");
        num = input.nextInt();

        System.out.println("Medicine: " + medicineStock.getEntry(medicineNo).getName() + " - " +
                medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDosageForm() + " (" +
                medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDose() + ")");
        switch (num) {
            case 1 -> {
                System.out.println("Original dosage cost: RM" + medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDosageCost());
                System.out.print("Enter the updated dosage cost: RM");
                tempCost = input.nextDouble();
            }
            case 2 -> {
                System.out.println("Original dosage price: RM" + medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDosagePrice());
                System.out.print("Enter the updated dosage price: RM");
                tempPrice = input.nextDouble();
            }
            default -> {
            }
        }

        System.out.println("Are you sure you want to update the medicine details? ('Y' for yes/'N' for no): ");
        option = input.next().charAt(0);
        try{
            Validation.validCharYN(option);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (Character.toUpperCase(option) == 'Y'){
            switch (num) {
                case 1 -> medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).setDosageCost(tempCost);
                case 2 -> medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).setDosagePrice(tempPrice);
                default -> {
                }
            }
            System.out.println("Update medicine details successfully!!!");
        }
        else {
            System.out.println("You have cancelled the update medicine details process");
        }
        input.nextLine();
        System.out.println("Press Enter key to go back to medicine management menu...");
        input.nextLine();
    }

    public void deleteMedicine()  {
        int num, option;
        char confirm;
        printMedicineList();
        System.out.print("Enter the number of medicine that you want to delete: ");
        num = input.nextInt();

        int index = 0, medicineNo = 0, dosageNo = 0;
        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                index++;
                if (index == num) {
                    medicineNo = j;
                    dosageNo = k;
                }
            }
        }

        System.out.println("[1] Delete whole medicine");
        System.out.println("[2] Delete the selected medicine dosage form");
        System.out.print("Enter your option: ");
        option = input.nextInt();

        switch (option) {
            case 1 -> System.out.print("Are you sure you want to delete the medicine " +
                    medicineStock.getEntry(medicineNo).getName() +
                    "? ('Y' for yes/'N' for no): ");
            case 2 -> System.out.print("Are you sure you want to delete the medicine " +
                    medicineStock.getEntry(medicineNo).getName() +
                    " with the dosage type - " +
                    medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDosageForm() +
                    " (" + medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDose() + ")" +
                    "? ('Y' for yes/'N' for no): ");
        }

        confirm = input.next().charAt(0);
        try{
            Validation.validCharYN(confirm);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (Character.toUpperCase(option) == 'Y'){
            switch (option){
                case 1:
                    medicineStock.remove(medicineNo);
                    break;
                case 2:
                    if (medicineStock.getEntry(medicineNo).getDosage().getNumberOfEntries() == 1){
                        medicineStock.remove(medicineNo);
                    }
                    else{
                        medicineStock.getEntry(medicineNo).getDosage().remove(dosageNo);
                    }
                    break;
                default:
                    break;
            }
            System.out.println("Delete medicine successfully!!!");
        }
        else {
            System.out.println("You have cancelled the update medicine details process");
        }
        input.nextLine();
        System.out.println("Press Enter key to go back to medicine management menu...");
        input.nextLine();
    }

    public void viewSummaryReport()  {
        double totalProfit = 0;
        System.out.println("=".repeat(118));
        System.out.printf("%70s\n", "Medicine Summary Report");
        System.out.println("=".repeat(118));
        System.out.printf("%58s %13s %9s\n", "On Hand", "Allocated", "Restock");
        System.out.printf("%-5s %-15s %-15s %10s %10s %11s %11s %10s %10s %11s\n", "ID", "Name", "Dosage Form", "Dose", "Quantity", "Quantity", "Quantity","Cost(RM）", "Price(RM)", "Profit(RM)");
        System.out.println("-".repeat(118));
        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                double profit = (medicineStock.getEntry(j).getDosage().getEntry(k).getDosagePrice() - medicineStock.getEntry(j).getDosage().getEntry(k).getDosageCost())
                        * medicineStock.getEntry(j).getDosage().getEntry(k).getRecord().getAllocateQuantity();
                totalProfit += profit;
                System.out.printf("%-5s %-15s %-15s %10s %8d %10d %10d %13.2f %10.2f %11.2f\n",
                        medicineStock.getEntry(j).getId(),
                        medicineStock.getEntry(j).getName(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageForm(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDose(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageQuantity(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getRecord().getAllocateQuantity(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getRecord().getRestockQuantity(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageCost(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosagePrice(),
                        profit
                );
            }
            System.out.println("-".repeat(118));
        }
        System.out.println(Font.useFont(Font.BOLD_BLUE, String.format("%106s %9.2f", "Total Profit(RM): ", totalProfit)));
        System.out.println("=".repeat(118));

        System.out.println("[1] Sort by medicine ID");
        System.out.println("[2] Sort by medicine name");
        System.out.println("[3] Sort by on hand quantity");
        System.out.println("[4] Sort by allocated quantity");
        System.out.println("[5] Sort by restock quantity");
        System.out.println("[0] Exit");
        System.out.print("Enter your option: ");
        int option = input.nextInt();


        switch (option) {
            case 1 -> {
                ArrayList.bubbleSort(medicineStock, medicineStock.getNumberOfEntries(), 1);
                viewSummaryReport();
            }
            case 2 -> {
                ArrayList.bubbleSort(medicineStock, medicineStock.getNumberOfEntries(), 2);
                viewSummaryReport();
            }
            case 3 -> {
                for (int index = 1; index <= medicineStock.getNumberOfEntries(); index++) {
                    ArrayList.bubbleSort(medicineStock.getEntry(index).getDosage(), medicineStock.getEntry(index).getDosage().getNumberOfEntries(), 3);
                }
                viewSummaryReport();
            }
            case 4 -> {
                for (int index = 1; index <= medicineStock.getNumberOfEntries(); index++) {
                    ArrayList.bubbleSort(medicineStock.getEntry(index).getDosage(), medicineStock.getEntry(index).getDosage().getNumberOfEntries(), 4);
                }
                viewSummaryReport();
            }
            case 5 -> {
                for (int index = 1; index <= medicineStock.getNumberOfEntries(); index++) {
                    ArrayList.bubbleSort(medicineStock.getEntry(index).getDosage(), medicineStock.getEntry(index).getDosage().getNumberOfEntries(), 5);
                }
                viewSummaryReport();
            }
            default -> Driver.pharmacistMenu();
        }
    }

    public static void allocateMedicine(Patient patient, MedicalRecord record){
        DoctorOperation.displayPatientRecord(patient, record);
        System.out.println("Press y if u want allocate the medicine: ");
        Character inputCharacter = Character.toUpperCase(input.next().charAt(0));
        if(inputCharacter == 'Y'){
            reduceMedicineStock(patient,record);
        }

    }

    public static void reduceMedicineStock(Patient patient,MedicalRecord record){
        ListInterface<Medicine> medicineStock = PharmacistOperation.getMedicineStock();
        ListInterface<Medicine> patientStock = record.getMedicineCart();
        for(int i = 1;i <= patientStock.getNumberOfEntries();i++){
            Medicine medicinePatient = patientStock.getEntry(i);
            for (int j = 1;i <= medicineStock.getNumberOfEntries();j++){
                Medicine medicineClinic = medicineStock.getEntry(j);
                //Check same name of medicine
                if(medicinePatient.getName().equals(medicineClinic.getName())){
                    Dosage dosagePatient = medicinePatient.getDosage().getEntry(1);
                    ListInterface<Dosage> dosageClinicList = medicineStock.getEntry(j).getDosage();
                    //Check Same name of dosage
                    for (int k = 1;k <= dosageClinicList.getNumberOfEntries();k++){
                        Dosage dosageClinic = dosageClinicList.getEntry(k);
                        if(dosagePatient.getDosageForm().equals(dosageClinic.getDosageForm()) && dosagePatient.getDose().equals(dosageClinic.getDose())){
                            dosageClinic.reduceStock(dosagePatient.getDosageQuantity());
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }


    public static ListInterface<Medicine> getMedicineStock() {
        return medicineStock;
    }
}
