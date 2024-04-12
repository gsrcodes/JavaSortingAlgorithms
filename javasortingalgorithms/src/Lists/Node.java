package Lists;

public class Node {
    private Node next;
    private Node prev;
    private int key;

    public Node(Node next, Node prev, int key) {
        this.next = next;
        this.prev = prev;
        this.key = key;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
