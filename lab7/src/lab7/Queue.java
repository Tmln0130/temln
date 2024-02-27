package lab7;
import java.util.LinkedList;

public class Queue<T> {
    private LinkedList<T> list;

    public Queue() {
        list = new LinkedList<>();
    }

    public void enqueue(T item) {
        list.addLast(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    public T front() {
        if (isEmpty()) {
            return null;
        }
        return list.getFirst();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void display() {
        for (T item : list) {
            System.out.println(item);
        }
    }

    public void clear() {
        list.clear();
    }
}
