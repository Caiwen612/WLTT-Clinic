package sorting;

import adt.ListInterface;
import entity.Dosage;
import adt.ArrayList;
import entity.Medicine;

public class SortArrayList {
    private static <T> void swap(ListInterface<T> a, int i, int j) {
        T temp = a.getEntry(i);
        a.replace(i, a.getEntry(j));
        a.replace(j, temp);

        /*T temp = a[i];
        a[i] = a[j];
        a[j] = temp;*/
    }

    /**
     * ********************* Bubble Sort ******************************
     * @param <T>
     * @param a
     * @param n
     */
    public static <T extends Comparable<T>> void bubbleSort(ListInterface<Dosage> a, int n) {
        boolean sorted = false;
        for (int pass = 1; pass <= n && !sorted; pass++) {
            sorted = true;
            for (int index = 1; index <= n - pass; index++) {
                // swap adjacent elements if first is greater than second
                if (a.getEntry(index).compareTo(a.getEntry(index+1)) > 0){
                    swap(a, index, index+1);
                    sorted = false;
                }
                /*if (a[index].compareTo(a[index + 1]) > 0) {
                    swap(a, index, index + 1); // swap adjacent elements
                    sorted = false;  // array not sorted because a swap was performed
                }*/
            }
        }
    }
}
