package entity;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class testing {
    private static  ListInterface<Patient> patientList = new ArrayList<Patient>(1000);

    public static void main(String[] args){
        showQueue();

    }
    private static Scanner input = new Scanner(System.in);
    private static String n;
    private static QueueInterface<waitingQueue> waitingQueue = new ArrayQueue<>();

    public static void showQueue() {
        waitingQueue.enqueue(new waitingQueue(new Patient("yeet"), 1, 1));
        waitingQueue.enqueue(new waitingQueue(new Patient("yeet1"), 2, 2));
        waitingQueue.enqueue(new waitingQueue(new Patient("yeet2"), 1, 3));
        waitingQueue.enqueue(new waitingQueue(new Patient("yeet3"), 3, 4));

        do {

            System.out.println("Previous Queue Number: ");
            System.out.println(waitingQueue.getFront().toString());

            System.out.println("Current Queue Number: ");
            waitingQueue.dequeue();
            System.out.println(waitingQueue.getFront().toString());


            //how many ppl waiting
            System.out.println("In Queue: ");
            System.out.println(waitingQueue.getSize());

            System.out.println("Press n to call the next number. ");

            n = input.next();

        } while (Objects.equals(n, "n"));
    }

}






