package dao;

import dao.interfaceDao.RobotDao;
import entity.Robot;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test for {@link RobotDao} class.
 */
public class RobotTest extends BaseTest {

    @Autowired
    private RobotDao robotDao;

    /**
     * Method testing save and find by ID query and checks the receives result.
     */
    @Test
    public void findByIdAndSaveTest() {
        Robot robot = new Robot();
        robot.setNumberRobot("123");
        robotDao.save(robot);
        Robot byId = robotDao.findById(robot.getId());
        assertEquals(byId.getNumberRobot(), "123");
    }

    /**
     * Method testing delete on database entity.
     */
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

    /**
     * Method testing update created entity new information.
     */
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

    /**
     * The method tests the receipt of all entities
     * of this type from the database.
     */
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
