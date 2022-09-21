package adt;

import java.util.Iterator;

/** SortedDoublyLinkList - An implementation for SortedListInterface.
 *  @author Tay Chai Boon
 */

public class SortedDoublyLinkList<T extends Comparable<T>> implements SortedListInterface<T>,Iterable<T>{
    private Node headNode;
    private Node tailNode;
    private int numberOfEntry;

    public SortedDoublyLinkList() {
        headNode = null;
        tailNode = null;
        numberOfEntry = 0;
    }

    @Override
    public boolean add(T newEntry) {
        //Create a new node
        Node newNode = new Node(newEntry);

        //Algorithm 1:Insert into empty
        if(isEmpty()){ //headNode <-- newNode --> tailNode
            System.out.println("RUN ADD EMPTY");
            //Default: NULL
            //New Node data come in: 1
            //Null <-- 1 --> Null
            headNode = tailNode = newNode;
            headNode.previous = null;
            tailNode.next = null;
            numberOfEntry++;
            return true;
        }

        //Algorithm 2: Change the head node to new node
        Node currentHeadNode = headNode;
        //Check newNode < headNode , return true
        if(newNode.data.compareTo(currentHeadNode.data) < 0){
            System.out.println("RUN ADD Beginning");//Change the beginning chain of node
            //Default :headNode(NULL) <-- currentHeadNode (3) --> 4 --> Null
            //New node data come in : 1
            //Changed: HeadNode(Null) <-- "newNode(1)" --> currentHeadNode (3) --> 3 --> Null
            headNode = newNode;//Change {headNode}, change 3 to 1
            newNode.previous = null;//Null <- newNode : Set new {headNodePrevious} to null indicate it is a head node
            newNode.next = currentHeadNode;//newNode -> currentHeadNode: Set new {headNodeNext} to currentHeadNode 3
            currentHeadNode.previous = newNode;//newNode <- currentHeadNode: Set previous currentHeadNode(3) to newNode 1(Link Back the Chain)
            numberOfEntry++;
            return true;
        }

        //Algorithm 3:Change the tail node to new node
        Node currentTailNode = tailNode;
        //Check newNode > tailNode, return true
        if(newNode.data.compareTo(tailNode.data) > 0){//if data of new data is larger than tail node
            System.out.println("Run add behind");
            //Default: Null <-- 1 <--> 2 <--> currentTailNode(3) -->Null
            //Change the end of node
            //New node data come in : 4
            //Changed: Null <-- 1 <--> 2 <--> 3 --> "4" --> Null
            tailNode = newNode;//change {tailNode}, change 3 to 4
            newNode.next = null;//newNode -> Null: Set new {newNodeNext} to null indicate it is a tail node.
            newNode.previous = currentTailNode;//currentTailNode <- newNode: Set new {tailNodePrevious} to currentTailNode (3)
            currentTailNode.next = newNode;//currentTailNode -> newNode: Set {currentTailNodeNext} to newNode (4) (Link Back the Chain)
            numberOfEntry++;
            return true;
        }

        //Algorithm 4: Insert at the middle
        Node current = headNode;
        Node previous = null;
        //Check current data > new node data, return true
        while (current != null){
            if(newEntry.compareTo(current.data) < 0){
                //Default: Null <-- 1 <--> 2 <--> 4 --> Null
                //New node data come in: 3
                //Changed: Null <-- 1 <--> 2 <--> "3" --> 4
                System.out.println("RUN ADD CENTER");
                System.out.println("current = " + current.data);
                System.out.println("previous = " + current.previous.data);
                previous = current.previous;//2
                previous.next = newNode;//2 -> 3
                newNode.previous = previous;//2 <- 3
                newNode.next = current;//3 -> 4
                current.previous = newNode;//3 <- 4
                numberOfEntry++;
                return true;
            }
            current = current.next;//move the node to next slide
        }
        return false;
    }

