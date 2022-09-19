package client;

import adt.ArrayList;
import adt.ArrayStack;
import adt.StackInterface;
import entity.*;

import java.util.Scanner;

//import static client.Main.medicineStock;

public class PaymentMenu {

    private static Scanner input = new Scanner(System.in);

    private static ArrayList<Medicine> medicineStock = new ArrayList<>();
    private static ArrayList<Patient> Patient = new ArrayList<>();
    private static Transaction[] transactionsArray = new Transaction[100];
    private static int transactionNum = 0;

    //Payment Module
    private static StackInterface<Transaction> transactionHistory = new ArrayStack<>();
    private static StackInterface<Transaction> tempStack = new ArrayStack<>();
    private static ArrayList<PrescriptionList> prescriptionList = new ArrayList<>();

    private static Invoice invoice;
    private static Payment payer;
    private static int patientNo = 1;

    public static void main(String[] args){

        menu();

    }

    public static void menu(){

        initializeMedicine();
        initializeTransactionHistory();
        initializePatient();

        int option = 1;

        while (option != 0){
            System.out.println("\n\n\n[1] Print Invoice");
            System.out.println("[2] Transaction History");
            System.out.println("[3] Print Medical Certificate");
            System.out.println("[4] Proceed Payment");
            System.out.println("[5] Next Customer");
            System.out.println("[0] Back");

            System.out.print("Enter your option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    printInvoice();
                    break;
                case 2:
                    transactionMenu();
                    break;
                case 3:
                    //MedicalCertificate();
                    //Waiting CB
                    break;
                case 4:
                    payment();
                    break;
                case 5:
                    //nextCustomer();
                    break;
            }
        }

    }

    //Transaction Menu (4 Function)
    public static void transactionMenu() {

        if (transactionHistory.peek() != null){

            transactionHistory();

            System.out.println("[1] View Latest Medicine Transaction");
            System.out.println("[2] Search Transaction");
            System.out.println("[3] Sort By Date");
            System.out.println("[4] Summary Report");
//            System.out.println("[4] View Ascending");
//            System.out.println("[5] View Descending");
            System.out.println("[0] Back");

            System.out.print("\nEnter your option: ");
            int option = input.nextInt();

            switch (option){
                case 1:
                    latestMedicineTransaction();
                    break;
                case 2:
                    searchTransaction();
                    break;
                case 3: //viewOldest to newest
                    viewSortByDate();
                    break;
                case 4:
                    viewSummaryReport();
                    break;
            }
        }
        else
        {
            System.out.println("\n\n\nThere is no transaction history exist currently!!");
        }

    }

