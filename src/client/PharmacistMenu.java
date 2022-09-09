package client;

import entity.Pharmacist;
import entity.PharmacistOperation;
import utility.ValidationException;
import entity.PharmacistOperation;

import java.util.Scanner;

public class PharmacistMenu {

    private static PharmacistOperation pharmacistOperation = new PharmacistOperation();
    private static Scanner input = new Scanner(System.in);

    public static void menu() throws ValidationException {
        int option;
        System.out.println("Current patient: ");
        System.out.println("[1] Check Medicine Stock");
        System.out.println("[2] Medicine Management");
        System.out.println("[3] Record Medicine Allocation");
        System.out.println("[4] Next Customer");
        System.out.println("[5] Summary Report");
        System.out.println("[0] Exit");

        System.out.print("Enter your option: ");
        option = input.nextInt();

        while (option != 0) {
            switch (option) {
                case 1 -> medicineStockManagement();
                case 2 -> medicineManagement();
                case 5 -> pharmacistOperation.viewSummaryReport();
                default -> {
                }
            }
        }
    }

    public static void medicineStockManagement() throws ValidationException {
        int option = 1;
        pharmacistOperation.printMedicineStock();
        while (option != 0) {
            System.out.println("[1] Search");
            System.out.println("[2] Sort");
            System.out.println("[3] Restock");
            System.out.println("[0] Exit");
            System.out.print("Enter your option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    pharmacistOperation.searchMedicineStock();
                    break;
                case 2:
                    pharmacistOperation.sortMedicineStock();
                    break;
                case 3:
                    pharmacistOperation.addMedicineStock();
                default:
                    menu();
                    break;
            }
        }
    }

    public static void medicineManagement() throws ValidationException {
        int option = 1;
        while (option != 0) {
            pharmacistOperation.printMedicineList();
            System.out.println("[1] Add new medicine");
            System.out.println("[2] Update current medicine");
            System.out.println("[3] Delete old medicine");
            System.out.println("[0] Exit");
            System.out.print("Enter your option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    pharmacistOperation.addNewMedicine();
                    break;
                case 2:
                    pharmacistOperation.updateCurrentMedicine();
                    break;
                case 3:
                    pharmacistOperation.deleteMedicine();
                default:
                    menu();
                    break;
            }
        }
    }


}
