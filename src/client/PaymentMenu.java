package client;

import adt.ArrayList;
import adt.ArrayStack;
import adt.ListInterface;
import adt.StackInterface;
import entity.*;

import java.util.Scanner;

//import static client.Main.medicineStock;

public class PaymentMenu {

    private static Scanner input = new Scanner(System.in);
    private static ListInterface<Patient> Patient = new ArrayList<>();
    private static ListInterface<TestingPrescription> Prescription = new ArrayList<>();
    private static ListInterface<Invoice> invoiceList = new ArrayList<>();
    private static ListInterface<Medicine> Medicine = new ArrayList<>();
    private static PharmacistOperation pharmacy = new PharmacistOperation();
    private static Dosage dosage;

    private static StackInterface<Transaction> transactionHistory = new ArrayStack<>();
    private static StackInterface<Transaction> tempStack = new ArrayStack<>();

    public static void main(String[] args){

        Patient patient1 = new Patient("ALOHA", "010101011111", "0101010101", "010101", "010101");

        printInvoice();
    }

    public static void menu(){

        initializePrescriptionList();
        initializeTransactionHistory();

        System.out.println("[1] Print Invoice");
        System.out.println("[2] Transaction History");
        System.out.println("[3] Print Medical Certificate");
        System.out.println("[4] Payment");
        System.out.println("[5] Next Customer");
        System.out.println("[0] Back");

        System.out.println("Enter your option: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                printInvoice();
                break;
            case 2:
                transactionHistory();
                break;
            case 3:
                //MedicalCertificate();
                break;
            case 4:
                payment();
                break;
            case 5:
                //nextCustomer();
                break;
        }
    }

    public static void initializePrescriptionList() {

        Prescription.add(new TestingPrescription(1, 2, 2));
        Prescription.add(new TestingPrescription(2, 1, 1));
        Prescription.add(new TestingPrescription(3, 2, 3));


    }

    public static void initializeTransactionHistory(){

        transactionHistory.push(new Transaction("T0001", "01-01-2022", "10:20:59", "I0001", "Lee Chun Kai", "Cash", 50.00));
        transactionHistory.push(new Transaction("T0002", "03-04-2022", "09:00:00", "I0002", "Wilson Yau Kai Chun", "Cash", 190.00));
        transactionHistory.push(new Transaction("T0003", "21-06-2022", "14:59:11", "I0003", "Lan Ke En", "Credit Card", 200.00));

        System.out.println(transactionHistory.getNumberOfStack());
    }