    public static void searchTransaction(){

        int idSearch;

        System.out.print("\n\nEnter the No.: ");
        idSearch = input.nextInt();

        Transaction transactionSearched = new Transaction();

        for (int i = 1; i <= idSearch; i++) {

            if (i == idSearch){
                transactionSearched = transactionHistory.peek();
            }

            tempStack.push(transactionHistory.peek());

            transactionHistory.pop();
        }

        while (tempStack.isEmpty() == false && tempStack.peek() != null) {
            Transaction transaction = tempStack.peek();

            tempStack.pop();

            // To restore contents of the original stack.
            transactionHistory.push(transaction);
        }

        System.out.println("\n\nTransaction ID: " + transactionSearched.getTransactionID());
        System.out.println("Date          : " + transactionSearched.getPayDate());
        System.out.println("Time          : " + transactionSearched.getPayTime());
        System.out.println("Invoice ID    : " + transactionSearched.getInvoice().getInvoiceID());
        System.out.println("Patient Name  : " + transactionSearched.getInvoice().getPatient().getPatientName());
        System.out.println("Payer Name    : " + transactionSearched.getPayment().getPayerName());
        System.out.println("Method        : " + transactionSearched.getMethod());
        System.out.printf("Amount Paid   : RM%.2f\n", transactionSearched.getPayment().getAmountPaid());

        System.out.printf("\n%-2s %14s %14s %7s %15s %16s %13s\n",
                "No", "Medicine Name", "Dosage Form", "Dose", "Quantity", "Unit Price (RM)", "Amount (RM)");
        System.out.println("---------------------------------------------------------------------------------------");

        for (int i = 0; i < transactionSearched.getInvoice().getPrescriptionList().getNumberOfEntries(); i++){// 3, 2
            System.out.printf("%-3d %-16s %-14s %-7s %9d %13.2f %15.2f\n", i + 1,
                    transactionSearched.getInvoice().getPrescriptionList().getEntry(i + 1).getMedicine().getName(),
                    transactionSearched.getInvoice().getPrescriptionList().getEntry(i + 1).getDosage().getDosageForm(),
                    transactionSearched.getInvoice().getPrescriptionList().getEntry(i + 1).getDosage().getDose(),
                    transactionSearched.getInvoice().getPrescriptionList().getEntry(i + 1).getQuantity(),
                    transactionSearched.getInvoice().getPrescriptionList().getEntry(i + 1).getDosage().getDosagePrice(),
                    transactionSearched.getInvoice().getAmount(i)
            );

            System.out.println("---------------------------------------------------------------------------------------");

        }

        System.out.printf("%75s %7.2f", "Sub Total   : ", transactionSearched.getInvoice().getSubTotal());
        System.out.printf("\n%75s %7.2f", "Tax Rate (6%)   : ", transactionSearched.getInvoice().calcTaxRate());
        System.out.printf("\n%75s %7.2f", "Grand Total   : ", transactionSearched.getInvoice().getTotal());

    }

    public static void viewSortByDate(){
        System.out.printf("\n\n%-3s %-8s %-12s %-12s %-12s %-20s %-13s %-15s\n", "No.", "Item#", "Date", "Time", "Invoice ID", "Payer Name", "Method", "Amount (RM)");

        System.out.println("--------------------------------------------------------------------------------------------------");


        while (transactionHistory.isEmpty() == false && transactionHistory.peek() != null) {
            tempStack.push(transactionHistory.peek());

            transactionHistory.pop();
        }

        int index = 0;

        while (tempStack.isEmpty() == false && tempStack.peek() != null) {
            index++;
            Transaction transaction = tempStack.peek();
            System.out.printf("%-3d %-8s %-12s %-14s %-10s %-20s %-13s %8.2f\n", index,
                    transaction.getTransactionID(),
                    transaction.getPayDate(),
                    transaction.getPayTime(),
                    transaction.getInvoice().getInvoiceID(),
                    transaction.getPayment().getPayerName(),
                    transaction.getMethod(),
                    transaction.getPayment().getAmountPaid());
            System.out.println("--------------------------------------------------------------------------------------------------");

            tempStack.pop();

            // To restore contents of the original stack.
            transactionHistory.push(transaction);
        }
    }

