package Files;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MakeTable start = new MakeTable();
        start.createFiles();
        start.createTable();
    }
}
