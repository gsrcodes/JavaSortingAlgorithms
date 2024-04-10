public class Bucket{
    private List list;
    private Bucket next;
    private Bucket prev;
    private int key;
    public Bucket(List list, Bucket next, Bucket prev, int key) {
        this.list = list;
        this.next = next;
        this.prev = prev;
        this.key = key;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
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

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}