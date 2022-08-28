package client;

import java.util.Scanner;

public class PharmacistMenu {

    static Scanner input = new Scanner(System.in);

    public static int menu(){
        System.out.println("Current patient: ");
        System.out.println("[1] Check Medical Stock");
        System.out.println("[2] Add Stock");
        System.out.println("[3] Record Medicine Allocation");
        System.out.println("[4] Next Customer");
        System.out.println("[5] Summary Report");

        System.out.println("Enter your option: ");
        int option = input.nextInt();

        return option;

    }
    public static void stockManagement(){
        System.out.println("Medicine List");
        System.out.println("ID Name DosageForm   Quantity");

    }
}
