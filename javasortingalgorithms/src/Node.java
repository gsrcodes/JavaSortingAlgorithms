public class Node {
    private Node next;
    private Node prev;
    private int info;

    public Node(Node next, Node prev, int info) {
        this.next = next;
        this.prev = prev;
        this.info = info;
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

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}
