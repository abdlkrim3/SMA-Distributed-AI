package ex_1;

import java.io.File;
import java.util.Scanner;

public class Ls {
    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);
        System.out.println("saisir le chemien : ");
        String path=scan.nextLine();
        File f1=new File(path);
        File[] lf=f1.listFiles();
        for (File files:lf) {
            System.out.print(files.getPath());

            if(files.isDirectory()){
                System.out.print("<DIR>");
            }
            else{
                System.out.print("<FILE>");
            }
            if(files.canRead()){
                System.out.print("r");
            }
            else{
                System.out.print("-");
            }
            if(files.canWrite()){
                System.out.print("w");
            }
            else{
                System.out.print("-");
            }
            if(files.isHidden()){
                System.out.println("h");
            }
            else{
                System.out.println("-");
            }
        }
    }
}
