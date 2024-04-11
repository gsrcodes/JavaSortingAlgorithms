package Files;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class File {
    private String fileName;
    private RandomAccessFile file;
    int comparasions, permutations;

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

        comparasions++;
        while (start < end && record.getKey() != recordAux.getKey()) {
            comparasions++;
            if (record.getKey() < recordAux.getKey())
                end = middle - 1;
            else
                start = middle + 1;
            middle = (start + end) / 2;

            seek(middle);
            recordAux.read(file);
        }
        comparasions++;
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

            comparasions++;
            while (pos > 0 && record1.getKey() < record2.getKey()) {
                seek(pos);
                record2.write(file);
                permutations++;
                seek(--pos - 1);
                record2.read(file);
                comparasions++;
            }
            seek(pos);
            record1.write(file);
            permutations++;
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
                comparasions++;
            }
            seek(pos);
            record1.write(file);
            comparasions++;
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
                comparasions++;
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

            seek(i);
            smaller.write(file);
            permutations += 2;
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
                comparasions++;
                if(record1.getKey() > record2.getKey()) {
                    seek(i);
                    permutations += 2;
                    record2.write(file);
                    record1.write(file);
                }
            }
            LS--;
        }
    }

    public void shakeSort()
    {
        Record reg1 = new Record();
        Record reg2 = new Record();
        int start = 0, end = filesize()-1;
        while(start < end)
        {
            for (int i = start; i < end; i++)
            {
                seek(i);
                reg1.read(file);
                reg2.read(file);
                comparasions++;
                if (reg1.getKey() > reg2.getKey())
                {
                    permutations += 2;
                    seek(i);
                    reg2.write(file);
                    reg1.write(file);
                }
            }
            end--;

            for (int i = end; i > start; i--)
            {
                seek(i-1);
                reg2.read(file);
                reg1.read(file);
                comparasions++;
                if (reg1.getKey() < reg2.getKey())
                {
                    permutations += 2;
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

                comparasions++;
                if (CR < LS && record1.getKey() < record2.getKey())
                    largest = CR;

                seek(largest);
                record1.read(file);
                seek(parent);
                record2.read(file);

                comparasions++;
                if (record1.getKey() > record2.getKey()) {
                    seek(parent);
                    record1.write(file);
                    seek(largest);
                    record2.write(file);
                    permutations += 2;
                }
                parent--;
            }
            permutations += 2;
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
                    comparasions++;
                    if (record1.getKey() > record2.getKey())
                    {
                        permutations += 2;
                        swaps(j, j + dist);
                        k = j;
                        seek(k);
                        record1.read(file);
                        seek(k - dist);
                        record2.read(file);
                        comparasions++;
                        while (k - dist >= i && record1.getKey() < record2.getKey())
                        {
                            comparasions++;
                            permutations += 2;
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
}