    //Bill List - Patient
    public static void printInvoice(){

        if (Patient.getEntry(patientNo) == null){
            System.out.println("\n\n\nThere is no invoice exist currently!!");
        }
        else {

            System.out.println("\n\n======================================================================================");
            System.out.println("                                      INVOICE                                         ");
            System.out.println("======================================================================================");

            System.out.println("Patient Name : " + Patient.getEntry(patientNo).getPatientName());
            System.out.println("Address      : " + Patient.getEntry(patientNo).getAddress());
            System.out.println("Contact No   : " + Patient.getEntry(patientNo).getPhoneNo());
            System.out.println("I/C No       : " + Patient.getEntry(patientNo).getIcNo());

            System.out.println("\nPrinted Date : " + invoice.getPrintDate());
            System.out.println("Printed Time : " + invoice.getPrintTime());
            System.out.println("Invoice ID   : " + invoice.getInvoiceID());

            System.out.printf("\n%-2s %14s %14s %7s %15s %16s %12s\n",
                    "No", "Medicine Name", "Dosage Form", "Dose", "Quantity", "Unit Price (RM)", "Amount (RM)");
            System.out.println("--------------------------------------------------------------------------------------");

            double subTotal = 0.0;

            for (int i = 0; i < invoice.getPrescriptionList().getNumberOfEntries(); i++) {
                System.out.printf("%-3d %-16s %-14s %-7s %9d %13.2f %14.2f\n", i + 1,
                        invoice.getPrescriptionList().getEntry(i + 1).getMedicine().getName(),
                        invoice.getPrescriptionList().getEntry(i + 1).getDosage().getDosageForm(),
                        invoice.getPrescriptionList().getEntry(i + 1).getDosage().getDose(),
                        invoice.getPrescriptionList().getEntry(i + 1).getQuantity(),
                        invoice.getPrescriptionList().getEntry(i + 1).getDosage().getDosagePrice(),
                        invoice.getAmount(i));

                subTotal += invoice.getAmount(i);

                System.out.println("--------------------------------------------------------------------------------------");

            }


            invoice.setSubTotal(subTotal);

            System.out.printf("%74s %7.2f", "Sub Total   : ", subTotal);
            System.out.printf("\n%74s %7.2f", "Tax Rate (6%)   : ", invoice.calcTaxRate());
            System.out.printf("\n%74s %7.2f", "Grand Total   : ", invoice.getTotal());
        }
    }

    //Proceed to payment (Menu -> Payment)
    public static void payment() {

        printInvoice();

        if (Patient.getEntry(patientNo) != null) {
            System.out.println("\n\nPay with");
            System.out.println("[1] Credit Card");
            System.out.println("[2] Cash");
            System.out.println("\n[0] Back");
            System.out.print("Enter your option: ");
            int option = input.nextInt();

            paymentTransaction(option);

        }

    }

    public static void paymentTransaction(int option) {

        boolean cash;

        if (option == 1) {
            cash = false;
        } else {
            cash = true;
        }

        String name = "";
        String cardNumber = "";
        String expDate = "";
        String cvv = "";

        if (cash) {
            name = Patient.getEntry(patientNo).getPatientName(); //Not 1, but should have customerNo
        } else {
            System.out.print("Enter name: ");
            name = input.next();

            System.out.print("Enter card number: ");
            cardNumber = input.next();

            System.out.print("Enter expiry date: ");
            expDate = input.next();

            System.out.print("Enter CVV: ");
            cvv = input.next();
        }

        double amountPay = invoice.getTotal();

        boolean success = false;

        while (!success) {
            System.out.print("\nEnter Amount: RM");
            double paid = input.nextDouble();

            if (paid < amountPay) {
                amountPay -= paid;
                success = false;
                System.out.printf("\nNot enough credit.\nRemaining: RM%.2f", (amountPay));
            }
            else {
                amountPay -= paid;
                if (amountPay <= 0) {
                    System.out.printf("\nRM%.2f will be refund.", Math.abs(amountPay));
                    System.out.println("\n\n\n\nPayment Successful!!");
                }
                success = true;
            }
        }

        if (cash){
            payer = new Payment(name, invoice.getTotal());
        }
        else {
            payer = new Payment(name, cardNumber, expDate, cvv, invoice.getTotal());
        }

        recordTransaction(cash);
    }

    //Record transaction - After Payment Done
    public static void recordTransaction(boolean cash){

        if (cash){
            transactionsArray[transactionNum] = new Transaction(invoice, payer, "Cash");
        }
        else {
            transactionsArray[transactionNum] =new Transaction(invoice, payer, "Credit Card");
        }

        transactionHistory.push(transactionsArray[transactionNum]);

        transactionNum++;
        patientNo++;
    }

