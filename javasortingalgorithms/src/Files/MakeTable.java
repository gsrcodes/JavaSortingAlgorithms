package Files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MakeTable {
    int len = 50;
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
        timeStartAll = System.currentTimeMillis();
        initFiles();
        printHeader();
        insertionSort();
        binaryInsertionSort();
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
        timeFinishedAll = System.currentTimeMillis();
        long duration = (timeFinishedAll - timeStartAll) / 1000;
        System.out.println("All sorting algorithms finished in " + duration + "s");
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

    private void runSortingAlgorithm(String algorithmName, Runnable sortingAlgorithm) throws IOException {
        // Sorted File
        fileCopy.copyFile(sortedFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.currentTimeMillis();
        sortingAlgorithm.run();
        timeFinished = System.currentTimeMillis();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        long duration = (timeFinished - timeStart) / 1000;
        table.writeBytes(algorithmName + ";" + comparisons + "; ;" + permutations + "; ;" + duration);

        // Reverse File
        fileCopy.copyFile(reverseFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.currentTimeMillis();
        sortingAlgorithm.run();
        timeFinished = System.currentTimeMillis();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 1000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;"+ duration);

        // Random File
        fileCopy.copyFile(randomFile);
        fileCopy.initComparisons();
        fileCopy.initPermatitons();
        timeStart = System.currentTimeMillis();
        sortingAlgorithm.run();
        timeFinished = System.currentTimeMillis();
        comparisons = fileCopy.getComparisons();
        permutations = fileCopy.getPermutations();
        duration = (timeFinished - timeStart) / 1000;
        table.writeBytes(";" + comparisons + "; ;" + permutations +"; ;" + duration + "\n");
    }

    private void insertionSort() throws IOException {
        runSortingAlgorithm("Insertion Sort", fileCopy::insertionSort);
    }

    private void binaryInsertionSort() throws IOException {
        runSortingAlgorithm("Binary Insertion Sort", fileCopy::binaryInsertionSort);
    }

    private void selectionSort() throws IOException {
        runSortingAlgorithm("Selection Sort", fileCopy::selectionSort);
    }

    private void bubbleSort() throws IOException {
        runSortingAlgorithm("Bubble Sort", fileCopy::bubbleSort);
    }
    private void shakeSort() throws IOException {
        runSortingAlgorithm("Bubble Sort", fileCopy::shakeSort);
    }

    private void shellSort() throws IOException {
        runSortingAlgorithm("Shell Sort", fileCopy::shellSort);
    }

    private void heapSort() throws IOException {
        runSortingAlgorithm("Heap Sort", fileCopy::heapSort);
    }

    private void quickSort() throws IOException {
        runSortingAlgorithm("Quick Sort", fileCopy::quickSort);
    }

    private void quickSortWithPivot() throws IOException {
        runSortingAlgorithm("Quick Sort with Pivot", fileCopy::quickSortWithPivot);
    }

    private void mergeSort2() throws IOException {
        runSortingAlgorithm("Merge 2", fileCopy::mergeSort2);
    }

    private void countingSort() throws IOException {
        runSortingAlgorithm("Counting Sort", fileCopy::countingSort);
    }

    private void radixSort() throws IOException {
        runSortingAlgorithm("Radix Sort", fileCopy::radixSort);
    }

    private void combSort() throws IOException {
        runSortingAlgorithm("Comb Sort", fileCopy::combSort);
    }

    private void gnomeSort() throws IOException {
        runSortingAlgorithm("Gnome Sort", fileCopy::gnomeSort);
    }

    private void timSort() throws IOException {
        runSortingAlgorithm("Tim Sort", fileCopy::timSort);
    }
}
