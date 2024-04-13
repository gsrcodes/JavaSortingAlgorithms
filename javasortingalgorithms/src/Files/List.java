package Files;

public class List {
    private Node start;
    private Node end;
    private int comparisons;

    public List() {
        comparisons = 0;
    }

    public int getComparisons() {
        return this.comparisons;
    }

    public Node getStart() {
        return this.start;
    }

    public void insertEnd(Record record) {
        Node newNode = new Node(null, end, record);
        if(start == null)
            start = end = newNode;
        else {
            end.setNext(newNode);
            end = newNode;
        }
    }

    public void insertionSort() {
        Node nodeI = start.getNext(), nodeJ;
        Record reg;
        while(nodeI != null) {
            reg = nodeI.getRecord();
            nodeJ = nodeI;
            comparisons++;
            while(nodeJ != start && reg.getKey() < nodeJ.getPrev().getRecord().getKey()) {
                comparisons++;
                nodeJ.setRecord(nodeJ.getPrev().getRecord());
                nodeJ = nodeJ.getPrev();
            }
            nodeJ.setRecord(reg);
            nodeI = nodeI.getNext();
        }
    }
    public void display() {
        Node aux = start;
        while(aux != null) {
            System.out.print(aux.getRecord().getKey()+" ");
            aux = aux.getNext();
        }
        System.out.println("");
    }
}