package Files;

import Lists.Stack;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class File {
    private String fileName;
    private RandomAccessFile file;

    int comparisons, movements;

    public void initComparisons() {
        comparisons = 0;
    }
    public void initMovements(){
        movements = 0;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }

    public int getMovements() {
        return movements;
    }

    public void setMovements(int movements) {
        this.movements = movements;
    }

    public File(String archiveName) {
        try {
            file = new RandomAccessFile(archiveName, "rw");
        } catch (IOException e){}
    }

    public RandomAccessFile getFile(){
        return file;
    }

    public void seek(int pos) {
        try {
            file.seek(pos * Record.length());
        }catch (IOException e){}
    }

    public void close() throws IOException {
        file.close();
    }

    public int filesize() {
        try {
            return (int) (file.length() / Record.length());
        } catch (IOException e){}
        return 0;
    }

    public boolean eof() {
        boolean bo = false;
        try {
            if (file.getFilePointer() == file.length())
                bo = true;
        } catch (IOException e){}
        return (bo);
    }

    public void truncate(long pos) {
        try {
            file.setLength(pos * Record.length());
        } catch (IOException exc){}
    }

    public void swaps(int i, int j) {
        Record record1 = new Record();
        Record record2 = new Record();
        seek(i);
        record1.read(file);
        seek(j);
        record2.read(file);
        seek(i);
        record2.write(file);
        seek(j);
        record1.write(file);
    }

    public void insertStart(int key) {
        Record novo = new Record(key);
        seek(filesize());
        novo.write(file);
    }

    public void display() {
        int i;
        Record aux = new Record();
        seek(0);
        i = 0;
        while (!eof()) {
            aux.read(file);
            aux.display();
            i++;
        }
        System.out.println("\n");
    }

    public int getLargest() {
        int largest;
        Record recordAux = new Record();
        seek(0);
        largest = recordAux.getKey();
        for (int i = 1; i < filesize(); i++) {
            recordAux.read(file);
            comparisons++;
            if (largest < recordAux.getKey())
                largest = recordAux.getKey();
        }
        return largest;
    }

    public void copyFile(File file) {
        Record record = new Record();
        for(int i = 0; i < file.filesize(); i++) {
            file.seek(i);
            record.read(file.getFile());
            seek(i);
            record.write(this.file);
        }
    }

    public void createSortedFile(int len) {
        Record record;
        int i = 0;
        while(i < len) {
            record = new Record(i+1);
            seek(i);
            record.write(file);
            i++;
        }
        truncate(len);
    }

    public void createReverseFile(int len) {
        Record record;
        int i = 0;
        while(len - 1 >= 0) {
            record = new Record(len);
            seek(i);
            record.write(file);
            i++;
            len--;
        }
        truncate(i);
    }

    public void createRandomFile(int len) {
        Record recordAux;
        int i = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(i=0; i < len; i++)
            list.add(i+1);

        Collections.shuffle(list);
        for(i=0; i < len; i++) {
            recordAux = new Record(list.get(i));
            seek(i);
            recordAux.write(file);
        }
        truncate(len);
    }

    private int binarySearch(Record record, int LS) {
        int start = 0, end = LS - 1, middle = LS / 2;
        Record recordAux = new Record();
        seek(middle);
        recordAux.read(file);

        comparisons++;
        while (start < end && record.getKey() != recordAux.getKey()) {
            comparisons++;
            if (record.getKey() < recordAux.getKey())
                end = middle - 1;
            else
                start = middle + 1;
            middle = (start + end) / 2;

            seek(middle);
            recordAux.read(file);
        }
        comparisons++;
        if (record.getKey() < recordAux.getKey())
            return middle;

        return middle + 1;
    }

    public void insertionSort() {
        int FS = filesize();
        int pos;
        Record record1 = new Record();
        Record record2 = new Record();
        for (int i = 1; i < FS; i++) {
            pos = i;
            seek(pos - 1);
            record2.read(file);
            record1.read(file);

            comparisons++;
            while (pos > 0 && record1.getKey() < record2.getKey()) {
                seek(pos);
                record2.write(file);
                movements++;
                seek(--pos - 1);
                record2.read(file);
                comparisons++;
            }
            
            seek(pos);
            record1.write(file);
            movements++;
        }
    }

    public void binaryInsertionSort() {
        int TL = filesize();
        Record record1 = new Record();
        Record record2 = new Record();
        int pos;
        for (int i = 1; i < TL; i++) {
            seek(i);
            record1.read(file);
            pos = binarySearch(record1, i);
            for (int j = i; j > pos; j--) {
                seek(j - 1);
                record2.read(file);
                record2.write(file);
                movements++;
            }
            seek(pos);
            movements++;
            record1.write(file);
        }
    }

    public void selectionSort() {
        Record smaller = new Record();
        Record record = new Record();
        int smallerPos;
        for (int i = 0; i < filesize() - 1; i++) {
            seek(i);
            smaller.read(file);
            smallerPos = i;
            for (int j = i + 1; j < filesize(); j++) {
                record.read(file);
                comparisons++;
                if (smaller.getKey() > record.getKey()) {
                    seek(j);
                    smaller.read(file);
                    smallerPos = j;
                }
            }
            seek(i);
            record.read(file);

            seek(smallerPos);
            record.write(file);
            movements++;

            seek(i);
            smaller.write(file);
            movements++;
        }
    }

    public void bubbleSort() {
        Record record1 = new Record();
        Record record2 = new Record();
        int i, LS = filesize();
        while(LS > 1) {
            for(i=0;i<LS-1;i++) {
                seek(i);
                record1.read(file);
                record2.read(file);
                comparisons++;
                if(record1.getKey() > record2.getKey()) {
                    seek(i);
                    movements += 2;
                    record2.write(file);
                    record1.write(file);
                }
            }
            LS--;
        }
    }

    public void shakeSort() {
        Record reg1 = new Record();
        Record reg2 = new Record();
        int start = 0, end = filesize()-1;
        while(start < end) {
            for (int i = start; i < end; i++) {
                seek(i);
                reg1.read(file);
                reg2.read(file);
                comparisons++;
                if (reg1.getKey() > reg2.getKey()) {
                    movements += 2;
                    seek(i);
                    reg2.write(file);
                    reg1.write(file);
                }
            }
            end--;

            for (int i = end; i > start; i--) {
                seek(i-1);
                reg2.read(file);
                reg1.read(file);
                comparisons++;
                if (reg1.getKey() < reg2.getKey()) {
                    movements += 2;
                    seek(i);
                    reg2.write(file);
                    seek(i-1);
                    reg1.write(file);
                }
            }
            start++;
        }
    }

    public void heapSort() {
        Record record1 = new Record();
        Record record2 = new Record();
        int parent, CL, CR, largest, LS;
        LS = filesize();
        while (LS > 1) {
            parent = LS / 2 - 1;
            while (parent >= 0) {
                CL = 2 * parent + 1;
                CR = CL + 1;
                largest = CL;

                seek(CL);
                record1.read(file);
                record2.read(file);

                comparisons++;
                if (CR < LS && record1.getKey() < record2.getKey())
                    largest = CR;

                seek(largest);
                record1.read(file);
                seek(parent);
                record2.read(file);

                comparisons++;
                if (record1.getKey() > record2.getKey()) {
                    seek(parent);
                    record1.write(file);
                    seek(largest);
                    record2.write(file);
                    movements += 2;
                }
                parent--;
            }
            movements += 2;
            swaps(0, LS - 1);
            LS--;
        }
    }

    public void shellSort() {
        Record record1 = new Record();
        Record record2 = new Record();
        int i, j, k, dist;
        for (dist = 4; dist > 0; dist /= 2)
            for (i = 0; i < dist; i++)
                for (j = i; j + dist < filesize(); j += dist) {
                    seek(j);
                    record1.read(file);
                    seek(j + dist);
                    record2.read(file);
                    comparisons++;
                    if (record1.getKey() > record2.getKey()) {
                        movements += 2;
                        swaps(j, j + dist);
                        k = j;
                        seek(k);
                        record1.read(file);
                        seek(k - dist);
                        record2.read(file);
                        comparisons++;
                        while (k - dist >= i && record1.getKey() < record2.getKey()) {
                            comparisons++;
                            movements += 2;
                            swaps(k, k - dist);
                            k -= dist;
                            seek(k);
                            record1.read(file);
                            seek(k - dist);
                            record2.read(file);
                        }

                    }
                }
    }

    public void gnomeSort() {
        int i = 0;
        Record record1 = new Record();
        Record record2 = new Record();

        while (i < filesize()) {
            seek(i - 1);
            record2.read(file);
            record1.read(file);
            comparisons++;
            if (i != 0 && record1.getKey() < record2.getKey()) {
                movements += 2;
                seek(i - 1);
                record2.read(file);
                record1.read(file);

                seek(i - 1);
                record1.write(file);
                record2.write(file);
                i--;
            }
            else
                i++;
        }
    }

    public void combSort() {
        int gap = filesize();
        boolean troca = true;
        Record record1 = new Record();
        Record record2 = new Record();

        while (gap != 1 || troca) {
            gap /= 1.3;
            if (gap < 1)
                gap = 1;
            troca = false;
            for (int i = 0; i < filesize() - gap; i++) {
                seek(i);
                record1.read(file);
                seek(i + gap);
                record2.read(file);
                comparisons++;
                if (record1.getKey() > record2.getKey()) {
                    swaps(i, i + gap);
                    movements += 2;
                    troca = true;
                }
            }
        }
    }

    public void quickSort() {
        quickSortWithoutPivot(0, filesize() - 1);
    }

    public void quickSortWithoutPivot(int start, int end) {
        int i = start, j = end;
        boolean flag = true;
        Record record1 = new Record();
        Record record2 = new Record();

        while(i < j) {
            seek(i);
            record1.read(file);
            seek(j);
            record2.read(file);
            if(flag) {
                comparisons++;
                while(i < j && record1.getKey() < record2.getKey()) {
                    i++;
                    seek(i);
                    record1.read(file);
                    comparisons++;
                }
            }
            else {
                comparisons++;
                while(i < j && record2.getKey() > record1.getKey()) {
                    j--;
                    seek(j);
                    record2.read(file);
                    comparisons++;
                }
            }
            movements +=2;
            swaps(i,j);
            flag = !flag;
        }
        if(start < i-1)
            quickSortWithoutPivot(start, i-1);
        if(j+1 < end)
            quickSortWithoutPivot(j+1, end);
    }

    public void quickSortWithPivot() {
        quickSortPivot(0, filesize() - 1);
    }
    public void quickSortPivot(int start, int end) {
        Record record1 = new Record();
        Record record2 = new Record();
        Record pivot = new Record();
        int i = start;
        int j = end;
        int value = (start + end) / 2;
        seek(value);
        pivot.read(file);
        while (i < j) {
            seek(i);
            record1.read(file);
            comparisons++;
            while (record1.getKey() < pivot.getKey()) {
                i++;
                seek(i);
                record1.read(file);
                comparisons++;
            }
            seek(j);
            record2.read(file);
            comparisons++;
            while (record2.getKey() > pivot.getKey()) {
                j--;
                seek(j);
                record2.read(file);
                comparisons++;
            }
            if (i <= j) {
                seek(i);
                record2.write(file);
                seek(j);
                record1.write(file);
                movements += 2;
                i++;
                j--;
            }
        }
        if (start < j)
            quickSortPivot(start, j);
        if (i < end)
            quickSortPivot(i, end);
    }

    public void countingSort() {
        int largest = getLargest() + 1;
        int array[] = new int[largest];
        int pos;
        Record record = new Record();

        for(int i = 0; i < largest; i++)
            array[i]=0;
        for(int i = 0; i < filesize(); i++) {
            seek(i);
            record.read(file);
            array[record.getKey()-1]++;
        }
        for(int i=1; i<largest; i++)
            array[i] += array[i-1];

        int array2[] = new int[filesize()];
        for(int i=0; i<filesize(); i++) {
            seek(i);
            record.read(file);
            pos = record.getKey();
            array2[array[pos-1]-1] = pos;
            array[pos-1]--;
        }

        for(int i=0; i<filesize(); i++) {
            record.setKey(array2[i]);
            seek(i);
            record.write(file);
            movements++;
        }
    }

    public void radixSort() {
        int largest = getLargest();
        for(int i = 1; largest/i > 0; i *= 10)
            countingSortRadix(i);
    }

    public void countingSortRadix(int x) {
        int LS = filesize(), i, pos, xa;
        int[] array = new int[10];
        int[] array2 = new int[LS];
        Record reg = new Record();

        i = 0;
        while(i < LS) {
            seek(i);
            reg.read(file);
            array[(reg.getKey()/x)%10]++;
            i++;
        }

        for(i = 1; i < 10; i++)
            array[i] += array[i-1];

        i=LS-1;
        while(i >= 0) {
            seek(i);
            reg.read(file);
            pos = reg.getKey();
            xa = array[(pos/x) %10]-1;
            array2[xa] = pos;
            array[(pos/x)%10]--;
            i--;
        }

        for(i = 0; i < LS; i++) {
            reg.setKey(array2[i]);
            seek(i);
            reg.write(file);
            movements++;
        }
    }

    public void mergeSort2() {
        int mid, left, right;
        Lists.Stack stack1 = new Lists.Stack();
        Lists.Stack stack2 = new Stack();
        File auxFile = new File("auxMerge.dat");
        stack1.push(0);
        stack1.push(filesize() - 1);
        while (!stack1.isEmpty()) {
            right = stack1.pop();
            left = stack1.pop();

            if (left < right) {
                stack2.push(left);
                stack2.push(right);
                mid = (left + right) / 2;
                stack1.push(left);
                stack1.push(mid);
                stack1.push(mid + 1);
                stack1.push(right);
            }
        }

        while (!stack2.isEmpty()) {
            right = stack2.pop();
            left = stack2.pop();
            mid = (left + right) / 2;

            fusion(auxFile, left, mid, mid + 1, right);
        }
        auxFile.truncate(0);
    }

    private void fusion(File aux, int start1, int end1, int start2, int end2) {
        int k = 0;
        int i = start1;
        int j = start2;
        Record record1 = new Record();
        Record record2 = new Record();

        aux.seek(0);

        while (i <= end1 && j <= end2) {
            seek(i);
            record1.read(file);
            seek(j);
            record2.read(file);

            comparisons++;
            if (record1.getKey() < record2.getKey()) {
                seek(k++);
                record1.write(aux.getFile());
                i++;
            } else {
                seek(k++);
                record2.write(aux.getFile());
                j++;
            }
        }

        while (i <= end1) {
            seek(i++);
            record1.read(file);

            seek(k++);
            record1.write(aux.getFile());
        }

        while (j <= end2) {
            seek(j++);
            record2.read(file);

            seek(k++);
            record2.write(aux.getFile());
        }

        aux.seek(0);
        for (i = 0; i < k; i++) {
            seek(i + start1);
            movements++;
            record1.read(aux.getFile());
            record1.write(file);
        }
        aux.truncate(0);
    }

    public void timSort_insertionSort(int left, int right) {
        int j;
        Record record1 = new Record();
        Record record2 = new Record();

        for (int i = left; i <= right; i++) {
            j = i;
            seek(i);
            record1.read(file);
            seek(j - 1);
            record2.read(file);

            comparisons++;
            while (j > left && record1.getKey() < record2.getKey()) {
                seek(j);
                record2.write(file);
                movements++;
                j--;
                seek(j - 1);
                record2.read(file);
                comparisons++;
            }
            seek(j);
            record1.write(file);
            movements++;
        }
    }

    public void timSort() {
        int size = filesize() - 1;
        int timSize = 32;
        File auxFile = new File("timSortAuxFile.dat");

        for (int i = 0; i <= size; i += timSize)
            timSort_insertionSort(i, Math.min((i + 31), size));

        for (int range = timSize; range <= size; range = 2 * range) {
            for (int left = 0; left <= size; left += 2 * range) {
                int middle = left + range - 1;
                int right = Math.min((left + 2 * range - 1), size);
                fusion(auxFile, left, middle, middle + 1, right);
            }
        }
    }

    public void bucketSort() {
        int largest = getLargest()+1;
        int nBuckets = (int) Math.sqrt(largest), pos;
        int max = (largest-1)/nBuckets;
        BucketList lb = new BucketList();
        Record reg = new Record();
        List listaAux;
        Bucket b;

        for(int i = 0; i < nBuckets; i++)
            lb.insertEnd(i);

        int i=0;
        while(i < filesize()) {
            seek(i);
            reg.read(file);
            pos = (reg.getKey()-1) / (max+1);
            listaAux = lb.searchBucket(pos).getList();
            listaAux.insertEnd(reg);
            listaAux.insertionSort();
            comparisons += listaAux.getComparisons();
            i++;
        }

        b = lb.getStart();
        Node node;
        i = 0;
        while(b != null) {
            node = b.getList().getStart();
            while(node != null) {
                seek(i);
                reg = node.getRecord();
                movements++;
                reg.write(file);
                node = node.getNext();
                i++;
            }
            b = b.getNext();
        }
    }

    public void fusion(File file1, File file2, int seq) {
        int i = 0, j = 0, k = 0, auxSeq = seq;
        Record record1 = new Record();
        Record record2 = new Record();
        while (k < filesize()) {
            while (i < seq && j < seq) {
                file1.seek(i);
                record1.read(file1.getFile());
                file2.seek(j);
                record2.read(file2.getFile());
                movements++;
                if (record1.getKey() < record2.getKey()) {
                    i++;
                    seek(k++);
                    record1.write(file);
                    movements++;
                }
                else {
                    j++;
                    seek(k++);
                    record2.write(file);
                    movements++;
                }
            }

            while (i < seq) {
                file1.seek(i++);
                record1.read(file1.getFile());
                seek(k++);
                record1.write(file);
                movements++;
            }
            while (j < seq) {
                file2.seek(j++);
                record2.read(file2.getFile());
                seek(k++);
                record2.write(file);
                movements++;
            }
            seq = seq + auxSeq;
        }
    }

    public void partition(File file1, File file2){
        int size = filesize() / 2;
        Record record = new Record();
        for (int i = 0; i < size; i++) {
            seek(i);
            record.read(file);
            file1.seek(i);
            record.write(file1.getFile());

            seek(i + size);
            record.read(file);
            file2.seek(i);
            record.write(file2.getFile());

            movements += 2;
        }
        file1.truncate(size);
        file2.truncate(size);
    }

    public void mergeSort() {
        int seq = 1;
        File file1 = new File("mergeFile1.dat");
        File file2 = new File("mergeFile2.dat");
        while (seq < filesize()) {
            partition(file1, file2);
            fusion(file1, file2, seq);
            seq = seq * 2;
        }
    }
}