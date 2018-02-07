package service.serviceInteface;

import entity.Robot;

import java.util.List;


/**
 * Interface for ability to implement of standard set of commands to work with database.
 * Here you can write your absent methods for later implementation on {@link service.serviceImpl.RobotServiceImpl}.
 */
public interface RobotService {

    void save (Robot robot);

    List<Robot> getAll ();

    Robot findById (Long id);

    void update (Robot robot);

    void delete (Robot robot);
}
