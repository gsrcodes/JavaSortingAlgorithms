package Files;

public class Main {
    public static void main(String[] args) {
        int len = 10;
        File reverseFile = new File("reverseFile.dat");
        File sortedFile = new File("sortedFile.dat");
        File randomFile = new File("randomFile.dat");
        File copyRandomFile = new File("copyRandomFile.dat");
        copyRandomFile.copyFile(randomFile);
        copyRandomFile.display();
        copyRandomFile.quickSortWithPivot();
        copyRandomFile.display();
    }
}
