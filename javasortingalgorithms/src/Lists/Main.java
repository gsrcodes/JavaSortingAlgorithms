package Lists;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List list = new List();
        for (int i = 0; i < 8; i++) {
            int randomValue = random.nextInt(100);
            list.insertEnd(randomValue);
        }
        list.display();
        list.shellSort();
        list.display();
    }
}