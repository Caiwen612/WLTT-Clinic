package client;

import adt.ListInterface;
import entity.Dosage;
import entity.Medicine;
import adt.ArrayList;
import org.w3c.dom.ls.LSOutput;
import sorting.SortArrayList;
import java.util.Locale;
import entity.Dosage;
import entity.Medicine;
import java.util.Scanner;
import utility.Font;
import utility.Validation;
import utility.ValidationException;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Medicine> medicineStock = new ArrayList<>(10);
    ;

    //Initialize medicine stock
    public static void initializeMedicineStock() {
        medicineStock.add(new Medicine("M001", "Acetaminophen", new ArrayList<Dosage>() {
            {
                add(new Dosage("Oral capsule", "325mg", 40, 5, 20));
                add(new Dosage("Oral liquid", "160mg/5mL", 30, 3, 15));
                add(new Dosage("Oral tablet", "500mg", 15, 8, 25));
            }
        }, "A pain reliever and fever reducer"));

        medicineStock.add(new Medicine("M002", "Oseltamivir", new ArrayList<Dosage>() {
            {
                add(new Dosage("Oral capsule", "300mg", 20, 5, 15));
                add(new Dosage("Oral capsule", "450mg", 40, 8, 20));
                add(new Dosage("Oral capsule", "600mg", 34, 10, 25));
            }
        }, "An antiviral medication that blocks the actions of influenza virus types A and B in the body"));

        medicineStock.add(new Medicine("M003", "Loperamide", new ArrayList<Dosage>() {
            {
                add(new Dosage("Oral capsule", "300mg", 18, 4, 15));
                add(new Dosage("Oral tablet", "450mg", 5, 8, 20));
            }
        }, "Used to treat diarrhea or to reduce the amount of stool(poop) in people who have an ileosomy"));
    }


    public static void main(String[] args) throws ValidationException {
        initializeMedicineStock();
        //ArrayList<Medicine> temp  = medicineStock.doubleArrayList();
        System.out.println("1002 CW Room 1\n");
        System.out.println("WLTT Clinic");
        System.out.println("[1] Counter");
        System.out.println("[2] Doctor");
        System.out.println("[3] Pharmacy");
        System.out.println("[4] Payment");
        System.out.println("[5] Shut down");

        System.out.print("Enter your option:");

        int option = input.nextInt();

        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                pharmacy();
                break;
            case 4:
                break;
            case 5:

                break;

        }

    }

    public static void pharmacy() throws ValidationException {
        int option = PharmacistMenu.menu();
        while (option != 0) {
            switch (option) {
                case 1:
                    medicineStockManagement();
                    break;
                case 2:
                    medicineManagement();
                    break;
                default:
                    break;
            }
        }
    }

    public static void medicineStockManagement() throws ValidationException {
        int option = 1;
        printMedicineStock();
        while (option != 0) {
            System.out.println("[1] Search");
            System.out.println("[2] Sort");
            System.out.println("[3] Restock");
            System.out.println("[0] Exit");
            System.out.print("Enter your option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    searchMedicineStock();
                    break;
                case 2:
                    sortMedicineStock();
                    break;
                case 3:
                    addMedicineStock();
                default:
                    pharmacy();
                    break;
            }
        }
    }

    public static void printMedicineStock() {
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

    public static void searchMedicineStock() {
        String keywords = "";
        input.nextLine();
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

    public static void sortMedicineStock() {
        /*ArrayList<Medicine> temp = medicineStock.copyArray();*/
        ArrayList<Medicine> temp = new ArrayList<Medicine>();
        for (int index = 1; index <= medicineStock.getNumberOfEntries(); index++) {
            temp.add(medicineStock.getEntry(index));
        }
        for (int index = 1; index <= temp.getNumberOfEntries(); index++) {
            SortArrayList.bubbleSort(temp.getEntry(index).getDosage(), temp.getNumberOfEntries());
        }

        printMedicineStock();

    }

    public static void addMedicineStock() {
        int num, quantity;
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

        medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).setDosageQuantity(quantity +
                medicineStock.getEntry(medicineNo).getDosage().getEntry(dosageNo).getDosageQuantity());

        System.out.println("Restock successfully.");
        System.out.println("Press Enter key to exit to medicine menu...");
        input.nextLine();
        input.nextLine();
    }

    public static void medicineManagement() throws ValidationException {
        int option = 1;
        printMedicineStock();
        while (option != 0) {
            System.out.println("[1] Add new medicine");
            System.out.println("[2] Update current medicine");
            System.out.println("[3] Delete old medicine");
            System.out.println("[0] Exit");
            System.out.print("Enter your option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    addNewMedicine();
                    break;
                case 2:
                    updateCurrentMedicine();
                    break;
                case 3:
                    addMedicineStock();
                default:
                    pharmacy();
                    break;
            }
        }
    }

    public static void addNewMedicine() throws ValidationException {
        char option;
        String mediName, mediFunc, dose;
        int quantity, doseFormQuantity = 0;

        System.out.print("Enter the name of new medicine: ");
        mediName = input.nextLine();

        System.out.print("Enter the function of the medicine: ");
        mediFunc = input.nextLine();

        System.out.print("Enter the number of dosage form: ");
        doseFormQuantity = input.nextInt();


        double[] doseCost = new double[doseFormQuantity], dosePrice = new double[doseFormQuantity];
        int[] dosageType = new int[doseFormQuantity], doseQuantity = new int[doseFormQuantity];
        String[] dosageForm = new String[doseFormQuantity];

        System.out.println("--------------------------------------------");
        for (int i = 0; i < doseFormQuantity; i++) {
            System.out.println("Dosage form " + i);
            System.out.println("[1] Oral capsule");
            System.out.println("[2] Oral tablet");
            System.out.println("[3] Oral liquid");
            System.out.print("Enter the dosage form: ");
            dosageType[i] = input.nextInt();

            input.nextLine();
            System.out.print("Enter the dose(e.g. 500mg, 150mg/mL): ");
            dosageForm[i] = input.nextLine();

            System.out.println("Enter the dosage cost: RM");
            doseCost[i] = input.nextDouble();

            System.out.println("Enter the dosage price: RM");
            dosePrice[i] = input.nextDouble();

            System.out.println("Enter the initial quantity: ");
            doseQuantity[i] = input.nextInt();

            input.nextLine();
            System.out.println("--------------------------------------------");
        }

        System.out.print("Are you confirm with the new medicine details? ('Y' for yes/'N' for no): " );
        option = input.next().charAt(0);
        Validation.validCharYN(option);

        String[] dosageFormArray = new String[]{"Oral capsule", "Oral tablet", "Oral liquid"};
        if (option == 'Y'){
            ArrayList<Dosage> tempDosage = new ArrayList<Dosage>();
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
        System.out.println("Press Enter key to go back to medicine management menu...");
        input.nextLine();
    }

    public static void updateCurrentMedicine(){
        int num, quantity;
        printMedicineStock();
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

        System.out.println("");



    }

    //invoice
    public static void payment () {
        int option = PaymentMenu.menu();
        switch (option) {
            case 1:

        }
    }
}