    @Override
    public boolean remove(T entry) {
        Node newNode = new Node(entry);

        //Algorithm 1:Invalid Delete action in adt
        if(isEmpty()){
            System.out.println("Empty");
            return false;
        }

        //Check whether entry contain or not
        if(!this.contains(entry)){
            System.out.println("Delete unsuccessful");
            System.out.println("Data not in linklist");
            return false;
        }

        //Algorithm 2:Delete at beginning
        Node currentHeadNode = headNode;
        //Check first node == newnode
        if(newNode.data.equals(currentHeadNode.data)){
            System.out.println("Delete at beginning");
            //Default :headNode(NULL) <-- currentHeadNode (3) --> 4 --> Null
            //Change the beginning chain of node
            //Remove data : 3
            //Changed: HeadNode(Null) --> 4 --> Null
            headNode = currentHeadNode.next;//Change {headNode}, change 3 to 4
            headNode.previous = null;//Null <- currentHeadNode : Set new head node previous to null indicate it is a head node
            //and Clear the currentHeadNode previous value to null.
            numberOfEntry--;
            return true;
        }

        //Algorithm 3:Delete at End
        Node currentTailNode = tailNode;
        if(newNode.data.equals(tailNode.data)){
            System.out.println("Delete at the end");
            //Default: Null <-- 1 <--> 2 <--> currentTailNode(3) -->Null
            //Change the end chain of node
            //Remove data: 3
            //Changed:  Null <-- 1 <--> 2 --> Null
            tailNode = currentTailNode.previous;//Change {tailNode}, change 3 to 2
            tailNode.next = null;
            numberOfEntry--;
            return true;
        }

        //Algorithm 4:Delete at Middle
        Node current = headNode;
        Node before = null;
        Node after = null;
        while (current != null){
            System.out.println("Delete at center");
            //Default: Null <-- 1 <--> 2 <--> 4 --> Null
            //Remove data: 2
            //Changed: Null <-- 1 <--> 4
            if(newNode.data.equals(current.data)){
                //FInd the before chain and after chain
                before = current.previous;//Set the before node
                after = current.next;//Set the after node

                //Perform the change of chain
                before.next = after;//1 -> 4
                after.previous = before;//1 <- 4
                numberOfEntry--;
                return true;
            }

            current = current.next;//move the node to next slide

        }
        return false;
    }

    @Override
    public boolean contains(T entry) {
        
        if(isEmpty()){
            return false;
        }

        boolean found = false;
        Node tempNode = headNode;
        for(T node: this){
            if (node.equals(entry)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public void clear() {
        headNode = null;
        tailNode = null;
        numberOfEntry = 0;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntry;
    }

    @Override
    public boolean isEmpty() {
        //Ternary operator
        //condition ? if true:false
        return numberOfEntry == 0 ? true:false;
    }

    @Override
    public T getEntry(T entry) {
        if(isEmpty()){
            return null;
        }
        T result = null;
        for(T data: this){
            if(data.equals(entry)){
                result = data;
                break;
            }
        }
        return result;
    }

    @Override
    public T getEntry(int index){
        //Check whether the index is valid or not.
        if(index < 0 && index > numberOfEntry + 1){
            return null;
        }
        if(isEmpty()){
            return null;
        }
        Node current = headNode;
        int count = 0;

        while (current != null){
            if(count == index-1){//make it standard
                return current.data;
            }

            count++;
            current = current.next;
        }
        return null;
    }

    @Override
    public Node getHead() {
        if(isEmpty()){
            return null;
        }
        return headNode;
    }

    @Override
    public Node getTail() {
        if(isEmpty()){
            return null;
        }
        return tailNode;
    }

    @Override
    public String printAscending(){
        if(isEmpty()){
            return null;
        }
        String outputStr = "";
        Node currentNode = headNode;
        int count = 1;
        while (currentNode != null) {
            outputStr += count + "\t" + currentNode.data + "\n";;
            currentNode = currentNode.next;
            count++;
        }
        return outputStr;
    }

    public String printDescending(){
        if(isEmpty()){
            return null;
        }
        String outputStr = "";
        Node currentNode = tailNode;
        int count = 1;
        while ( currentNode != null){
            outputStr += count + "\t" + currentNode.data + "\n";
            currentNode = currentNode.previous;
            count++;
        }
        return outputStr;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>(this);
    }

    class Node {
        private T data;//Store the data
        private Node previous;//store the previous address
        private Node next;//store the next address


        private Node(T data){
            this.data = data;
            next = null;
        }

        private Node(T data, Node next){
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public Node getPrevious() {
            return previous;
        }

        public Node getNext() {
            return next;
        }
    }

    private static class ListIterator<T extends Comparable<T>> implements Iterator<T>{
        SortedDoublyLinkList<T>.Node current;

        public ListIterator(SortedDoublyLinkList<T> doublyLinkList){
            current = doublyLinkList.getHead();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
