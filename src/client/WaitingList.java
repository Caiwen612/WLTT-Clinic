package client;

import adt.ArrayQueue;
import adt.QueueInterface;
import entity.Patient;
import entity.waitingQueue;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class WaitingList {
    private static Scanner input = new Scanner(System.in);
    private static String n;
    private static QueueInterface<waitingQueue> waitingQueue = new ArrayQueue<>();

    //change if needed
    private static Queue<Patient> room1 = new LinkedList<>();
    private static Queue<Patient> room2 = new LinkedList<>();
    private static Queue<Patient> room3 = new LinkedList<>();

    public static void main(String[] args) {
        showQueue();
    }

    public static void showQueue() {
        /*waitingQueue.enqueue(new waitingQueue(new Patient("yeet"), 1, 1));
        waitingQueue.enqueue(new waitingQueue(new Patient("yeet1"), 2, 2));
        waitingQueue.enqueue(new waitingQueue(new Patient("yeet2"), 1, 3));
        waitingQueue.enqueue(new waitingQueue(new Patient("yeet3"), 3, 4));
        */

        do {
            if (waitingQueue.isEmpty()) {
                System.out.println("No one in queue yet!");
                break;

            } else {
                //clear screen
                System.out.println("Previous Queue Number: ");
                if (waitingQueue.getFront().getPatientList() == null) {
                    System.out.println("waitingNo= " + 0 + ", roomNo= " + 0);
                } else {
                    System.out.println(waitingQueue.getFront());
                }

                System.out.println("Current Queue Number: ");
                waitingQueue.dequeue();
                if (waitingQueue.getFront() == null) {
                    System.out.println("No patients registered in queue");
                    break;
                }
                else {
                    System.out.println(waitingQueue.getFront());
                }

                //add patient details in the room queue before dequeuing
                if (waitingQueue.getFront().getRoomNo() == 1) {
                    room1.add(waitingQueue.getFront().getPatientList());
                } else if (waitingQueue.getFront().getRoomNo() == 2) {
                    room2.add(waitingQueue.getFront().getPatientList());
                } else {
                    room3.add(waitingQueue.getFront().getPatientList());
                }

                //how many ppl waiting
                System.out.println("In Queue: ");
                System.out.println(waitingQueue.getSize());
                System.out.println();
                System.out.print("Press n to call the next number: ");
                n = input.next();

            }
        }
            while (Objects.equals(n, "n")) ;
    }

}


