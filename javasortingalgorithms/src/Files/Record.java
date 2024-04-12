package Files;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Record {
    public final int FS = 1022;
    private int key; //4 bytes
    private char trash[] = new char[FS]; //2044 bytes

    public Record(){

    }

    public Record(int key) {
        this.key = key;
        for(int i = 0; i < FS; i++)
            trash[i] = 'X';
    }

    public void read(RandomAccessFile file) {
        try {
            key = file.readInt();
            for(int i = 0; i < FS; i++)
                trash[i] = file.readChar();
        } catch(IOException e){

        }
    }

    public void write(RandomAccessFile file)
    {
        try {
            file.writeInt(key);
            for(int i = 0 ; i < FS ; i++)
                file.writeChar(trash[i]);
        } catch(IOException e){

        }
    }

    static int length() {
        return(2048);
    }

    public void display() {
        System.out.print(this.key + " - ");
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

}