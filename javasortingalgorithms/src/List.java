public class List {
    Node start;
    Node end;

    int logicalSize;

    public List() {
        this.start = null;
        this.end = null;
        logicalSize = 0;
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
        logicalSize++;
    }

    public void insertEnd(int info) {
        Node newNode = new Node(null, end, info);
        if(end == null)
            end = start = newNode;
        else {
            end.setNext(newNode);
            end = newNode;
        }
        logicalSize++;
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

    private Node getNode(int pos) { // Get x node by position
        Node aux = start;
        for (int i = 0; i < pos; i++)
            aux = aux.getNext();
        return aux;
    }


    private void swaps(Node one, Node two) {
        int temp = one.getInfo();
        one.setInfo(two.getInfo());
        two.setInfo(temp);
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

    public void insertionSort() {
        Node startSearch = this.start.getNext(), posPointer;
        int auxInt;

        while(startSearch != null) {
            auxInt = startSearch.getInfo();
            posPointer = startSearch;
            while(posPointer != this.start && auxInt < posPointer.getPrev().getInfo()){
                posPointer.setInfo(posPointer.getPrev().getInfo());
                posPointer = posPointer.getPrev();
            }

            posPointer.setInfo(auxInt);
            startSearch = startSearch.getNext();
        }
    }

    public void heapSort() {
        int ChildL, ChildR, parent;
        int LS = logicalSize; // Logical Size
        Node nodeChildL;
        Node nodeChildR;
        Node nodeParent;
        Node largestChild;
        Node auxEnd = end;

        while (LS > 1) {
            for (parent = LS / 2 - 1; parent >= 0; parent--) {
                // Positions
                ChildL = parent * 2 + 1;
                ChildR = parent * 2 + 2;

                // Nodes
                nodeChildL = getNode(ChildL);
                nodeChildR = getNode(ChildR);
                nodeParent = getNode(parent);

                // get the largest child
                if (ChildR < LS && nodeChildR.getInfo() > nodeChildL.getInfo())
                    largestChild = nodeChildR;
                else
                    largestChild = nodeChildL;

                if (nodeParent.getInfo() < largestChild.getInfo())
                    swaps(nodeParent, largestChild);
            }
            swaps(start, auxEnd);
            auxEnd = auxEnd.getPrev();
            LS--;
        }
    }

    public void shellSort() {
        for (int gap = logicalSize / 2; gap > 0; gap /= 2) // gap = distance between elements to be compared
            for (int i = gap; i < logicalSize; i++) {
                int current = getNode(i).getInfo();
                int j = i;
                while (j >= gap && getNode(j - gap).getInfo() > current) {
                    getNode(j).setInfo(getNode(j - gap).getInfo());
                    j -= gap;
                }
                getNode(j).setInfo(current);
            }
    }

    public void quickWithoutPivot() {
        this.quickWoutP(start, end);
    }
    private void quickWoutP(Node start, Node end) {
        Node i = start, j = end;
        int aux;
        while(i != null && i != j) {
            while(i !=j && i.getInfo() <= j.getInfo())
                i = i.getNext();
            if(j.getInfo() != i.getInfo()) {
                aux = i.getInfo();
                i.setInfo(j.getInfo());
                j.setInfo(aux);
                j = j.getPrev();
            }

            while(i != j && j.getInfo() >= i.getInfo())
                j = j.getPrev();

            if(j.getInfo() != i.getInfo()) {
                aux = i.getInfo();
                i.setInfo(j.getInfo());
                j.setInfo(aux);
                i = i.getNext();
            }

        }

        if(start != i)
            quickWoutP(start, i.getPrev());
        if(end != j)
            quickWoutP(j.getNext(), end);
    }

    public void quickWithPivot() {
        quickWithP(0, logicalSize - 1);
    }
    private void quickWithP(int start, int end) {
        int i = start, j = end, temp;
        Node nodeI, nodeJ;
        int pivot = getNode((i + j)/2).getInfo();
        while(i < j) {
            nodeI = getNode(i);
            while(nodeI.getInfo() < pivot) {
                i++;
                nodeI = nodeI.getNext();
            }

            nodeJ = getNode(j);
            while(nodeJ.getInfo() > pivot) {
                j--;
                nodeJ = nodeJ.getPrev();
            }

            if(i <= j) {
                temp = nodeI.getInfo();
                nodeI.setInfo(nodeJ.getInfo());
                nodeJ.setInfo(temp);
                i++;
                j--;
            }
        }
        if(start < j)
            quickWithP(start, j);
        if(i < end)
            quickWithP(i, end);
    }

    // This implementation is not as optimized as other sorting algorithms,
    // but it's kept concise with minimum lines of code.
    public void combSort() {
        for (int gap = logicalSize; gap >= 1; gap = (int) (gap / 1.3))
            for (int i = 0; i < logicalSize; i++)
                if (i + gap < logicalSize)
                    if (getNode(i).getInfo() > getNode(i + gap).getInfo())
                        swaps(getNode(i), getNode(i + gap));
    }

    public void gnomeSort() {
        int aux;
        for (int i = 0; i < logicalSize - 1; i++) {
            if (getNode(i).getInfo() > getNode(i).getNext().getInfo()) {
                aux = getNode(i).getInfo();
                getNode(i).setInfo(getNode(i).getNext().getInfo());
                getNode(i).getNext().setInfo(aux);
                i = -1;
                System.out.println(aux);
            }
        }
    }
}