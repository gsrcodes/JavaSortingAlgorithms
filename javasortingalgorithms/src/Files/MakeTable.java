package Files;

import java.io.IOException;
import java.io.RandomAccessFile;

public class MakeTable {
    int len = 1024;
    long timeStart, timeFinished, timeStartAll, timeFinishedAll;
    int comparisons, movements;
    File reverseFile, sortedFile, randomFile;
    File fileCopy;
    RandomAccessFile table;

    void printHeader() throws IOException {
        table.writeBytes("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        table.writeBytes(String.format("%-25s%-80s%-80s%-80s%n", "| Sorting Algorithms", "| Sorted File", "| Reverse File", "| Randomic File                                                                 |"));
        table.writeBytes("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        table.writeBytes("|                        |  Comp.Prog. *  |  Comp.Equa. #  |  Mov.Prog. +  |  Mov Equa. -   |    Time    |  Comp.Prog. *  |  Comp.Equa. #  |  Mov.Prog. +  |  Mov Equa. -   |    Time    |  Comp.Prog. *  |  Comp.Equa. #  |  Mov.Prog. +  |  Mov Equa. -   |    Time    |\n");
        table.writeBytes("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

    }

    public void createTable() throws IOException {
        timeStartAll = System.currentTimeMillis();
        initFiles();
        printHeader();
        System.out.println("Sorting algorithms are being executed...");
        insertionSort();
        binaryInsertionSort();
        selectionSort();
        bubbleSort();
        shakeSort();
        shellSort();
        heapSort();
        quickSort();
        quickSortWithPivot();
        mergeSort();
        mergeSort2();
        countingSort();
        bucketSort();
        radixSort();
        combSort();
        gnomeSort();
        timSort();
        timeFinishedAll = System.currentTimeMillis();
        long duration = (timeFinishedAll - timeStartAll) / 1000;
        table.writeBytes("NOTE: -1 = Doesn't exist\n");
        table.writeBytes("All sorting algorithms finished in " + duration + "s");
    }
    public void initFiles() throws IOException {
        sortedFile = new File("sortedFile.dat");
        randomFile = new File("randomFile.dat");
        reverseFile = new File("reverseFile.dat");
        java.io.File fileCopyAux = new java.io.File("fileCopy.dat");
        if (fileCopyAux.exists())
            fileCopyAux.delete();
        fileCopy = new File("fileCopy.dat");
        java.io.File tableFile = new java.io.File("table.txt");
        if (tableFile.exists())
            tableFile.delete();
        try {
            table = new RandomAccessFile(tableFile, "rw");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createFiles() throws IOException {
        System.out.println("Wait, creating files...\n\n");
        initFiles();
        sortedFile.createSortedFile(len);
        randomFile.createRandomFile(len);
        reverseFile.createReverseFile(len);
    }

    public double getComparisonsEqua(int key) {
        double value = 1;
        if (key == 0)
            value = len - 1;
        else if (key == 1)
            value = ((len * len + len - 2) / 4);
        else if (key == 2)
            value = ((len * len + len - 4) / 4);
        else if (key == 3)
            value = ((len * len - len) / 2);
        else if (key == 4)
            value = len * (Math.log(len) - Math.log(Math.E));

        return Math.round(value);
    }

    public double getMovementsEqua(int key) {
        double value = -1;
        if (key == 0)
            value = (3 * (len - 1));
        else if (key == 1)
            value = ((len * len + 3 * len - 4) / 2);
        else if (key == 2)
            value = ((len * len + 9 * len - 10) / 4);
        else if (key == 3)
            value = (len * len / 4 + 3 * (len - 1));
        else if (key == 4)
            value = (len * (Math.log(len) + Math.E));
        else if (key == 5)
            value = (3 * (len * len - len) / 2);
        else if (key == 6)
            value = (3 * (len * len - len) / 4);
        else if (key == 7)
            value = 0;
        return Math.round(value);
    }

    private void runSortingAlgorithm(String algorithmName, Runnable sortingAlgorithm, double sortedCompEq, double sortedMovEq, double reverseCompEq,
                                     double reverseMovEq, double randomCompEq, double randomMovEq) throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initMovements();
        timeStart = System.currentTimeMillis();
        sortingAlgorithm.run();
        timeFinished = System.currentTimeMillis();
        comparisons = fileCopy.getComparisons();
        movements = fileCopy.getMovements();
        long duration = (timeFinished - timeStart) / 1000;
        table.writeBytes(String.format("| %-22s |  %-13s |  %-13s |  %-12s |  %-13s |   %-8s ", algorithmName, comparisons, (int)sortedCompEq, movements, (int)sortedMovEq, duration));

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initMovements();
        timeStart = System.currentTimeMillis();
        sortingAlgorithm.run();
        timeFinished = System.currentTimeMillis();
        comparisons = fileCopy.getComparisons();
        movements = fileCopy.getMovements();
        duration = (timeFinished - timeStart) / 1000;
        table.writeBytes(String.format("|  %-13s |  %-13s |  %-12s |  %-13s |   %-8s ", comparisons, (int)reverseCompEq, movements, (int)reverseMovEq, duration));

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initMovements();
        timeStart = System.currentTimeMillis();
        sortingAlgorithm.run();
        timeFinished = System.currentTimeMillis();
        comparisons = fileCopy.getComparisons();
        movements = fileCopy.getMovements();
        duration = (timeFinished - timeStart) / 1000;
        table.writeBytes(String.format("|  %-13s |  %-13s |  %-12s |  %-13s |   %-8s |\n", comparisons, (int)randomCompEq, movements, (int)randomMovEq, duration));
        table.writeBytes("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

    }

    private void insertionSort() throws IOException {
        runSortingAlgorithm("Insertion Sort", fileCopy::insertionSort, getComparisonsEqua(0), getMovementsEqua(0), getComparisonsEqua(2), getMovementsEqua(1), getComparisonsEqua(1), getMovementsEqua(2));
    }

    private void binaryInsertionSort() throws IOException {
        runSortingAlgorithm("Binary Insertion Sort", fileCopy::binaryInsertionSort, getComparisonsEqua(4), getMovementsEqua(0), getComparisonsEqua(4), getMovementsEqua(1), getComparisonsEqua(4), getMovementsEqua(2));
    }

    private void selectionSort() throws IOException {
        runSortingAlgorithm("Selection Sort", fileCopy::selectionSort, getComparisonsEqua(3), getMovementsEqua(0), getComparisonsEqua(3), getMovementsEqua(3), getComparisonsEqua(3), getMovementsEqua(4));
    }

    private void bubbleSort() throws IOException {
        runSortingAlgorithm("Bubble Sort", fileCopy::bubbleSort, getComparisonsEqua(3), getMovementsEqua(7), getComparisonsEqua(3), getMovementsEqua(6), getComparisonsEqua(3), getMovementsEqua(5));
    }
    private void shakeSort() throws IOException {
        runSortingAlgorithm("Shake Sort", fileCopy::shakeSort, getComparisonsEqua(3), getMovementsEqua(7), getComparisonsEqua(3), getMovementsEqua(6), getComparisonsEqua(3), getMovementsEqua(5));
    }

    private void shellSort() throws IOException {
        runSortingAlgorithm("Shell Sort", fileCopy::shellSort, -1, -1, -1, -1, -1, -1);
    }

    private void heapSort() throws IOException {
        runSortingAlgorithm("Heap Sort", fileCopy::heapSort, -1, -1, -1, -1, -1, -1);
    }

    private void quickSort() throws IOException {
        runSortingAlgorithm("Quick Sort", fileCopy::quickSort, -1, -1, -1, -1, -1 , -1);
    }

    private void quickSortWithPivot() throws IOException {
        runSortingAlgorithm("Quick Sort with Pivot", fileCopy::quickSortWithPivot, -1, -1, -1, -1, -1 , -1);
    }
    private void mergeSort() throws IOException {
        runSortingAlgorithm("Merge Sort", fileCopy::mergeSort, -1, -1, -1, -1, -1 , -1);
    }
    private void mergeSort2() throws IOException {
        runSortingAlgorithm("Merge Sort 2", fileCopy::mergeSort2, -1, -1, -1, -1, -1 , -1);
    }

    private void countingSort() throws IOException {
        runSortingAlgorithm("Counting Sort", fileCopy::countingSort, -1, -1, -1, -1, -1 , -1);
    }
    private void bucketSort() throws IOException {
        runSortingAlgorithm("Bucket Sort", fileCopy::bucketSort, -1, -1, -1, -1, -1 , -1);
    }
    private void radixSort() throws IOException {
        runSortingAlgorithm("Radix Sort", fileCopy::radixSort, -1, -1, -1, -1, -1 , -1);
    }

    private void combSort() throws IOException {
        runSortingAlgorithm("Comb Sort", fileCopy::combSort, -1, -1, -1, -1, -1 , -1);
    }

    private void gnomeSort() throws IOException {
        runSortingAlgorithm("Gnome Sort", fileCopy::gnomeSort, -1, -1, -1, -1, -1 , -1);
    }

    private void timSort() throws IOException {
        runSortingAlgorithm("Tim Sort", fileCopy::timSort, -1, -1, -1, -1, -1 , -1);
    }
}
