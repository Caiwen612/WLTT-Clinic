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

    private static ListInterface<TestingPrescription> Prescription = new ArrayList<>();
    private static PharmacistOperation pharmacy = new PharmacistOperation();
    private static Dosage dosage;
    private static PharmacistOperation pharmacistOperation = new PharmacistOperation();

    private static ArrayList<Medicine> medicineStock = new ArrayList<>();
    private static ArrayList<Patient> Patient = new ArrayList<>();

    private static ArrayList<PrescriptionListMedicine> medicineList = new ArrayList<>();

    private static StackInterface<Transaction> transactionHistory = new ArrayStack<>();
    private static StackInterface<Transaction> tempStack = new ArrayStack<>();
    private static StackInterface<Invoice> invoiceList = new ArrayStack<>();

    public static void main(String[] args){

        menu();

    }

    public static void menu(){

        initializeTransactionHistory();
        initializeMedicine();
        initializePatient();

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

//    public static void initializePrescriptionList() {
//
//        Prescription.add(new TestingPrescription(1, 2, 2));
//        Prescription.add(new TestingPrescription(2, 1, 1));
//        Prescription.add(new TestingPrescription(3, 2, 3));
//
//
//    }

    public static void initializeTransactionHistory(){

        transactionHistory.push(new Transaction("T0001", "01-01-2022", "10:20:59", "I0001", "Lee Chun Kai", "Cash", 50.00));
        transactionHistory.push(new Transaction("T0002", "03-04-2022", "09:00:00", "I0002", "Wilson Yau Kai Chun", "Cash", 190.00));
        transactionHistory.push(new Transaction("T0003", "21-06-2022", "14:59:11", "I0003", "Lan Ke En", "Credit Card", 200.00));

        System.out.println(transactionHistory.getNumberOfStack());
    }

    public static void initializeMedicine(){
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

    public static void initializePatient() {

        TestingPrescription prescription1 = new TestingPrescription(1, 2);
        TestingPrescription prescription2 = new TestingPrescription(5, 2);
        TestingPrescription prescription3 = new TestingPrescription(3, 1);

        Prescription.add(prescription1);
        Prescription.add(prescription2);
        Prescription.add(prescription3);

        Patient patient = new Patient("ALOHA", "010101011111", "0101010101", "010101", "010101");

        Patient.add(patient);

    }

    public static void printInvoice(){

        String id = "I" + String.format("%04d", (invoiceList.getNumberOfStack() + 1));

        Patient patient = Patient.getEntry(1); //Should be customerNo

        for (int i = 0; i < Prescription.getNumberOfEntries(); i++){
            int index = 0;
            int qty = Prescription.getEntry(i + 1).getQuantity();
            for (int j = 0; j < medicineStock.getNumberOfEntries(); j++){
                for (int k = 0; k < medicineStock.getEntry(j + 1).getDosage().getNumberOfEntries(); k++){
                    index++;
                    if (Prescription.getEntry(i + 1).getMedicineNo() == index){

                        Medicine medicine = medicineStock.getEntry(j + 1);
                        Dosage dosage = medicineStock.getEntry(j + 1).getDosage().getEntry(k + 1);

                        medicineList.add(new PrescriptionListMedicine(medicine, dosage, qty));

                    }
                }
            }
        }

        Invoice invoice = new Invoice(id, patient, medicineList);


        invoiceList.push(invoice);


        System.out.println("======================================================================================");
        System.out.println("                                      INVOICE                                         ");
        System.out.println("======================================================================================");

        System.out.println("Patient Name : " + Patient.getEntry(1).getPatientName());
        System.out.println("Address      : " + Patient.getEntry(1).getAddress());
        System.out.println("Contact No   : " + Patient.getEntry(1).getPhoneNo());
        System.out.println("I/C No       : " + Patient.getEntry(1).getIcNo());

        System.out.println("\nPrinted Date : " + invoice.getDate());
        System.out.println("Invoice ID   : " + invoice.getId());

        System.out.printf("\n%-2s %14s %14s %7s %15s %16s %12s\n",
                "No", "Medicine Name", "Dosage Form", "Dose", "Quantity", "Unit Price (RM)", "Amount (RM)");
        System.out.println("--------------------------------------------------------------------------------------");


        double subTotal = 0.0;

        for (int i = 0; i < invoiceList.peek().getMedicineList().getNumberOfEntries(); i++){
            System.out.printf("%-3d %-16s %-14s %-7s %9d %13.2f %14.2f\n", i + 1,
                    invoiceList.peek().getMedicineList().getEntry(i + 1).getMedicine().getName(),
                    invoiceList.peek().getMedicineList().getEntry(i + 1).getDosage().getDosageForm(),
                    invoiceList.peek().getMedicineList().getEntry(i + 1).getDosage().getDose(),
                    invoiceList.peek().getMedicineList().getEntry(i + 1).getQuantity(),
                    invoiceList.peek().getMedicineList().getEntry(i + 1).getDosage().getDosagePrice(),

                    invoiceList.peek().getMedicineList().getEntry(i + 1).getAmount());

            subTotal += invoiceList.peek().getMedicineList().getEntry(i + 1).getAmount();

            System.out.println("--------------------------------------------------------------------------------------");

        }


        invoice.setSubTotal(subTotal);

        System.out.printf("%74s %7.2f", "Sub Total   : ", subTotal);
        System.out.printf("\n%74s %7.2f", "Tax Rate (6%)   : ", invoice.calcServiceRate());
        System.out.printf("\n%74s %7.2f", "Grand Total   : ", invoice.getTotal());

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

        System.out.println("-----------------------------------------------------------------------------------------------");

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
            System.out.println("-----------------------------------------------------------------------------------------------");

            tempStack.pop();

            // To restore contents of
            // the original stack.
            transactionHistory.push(transaction);
        }

    }



}
