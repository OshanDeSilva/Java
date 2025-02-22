//Array based Stack

public class Stack {
    private int[] array;
    private int top;
    private int size;

    // constructer of the Stack class
    public Stack(int size) {
        this.size = size;
        this.array = new int[size];
        this.top = -1;
    }

    // Check whether the stack is empty
    private boolean isEmpty() {
        if (top == -1)
            return true;
        else
            return false;
    }

    // Check whether the stack is full
    private boolean isFull() {
        if (top == size - 1)
            return true;
        else
            return false;
    }

    // push an element to the stack
    public void push(int element) {
        if (isFull()) {
            System.out.println("Stack is Full!");
        } else {
            top++;
            array[top] = element;
        }
    }

    // get top element from the Stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return 0;
        } else {
            int retVal = array[top];
            top--;
            return retVal;
        }
    }

}
