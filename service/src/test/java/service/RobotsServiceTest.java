package service;

import entity.Robot;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service.serviceInteface.RobotService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test for {@link RobotService} class.
 */
public class RobotsServiceTest extends BaseTest {

    @Autowired
    private RobotService robotService;

    /**
     * Method tests of saving and finding by ID query and checks received result.
     */
    @Test
    public void findByIdAndSaveTest() {
        Robot robot = new Robot();
        robot.setNumberRobot("123");
        robotService.save(robot);
        Robot byId = robotService.findById(robot.getId());
        assertEquals(byId.getNumberRobot(), "123");
    }

    /**
     * Method tests of updating of created entity with new information.
     */
    @Test
    public void deleteTest() {
        Robot robot = new Robot();
        robot.setNumberRobot("123");
        robotService.save(robot);
        Robot byId = robotService.findById(robot.getId());
        robotService.delete(byId);
        Robot id = robotService.findById(byId.getId());
        assertNull(id);
    }

    /**
     * Method testing update created entity new information.
     */
    @Test
    public void updateTest() {
        Robot robot = new Robot();
        robot.setNumberRobot("123");
        robotService.save(robot);
        Robot byId = robotService.findById(robot.getId());
        assertEquals(byId.getNumberRobot(), "123");
        robot.setNumberRobot("321");
        robotService.update(robot);
        Robot id = robotService.findById(robot.getId());
        assertEquals(id.getNumberRobot(), "321");
    }

    /**
     * The method tests receiving of all entities of this type from the database.
     */
    @Test
    public void findAllTest () {
        Robot robotFirst = new Robot();
        Robot robotSecond = new Robot();
        robotFirst.setNumberRobot("123");
        robotService.save(robotFirst);
        robotSecond.setNumberRobot("321");
        robotService.save(robotSecond);
        List<Robot> all = robotService.getAll();
        assertNotNull(all);
    }
}
