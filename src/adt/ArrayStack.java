package adt;/* * @Author: Lee Chun Kai * @Group: RSF2S1G1 * */public class ArrayStack<T> implements StackInterface<T> {  private T[] stackArray;  private int topIndex; // index of top entry  private static final int DEFAULT_CAPACITY = 50;  public ArrayStack() {    this(DEFAULT_CAPACITY);  }  public ArrayStack(int initialCapacity) {    stackArray = (T[]) new Object[initialCapacity];    topIndex = 0;  }  //Add (Push) the new element to the top of stack  //capacity of stack will be expanded when necessary  @Override  public void push(T newStackEntry) {    topIndex++;    if (getNumberOfElement() == stackArray.length) {      expandStackSize();    }    stackArray[topIndex] = newStackEntry;  }  //Delete (Pop) the top element from stack  @Override  public T pop() {    T topElement = null;    if (!isEmpty()) {      topElement = stackArray[topIndex];      stackArray[topIndex] = null;      topIndex--;    }    return topElement;  }  //Return the top element in the stack  @Override  public T peek() {    T topElement = null;    if (!isEmpty()) {      topElement = stackArray[topIndex];    }    return topElement;  }  @Override  public boolean isEmpty() {    return topIndex <= 0;  }  public void clear() {    topIndex = 0;  }  public int getNumberOfElement(){    return topIndex;  }  private void expandStackSize() {    T[] doubleStackArray = (T[])(new Object[stackArray.length*2]);    for (int i = 0; i < stackArray.length; i++)      doubleStackArray[i] = stackArray[i];    stackArray = doubleStackArray;  }} 