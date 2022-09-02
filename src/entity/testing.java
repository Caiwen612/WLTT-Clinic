package entity;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;

import java.util.Scanner;

public class testing {
    private static  ListInterface<Patient> patientList = new ArrayList<>(1000);

    public static void main(String[] args){
        patientList.add(new Patient("yeet"));
        patientList.add(new Patient("yeet1"));
        patientList.add(new Patient("yeet2"));
        patientList.add(new Patient("yeet3"));
        //patientList.remove(2);

        System.out.println(patientList.toString().contains(new Patient("shitz").getIcNo()));

        System.out.println(patientList.getNumberOfEntries());
        for (int i = 0; i < patientList.getNumberOfEntries() +1; i++) {
            System.out.println(patientList.getEntry(i));

        }
    }

    }

