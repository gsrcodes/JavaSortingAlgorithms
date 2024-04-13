package Files;

public class Bucket {
    private Bucket next;
    private Bucket prev;
    private List list;
    private int key;

    public Bucket(Bucket next, Bucket prev, List list, int key) {
        this.next = next;
        this.prev = prev;
        if(list == null)
            this.list = new List();
        else
            this.list = list;
        this.key = key;
    }

    public Bucket getNext() {
        return next;
    }

    public void setNext(Bucket next) {
        this.next = next;
    }

    public Bucket getPrev() {
        return prev;
    }

    public void setPrev(Bucket prev) {
        this.prev = prev;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}