    //Print Transaction History
    public static void transactionHistory() {

        int index = 0;

        System.out.println("\n\n==================================================================================================");
        System.out.println("                                      TRANSACTION HISTORY                                         ");
        System.out.println("==================================================================================================");

        System.out.printf("\n%-3s %-8s %-12s %-12s %-12s %-20s %-13s %-15s\n","No.", "ID", "Date", "Time", "Invoice ID", "Payer Name", "Method", "Amount (RM)");

        System.out.println("---------------------------------------------------------------------------------------------------");

        while (transactionHistory.isEmpty() == false && transactionHistory.peek() != null) {
            index++;
            Transaction transaction = transactionHistory.peek();
            System.out.printf("%-3d %-8s %-12s %-14s %-10s %-20s %-13s %8.2f\n", index,
                    transaction.getTransactionID(),
                    transaction.getPayDate(),
                    transaction.getPayTime(),
                    transaction.getInvoice().getInvoiceID(),
                    transaction.getPayment().getPayerName(),
                    transaction.getMethod(),
                    transaction.getPayment().getAmountPaid());
            System.out.println("---------------------------------------------------------------------------------------------------");

            tempStack.push(transactionHistory.peek());

            transactionHistory.pop();
        }


        while (tempStack.isEmpty() == false && tempStack.peek() != null) {
            Transaction transaction = tempStack.peek();
            tempStack.pop();

            // To restore contents of the original stack.
            transactionHistory.push(transaction);
        }

    }

    public static void latestMedicineTransaction() {

        System.out.println("\n\nTransaction ID: " + transactionHistory.peek().getTransactionID());
        System.out.println("Payment Date  : " + transactionHistory.peek().getPayDate());
        System.out.println("Payment Time  : " + transactionHistory.peek().getPayTime());
        System.out.println("Invoice ID    : " + transactionHistory.peek().getInvoice().getInvoiceID());
        System.out.println("Patient Name  : " + transactionHistory.peek().getInvoice().getPatient().getPatientName());

        System.out.printf("\n%-2s %14s %14s %7s %15s\n",
                "No", "Medicine Name", "Dosage Form", "Dose", "Quantity");
        System.out.println("---------------------------------------------------------");


        for (int i = 0; i < invoice.getPrescriptionList().getNumberOfEntries(); i++){
            System.out.printf("%-3d %-16s %-14s %-7s %9d\n", i + 1,
                    transactionHistory.peek().getInvoice().getPrescriptionList().getEntry(i + 1).getMedicine().getName(),
                    transactionHistory.peek().getInvoice().getPrescriptionList().getEntry(i + 1).getDosage().getDosageForm(),
                    transactionHistory.peek().getInvoice().getPrescriptionList().getEntry(i + 1).getDosage().getDose(),
                    transactionHistory.peek().getInvoice().getPrescriptionList().getEntry(i + 1).getQuantity());

            System.out.println("---------------------------------------------------------");

        }

    }

    public static void viewSummaryReport() {

        int index = 0;
        double profit = 0;
        double totalProfit = 0;

        System.out.println("\n\n==========================================================================================");
        System.out.println("                                    SUMMARY REPORT                                        ");
        System.out.println("==========================================================================================");

        System.out.printf("\n%-3s %-8s %-14s %-20s %-14s %-14s %-15s\n",
                "No.", "ID", "Payment Date", "Payer Name", "Method", "Amount (RM)", "Profit (RM)");
        System.out.println("------------------------------------------------------------------------------------------");

        while (transactionHistory.isEmpty() == false && transactionHistory.peek() != null){
            index ++;

            Transaction transaction = transactionHistory.peek();

            for (int i = 0; i < transaction.getInvoice().getPrescriptionList().getNumberOfEntries(); i++){
                double price = transaction.getInvoice().getPrescriptionList().getEntry(i+1).getDosage().getDosagePrice();
                double qty = transaction.getInvoice().getPrescriptionList().getEntry(i+1).getQuantity();
                double cost  = transaction.getInvoice().getPrescriptionList().getEntry(i+1).getDosage().getDosageCost();

                profit = (price - cost) * qty;
            }

            profit += transaction.getInvoice().calcTaxRate();

            System.out.printf("%-3d %-9s %-13s %-20s %-13s %8.2f %14.2f\n", index,
                    transaction.getTransactionID(),
                    transaction.getPayDate(),
                    transaction.getPayment().getPayerName(),
                    transaction.getMethod(),
                    transaction.getPayment().getAmountPaid(),
                    profit
            );

            System.out.println("------------------------------------------------------------------------------------------");

            totalProfit += profit;

            tempStack.push(transactionHistory.peek());
            transactionHistory.pop();
        }


        System.out.printf("%78s %7.2f", "Total Profit (RM) : ", totalProfit);

        while (tempStack.isEmpty() == false && tempStack.peek() != null) {
            Transaction transaction = tempStack.peek();
            tempStack.pop();

            // To restore contents of the original stack.
            transactionHistory.push(transaction);
        }

    }


