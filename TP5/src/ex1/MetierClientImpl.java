package ex1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class MetierClientImpl implements IMetier<Client>{
    private String save="clients.dat";
    List<Client> listClient=new ArrayList<>();
    @Override
    public Client add(Client o) {
        listClient.add(o);
        return null;
    }

    @Override
    public List<Client> getAll() {
        File f1=new File(save);
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(f1);
            ObjectInputStream ois=new ObjectInputStream(fis);
            Object e;
            while((e=ois.readObject())!=null){
                System.out.println(e);
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {

        }
        return listClient;
    }

    @Override
    public Client findById(long id) {
        for (Client client:listClient) {
            if(client.getId()==id) System.out.println(client);
        }
        return null;
    }
    @Override
    public void delete(long id) {
        for (Client client:listClient) {
            if(client.getId()==id) listClient.remove(client);
        }

    }

    @Override
    public void saveAll() {
        File f1=new File(save);
        FileOutputStream fos= null;
        try {
            fos = new FileOutputStream(f1);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(listClient);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
