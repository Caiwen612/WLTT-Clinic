package client;

import adt.ListInterface;
import entity.Dosage;
import entity.Medicine;
import adt.ArrayList;
import java.util.Locale;
import adt.ArrayList;
import entity.Dosage;
import entity.Medicine;
import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Medicine> medicineStock = new ArrayList<>(10);;

    //Initialize medicine stock
    public static void initializeMedicineStock() {
        medicineStock.add(new Medicine("M001", "Acetaminophen", new ArrayList<Dosage>() {
            {
                add(new Dosage("Oral capsule", "325 mg", 20, 5, 20));
                add(new Dosage("Oral liquid", "160mg/5mL", 30, 3, 15));
                add(new Dosage("Oral tablet", "500 mg", 15, 8, 25));
            }
        }, "A pain reliever and fever reducer"));

        medicineStock.add(new Medicine("M002", "Oseltamivir", new ArrayList<Dosage>() {
            {
                add(new Dosage("Oral capsule", "300 mg", 20, 5, 15));
                add(new Dosage("Oral capsule", "450 mg", 8, 8, 20));
            }
        }, "An antiviral medication that blocks the actions of influenza virus types A and B in the body"));

        medicineStock.add(new Medicine("M003", "Loperamide", new ArrayList<Dosage>() {
            {
                add(new Dosage("Oral capsule", "300 mg", 18, 4, 15));
                add(new Dosage("Oral tablet", "450 mg", 5, 8, 20));
            }
        }, "Used to treat diarrhea or to reduce the amount of stool(poop) in people who have an ileosomy"));
    }


    public static void main(String[] args) {
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

        switch (option){
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

    public static void pharmacy(){
        int option = PharmacistMenu.menu();
        switch (option){
            case 1:
                medicineStockManagement();
        }
    }

    public static void medicineStockManagement(){
        int i = 1, option = 1;
        System.out.println("===========================================================");
        System.out.printf("%36s\n","Medicine List");
        System.out.println("===========================================================");
        System.out.printf("%-4s %-15s %-15s %10s %10s\n", "No", "Name", "Dosage Form", "Dose", "Quantity");
        System.out.println("-----------------------------------------------------------");
        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            for (int k = 1; k <= medicineStock.getEntry(j).getDosage().getNumberOfEntries(); k++) {
                System.out.printf("%-4s %-15s %-15s %10s %8s\n",i,
                        medicineStock.getEntry(j).getName(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageForm(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDose(),
                        medicineStock.getEntry(j).getDosage().getEntry(k).getDosageQuantity());
                i++;
            }
            System.out.println("-----------------------------------------------------------");
        }

        while(option != 0){
            System.out.println("[1] Search");
            System.out.println("[2] Sort");
            System.out.println("[0] Exit");
            System.out.print("Enter your option: ");
            option = input.nextInt();

            switch (option){
                case 1:
                    searchMedicineStock();
                    break;
                default:
                    break;
            }
        }
    }



    public static void searchMedicineStock(){
        String keywords = "";
        input.nextLine();
        System.out.print("Enter the keywords: ");
        keywords = input.nextLine();

        int i = 1;
        System.out.println("===========================================================");
        System.out.printf("%36s\n","Medicine List");
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
}
