package service.serviceInteface;

import entity.Log;

import java.util.List;

/**
 * Interface for ability to implement of standard set of commands to work with database.
 * Here you can write your absent methods for later implementation on {@link service.serviceImpl.LogServiceImpl}.
 */
public interface LogService {

    void save (Log log);

    List<Log> getAll ();

    Log findById (Long id);

    void update (Log log);

    void delete (Log log);
}
