package ex1;

import java.io.*;

public class Application5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file=new File("employes.dat");
        FileInputStream fis=new FileInputStream(file);
        ObjectInputStream ois =new ObjectInputStream(fis);
        Employe e1=(Employe)ois.readObject();
        Employe e2=(Employe)ois.readObject();
        Employe e3=(Employe)ois.readObject();

        System.out.println(e1.getNom());
        System.out.println(e2.getNom());
        System.out.println(e3.getNom());

    }

}
