package ex1;

import java.io.*;

public class Application4 {
    public static void main(String[] args) throws IOException {
        Employe e1=new Employe("nom1","prenom1",12000);
        Employe e2=new Employe("nom2","prenom2",14000);
        Employe e3=new Employe("nom3","prenom3",16000);

        File f1=new File("employes.dat");
        FileOutputStream fos=new FileOutputStream(f1);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(e1);
        oos.writeObject(e2);
        oos.writeObject(e3);
        oos.close();

    }
}
