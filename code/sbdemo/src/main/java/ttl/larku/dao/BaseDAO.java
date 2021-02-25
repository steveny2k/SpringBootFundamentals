package ttl.larku.dao;

import java.util.List;

/**
 * @param <T>
 * @author anil
 */
public interface BaseDAO<T> {

    public void update(T updateObject);

    public void delete(T deleteObject);

    public T create(T newObject);

    public T get(int id);

    public List<T> getAll();

    public default void deleteStore() { throw new UnsupportedOperationException("Needs Implementing");}

    public default void createStore() { throw new UnsupportedOperationException("Needs Implementing");}
}
