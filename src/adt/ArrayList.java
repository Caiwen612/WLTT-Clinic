package adt;
/*
 * @Author: Wilson Yau Kai Chun
 * @Group: RSF2S1G1
 * */

import entity.Dosage;
import entity.Medicine;

import java.io.Serializable;
import java.util.Iterator;


public class ArrayList<T> implements ListInterface<T>, Serializable{

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        //Validate the duplicated element
        for (int index = 1; index <= numberOfEntries; index++) {
            if (getEntry(index) == newEntry){
                return false;
            }
        }
        if (isFull()){
            this.doubleArrayList();
        }
        array[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            makeRoom(newPosition);
            array[newPosition - 1] = newEntry;
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public boolean remove(int givenPosition) {
        boolean isSuccessful = true;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {

            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }
            numberOfEntries--;
        }
        else{
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        numberOfEntries = 0;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            array[givenPosition - 1] = newEntry;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            result = array[givenPosition - 1];
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public int indexOf(T anEntry) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == anEntry){
                return (index + 1);
            }
        }
        return -1;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    @Override
    public Iterator<T> getIterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index;

        private ArrayListIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return (getEntry(index+1)) != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T nextEntry = array[index];
                index++;
                return nextEntry;
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    //Sort array
    public static <T extends Comparable<T>> void bubbleSort(ListInterface<T> a, int n, int option) {
        boolean sorted = false;
        for (int pass = 1; pass <= n && !sorted; pass++) {
            sorted = true;
            for (int index = 1; index <= n - pass; index++) {
                // swap adjacent elements if first is greater than second
                switch (option){
                    case 1: //compare medicine ID
                        if (a.getEntry(index) instanceof Medicine){
                            if (((Medicine) a.getEntry(index)).compareID(a.getEntry(index + 1)) > 0){
                                swap(a, index, index+1);
                                sorted = false;
                            }
                        }
                        break;
                    case 2://compare medicine Name
                        if (a.getEntry(index) instanceof Medicine){
                            if (((Medicine) a.getEntry(index)).compareName(a.getEntry(index + 1)) > 0){
                                swap(a, index, index+1);
                                sorted = false;
                            }
                        }
                        break;
                    case 3://compare on hand quantity
                        if (a.getEntry(index) instanceof Dosage){
                            if (((Dosage) a.getEntry(index)).compareDosageQuantity(a.getEntry(index + 1)) > 0){
                                swap(a, index, index+1);
                                sorted = false;
                            }
                        }
                        break;
                    case 4://compare allocated quantity
                        if (a.getEntry(index) instanceof Dosage){
                            if (((Dosage) a.getEntry(index)).compareAllocatedQuantity(a.getEntry(index + 1)) > 0){
                                swap(a, index, index+1);
                                sorted = false;
                            }
                        }
                        break;
                    case 5://compare restock quantity
                        if (a.getEntry(index) instanceof Dosage){
                            if (((Dosage) a.getEntry(index)).compareRestockQuantity(a.getEntry(index + 1)) > 0){
                                swap(a, index, index+1);
                                sorted = false;
                            }
                        }
                        break;
                }
            }
        }
    }

    private static <T> void swap(ListInterface<T> a, int i, int j) {
        T temp = a.getEntry(i);
        a.replace(i, a.getEntry(j));
        a.replace(j, temp);
    }

    //Double the array list
    public void doubleArrayList(){
        int length = getNumberOfEntries() * 2;
        ArrayList<T> temp = new ArrayList<>(length);
        for (int index = 1; index <= this.getNumberOfEntries(); index++) {
            temp.add(this.getEntry(index));
        }
    }

    //Copy the current arraylist to a temporary arraylist
    public T copyArrayList(ListInterface<T> anArrayList){
        if (numberOfEntries <= anArrayList.getNumberOfEntries()){
            for (int index = 0; index < numberOfEntries; index++) {
                anArrayList.add(this.getEntry(index));
            }

        }
        return (T) anArrayList;
    }

    //Create one new space by shifting the next element to next position
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        if (lastIndex + 1 - newIndex >= 0)
            System.arraycopy(array, newIndex, array, newIndex + 1, lastIndex + 1 - newIndex);
    }

    //Remove the gap and shift the next element
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = numberOfEntries - 1;

        if (lastIndex - removedIndex >= 0)
            System.arraycopy(array, removedIndex + 1, array, removedIndex, lastIndex - removedIndex);
    }

}
