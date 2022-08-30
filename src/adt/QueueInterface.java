package adt;

import java.util.Iterator;

public interface QueueInterface<T> {
    public Iterator<T> getIterator();

    public void enqueue(T newEntry);

    public T dequeue();

    public T getFront();

    public boolean isEmpty();

    public void clear();
}