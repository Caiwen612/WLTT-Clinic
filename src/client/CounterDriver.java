package client;


import java.util.Scanner;

public class CounterDriver {
    private static Scanner input = new Scanner(System.in);

    static int waitingNo = 100;
    public static void counterMenu(){

        CounterMenu.bubbleSort(CounterMenu.getpatientList(), CounterMenu.getpatientList().getNumberOfEntries());
        System.out.println("Counter: ");
        System.out.println("[1] Search for Patient");
        System.out.println("[2] Add New Patient");
        System.out.println("[3] Edit Existing Patient Details");
        System.out.println("[4] Delete Existing Patient Details");
        System.out.println("[5] Queue Number");
        System.out.println("Enter your option: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                if (!(CounterMenu.getpatientList().isEmpty())) {
                    CounterMenu.searchPatient();
                } else {
                    System.out.println("Patient List is empty. Please add patient first!");
                }
                break;
            case 2:
                CounterMenu.addPatient();
                break;
            case 3:
                if (!(CounterMenu.getpatientList().isEmpty())) {
                    CounterMenu.editPatient();
                } else {
                    System.out.println("Patient List is empty. Please add patient first!");
                }
                break;
            case 4:
                if (!(CounterMenu.getpatientList().isEmpty())) {
                    CounterMenu.deletePatient();
                } else {
                    System.out.println("Patient List is empty. Please add patient first!");
                }
                break;
            case 5:
                CounterMenu.showQueue();
                break;
        }
    }
}
