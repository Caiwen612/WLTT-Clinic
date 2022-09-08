package client;

import entity.PharmacistOperation;
import utility.ValidationException;

import java.util.Scanner;
import client.PharmacistMenu;
import entity.PharmacistOperation;

public class Main {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws ValidationException {
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
                PharmacistMenu.menu();
                break;
            case 4:
                break;
            case 5:

                break;

        }

    }

}
