package client;

import adt.ArrayStack;
import adt.ListInterface;
import adt.StackInterface;
import driver.Driver;
import entity.*;

import java.util.Scanner;

public class PaymentManager {
    private static Scanner input = new Scanner(System.in);
    private static Transaction[] transactionsArray = new Transaction[100];
    private static int transactionNum = 0;

    //Payment Module
    private static StackInterface<Transaction> transactionHistory = new ArrayStack<>();
    private static StackInterface<Transaction> tempStack = new ArrayStack<>();

    public PaymentManager(){
        ListInterface<Medicine> medicineStock = null;

        //FIRST - Loperamide (Oral tablet)
//
//        Medicine testingMedicine4 = medicineStock.getEntry(3).clone();
//        Dosage testingDosage4 = medicineStock.getEntry(3).getDosage().getEntry(2);
//        ListInterface<Medicine> tempList1 = new ArrayList<>();
//        testingMedicine4.getDosage().clear();
//        testingDosage4.setDosageQuantity(1);
//        testingMedicine4.getDosage().add(testingDosage4);
//        Patient patient1 = new Patient("Patient1", "111111111111", "1111111111", "010101", "010101");
//        Invoice invoice1 = new Invoice(tempList1, patient1, 20, 21.2);
//        Payment payer1 = new Payment("Payer1", 21.2);
//        transactionHistory.push(new Transaction("01-01-2022", "10:20:59", invoice1, payer1, "Cash"));
//
//
//
//        //SECOND - Atorvastatin (Oral tablet)
//        Medicine testingMedicine5 = medicineStock.getEntry(4).clone();
//        Dosage testingDosage5 = medicineStock.getEntry(4).getDosage().getEntry(1);
//        ListInterface<Medicine> tempList2 = new ArrayList<>();
//        testingMedicine5.getDosage().clear();
//        testingDosage5.setDosageQuantity(2);
//        testingMedicine5.getDosage().add(testingDosage5);
//        Patient patient2 = new Patient("Patient2", "222222222222", "2222222222", "020202", "030303");
//        Invoice invoice2 = new Invoice(tempList2, patient2,30,31.8);
//        Payment payer2 = new Payment("Payer2", 31.8);
//        transactionHistory.push(new Transaction("03-04-2022", "09:00:00", invoice2, payer2, "Cash"));
//
//
//        //THIRD - Lisinopril (Oral tablet)
//        Medicine testingMedicine6 = medicineStock.getEntry(5).clone();
//        Dosage testingDosage6 = medicineStock.getEntry(5).getDosage().getEntry(2);
//        ListInterface<Medicine> tempList3 = new ArrayList<>();
//        testingMedicine6.getDosage().clear();
//        testingDosage6.setDosageQuantity(3);
//        testingMedicine6.getDosage().add(testingDosage5);
//        Patient patient3 = new Patient("Patient3", "333333333333", "3333333333", "030303", "030303");
//        Invoice invoice3 = new Invoice(tempList3, patient3, 75, 4.5);
//        Payment payer3 = new Payment("Payer3", 79.5);
//        transactionHistory.push(new Transaction("21-06-2022", "14:59:11", invoice3, payer3, "Credit Card"));

    }

    //Bill List - Patient
    public static void printInvoice(Patient patient){
        Invoice invoice = patient.getTempInvoice();
        if(patient.getTempInvoice() == null){
            invoice = new Invoice();
            invoice.setPatient(patient);
            invoice.setPrescriptionList(patient.getHistory().getEntry(patient.getHistory().getNumberOfEntries()).getMedicineCart());
            patient.setTempInvoice(invoice);
        }
        if (patient == null){
            System.out.println("\n\n\nThere is no invoice exist currently!!");
        }
        else {
            System.out.println("\n\n======================================================================================");
            System.out.println("                                      INVOICE                                         ");
            System.out.println("======================================================================================");

            System.out.println("Patient Name : " + patient.getPatientName());
            System.out.println("Address      : " + patient.getAddress());
            System.out.println("Contact No   : " + patient.getPhoneNo());
            System.out.println("I/C No       : " + patient.getIcNo());

            System.out.println("\nPrinted Date : " + invoice.getPrintDate());
            System.out.println("Printed Time : " + invoice.getPrintTime());
            System.out.println("Invoice ID   : " + invoice.getInvoiceID());

            System.out.printf("\n%-2s %14s %14s %7s %15s %16s %12s\n",
                    "No", "Medicine Name", "Dosage Form", "Dose", "Quantity", "Unit Price (RM)", "Amount (RM)");
            System.out.println("--------------------------------------------------------------------------------------");

            double subTotal = 0.0;

            for (int i = 1; i <= invoice.getPrescriptionList().getNumberOfEntries(); i++) {
                Medicine medicine = invoice.getPrescriptionList().getEntry(i);
                Dosage dosage = medicine.getDosage().getEntry(1);
                System.out.printf("%-3d %-16s %-14s %-7s %9d %13.2f %14.2f\n", i,
                        medicine.getName(),
                        dosage.getDosageForm(),
                        dosage.getDose(),
                        dosage.getDosageQuantity(),
                        dosage.getDosagePrice(),
                        invoice.getAmount(i));
                subTotal += invoice.getAmount(i);
                System.out.println("--------------------------------------------------------------------------------------");

            }

            invoice.setSubTotal(subTotal);

            System.out.printf("%74s %7.2f", "Sub Total   : ", subTotal);
            System.out.printf("\n%74s %7.2f", "Tax Rate (6%)   : ", invoice.calcTaxRate());
            System.out.printf("\n%74s %7.2f", "Grand Total   : ", invoice.getTotal());
            System.out.println("");
        }
        Driver.pressAnyKeyToContinueWithPrompt();
    }

