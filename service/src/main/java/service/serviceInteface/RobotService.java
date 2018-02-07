package service.serviceInteface;

import entity.Robot;

import java.util.List;


/**
 * Interface for the ability to implement a standard set commands to work with the database.
 * Here you can write your lacking methods for later implementation on {@link service.serviceImpl.RobotServiceImpl}.
 */
public interface RobotService {

    void save (Robot robot);

    List<Robot> getAll ();

    Robot findById (Long id);

    void update (Robot robot);

    void delete (Robot robot);
}
