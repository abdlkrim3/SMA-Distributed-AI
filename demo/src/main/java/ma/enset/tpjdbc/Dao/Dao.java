package ma.enset.tpjdbc.Dao;

import java.util.List;

public interface Dao <T>{
    List<T> findAll();
    T findByID(int id);
    T save(T o);
    boolean delete(T o);
    T update(T o);
}
