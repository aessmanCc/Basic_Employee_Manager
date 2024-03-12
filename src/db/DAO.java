//This is a class uses the DAO interface that is implemented by the EmployeeDb.

package db;

import java.util.List;

public interface DAO<T> {
    T get(int employeeID);
    List<T> getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
}