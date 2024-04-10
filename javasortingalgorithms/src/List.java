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


    private void swaps(Node i, Node j) {
        int temp = i.getInfo();
        i.setInfo(j.getInfo());
        j.setInfo(temp);
    }

    public int binnarySearch(int key, int LS) {
        int start = 0, end = LS - 1, middle = LS / 2;

        Node aux = getNode(middle);
        while (start < end && aux.getInfo() != key) {
            if (aux.getInfo() == key)
                return middle;
            else if (key > aux.getInfo())
                start = middle + 1;
                else
                end = middle - 1;

            middle = (start + end) / 2;
            aux = getNode(middle);
        }
        if (key > aux.getInfo())
            return middle + 1;
        return middle;
    }

    public int getLargest() {
        int i = 0, largest = 0;
        while (i < logicalSize) {
            if (getNode(i).getInfo() > largest) {
                largest = getNode(i).getInfo();
            }
            i++;
        }
        return largest;
    }

    // Sort Algorithms

    public void InsertionSort() {
        int aux;
        Node i, pos;
        i = start.getNext();
        while (i != null) {
            aux = i.getInfo();
            pos = i;
            while (pos != start && aux < pos.getPrev().getInfo()) {
                pos.setInfo(pos.getPrev().getInfo());
                pos = pos.getPrev();
            }
            pos.setInfo(aux);
            i = i.getNext();
        }
    }

    public void binaryInsertionSort() {
        int aux, pos;
        for (int i = 1; i < logicalSize; i++) {
            aux = getNode(i).getInfo();
            pos = binnarySearch(aux, i);

            for (int j = i; j > pos; j--)
                getNode(j).setInfo(getNode(j - 1).getInfo());

            getNode(pos).setInfo(aux);
        }
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
        while(i != null && i != j) {
            while(i !=j && i.getInfo() <= j.getInfo())
                i = i.getNext();
            if(j.getInfo() != i.getInfo()) {
                swaps(i, j);
                j = j.getPrev();
            }

            while(i != j && j.getInfo() >= i.getInfo())
                j = j.getPrev();
            if(j.getInfo() != i.getInfo()) {
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
        int pivot = getNode((i + j)/2).getInfo();
        while(i <= j) {
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

    public void countingSort() {
        int maior = getLargest();
        int[] vet = new int[maior];
        int tam = logicalSize;

        for(int i=0; i<maior; i++)
            vet[i] = 0;

        Node aux = start;
        while(aux != null) {
            vet[aux.getInfo()-1]++; // frequency array
            aux = aux.getNext();
        }

        for(int i=1; i<maior; i++) // sum array
            vet[i] += vet[i-1];

        List listAux = new List();
        for(int i=0; i<=tam; i++)
            listAux.insertEnd(0);

        aux = start;
        Node nodeAux;
        while(aux!= null) {
            nodeAux = listAux.getStart();
            int i=0;
            while(nodeAux != null && i < vet[aux.getInfo()-1]-1) {
                i++;
                nodeAux = nodeAux.getNext();
            }
            vet[aux.getInfo()-1]--;
            nodeAux.setInfo(aux.getInfo());
            aux = aux.getNext();
        }

        aux = start;
        nodeAux = listAux.getStart();
        while(aux != null && nodeAux != null) {
            aux.setInfo(nodeAux.getInfo());
            aux = aux.getNext();
            nodeAux = nodeAux.getNext();
        }
    }

    private void partition1(int len, List part1, List part2) {
        Node auxStart = start;
        Node auxMiddle = getNode(len/2);

        while(auxStart != auxMiddle) {
            part1.insertEnd(auxStart.getInfo());
            auxStart = auxStart.getNext();
        }
        while(auxMiddle != null) {
            part2.insertEnd(auxMiddle.getInfo());
            auxMiddle = auxMiddle.getNext();
        }
    }

    public void fusion1 (List part1, List part2, int seq, int len) {
        Node  nodeI = part1.getStart(), nodeJ = part2.getStart(), startAux = start;
        int auxSeq = seq, startAuxPos=0, nodeIPos=0, nodeJPos=0;
        while(startAuxPos < len) {
            while(nodeIPos < seq && nodeJPos < seq) {
                if(nodeI.getInfo() < nodeJ.getInfo()) {
                    startAux.setInfo(nodeI.getInfo());
                    nodeI = nodeI.getNext();
                    nodeIPos++;
                }
                else {
                    startAux.setInfo(nodeJ.getInfo());
                    nodeJ = nodeJ.getNext();
                    nodeJPos++;
                }
                startAux = startAux.getNext();
                startAuxPos++;
            }
            while(nodeIPos < seq) {
                startAux.setInfo(nodeI.getInfo());
                startAux = startAux.getNext();
                startAuxPos++;
                nodeI = nodeI.getNext();
                nodeIPos++;
            }
            while(nodeJPos < seq) {
                startAux.setInfo(nodeJ.getInfo());
                startAux = startAux.getNext();
                startAuxPos++;
                nodeJ = nodeJ.getNext();
                nodeJPos++;
            }
            seq += auxSeq;
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

    public void radixCountingSort(int v) // radixSort
    {
        int pos, j, i;
        int[] vet = new int[10];
        List listAux = new List();
        for(i=  0; i <= logicalSize; i++)
            listAux.insertEnd(0);

        Node nodeAux = start;
        for(i = 0; i < logicalSize; i++) {
            vet[(nodeAux.getInfo()/v)%10]++;
            nodeAux = nodeAux.getNext();
        }

        for(i = 1; i < 10; i++)
            vet[i] += vet[i-1];

        Node auxEnd = end;
        for(i = logicalSize; i > 0; i--)
        {
            pos = vet[(auxEnd.getInfo()/v) %10]-1;
            j = 0;
            nodeAux = listAux.getStart();
            while(nodeAux != null && j < pos) {
                nodeAux = nodeAux.getNext();
                j++;
            }
            nodeAux.setInfo(auxEnd.getInfo());
            vet[(auxEnd.getInfo()/v)%10]--;
            auxEnd = auxEnd.getPrev();
        }

        Node startAux = start;
        nodeAux = listAux.getStart();
        while (startAux != null && nodeAux != null)
        {
            startAux.setInfo(nodeAux.getInfo());
            startAux = startAux.getNext();
            nodeAux = nodeAux.getNext();
        }
    }

    private void timInsertionSort(int left, int right) {
        int temp, j;
        for (int i = left + 1; i <= right; i++) {
            temp = getNode(i).getInfo();
            j = i - 1;
            while (getNode(j).getInfo() > temp && j >= left) {
                getNode(j + 1).setInfo(getNode(j).getInfo());
                j--;
            }
            getNode(j + 1).setInfo(temp);
        }
    }

    private void timMerge(int left, int middle, int right) {
        int len1 = middle - left + 1, len2 = right - middle, i = 0, j = 0, k = left;
        int[] leftArray = new int[len1];
        int[] rightArray = new int[len2];
        for (int x = 0; x < len1; x++)
            leftArray[x] = getNode(left + x).getInfo();
        for (int x = 0; x < len2; x++)
            rightArray[x] = getNode(middle + 1 + x).getInfo();

        while (i < len1 && j < len2) {
            if (leftArray[i] <= rightArray[j]) {
                getNode(k).setInfo(leftArray[i]);
                i++;
            }
            else {
                getNode(k).setInfo(rightArray[j]);
                j++;
            }
            k++;
        }

        while (i < len1) {
            getNode(k).setInfo(leftArray[i]);
            k++;
            i++;
        }

        while (j < len2) {
            getNode(k).setInfo(rightArray[j]);
            k++;
            j++;
        }
    }

    public void timSort() {
        int n = logicalSize, RUN = 32;
        int smaller;

        for (int i = 0; i < n; i = i + RUN) {
            smaller = Math.min(i + 31, n - 1); // smaller = math.min get the smaller between (x, y)
            timInsertionSort(i, smaller);
        }

        for (int size = RUN; size < n; size = 2 * size)
            for (int left = 0; left < n; left += 2 * size) {
                int middle = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                timMerge(left, middle, right);
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
            pos = (nodeAux.getInfo()-1) / (max+1);
            listAux = bucketList.searchBucket(pos).getList();
            listAux.insertStart(nodeAux.getInfo());
            listAux.insertionSort();
            nodeAux = nodeAux.getNext();
        }

        b = bucketList.getStart();
        while(b != null)
            b = b.getNext();

        b = bucketList.getStart();
        Node node;
        nodeAux = start;
        while(b != null) {
            node = b.getList().getStart();
            while(node != null) {
                nodeAux.setInfo(node.getInfo());
                nodeAux = nodeAux.getNext();
                node = node.getNext();
            }
            b = b.getNext();
        }
    }
}