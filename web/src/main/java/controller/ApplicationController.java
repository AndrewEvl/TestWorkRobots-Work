package controller;

import entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInteface.LogService;
import service.serviceInteface.RobotService;
import service.serviceInteface.WorkService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
public class ApplicationController {

    private final RobotService robotService;
    private final WorkService workService;
    private final LogService logService;
    private int ROUND = 0;

    public ApplicationController(RobotService robotService, WorkService workService, LogService logService) {
        this.robotService = robotService;
        this.workService = workService;
        this.logService = logService;
    }

    @ModelAttribute("work")
    public Work work() {
        return new Work();
    }

    @ModelAttribute("robot")
    public Robot robot() {
        return new Robot();
    }

    @GetMapping("/home/stop")
    public String homeStopPageGet(Model model) {
        List<Log> allLog = logService.getAll();
        Collections.reverse(allLog);
        List<Robot> allRobots = robotService.getAll();
        List<Work> allWorks = workService.getAll();
        model.addAttribute("allLog", allLog);
        model.addAttribute("allRobots", allRobots);
        model.addAttribute("allWorks", allWorks);
        return "home-stop-page";
    }

    @GetMapping("/home")
    public String homePageGet(Model model) {
        if (ROUND == 0) {
            for (int i = 0; i < 5; i++) {
                createRobots();
            }
        }
        randomKillRobots();
        creatingWork();
        List<Log> allLog = logService.getAll();
        Collections.reverse(allLog);
        List<Robot> allRobots = robotService.getAll();
        List<Work> allWorks = workService.getAll();
        if (allRobots.size() < 2) {
            createRobots();
        }
        ROUND++;
        model.addAttribute("allLog", allLog);
        model.addAttribute("allRobots", allRobots);
        model.addAttribute("allWorks", allWorks);
        return "home-page";
    }

    @GetMapping("/add-robots")
    public String addRobotsGet(Robot robot) {
        robotService.save(robot);
        Log log = new Log();
        log.setLog("Create robot " + robot.getNumberRobot());
        logService.save(log);
        return "redirect:/home";
    }

    @GetMapping("/add-work")
    public String addWorkGet() {
        return "add-work-page";
    }

    @PostMapping("/add-work")
    public String addWorkPost(Work work) {
        workService.save(work);
        return "redirect:/home";
    }

    @GetMapping("/add-robots-on-work")
    public String addRobotsOnWorkGet(Work work, Model model) {
        Long id = work.getId();
        model.addAttribute("id", id);
        return "redirect:/add-robots-on-work/{id}";
    }

    @GetMapping("/add-robots-on-work/{id}")
    public String addRobotsOnWorkPost(@PathVariable("id") Long id) {
        Work work = workService.findById(id);
        int sizeWork = work.getSizeWork();
        Random random = new Random();
        List<Robot> all = robotService.getAll();
        if (sizeWork > all.size()) {
            int difference = sizeWork - all.size();
            for (int i = 0; i <= difference; i++) {
                createRobots();
            }
        }
        //TODO Create normal method!
        for (int i = 0; i < sizeWork; ) {
            Robot robotById = robotService.findById((long) random.nextInt(all.size() - 1));
            if (robotById != null) {
                robotById.setWork(work);
                robotService.update(robotById);
                i++;
            }
        }
        logSuccessfulWork(work);
        workService.delete(work);
        return "redirect:/home";
    }

    @GetMapping("/auto-add-robots-on-work")
    public String autoAddRobotsOnWork() {
        List<Work> workList = workService.getAll();
        if (workList.isEmpty()) {
            return "redirect:/home";
        }
        Work work = workList.get(0);
        int sizeWork = work.getSizeWork();
        List<Robot> all = robotService.getAll();
        if (sizeWork > all.size()) {
            int difference = sizeWork - all.size();
            for (int i = 0; i <= difference; i++) {
                createRobots();
            }
        }
//        for (int i = 0; i < sizeWork - 1; ) {
//            Robot robot = all.get(i);
//            if (robot != null) {
//                robot.setWork(work);
//                robotService.update(robot);
//                i++;
//            }
//        }
        logSuccessfulWork(work);
        workService.delete(work);
        return "redirect:/home";
    }

    @GetMapping("/auto-create-work")
    public String autoCreateWorkGet() {
        creatingWork();
        return "redirect:/home";
    }

    private String generationRobotNumber() {
        int length = 7;
        String characters = "1234567890";
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    private void createRobots() {
        Robot robot = new Robot();
        robot.setNumberRobot(generationRobotNumber());
        robotService.save(robot);
        logCreateRobot(robot);

    }

    private void randomKillRobots() {
        Random random = new Random();
        int randomInt = random.nextInt(15);

        if (randomInt == 9) {
            List<Robot> all = robotService.getAll();
            int size = all.size();
            Robot robot = all.get(random.nextInt(size - 1));
            Robot robotsById = robotService.findById(robot.getId());
            robotService.delete(robotsById);
            logDestroyRobot(robotsById);
        }
    }

    private void creatingWork() {
        Random random = new Random();
        int randomInt = random.nextInt(10);
        Work work = new Work();
        if (randomInt == 6) {
            work.setWorks("Some assignment");
            work.setSizeWork(random.nextInt(15) + 1);
            workService.save(work);
            logCreateWork(work);
        }
    }

    private void logCreateRobot(Robot robot) {
        Log log = new Log();
        log.setLog("Create robot " + robot.getNumberRobot());
        logService.save(log);
    }

    private void logDestroyRobot(Robot robot) {
        Log log = new Log();
        log.setLog("Robot was name " + robot.getNumberRobot() + " destroyed!");
        logService.save(log);
    }

    private void logCreateWork(Work work) {
        Log log = new Log();
        log.setLog("Work " + work.getWorks() + " № " + work.getId() + " created!");
        logService.save(log);
    }

    private void logSuccessfulWork(Work work) {
        Log log = new Log();
        log.setLog("Work №" + work.getId() + " " + work.getWorks() + " successfully!");
        logService.save(log);
    }

}