    //Initialize Data
    public static void initializeTransactionHistory(){

        //First - Loperamide (Oral tablet)
        Medicine testingMedicine4 = medicineStock.getEntry(3);
        Dosage testingDosage4 = medicineStock.getEntry(3).getDosage().getEntry(2);
        ArrayList<PrescriptionList> tempList1 = new ArrayList<>();
        tempList1.add(new PrescriptionList(testingMedicine4, testingDosage4, 1));
        Patient patient1 = new Patient("Patient1", "111111111111", "1111111111", "010101", "010101");
        Invoice invoice1 = new Invoice(tempList1, patient1, 20, 21.2);
        Payment payer1 = new Payment("Payer1", 21.2);
        transactionHistory.push(new Transaction("01-01-2022", "10:20:59", invoice1, payer1, "Cash"));

        //second
        Medicine testingMedicine5 = medicineStock.getEntry(4);
        Dosage testingDosage5 = medicineStock.getEntry(4).getDosage().getEntry(1);
        ArrayList<PrescriptionList> tempList2 = new ArrayList<>();
        tempList2.add(new PrescriptionList(testingMedicine5, testingDosage5, 2));
        Patient patient2 = new Patient("Patient2", "222222222222", "2222222222", "020202", "030303");
        Invoice invoice2 = new Invoice(tempList2, patient2,30,31.8);
        Payment payer2 = new Payment("Payer2", 31.8);
        transactionHistory.push(new Transaction("03-04-2022", "09:00:00", invoice2, payer2, "Cash"));


        //THIRD
        Medicine testingMedicine6 = medicineStock.getEntry(5);
        Dosage testingDosage6 = medicineStock.getEntry(5).getDosage().getEntry(2);

        ArrayList<PrescriptionList> tempList3 = new ArrayList<>();

        tempList3.add(new PrescriptionList(testingMedicine6, testingDosage6, 3));
        Patient patient3 = new Patient("Patient3", "333333333333", "3333333333", "030303", "030303");
        Invoice invoice3 = new Invoice(tempList3, patient3, 75, 4.5);
        Payment payer3 = new Payment("Payer3", 79.5);
        transactionHistory.push(new Transaction("21-06-2022", "14:59:11", invoice3, payer3, "Credit Card"));
    }

    public static void initializePatient() {

        Medicine testingMedicine1 = medicineStock.getEntry(1);
        Dosage testingDosage1 = medicineStock.getEntry(1).getDosage().getEntry(1);
        Medicine testingMedicine2 = medicineStock.getEntry(2);
        Dosage testingDosage2 = medicineStock.getEntry(2).getDosage().getEntry(2);
        Medicine testingMedicine3 = medicineStock.getEntry(3);
        Dosage testingDosage3 = medicineStock.getEntry(3).getDosage().getEntry(2);

        prescriptionList.add(new PrescriptionList(testingMedicine1, testingDosage1, 1));
        prescriptionList.add(new PrescriptionList(testingMedicine2, testingDosage2, 2));
        prescriptionList.add(new PrescriptionList(testingMedicine3, testingDosage3, 3));

        Patient patient = new Patient("ALOHA", "010101011111", "0101010101", "010101", "010101");

        Patient.add(patient);

        invoice = new Invoice(prescriptionList, patient);

    }

    //Initialize Data - Wilson
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
}
