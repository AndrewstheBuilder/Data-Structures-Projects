public class SinglyLinkedList<E> {
    private SinglyLink<E> head;
    private SinglyLink<E> tail;
    private int listSize;

    protected SinglyLinkedList() {
        clear();
    }

    //Remove all elements
    protected void clear() {
        head = tail = new SinglyLink<E>(null);
        listSize = 0;
    }

    //Append reference of generic array to linked list
    protected void appendTail(E[] it) {
        if(tail.element() == null) {
            tail.setElement(it);
            listSize++;
            return;
        }
        tail.setNext(new SinglyLink<E>(it,null));
        tail = tail.next(); //create new tail
        listSize++;
    }

    //removes the head node
    protected void removeHead() {
        //Only a single node exists
        if(head.next() == null) {
            clear();
            listSize = 0;
            return;
        }
        head.setElement(head.next().element());
        head.setNext(head.next().next());
        listSize--;
    }

    //Return list length
    protected int length() {
        return listSize;
    }


    //Tell if the list is empty or not
    protected boolean isEmpty() {
        return listSize == 0;
    }

    //returns head node element
    protected E[] getHead() {
        return head.element();
    }

    //returns tail node element
    protected E[] getTail() {
        return tail.element();
    }
}

class SinglyLink<E> {
    private E[] e;
    private SinglyLink<E> n;

    //Constructors
    SinglyLink(E[] it, SinglyLink<E> inn) {
        e = it;
        n = inn;
    }

    SinglyLink(SinglyLink<E> inn) {
        e = null;
        n = inn;
    }

    //Return element value
    E[] element() {
        return e;
    }

    //Set element value
    E[] setElement(E[] it) {
        return e = it;
    }

    //Return next link
    SinglyLink<E> next() {
        return n;
    }

    //Set next link
    SinglyLink<E> setNext(SinglyLink<E> inn) {
        return n = inn;
    }
}