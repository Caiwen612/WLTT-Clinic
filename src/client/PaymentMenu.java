package client;

import java.util.Scanner;

public class PaymentMenu {

    private static Scanner input = new Scanner(System.in);

    public static int menu(){
        System.out.println("[1] Print Invoice");
        System.out.println("[2] Transaction History");
        System.out.println("[3] Print Medical Certificate");
        System.out.println("[4] Next Customer");
        System.out.println("[0] Back");

        System.out.println("Enter your option: ");
        int option = input.nextInt();

        return option;
    }

}
