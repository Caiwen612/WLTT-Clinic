package client;

import entity.Patient;
import adt.ArrayQueue;
import adt.ArrayList;
import adt.ListInterface;
import adt.QueueInterface;
import entity.waitingQueue;

import java.util.Scanner;

public class WaitingList {
    static Scanner input = new Scanner(System.in);
    static String n;

    public static void showQueue() {
        //display queue
        System.out.println("Waiting Queue: ");

        System.out.println("Current Queue Number: ");
        //System.out.println(waitingQueue.getFront());

        System.out.println("Previous Queue Number: ");
        //System.out.println(waitingQueue.);

        System.out.println("In Queue: ");
        //System.out.println(wQ.getSize());

        System.out.println("Press n to call the next number. ");

        do {
            //System.out.println(waitingQueue.ArrayQueueIterator.next());
           n = input.next();
        } while (n == "N");
    }

}


