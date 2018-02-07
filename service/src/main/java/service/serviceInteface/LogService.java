package service.serviceInteface;

import entity.Log;
import entity.Robot;

import java.util.List;

/**
 * Interface for the ability to implement a standard set commands to work with the database.
 * Here you can write your lacking methods for later implementation on {@link service.serviceImpl.LogServiceImpl}.
 */
public interface LogService {

    void save (Log log);

    List<Log> getAll ();

    Log findById (Long id);

    void update (Log log);

    void delete (Log log);
}
