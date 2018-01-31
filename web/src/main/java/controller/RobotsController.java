package controller;

import entity.Robot;
import entity.StatusRobot;
import entity.Work;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.serviceInteface.RobotService;
import service.serviceInteface.WorkService;

import java.util.List;
import java.util.Random;

@Controller
public class RobotsController {

    private final RobotService robotService;
    private final WorkService workService;
    private int SIZE;
    int ROUND = 0;

    public RobotsController(RobotService robotService, WorkService workService) {
        this.robotService = robotService;
        this.workService = workService;
    }

    @GetMapping("/home")
    public String homePageGet(Model model) {
        if (ROUND == 0) {
            for (int i = 0; i <= 5; i++) {
                startCreateRobots();
            }
        }
        if (ROUND >= 1) {
            if (randomKillRobots() != null) {
                String killRobots = "Some robot self-destructed";
                model.addAttribute("randomKill", killRobots);
            }
        }
        List<Robot> allRobots = robotService.getAll();
        List<Work> allWorks = workService.getAll();
        SIZE = allRobots.size();
        ROUND++;
        model.addAttribute("allRobots", allRobots);
        model.addAttribute("allWorks", allWorks);
        return "home-page";
    }

    @GetMapping("/add-robots")
    public String addRobotsGet(Robot robot) {
        robot.setNumberRobot(generationRobotNumber());
        robot.setStatusRobot(StatusRobot.LEAFE);
        robotService.save(robot);
        return "redirect:/home";
    }

    private String generationRobotNumber() {
        int length = 5;
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    private void startCreateRobots() {
        Robot robot = new Robot();
        robot.setNumberRobot(generationRobotNumber());
        robot.setStatusRobot(StatusRobot.LEAFE);
        robotService.save(robot);
    }

    private String randomKillRobots() {
        Random random = new Random();
        Robot robotsById = new Robot();
        int randomInt = random.nextInt(16);
        if (randomInt == 9) {
            long randomId = random.nextInt(SIZE - 1);
            robotsById = robotService.findById(randomId);
            if (robotsById != null) {
                robotService.delete(robotsById);
            }
        }
        assert robotsById != null;
        return robotsById.getNumberRobot();
    }
}
