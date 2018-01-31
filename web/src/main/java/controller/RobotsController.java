package controller;

import entity.Robot;
import entity.Work;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.serviceInteface.RobotService;
import service.serviceInteface.WorkService;

import java.util.List;

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
        return "robots-html/home-page";
    }

    @GetMapping("add-robots")
    public String addRobotsGet (Model model){
        return "redirect:/home";
    }
}
