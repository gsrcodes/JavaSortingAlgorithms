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

    public void selectionSort() {
        Node current = start, compare, posSmaller;
        int aux;
        while (current.getNext() != null) {
            compare = current;
            posSmaller = current;

            while(compare != null) {
                if (compare.getInfo() < posSmaller.getInfo()) {
                    posSmaller = compare;
                }

                compare = compare.getNext();
            }

            aux = current.getInfo();
            current.setInfo(posSmaller.getInfo());
            posSmaller.setInfo(aux);
            current = current.getNext();
        }
    }

    public void bubbleSort() {
        Node end = this.end, current;
        int auxInt;
        boolean swap = true;
        while(end != this.start.getNext() && swap) {
            current = start;
            swap = false;
            while(current != end) {
                if(current.getInfo() > current.getNext().getInfo()) {
                    swap = true;
                    auxInt = current.getNext().getInfo();
                    current.getNext().setInfo(current.getInfo());
                    current.setInfo(auxInt);
                }
                current = current.getNext();
            }
            end = end.getPrev();
        }
    }

    public void shakeSort() {
        Node end = this.end, start = this.start, current;
        int auxInt;
        boolean swap = true;
        while(end != start && swap) {
            current = start;
            swap = false;
            while(current != end) {
                if(current.getInfo() > current.getNext().getInfo()) {
                    swap = true;
                    auxInt = current.getNext().getInfo();
                    current.getNext().setInfo(current.getInfo());
                    current.setInfo(auxInt);
                }
                current = current.getNext();
            }
            end = end.getPrev();

            current = end;
            while(current != start) {
                if(current.getInfo() < current.getPrev().getInfo()) {
                    swap = true;
                    auxInt = current.getPrev().getInfo();
                    current.getPrev().setInfo(current.getInfo());
                    current.setInfo(auxInt);
                }
                current = current.getPrev();
            }
            start = start.getNext();
        }
    }
}