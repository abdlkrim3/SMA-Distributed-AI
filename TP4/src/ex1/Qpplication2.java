package ex1;

import java.io.*;

public class Qpplication2 {
    public static void main(String[] args) throws IOException {
        File f1=new File("image1.jfif");
        File f2=new File("image2.jfif");
        FileInputStream fis=new FileInputStream(f1);
        FileOutputStream fos=new FileOutputStream(f2);
        int c;
        while ((c=fis.read())!=-1){
            fos.write(c);
        }
        fos.close();
        fis.close();
    }
}
