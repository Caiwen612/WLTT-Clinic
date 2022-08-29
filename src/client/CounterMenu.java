package client;

import entity.Patient;
import adt.ArrayQueue;
import  adt.ArrayList;
import adt.ListInterface;
import adt.QueueInterface;

public class CounterMenu {
    private ListInterface<Patient> patientList = new ArrayList<Patient>();
    private QueueInterface<String> waitingList = new ArrayQueue<String>();

    public static void menu(){
        System.out.println("Counter: ");
        System.out.println("[1] Search for Patient");
        System.out.println("[2] Add New Patient");
        System.out.println("[3] Queue Number");


    }




}
