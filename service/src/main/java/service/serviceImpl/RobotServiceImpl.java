package service.serviceImpl;

import dao.interfaceDao.RobotDao;
import entity.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.serviceInteface.RobotService;

import java.util.List;

/**
 * Here you can write the implement from{@link RobotService } of methods that are not enough for working with database.
 */
@Service
//@Transactional
//TODO reverse transaction
public class RobotServiceImpl implements RobotService {


    private final RobotDao robotDao;

    @Autowired
    public RobotServiceImpl(RobotDao robotDao) {
        this.robotDao = robotDao;
    }

    @Override
    public void save(Robot robot) {
        robotDao.save(robot);
    }

    @Override
    public List<Robot> getAll() {
        return robotDao.findAll();
    }

    @Override
    public Robot findById(Long id) {
        return robotDao.findById(id);
    }

    @Override
    public void update(Robot robot) {
        robotDao.update(robot);
    }

    @Override
    public void delete(Robot robot) {
        robotDao.delete(robot);
    }
}
