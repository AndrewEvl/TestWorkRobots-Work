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

/**
 * Class for creating and defecting them to the page.
 * Creating a GET and POST query.
 */
@Controller
public class ApplicationController {

    /**
     * Implements service <b>robotService</b> <b>workService</b> <b>logService</b> on application controller.
     */
    private final RobotService robotService;
    private final WorkService workService;
    private final LogService logService;
    private int ROUND = 0;

    public ApplicationController(RobotService robotService, WorkService workService, LogService logService) {
        this.robotService = robotService;
        this.workService = workService;
        this.logService = logService;
    }

    /**
     * Create attribute work from model.
     *
     * @return - new entity Work.
     */
    @ModelAttribute("work")
    public Work work() {
        return new Work();
    }

    /**
     * Create attribute robot from model.
     *
     * @return - new entity Robot
     */
    @ModelAttribute("robot")
    public Robot robot() {
        return new Robot();
    }

    /**
     * This page puts the application on pause.
     * Loads from the database all application log, all active robots and all the uncompleted work.
     * And transfers to the page
     *
     * @param model - for output to HTML
     * @return - HTML page "home-stop-page.html"
     */
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

    /**
     * A page with dynamically displayed application work.
     * Creation of new robots, works and deactivation random robots.
     *
     * @param model - for output to HTML
     * @return - HTML page "home-page.html"
     */
    @GetMapping("/home")
    public String homePageGet(Model model) {
//        Counts the number of calls by the home page.
        if (ROUND == 0) {
            for (int i = 0; i < 5; i++) {
                createRobots();
            }
        }
//        Call method for kill random robots.
        randomKillRobots();
//        Call method for creating work.
        creatingWork();
        List<Log> allLog = logService.getAll();
//        Reverse list log on
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

    /**
     *
     * @param robot - Creating entity robots
     * @return - Redirection on home page
     */
    @GetMapping("/add-robots")
    public String addRobotsGet(Robot robot) {
        robotService.save(robot);
        Log log = new Log();
        log.setLog("Create robot " + robot.getNumberRobot());
        logService.save(log);
        return "redirect:/home";
    }

    /**
     *
     * @return
     */
    @GetMapping("/add-work")
    public String addWorkGet() {
        return "add-work-page";
    }

    /**
     *
     * @param work
     * @return
     */
    @PostMapping("/add-work")
    public String addWorkPost(Work work) {
        workService.save(work);
        return "redirect:/home";
    }

    /**
     *
     * @param work
     * @param model
     * @return
     */
    @GetMapping("/add-robots-on-work")
    public String addRobotsOnWorkGet(Work work, Model model) {
        Long id = work.getId();
        model.addAttribute("id", id);
        return "redirect:/add-robots-on-work/{id}";
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/add-robots-on-work/{id}")
    public String addRobotsOnWorkPost(@PathVariable("id") Long id) {
        Work work = workService.findById(id);
        int sizeWork = work.getSizeWork();
        List<Robot> all = robotService.getAll();
        if (sizeWork > all.size()) {
            int difference = sizeWork - all.size();
            for (int i = 0; i <= difference; i++) {
                createRobots();
            }
        }
        logSuccessfulWork(work);
        workService.delete(work);
        return "redirect:/home";
    }

    /**
     * This method add robots in work. If works list empty redirect user in home page.
     * @return - redirect on home page.
     */
    @GetMapping("/auto-add-robots-on-work")
    public String autoAddRobotsOnWork() {
        List<Work> workList = workService.getAll();
        if (workList.isEmpty()) {
            return "redirect:/home";
        }
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 1) {
            Work work = workList.get(0);
            int sizeWork = work.getSizeWork();
            List<Robot> all = robotService.getAll();
            if (sizeWork > all.size()) {
                int difference = sizeWork - all.size();
                for (int i = 0; i <= difference; i++) {
                    createRobots();
                }
            }

            logSuccessfulWork(work);
            workService.delete(work);
        }
        return "redirect:/home";
    }

    /**
     * This method auto creating new entity Work.
     * @return - redirect on home page
     */
    @GetMapping("/auto-create-work")
    public String autoCreateWorkGet() {
        creatingWork();
        return "redirect:/home";
    }

    /**
     * This method generation random number in robot
     * @return - String {@link Robot.numberRobot}
     */
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

    /**
     * This method creating new entity robot.
     */
    private void createRobots() {
        Robot robot = new Robot();
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 1) {
            robot.setNumberRobot(generationRobotNumber());
            robotService.save(robot);
            logCreateRobot(robot);
        }

    }

    /**
     * This method random delete robots in database.
     */
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

    /**
     * This method random creating new entity work.
     */
    private void creatingWork() {
        Work work = new Work();
        Random random = new Random();
        int randomInt = random.nextInt(3);
        if (randomInt == 1) {
            work.setWorks("Some assignment");
            work.setSizeWork(random.nextInt(15) + 1);
            workService.save(work);
            logCreateWork(work);
        }
    }

    /**
     * This method add in log information on creating new entity robots.
     * @param robot -
     */
    private void logCreateRobot(Robot robot) {
        Log log = new Log();
        log.setLog("Create robot " + robot.getNumberRobot());
        logService.save(log);
    }

    /**
     * This method add in log information on delete entity robot in database.
     * @param robot
     */
    private void logDestroyRobot(Robot robot) {
        Log log = new Log();
        log.setLog("Robot was name " + robot.getNumberRobot() + " destroyed!");
        logService.save(log);
    }

    /**
     * This method add in log information on creating new entity Work.
     * @param work
     */
    private void logCreateWork(Work work) {
        Log log = new Log();
        log.setLog("Work " + work.getWorks() + " № " + work.getId() + " created!");
        logService.save(log);
    }

    /**
     * This method add in log information on successful work robots.
     * @param work
     */
    private void logSuccessfulWork(Work work) {
        Log log = new Log();
        log.setLog("Work №" + work.getId() + " " + work.getWorks() + " successfully! " + work.getSizeWork() + " Robots were involved.");
        logService.save(log);
    }

}
