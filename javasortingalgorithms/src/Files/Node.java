package Files;

public class Node {
    private Node next;
    private Node prev;
    private Record record;

    public Node(Node next, Node prev, Record record) {
        this.next = next;
        this.prev = prev;
        this.record = new Record(record.getKey());
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

    public Record getRecord() {
        return this.record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}