    public static void paymentProceed(Patient patient) {

        printInvoice(patient);

        if (patient != null && !patient.isPaymentStatus()) {
            System.out.println("\n\nPay with");
            System.out.println("[1] Credit Card");
            System.out.println("[2] Cash");
            System.out.println("\n[0] Back");
            System.out.print("Enter your option: ");
            int option = input.nextInt();

            paymentTransaction(option,patient);
        } else{
            System.out.println("This patient already make a payment.");
            Driver.pressAnyKeyToContinueWithPrompt();
        }

    }

    public static void paymentTransaction(int option,Patient patient) {

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
            name = patient.getPatientName(); //Not 1, but should have customerNo
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

        double amountPay = patient.getTempInvoice().getTotal();

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
        Payment payer = null;
        if (cash){
            payer = new Payment(name, patient.getTempInvoice().getTotal());
        }
        else {
            payer = new Payment(name, cardNumber, expDate, cvv, patient.getTempInvoice().getTotal());
        }
        recordTransaction(patient,cash,payer);
    }

    //Record transaction - After Payment Done
    public static void recordTransaction(Patient patient,boolean cash,Payment payer){
        patient.setPaymentStatus(true);
        if (cash){
            transactionsArray[transactionNum] = new Transaction(patient.getTempInvoice(), payer, "Cash");
        }
        else {
            transactionsArray[transactionNum] =new Transaction(patient.getTempInvoice(), payer, "Credit Card");
        }

        transactionHistory.push(transactionsArray[transactionNum]);
        transactionNum++;
        System.out.println("Transaction has successful");
        Driver.pressAnyKeyToContinueWithPrompt();
    }

    //Transaction Menu (4 Function)
    public static void transactionMenu() {
        if (transactionHistory.peek() != null){
            transactionHistory();

            System.out.println("[1] View Latest Medicine Transaction");
            System.out.println("[2] Search Transaction");
            System.out.println("[3] Sort By Date");
            System.out.println("[4] Summary Report");
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
            Driver.pressAnyKeyToContinueWithPrompt();
        }

    }

    //Print Transaction History
    public static void transactionHistory() {

        int index = 0;
        Driver.clearScreen();
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
        Driver.pressAnyKeyToContinueWithPrompt();

    }

    //Search For Transaction Details
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

        for (int i = 1; i <= transactionSearched.getInvoice().getPrescriptionList().getNumberOfEntries(); i++){// 3, 2
            Medicine medicine = transactionSearched.getInvoice().getPrescriptionList().getEntry(i);
            Dosage dosage = medicine.getDosage().getEntry(1);
            System.out.printf("%-3d %-16s %-14s %-7s %9d %13.2f %15.2f\n", i + 1,
                    medicine.getName(),
                    dosage.getDosageForm(),
                    dosage.getDose(),
                    dosage.getDosageQuantity(),
                    dosage.getDosagePrice(),
                    transactionSearched.getInvoice().getAmount(i)
            );

            System.out.println("---------------------------------------------------------------------------------------");

        }

        System.out.printf("%75s %7.2f", "Sub Total   : ", transactionSearched.getInvoice().getSubTotal());
        System.out.printf("\n%75s %7.2f", "Tax Rate (6%)   : ", transactionSearched.getInvoice().calcTaxRate());
        System.out.printf("\n%75s %7.2f", "Grand Total   : ", transactionSearched.getInvoice().getTotal());
        Driver.pressAnyKeyToContinueWithPrompt();

    }

    //Oldest -> Newest
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
        Driver.pressAnyKeyToContinueWithPrompt();
    }

    //View Latest Medicine Transaction (Medicine Only)
    public static void latestMedicineTransaction() {

        System.out.println("\n\nTransaction ID: " + transactionHistory.peek().getTransactionID());
        System.out.println("Payment Date  : " + transactionHistory.peek().getPayDate());
        System.out.println("Payment Time  : " + transactionHistory.peek().getPayTime());
        System.out.println("Invoice ID    : " + transactionHistory.peek().getInvoice().getInvoiceID());
        System.out.println("Patient Name  : " + transactionHistory.peek().getInvoice().getPatient().getPatientName());

        System.out.printf("\n%-2s %14s %14s %7s %15s\n",
                "No", "Medicine Name", "Dosage Form", "Dose", "Quantity");
        System.out.println("---------------------------------------------------------");


        for (int i = 1; i <= transactionHistory.peek().getInvoice().getPrescriptionList().getNumberOfEntries(); i++){
            Medicine medicine = transactionHistory.peek().getInvoice().getPrescriptionList().getEntry(i);
            Dosage dosage = medicine.getDosage().getEntry(1);
            System.out.printf("%-3d %-16s %-14s %-7s %9d\n", i + 1,
                    medicine.getName(),
                    dosage.getDosageForm(),
                    dosage.getDose(),
                    dosage.getDosageQuantity());

            System.out.println("---------------------------------------------------------");

        }
        Driver.pressAnyKeyToContinueWithPrompt();

    }

    //View Profit
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

            for (int i = 1; i <= transaction.getInvoice().getPrescriptionList().getNumberOfEntries(); i++){
                double price = transaction.getInvoice().getPrescriptionList().getEntry(i+1).getDosage().getEntry(1).getDosagePrice();
                double qty = transaction.getInvoice().getPrescriptionList().getEntry(i+1).getDosage().getEntry(1).getDosageQuantity();
                double cost  = transaction.getInvoice().getPrescriptionList().getEntry(i+1).getDosage().getEntry(1).getDosageCost();

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
        Driver.pressAnyKeyToContinueWithPrompt();

    }


}
