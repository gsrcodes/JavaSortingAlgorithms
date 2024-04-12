package Files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MakeTable {
    int len = 1024;
    long timeStart, timeFinished, timeStartAll, timeFinishedAll;
    int comparisons, permutations;
    File reverseFile, sortedFile, randomFile;
    File fileCopy;
    RandomAccessFile table;

    void printHeader() throws IOException {
        table.writeBytes("Metodos de Ordenacao;Arquivo Ordenado; ; ; ; ; Arquivo em Ordem Reversa; ; ; ; ; Arquivo Randomico\n" +
                ";Comp. Prog. *; Comp. Equa. #; Mov. Prog. +; Mov. Equa. -; Tempo;Comp. Prog. *; Comp. Equa. #; Mov. Prog. +; Mov. Equa. -; " +
                "Tempo;Comp. Prog. *; Comp. Equa. #; Mov. Prog. +; Mov. Equa. -; Tempo\n");
    }

    public void createTable() throws IOException {
        timeStartAll = System.nanoTime();
        initFiles();
        printHeader();
        insertionSort();
        binaryInsertinoSort();
        selectionSort();
        bubbleSort();
        shakeSort();
        shellSort();
        heapSort();
        quickSort();
        quickSortWithPivot();
        mergeSort2();
        countingSort();
        radixSort();
        combSort();
        gnomeSort();
        timSort();
        timeFinishedAll = System.nanoTime();
        long duration = (timeFinished - timeStart) / 10000;
        System.out.println("All sorting algorithms finished in " + duration + "ms");
    }
    public void initFiles() throws IOException {
        sortedFile = new File("sortedFile.dat");
        randomFile = new File("randomFile.dat");
        reverseFile = new File("reverseFile.dat");
        fileCopy = new File("fileCopy.dat");
        java.io.File tableFile = new java.io.File("table.csv");
        if (tableFile.exists())
            tableFile.delete();
        try {
            table = new RandomAccessFile(tableFile, "rw");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createFiles() throws IOException {
        initFiles();
        sortedFile.createSortedFile(len);
        randomFile.createRandomFile(len);
        reverseFile.createReverseFile(len);
    }

    private void insertionSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.insertionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Insertion Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.insertionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.insertionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void binaryInsertinoSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.binaryInsertionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Binary Insertion Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.binaryInsertionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.binaryInsertionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void selectionSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.selectionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Selection Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.selectionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.selectionSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void bubbleSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.bubbleSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Bubble Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.bubbleSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.bubbleSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void shakeSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.shakeSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Shake Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.shakeSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.shakeSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void shellSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.shellSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Shell Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.shellSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.shellSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void heapSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.heapSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Heap Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.heapSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.heapSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void quickSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.quickSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Quick Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.quickSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.quickSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void quickSortWithPivot() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.quickSortWithPivot();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Quick Sort W/ Pivot;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.quickSortWithPivot();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.quickSortWithPivot();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void mergeSort2() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.mergeSort2();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Merge Sort 2;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.mergeSort2();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.mergeSort2();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void countingSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.countingSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Counting Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.countingSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.countingSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void radixSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.radixSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Radix Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.radixSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.radixSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void combSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.combSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Comb Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.combSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.combSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void gnomeSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.gnomeSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Gnome Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.gnomeSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.gnomeSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void timSort() throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.timSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 100000;
        table.writeBytes("Tim Sort;" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.timSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.nanoTime();
        fileCopy.timSort();
        timeFinished = System.nanoTime();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 100000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }
}
