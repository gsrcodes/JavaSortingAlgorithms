package Files;

public class Main {
    public static void main(String[] args) {
        int len = 10;
        long startTime = System.nanoTime();

        File randomFile = new File("sortedFile.dat");
        randomFile.createRandomFile(len);
        randomFile.display();
        randomFile.selectionSort();
        randomFile.display();
        /*
        long endTime = System.nanoTime();
        long durationSorted = (endTime - startTime) / 1000000 / 1000;

        startTime = System.nanoTime();
        File reverseFile = new File("reverseFile.dat");
        reverseFile.createReverseFile(len);
        reverseFile.insertionSort();
        endTime = System.nanoTime();
        long durationReverse = (endTime - startTime) / 1000000 / 1000;

        startTime = System.nanoTime();
        File randomFile = new File("randomFile.dat");
        randomFile.createRandomFile(len);
        randomFile.insertionSort();
        endTime = System.nanoTime();
        long durationRandom = (endTime - startTime) / 1000000 / 1000;

        System.out.println("Tempo de execução para o arquivo ordenado: " + durationSorted + " s");
        System.out.println("Tempo de execução para o arquivo reverso: " + durationReverse + " s");
        System.out.println("Tempo de execução para o arquivo aleatório: " + durationRandom + " s");
        */
    }
}
