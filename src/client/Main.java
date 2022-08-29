package client;

import entity.Dosage;
import entity.Medicine;
import entity.Pharmacist;
import ADT.ArrayList;
//import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Medicine> medicineStock = new ArrayList<>(10);;

    //Initialize medicine stock
    public void initializeMedicineStock(){
        medicineStock.add(new Medicine("M001", "Acetaminophen", new Dosage[]{
                new Dosage("Oral capsule", "325 mg",20,5,20),
                new Dosage("Oral liquid", "160mg/5mL", 30, 3,15),
                new Dosage("Oral tablet", "500 mg",15,8,25)}, "A pain reliever and fever reducer"));
        medicineStock.add(new Medicine("M002", "Oseltamivir", new Dosage[]{
                new Dosage("Oral capsule", "300 mg", 20, 5, 15),
                new Dosage("Oral capsule", "450 mg",8,8,20)}, "An antiviral medication that blocks the actions of influenza virus types A and B in the body"

        ));
        medicineStock.add(new Medicine("M003", "Loperamide", new Dosage[]{
                new Dosage("Oral capsule", "300 mg", 18, 4, 15),
                new Dosage("Oral tablet", "450 mg",5,8,20)}, "Used to treat diarrhea or to reduce the amount of stool(poop) in people who have an ileosomy"

        ));

    }

    public static void main(String[] args) {
        System.out.println("1002 CW Room 1\n");
        System.out.println("WLTT Clinic");
        System.out.println("[1] Counter");
        System.out.println("[2] Doctor");
        System.out.println("[3] Pharmacy");
        System.out.println("[4] Payment");
        System.out.println("[5] Shut down");

        System.out.println("Enter your option:");

        int option = input.nextInt();

        switch (option){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                pharmacy();
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
        int i = 1;
        System.out.println("Medicine List\n\n");
        System.out.printf("%4s %10s %12s %8s %10s\n", "No", "Name", "Dosage Form", "Dose", "Quantity");
        for (Medicine medicine: medicineStock
             ) {
            System.out.printf("%4s %10s %12s %8s %10s\n", medicine.getId(), medicine.getName(), medicineStock.);
        }

    }
}
