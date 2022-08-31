package client;

import adt.ArrayQueue;
import adt.QueueInterface;
import entity.Patient;
import entity.waitingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class WaitingList {
    static Scanner input = new Scanner(System.in);
    static String n;
    private static QueueInterface<waitingQueue> waitingQueue = new ArrayQueue<>();
    //change if needed
    private static Queue<Patient> room1 = new LinkedList<>();
    private static Queue<Patient> room2 = new LinkedList<>();
    private static Queue<Patient> room3 = new LinkedList<>();

    public static void showQueue() {
        System.out.println("Previous Queue Number: ");
        System.out.println(waitingQueue.getFront());

        System.out.println("Current Queue Number: ");
        waitingQueue.dequeue();
        System.out.println(waitingQueue.getFront());

        if (waitingQueue.getFront().getRoomNo() == 1){
            room1.add(waitingQueue.getFront().getPatientList());
        }
        else if (waitingQueue.getFront().getRoomNo() == 2){
            room2.add(waitingQueue.getFront().getPatientList());
        }
        else {
            room3.add(waitingQueue.getFront().getPatientList());
        }

        //how many ppl waiting
        System.out.println("In Queue: ");
        System.out.println(waitingQueue.getSize());

        System.out.println("Press n to call the next number. ");

        do {
           n = input.next();
        } while (n == "N");
    }

}


