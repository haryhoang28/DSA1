package homework4.ex4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements QueueInterface<E> {
    class Node {
        E element;
        Node next;
    }

    private int size;
    private Node front, rear;

    public LinkedListQueue() {
        front = null;
        rear = null;
        size = 0;
    }
    @Override
    public void enqueue(E element) {
        Node newNode = new Node();
        newNode.element = element;
        if (this.rear == null) {
            this.front = this.rear = newNode;
            return;
        }
        this.rear.next = newNode;
        this.rear = newNode;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("The queue is empty");
        }
        E element = this.front.element;
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        }
        return element;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListQueueIterator();
    }
    class LinkedListQueueIterator implements Iterator<E> {
        private Node current = front;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = current.next;
            return element;
        }
    }
}
