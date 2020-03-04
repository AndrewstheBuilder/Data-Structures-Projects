public class LinkedArrayQueue<E> extends SinglyLinkedList {
    //member data
    private SinglyLinkedList<E> lList; 
    private int front = 0; 
    private int back = 0;

    //my member data
    private int arrayQueueSize = 0;
    private E[] queueArray; 
    private static final int MAX_SIZE = 8; 

    // the default constructor
    public LinkedArrayQueue() {
        lList = new SinglyLinkedList<>();
    }

    //return the number of elements stored in the queue
    public int size() {
     return arrayQueueSize;
    }

    //return the number of arrays in linked list.
    public int numArrays() {
        return lList.length();
    }

    //test if the queue is empty
    public boolean isEmpty() {
        return lList.isEmpty();
    }

    //return the element at the front of the queue. return null if queue is empty
    public E first() {
        if (lList.isEmpty()) return null;
        queueArray = lList.getHead();
        return queueArray[front];
    }

    //return the element at the back of the queue. return null if queue is empty
    public E last() {
        if (lList.isEmpty()) return null;
        queueArray = lList.getTail();
        return queueArray[back];
    }

    //push e to the back of the queue.
    public void enqueue(E e) {
        arrayQueueSize++;
        if (lList.isEmpty() || back == 7) {
            queueArray = (E[]) new Object[MAX_SIZE];
            lList.appendTail(queueArray);
            back = 0;
            queueArray[back] = e;
            return;
        }
        back++;
        queueArray = lList.getTail();
        queueArray[back] = e;
    }

    //pop and return the element at the front of the queue. return null if queue is empty
    public E dequeue() {
        //if list is empty return null
        if (lList.isEmpty()) return null;
        
        arrayQueueSize--;
        queueArray = lList.getHead();
        E it = queueArray[front];
        queueArray[front] = null;
        front++;
        //if array is empty or LinkedArrayQueue is empty
        if (front == 8 || arrayQueueSize == 0) {
            lList.removeHead();
            front = 0;
        }
        return it;
    }
}