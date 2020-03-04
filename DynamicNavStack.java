public class DynamicNavStack<E> {
    //member data
    private E[] navStack;
    private int undoI;
    private int redoI;
    private int initialCapacity;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 2;

    //Default constructor
    DynamicNavStack() {
        this(DEFAULT_CAPACITY);
    }

    //Constructor creates generic array of specific capacity or default capacity
    @SuppressWarnings("unchecked")
    DynamicNavStack(int c) {
        if (c <= 0) {
            navStack = (E[]) new Object[DEFAULT_CAPACITY];
            initialCapacity = capacity = DEFAULT_CAPACITY;
            undoI = 0;
            redoI = DEFAULT_CAPACITY - 1;
        } else {
            navStack = (E[]) new Object[c];
            initialCapacity = capacity = c;
            undoI = 0;
            redoI = c - 1;
        }
    }

    //return number of all elements in undo and redo stack
    public int size() {
        return undoI + (capacity - (redoI + 1));
    }

    //return true if undo stack is not empty and false otherwise
    public boolean canUndo() {
        return undoI > 0;
    }

    //return true if redo stack is not empty and false otherwise
    public boolean canRedo() {
        return (redoI + 1) < capacity;
    }

    //return top of undo stack or null, does not delete element
    public E undoTop() {
        if (undoI == 0) return null;
        return navStack[undoI - 1];
    }

    //return top of redo stack or null, does not delete element
    public E redoTop() {
        if ((redoI + 1) == capacity) return null;
        return navStack[redoI + 1];
    }

    //return true if there are no elements in the stack
    public boolean isEmpty() {
        return undoI == 0 && (redoI + 1) == capacity;
    }

    //return current capacity of stack
    public int capacity() {
        return capacity;
    }

    //Push element e on to undo stack and empty redo stack
    public void push(E e) {
        if (isFull())
            resize(capacity * 2);
        navStack[undoI++] = e; //push element on to undo stack
        //clear redo stack
        for (int i = redoI; i < capacity; i++)
            navStack[i] = null;
        redoI = capacity - 1;
        if (isOneFourthSize())
            resize(capacity / 2);
    }

    //pop element from undo stack and push onto redo stack
    public E undo() {
        if (undoI == 0) return null;
        E u = navStack[--undoI];
        navStack[undoI] = null;
        navStack[redoI--] = u;
        return u;
    }

    //pop element from redo stack and push onto undo stack
    public E redo() {
        if ((redoI + 1) == capacity) return null;
        E r = navStack[++redoI];
        navStack[redoI] = null;
        navStack[undoI++] = r;
        return r;
    }

    //resize stack to newSize
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        if (newSize >= initialCapacity) {
            E newArray[] = (E[]) new Object[newSize];

            //for undo stack
            for (int i = 0; i < undoI; i++)
                newArray[i] = navStack[i];

            //for redo stack
            int newRedoI = newSize - (capacity - redoI);
            for (int j = newSize - 1; j > newRedoI; j--) {
                newArray[j] = navStack[redoI--];
            }
            redoI = newRedoI;
            navStack = newArray;
            capacity = newSize;
        }
    }

    //return true if undo and redo point to the same spot
    private boolean isFull() {
        return undoI == redoI;
    }

    //return true if array holds elements at less than or equal to 1/4 capacity
    private boolean isOneFourthSize() {
        //array can't shrink below initial capacity
        return size() * 4 <= capacity && capacity > initialCapacity;
    }



    public String toString() {
        String ret = "Array Looks Like this: [";
        for (int i = 0; i < capacity; i++)
            if (navStack[i] != null) ret += navStack[i].toString() + " ";
            else ret += "null ";
        ret += "]\n";
        ret += "undo stack: [";
        for (int i = 0; i < undoI; i++) ret += navStack[i].toString() + " ";
        ret += "]\n";
        ret += "redo stack: [";
        for (int i = capacity - 1; i > redoI; i--) ret += navStack[i].toString() + " ";
        ret += "]";
        return ret;
    }
}