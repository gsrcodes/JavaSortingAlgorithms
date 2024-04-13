package Files;

public class BucketList {
    private Bucket start;
    private Bucket end;

    public BucketList() {

    }

    public Bucket getStart() {
        return this.start;
    }

    public void insertEnd(int key) {
        Bucket newBucket = new Bucket(null, end, null, key);
        if(start == null)
            start = end = newBucket;
        else {
            end.setNext(newBucket);
            end = newBucket;
        }
    }

    public Bucket searchBucket(int pos) {
        Bucket aux = start;
        if(start.getKey() != pos)
            while(aux != null && aux.getKey() != pos)
                aux = aux.getNext();
        return aux;
    }
}