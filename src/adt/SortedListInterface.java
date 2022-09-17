package adt;

import java.util.Iterator;

/** SortedListInterface - An interface for the ADT sortedDoublyLinklist.
 *  @author Tay Chai Boon
 */

public interface SortedListInterface <T extends Comparable<T>> extends Iterable<T> {

    /** Adds newEntry into the list based on the sort. Sort is based on comparable.
     *
     * @param newEntry
     * @return true if anEntry is success add into the list.
     */
    public boolean add(T newEntry);

    /** Remove entry from the list.
     *
     * @param entry
     * @return true if anEntry is success remove the list.
     */
    public boolean remove(T entry);

    /** Find whether the entry is inside in the list or not
     *
     * @param entry
     * @return true if anEntry is contains at the list
     */
    public boolean contains(T entry);

    /** Clear all the list
     *
     */
    public void clear();

    /** Get the number of entry in the list
     *
     * @return the number of entry in the list
     */
    public int getNumberOfEntries();

    /** Check the list whether is empty or not
     *
     * @return true if the list is empty
     */
    public boolean isEmpty();

    /** Get Entry based on data
     *
     * @param entry
     * @return the entry that inside of the list
     */
    public T getEntry(T entry);

    /** Get Entry based on index of list
     *
     * @param index
     * @return the entry based on index
     */
    public T getEntry(int index);

    /** Get the head of the link list
     *
     * @return the head of the link list
     */
    public SortedDoublyLinkList<T>.Node getHead();

    /** Get the tail of the link list
     *
     * @return the tail of the link list
     */
    public SortedDoublyLinkList<T>.Node getTail();

    /** Print the element according ascending order by start print from head of node.
     *
     * @return a set of data based on ascending order.
     */
    public String printAscending();

    /**Print the element according descending order by start print from tail of node.
     *
     * @returna set of data based on descending order.
     */
    public String printDescending();


    /** Get the Iterator of list
     *
     * @return the iterator
     */
    public Iterator<T> iterator();
}
