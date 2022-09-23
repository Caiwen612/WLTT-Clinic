/**
 * @author Wilson Yau Kai Chun
 * @group RSF2S1G1
 * @studentId 21WMR02983
 */
package adt;
import java.io.Serializable;
import java.util.Iterator;

public interface ListInterface<T>  {
    public boolean add(T newEntry);
    public boolean add(int newPosition, T newEntry);
    public boolean remove(int givenPosition);
    public void clear();
    public boolean replace(int givenPosition, T newEntry);
    public T getEntry(int givenPosition);
    public boolean contains(T anEntry);
    public int indexOf(T anEntry);
    public int getNumberOfEntries();
    public boolean isEmpty();
    public boolean isFull();
    public Iterator<T> getIterator();
}

