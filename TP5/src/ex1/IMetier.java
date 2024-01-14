package ex1;

import java.util.List;

public interface IMetier<T> {
    public T add(T o);
    public List<T> getAll();
    public T findById(long id);
    public void delete(long id);
    public void saveAll();

}
