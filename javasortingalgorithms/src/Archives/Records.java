package Archives;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Records {

    private int key; //4 bytes
    public final int FS = 1022;
    private char trash[] = new char[FS]; //2044 bytes

    public Records(){

    }

    public Records(int key) {
        this.key = key;
        for(int i = 0; i < FS; i++)
            trash[i] = 'X';
    }

    public void readArchive(RandomAccessFile archive) {
        try {
            key = archive.readInt();
            for(int i = 0; i < FS; i++)
                trash[i]=archive.readChar();
        } catch(IOException e){

        }
    }

    public void writeArchive(RandomAccessFile archive)
    {
        try {
            archive.writeInt(key);
            for(int i = 0 ; i < FS ; i++)
                archive.writeChar(trash[i]);
        } catch(IOException e){

        }
    }

    static int length() {
        return(2048);
    }

    public void displayRecord()
    {
        System.out.print(this.key);
    }

    public int getKey()
    {
        return this.key;
    }

    public void setKey(int key)
    {
        this.key = key;
    }

}