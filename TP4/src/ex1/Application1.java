package ex1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Application1 {
    public static void main(String[] args) throws IOException {
        File f1=new File("fichier1.txt");
        File f2=new File("fichier2.txt");
        FileReader fr=new FileReader(f1);
        FileWriter fw=new FileWriter(f2);
        int c;
        while ((c=fr.read())!=-1){
            fw.write(c);
        }
        fr.close();
        fw.close();
    }

}
