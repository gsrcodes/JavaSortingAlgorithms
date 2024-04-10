public class BucketList {
    private Bucket start;
    private Bucket end;

    public BucketList() {
        start = null;
        end = null;
    }

    public Bucket getStart() {
        return start;
    }

    public void insertNewStart(int key) {
        List list = new List();
        Bucket newBucket = new Bucket(list, null, end, key);
        if(start == null)
            start = end = newBucket;
        else {
            end.setNext(newBucket);
            end = newBucket;
        }
    }

    public Bucket searchBucket(int pos) {
        Bucket bucketAux = start;
        if(bucketAux.getKey() == pos)
            return bucketAux;
        else {
            while(bucketAux != null && bucketAux.getKey() != pos)
                bucketAux = bucketAux.getNext();
            if(bucketAux != null)
                return bucketAux;
            else
                return null;
        }
    }


}