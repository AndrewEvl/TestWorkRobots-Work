package service.serviceInteface;

import entity.Log;
import entity.Robot;

import java.util.List;

public interface LogService {

    void save (Log log);

    List<Log> getAll ();

    Log findById (Long id);

    void update (Log log);

    void delete (Log log);
}
