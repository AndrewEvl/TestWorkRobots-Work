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

    public RobotsController(RobotService robotService, WorkService workService) {
        this.robotService = robotService;
        this.workService = workService;
    }

    @GetMapping("/home")
    public String homePageGet (Model model){
        List<Robot> allRobots = robotService.getAll();
        List<Work> allWorks = workService.getAll();
        model.addAttribute("allRobots", allRobots);
        model.addAttribute("allWorks", allWorks);
        return "home-page";
    }

    @GetMapping("/add-robots")
    public String addRobotsGet (Robot robot){
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
}
