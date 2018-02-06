package dao;

import dao.interfaceDao.RobotDao;
import entity.Robot;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RobotTest extends BaseTest {

    @Autowired
    private RobotDao robotDao;

    @Test
    public void findByIdAndSaveTest() {
        Robot robot = new Robot();
        robot.setNumberRobot("123");
        robotDao.save(robot);
        Robot byId = robotDao.findById(robot.getId());
        assertEquals(byId.getNumberRobot(), "123");
    }

    @Test
    public void deleteTest() {
        Robot robot = new Robot();
        robot.setNumberRobot("123");
        robotDao.save(robot);
        Robot byId = robotDao.findById(robot.getId());
        robotDao.delete(byId);
        Robot id = robotDao.findById(byId.getId());
        assertNull(id);
    }

    @Test
    public void updateTest() {
        Robot robot = new Robot();
        robot.setNumberRobot("123");
        robotDao.save(robot);
        Robot byId = robotDao.findById(robot.getId());
        assertEquals(byId.getNumberRobot(), "123");
        robot.setNumberRobot("321");
        robotDao.update(robot);
        Robot id = robotDao.findById(robot.getId());
        assertEquals(id.getNumberRobot(), "321");
    }

    @Test
    public void findAllTest () {
        Robot robotFirst = new Robot();
        Robot robotSecond = new Robot();
        robotFirst.setNumberRobot("123");
        robotDao.save(robotFirst);
        robotSecond.setNumberRobot("321");
        robotDao.save(robotSecond);
        List<Robot> all = robotDao.findAll();
        assertNotNull(all);
    }
}