    public static void printInvoice(){

        double price = 10.81;

        if ((price * 100)%100 > 90){
            System.out.println(            Math.round(price));
        }



        System.out.println("======================================================================================");
        System.out.println("                                      INVOICE                                         ");
        System.out.println("======================================================================================");

        System.out.println("Patient Name : ");
        System.out.println("Address      : ");
        System.out.println("Contact No   : ");
        System.out.println("I/C No       : ");

        System.out.println("\nPrinted Date : ");
        System.out.println("Invoice ID   : ");

        System.out.printf("\n%-2s %14s %14s %9s %13s %16s %12s\n",
                "No", "Medicine Name", "Dosage Form", "Dose", "Quantity", "Unit Price (RM)", "Amount (RM)");
        System.out.println("--------------------------------------------------------------------------------------");


        //Testing Purpose

/*        for (int i = 1; i < Prescription.getNumberOfEntries(); i++){
            System.out.printf("%-3d %-16s %-14s %-7s %7d %13.2f %14.2f",
                    Medicine.getEntry(Prescription.getEntry(i).getMedicineNo()).getName(),
                    Medicine.getEntry(Prescription.getEntry(i).getMedicineNo()).getDosage().getEntry(Prescription.getEntry(i).getDosageNo()).getDosageForm(),
                    Medicine.getEntry(Prescription.getEntry(i).getMedicineNo()).getDosage().getEntry(Prescription.getEntry(i).getDosageNo()).getDose(),
                    Prescription.getEntry(i).getQuantity(),
                    Medicine.getEntry(Prescription.getEntry(i).getMedicineNo()).getDosage().getEntry(Prescription.getEntry(i).getDosageNo()).getDosagePrice(),
                    Medicine.getEntry(Prescription.getEntry(i).getMedicineNo()).getDosage().getEntry(Prescription.getEntry(i).getDosageNo()).getDosagePrice() * Prescription.getEntry(i).getQuantity()
            );
        }*/

        System.out.printf("%-3d %-16s %-14s %-7s %7d %13.2f %14.2f", 1, "Acetaminophen", "Oral capsule", "160mg/5mL", 2, 10.0, 10.0);
        System.out.println("\n--------------------------------------------------------------------------------------");

        System.out.printf("%74s %7.2f", "Sub Total   : ", 1078.0);
        System.out.printf("\n%74s %7.2f", "Tax Rate (6%)   : ", 1078.0);
        System.out.printf("\n%74s %7.2f", "Grand Total   : ", 1078.0);

        int total = 0;

/*        for (int j = 1; j <= medicineStock.getNumberOfEntries(); j++) {
            System.out.printf(" %-4s %-15s %-15s %10s %6s %10.2f %15.2f\n",j,
                    medicineStock.getEntry(Prescription.getEntry(j).getMedicineNo()).getName(),
                    medicineStock.getEntry(Prescription.getEntry(j).getMedicineNo()).getDosage().getEntry(Prescription.getEntry(j).getDosageNo()).getDosageForm(),
                    medicineStock.getEntry(Prescription.getEntry(j).getMedicineNo()).getDosage().getEntry(Prescription.getEntry(j).getDosageNo()).getDose(),
                    Prescription.getEntry(j).getQuantity(),
                    medicineStock.getEntry(Prescription.getEntry(j).getMedicineNo()).getDosage().getEntry(Prescription.getEntry(j).getDosageNo()).getDosagePrice(),
                    medicineStock.getEntry(Prescription.getEntry(j).getMedicineNo()).getDosage().getEntry(Prescription.getEntry(j).getDosageNo()).getDosagePrice() * Prescription.getEntry(j).getQuantity()

            );

            System.out.println("--------------------------------------------------------------------------------------");

//            total += medicineStock.getEntry(prescriptionList.getEntry(j).getMedicineNo()).getDosage().getEntry(prescriptionList.getEntry(j).getDosageNo()).getDosagePrice();

        }*/

    }

    public static void payment() {

        printInvoice();

        System.out.println("Pay with");
        System.out.println("[1] Credit Card");
        System.out.println("[2] Cash");
        System.out.println("\n[0] Back");
        System.out.print("Enter your option: ");
        int option = input.nextInt();

        switch (option){
            case 1:
                creditCard();
                break;
            case 2:
                paymentAmount();
                break;
            default:
                break;
        }

    }

    public static void creditCard(){

        System.out.print("Enter card number: ");
        String cardNumber = input.next();

        System.out.print("Enter expiry date: ");
        String expiryDate = input.next();

        System.out.print("Enter CVV: ");
        String cvv = input.next();

        paymentAmount();
    }

    public static void paymentAmount(){

        boolean success = false;

        double amount = 10; //testing, should be getTotal

        while(!success){
            System.out.print("\nEnter Amount: RM");
            double paid = input.nextDouble();
            amount -= paid;

            if (paid < amount) {
                success = false;
                System.out.printf("\nNot enough credit. Remaining: RM%.2f", (amount));
            }
            else {

                if (amount < 0) {
                    System.out.printf("\nRM%.2f will be refund.", Math.abs(amount));
                }
                success = true;
            }

        }

    }

    public static void transactionHistory(){

        System.out.printf("%-8s %-12s %-12s %-12s %-20s %-13s %-15s\n", "Item#", "Date", "Time", "Invoice ID", "Payer Name", "Method", "Amount (RM)");

        System.out.println("-".repeat(95));

        while (transactionHistory.isEmpty() == false)
        {
            tempStack.push(transactionHistory.peek());
            transactionHistory.pop();
        }

        while (tempStack.isEmpty() == false)
        {
            Transaction transaction = tempStack.peek();
            System.out.printf("%-8s %-12s %-14s %-10s %-20s %-13s %8.2f\n",
                    transaction.getTransactionNo(),
                    transaction.getDate(),
                    transaction.getTime(),
                    transaction.getInvoiceID(),
                    transaction.getPayerName(),
                    transaction.getMethod(),
                    transaction.getAmount());
            System.out.println("-".repeat(95));

            tempStack.pop();

            // To restore contents of
            // the original stack.
            transactionHistory.push(transaction);
        }

    }

}
