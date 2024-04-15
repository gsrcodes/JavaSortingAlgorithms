package Lists;

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
            print.append(aux.getKey());
            aux = aux.getNext();
            if(aux != null)
                print.append(" - ");
        }
        System.out.println(print);
    }

    private Node getNode(int pos) { // get x node by position
        Node aux = start;
        for (int i = 0; i < pos; i++)
            aux = aux.getNext();
        return aux;
    }

    public int getPosition(Node nodeAux){ // get x position by node
        Node nodeI = start;
        int i = 0;
        while(nodeI != null && nodeI != nodeAux) {
            nodeI = nodeI.getNext();
            i++;
        }
        return i;
    }

    public Node setPosition(Node node, int currentPos, int finalPos)
    {
        Node nodeAux = node;
        int i;
        if(currentPos > finalPos) {
            i = currentPos-finalPos;
            while(nodeAux != null && i>0) {
                i--;
                nodeAux = nodeAux.getPrev();
            }
        }
        else if(currentPos < finalPos) {
            i = finalPos-currentPos;
            while(nodeAux != null && i>0) {
                i--;
                nodeAux = nodeAux.getNext();
            }
        }
        return nodeAux;
    }

    private void swaps(Node i, Node j) {
        int temp = i.getKey();
        i.setKey(j.getKey());
        j.setKey(temp);
    }

    public int binarySearch(int key, int LS) {
        int start = 0, end = LS - 1, middle = LS / 2;

        Node aux = getNode(middle);
        while (start < end && aux.getKey() != key) {
            if (aux.getKey() == key)
                return middle;
            else if (key > aux.getKey())
                start = middle + 1;
                else
                end = middle - 1;

            middle = (start + end) / 2;
            aux = getNode(middle);
        }
        if (key > aux.getKey())
            return middle + 1;
        return middle;
    }

    public int getLargest() {
        int i = 0, largest = 0;
        while (i < logicalSize) {
            if (getNode(i).getKey() > largest) {
                largest = getNode(i).getKey();
            }
            i++;
        }
        return largest;
    }

    public void fusion1 (List part1, List part2, int seq, int len) {
        Node  nodeI = part1.getStart(), nodeJ = part2.getStart(), startAux = start;
        int auxSeq = seq, startAuxPos=0, nodeIPos=0, nodeJPos=0;
        while(startAuxPos < len) {
            while(nodeIPos < seq && nodeJPos < seq) {
                if(nodeI.getKey() < nodeJ.getKey()) {
                    startAux.setKey(nodeI.getKey());
                    nodeI = nodeI.getNext();
                    nodeIPos++;
                }
                else {
                    startAux.setKey(nodeJ.getKey());
                    nodeJ = nodeJ.getNext();
                    nodeJPos++;
                }
                startAux = startAux.getNext();
                startAuxPos++;
            }
            while(nodeIPos < seq) {
                startAux.setKey(nodeI.getKey());
                startAux = startAux.getNext();
                startAuxPos++;
                nodeI = nodeI.getNext();
                nodeIPos++;
            }
            while(nodeJPos < seq) {
                startAux.setKey(nodeJ.getKey());
                startAux = startAux.getNext();
                startAuxPos++;
                nodeJ = nodeJ.getNext();
                nodeJPos++;
            }
            seq += auxSeq;
        }
    }

    public void fusion2(List part1, int start1, int end1, int start2, int end2) {
        part1.start = part1.end = null;
        Node nodeI = start, nodeJ = start, nodeK, nodeAUx = start;
        int nodeIPos = 0, nodeJPos = 0, nodeKPos = 0, nodeAuxPos = 0;

        nodeI = setPosition(nodeI, nodeIPos, start1);
        nodeIPos = start1;
        nodeJ = setPosition(nodeJ, nodeJPos, start2);
        nodeJPos = start2;

        while(nodeIPos<=end1 && nodeJPos<=end2)
            if(nodeI.getKey() < nodeJ.getKey()) {
                part1.insertEnd(nodeI.getKey());
                nodeI = nodeI.getNext();
                nodeIPos++;
                nodeKPos++;
            }
            else {
                part1.insertEnd(nodeJ.getKey());
                nodeJ = nodeJ.getNext();
                nodeJPos++;
                nodeKPos++;
            }
        while(nodeIPos <= end1 && nodeI.getNext() != null) {
            part1.insertEnd(nodeI.getKey());
            nodeI = nodeI.getNext();
            nodeIPos++;
            nodeKPos++;
        }
        while(nodeJPos <= end2 && nodeJ.getNext() != null) {
            part1.insertEnd(nodeJ.getKey());
            nodeJ = nodeJ.getNext();
            nodeJPos++;
            nodeKPos++;
        }
        nodeK = part1.getStart();
        int count = 0;
        while(count < nodeKPos) {
            nodeAUx = setPosition(nodeAUx, nodeAuxPos, count+start1);
            nodeAuxPos = count+start1;
            nodeAUx.setKey(nodeK.getKey());
            nodeK = nodeK.getNext();
            count++;
        }
    }

    // Sorting Algorithms

    public void insertionSort() {
        Node startSearch = this.start.getNext(), posPointer;
        int auxInt;

        while(startSearch != null) {
            auxInt = startSearch.getKey();
            posPointer = startSearch;
            while(posPointer != this.start && auxInt < posPointer.getPrev().getKey()){
                posPointer.setKey(posPointer.getPrev().getKey());
                posPointer = posPointer.getPrev();
            }

            posPointer.setKey(auxInt);
            startSearch = startSearch.getNext();
        }
    }

    public void binaryInsertionSort() {
        int aux, pos;
        for (int i = 1; i < logicalSize; i++) {
            aux = getNode(i).getKey();
            pos = binarySearch(aux, i);

            for (int j = i; j > pos; j--)
                getNode(j).setKey(getNode(j - 1).getKey());

            getNode(pos).setKey(aux);
        }
    }

    public void selectionSort() {
        Node current = start, compare, posSmaller;
        int aux;
        while (current.getNext() != null) {
            compare = current;
            posSmaller = current;

            while(compare != null) {
                if (compare.getKey() < posSmaller.getKey()) {
                    posSmaller = compare;
                }

                compare = compare.getNext();
            }

            aux = current.getKey();
            current.setKey(posSmaller.getKey());
            posSmaller.setKey(aux);
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
                if(current.getKey() > current.getNext().getKey()) {
                    swap = true;
                    auxInt = current.getNext().getKey();
                    current.getNext().setKey(current.getKey());
                    current.setKey(auxInt);
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
                if(current.getKey() > current.getNext().getKey()) {
                    swap = true;
                    auxInt = current.getNext().getKey();
                    current.getNext().setKey(current.getKey());
                    current.setKey(auxInt);
                }
                current = current.getNext();
            }
            end = end.getPrev();

            current = end;
            while(current != start) {
                if(current.getKey() < current.getPrev().getKey()) {
                    swap = true;
                    auxInt = current.getPrev().getKey();
                    current.getPrev().setKey(current.getKey());
                    current.setKey(auxInt);
                }
                current = current.getPrev();
            }
            start = start.getNext();
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
                if (ChildR < LS && nodeChildR.getKey() > nodeChildL.getKey())
                    largestChild = nodeChildR;
                else
                    largestChild = nodeChildL;

                if (nodeParent.getKey() < largestChild.getKey())
                    swaps(nodeParent, largestChild);
            }
            swaps(start, auxEnd);
            auxEnd = auxEnd.getPrev();
            LS--;
        }
    }

    public void shellSort() {
        int gap = 1, aux, i, j;
        while (gap < logicalSize)
            gap = gap * 3 + 1;
        gap = gap / 3;
        while (gap > 0) {
            Node current;
            for (i = gap; i < logicalSize; i++) {
                current = getNode(i);
                aux = current.getKey();
                j = i;

                Node nodeJ = getNode(j);
                Node nodeGap = getNode(j - gap);

                while (j - gap >= 0 && aux < getNode(j-gap).getKey()) {
                    nodeJ.setKey(nodeGap.getKey());
                    j = j - gap;

                    nodeJ = getNode(j);
                    nodeGap = getNode(j - gap);
                }
                getNode(j).setKey(aux);
            }
            gap /= 3;
        }
    }

    public void quickWithoutPivot() {
        this.quickWoutP(start, end);
    }
    private void quickWoutP(Node start, Node end) {
        Node i = start, j = end;
        while(i != null && i != j) {
            while(i !=j && i.getKey() <= j.getKey())
                i = i.getNext();
            if(j.getKey() != i.getKey()) {
                swaps(i, j);
                j = j.getPrev();
            }

            while(i != j && j.getKey() >= i.getKey())
                j = j.getPrev();
            if(j.getKey() != i.getKey()) {
                swaps(i, j);
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
        int i = start, j = end;
        Node nodeI, nodeJ;
        int pivot = getNode((i + j)/2).getKey();
        while(i <= j) {
            nodeI = getNode(i);
            while(nodeI.getKey() < pivot) {
                i++;
                nodeI = nodeI.getNext();
            }

            nodeJ = getNode(j);
            while(nodeJ.getKey() > pivot) {
                j--;
                nodeJ = nodeJ.getPrev();
            }

            if(i <= j) {
                swaps(nodeI, nodeJ);
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
                    if (getNode(i).getKey() > getNode(i + gap).getKey())
                        swaps(getNode(i), getNode(i + gap));
    }

    public void gnomeSort() {
        int aux;
        for (int i = 0; i < logicalSize - 1; i++)
            if (getNode(i).getKey() > getNode(i).getNext().getKey()) {
                aux = getNode(i).getKey();
                getNode(i).setKey(getNode(i).getNext().getKey());
                getNode(i).getNext().setKey(aux);
                i = -1;
                System.out.println(aux);
                System.out.println(i);
            }
    }

    public void countingSort() {
        int largest = getLargest();
        int[] array = new int[largest];
        int len = logicalSize;

        for(int i = 0; i < largest; i++)
            array[i] = 0;

        Node node = start;
        while(node != null) {
            array[node.getKey()-1]++; // frequency array
            node = node.getNext();
        }

        for(int i = 1; i < largest; i++) // counting array
            array[i] += array[i-1];

        List listAux = new List();
        for(int i = 0; i <= len; i++)
            listAux.insertEnd(0);

        node = start;
        Node nodeAux;
        while(node!= null) {
            nodeAux = listAux.getStart();
            int i=0;
            while(nodeAux != null && i < array[node.getKey()-1]-1) {
                i++;
                nodeAux = nodeAux.getNext();
            }
            array[node.getKey()-1]--;
            nodeAux.setKey(node.getKey());
            node = node.getNext();
        }

        node = start;
        nodeAux = listAux.getStart();
        while(node != null && nodeAux != null) {
            node.setKey(nodeAux.getKey());
            node = node.getNext();
            nodeAux = nodeAux.getNext();
        }
    }

    private void partition1(int len, List part1, List part2) {
        Node auxStart = start;
        Node auxMiddle = getNode(len/2);

        while(auxStart != auxMiddle) {
            part1.insertEnd(auxStart.getKey());
            auxStart = auxStart.getNext();
        }
        while(auxMiddle != null) {
            part2.insertEnd(auxMiddle.getKey());
            auxMiddle = auxMiddle.getNext();
        }
    }

    public void mergeSort1() {
        List part1 = new List();
        List part2 = new List();
        for (int seq = 1; seq < logicalSize; seq *= 2) {
            partition1(logicalSize, part1, part2);
            fusion1(part1, part2, seq, logicalSize);
        }
    }

    public void radixSort()
    {
        int maior = getLargest();
        for(int i=1; maior/i > 0; i*=10)
            radixCountingSort(i);
    }

    public void radixCountingSort(int v) {
        int pos, j, i;
        int[] array = new int[10];
        List listAux = new List();
        for(i=  0; i <= logicalSize; i++)
            listAux.insertEnd(0);

        Node nodeAux = start;
        for(i = 0; i < logicalSize; i++) {
            array[(nodeAux.getKey()/v)%10]++;
            nodeAux = nodeAux.getNext();
        }

        for(i = 1; i < 10; i++)
            array[i] += array[i-1];

        Node auxEnd = end;
        for(i = logicalSize; i > 0; i--)
        {
            pos = array[(auxEnd.getKey()/v) %10]-1;
            j = 0;
            nodeAux = listAux.getStart();
            while(nodeAux != null && j < pos) {
                nodeAux = nodeAux.getNext();
                j++;
            }
            nodeAux.setKey(auxEnd.getKey());
            array[(auxEnd.getKey()/v)%10]--;
            auxEnd = auxEnd.getPrev();
        }

        Node startAux = start;
        nodeAux = listAux.getStart();
        while (startAux != null && nodeAux != null)
        {
            startAux.setKey(nodeAux.getKey());
            startAux = startAux.getNext();
            nodeAux = nodeAux.getNext();
        }
    }

    public void bucketSort() {
        int largest = getLargest();
        int nBuckets = (int) Math.sqrt(largest), pos;
        int max = (largest-1)/nBuckets;
        BucketList bucketList = new BucketList();
        List listAux;
        Node nodeAux = start;
        Bucket b;

        for(int i=0; i<nBuckets; i++)
            bucketList.insertNewStart(i);

        while(nodeAux != null) {
            pos = (nodeAux.getKey()-1) / (max+1);
            listAux = bucketList.searchBucket(pos).getList();
            listAux.insertStart(nodeAux.getKey());
            listAux.insertionSort();
            nodeAux = nodeAux.getNext();
        }

        b = bucketList.getStart();
        Node node;
        nodeAux = start;
        while(b != null) {
            node = b.getList().getStart();
            while(node != null) {
                nodeAux.setKey(node.getKey());
                nodeAux = nodeAux.getNext();
                node = node.getNext();
            }
            b = b.getNext();
        }
    }

    public void timSortInsertionSort(int left, int right) {
        int nodeIPos = 0, nodeJPos, value;
        Node nodeI = start, nodeJ;
        nodeI = setPosition(nodeI, nodeIPos, nodeIPos = left);

        while(nodeIPos <= right) {
            nodeJ = nodeI;
            nodeJPos = nodeIPos;
            value = nodeI.getKey();
            while(nodeJPos >left && value < nodeJ.getPrev().getKey()) {
                nodeJ.setKey(nodeJ.getPrev().getKey());
                nodeJ = nodeJ.getPrev();
                nodeJPos--;
            }
            nodeJ.setKey(value);
            nodeI = nodeI.getNext();
            nodeIPos++;
        }
    }

    public void timSort() {
        int len = logicalSize - 1;
        int tim = 32;
        List part1 = new List();

        for(int i = 0; i < len; i += tim)
            timSortInsertionSort(i, Math.min((i + 31), len));

        for(int range = tim; range < len; range = 2*range)
            for(int left = 0; left < len; left += 2*range) {
                int middle = left + range-1;
                int right = Math.min((left+2 * range-1), len);
                fusion2(part1, left, middle, middle+1, right);
            }
    }

    public void Merge(List part1, int esq, int dir){
        int meio;
        if(esq < dir) {
            meio = (esq+dir)/2;
            Merge(part1, esq, meio);
            Merge(part1, meio+1, dir);
            fusion2(part1, esq, meio, meio+1, dir);
        }
    }

    public void mergeSort2() {
        List part1 = new List();
        int esq = 0, dir = getPosition(end);
        Merge(part1, esq, dir);
    }
}