public class List {
    Node start;
    Node end;

    public List() {
        this.start = null;
        this.end = null;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public void insertStart (int info) {
        Node newNode = new Node(start, null, info);
        if(start == null)
            start = end = newNode;
        else {
            start.setPrev(newNode);
            start = newNode;
        }
    }

    public void insertEnd(int info) {
        Node newNode = new Node(null, end, info);
        if(end == null)
            end = start = newNode;
        else {
            end.setNext(newNode);
            end = newNode;
        }
    }

    public void display() {
        Node aux = start;
        StringBuilder print = new StringBuilder();
        while(aux != null){
            print.append(aux.getInfo());
            aux = aux.getNext();
            if(aux != null)
                print.append(" - ");
        }
        System.out.println(print);
    }
}