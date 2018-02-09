package controller;

import entity.Log;
import entity.Robot;
import entity.Work;
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
 * Class for creation of HTML page.
 * Creation of GET and POST query.
 */
@Controller
public class ApplicationController {

    /**
     * Implement service <b>robotService</b> in the controller of application.
     */
    private final RobotService robotService;
    /**
     * Implement service <b>workService</b> in the controller of application.
     */
    private final WorkService workService;
    /**
     * Implement service <b>logService</b> in the controller of application.
     */
    private final LogService logService;
    /**
     * How many rounds passed since the start of the application
     */
    private int ROUND = 0;

    public ApplicationController(RobotService robotService, WorkService workService, LogService logService) {
        this.robotService = robotService;
        this.workService = workService;
        this.logService = logService;
    }

    /**
     * Creation of attribute work for model.
     *
     * @return - new entity {@link Work}.
     */
    @ModelAttribute("work")
    public Work work() {
        return new Work();
    }

    /**
     * Creation of attribute robot for model.
     *
     * @return - new entity {@link Robot}
     */
    @ModelAttribute("robot")
    public Robot robot() {
        return new Robot();
    }

    /**
     * This page makes the application on pause.
     * Load all application log, all active robots and all uncompleted work from database.
     * And transfer to the page
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
     * Page for dynamically displayed application work.
     * Creation of new robots, works and deactivation of random robots.
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
     * @param robot - Creation of entity {@link Robot}
     * @return - Redirection to home page
     */
    @GetMapping("/add-robots")
    public String addRobotsGet(Robot robot) {
        robot.setNumberRobot(generationRobotNumber());
        robotService.save(robot);
        logCreateRobot(robot);
        return "redirect:/home";
    }

    /**
     * User adds entity Work.GET method.
     *
     * @return - Redirect to add-work-page.html
     */
    @GetMapping("/add-work")
    public String addWorkGet() {
        return "add-work-page";
    }

    /**
     * User adds entity Work.POST method.
     *
     * @param work - saving the received entity Work
     * @return - Redirect to home page
     */
    @PostMapping("/add-work")
    public String addWorkPost(Work work) {
        workService.save(work);
        return "redirect:/home";
    }

    /**
     * User adds robots to work.GET method.
     *
     * @param work  {@link Work} entity
     * @param model for output to HTML
     * @return - redirect on POST method
     */
    @GetMapping("/add-robots-on-work")
    public String addRobotsOnWorkGet(Work work, Model model) {
        Long id = work.getId();
        model.addAttribute("id", id);
        return "redirect:/add-robots-on-work/{id}";
    }

    /**
     * User adds {@link Robot} to {@link Work}. workService finds work on ID.POST method.
     *
     * @param id - work ID on database.
     * @return - redirect home page.
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
     * This method adds {@link Robot} to {@link Work}. If the list of works is empty it will redirect user to home page.
     *
     * @return - redirect to home page.
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
     * This method auto creates new entity {@link Work}.
     *
     * @return - redirect to home page
     */
    @GetMapping("/auto-create-work")
    public String autoCreateWorkGet() {
        creatingWork();
        return "redirect:/home";
    }

    /**
     * This method generations random {@link Robot}number.
     *
     * @return - String {@link Robot} numberRobots
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
     * This method creates new entity {@link Robot}.
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
     * This method random deletes {@link Robot} in database.
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
     * This method random creates new entity {@link Work}.
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
     * This method adds information about creation of new entity {@link Robot} to {@link Log}.
     *
     * @param robot - created entity of the robot.
     */
    private void logCreateRobot(Robot robot) {
        Log log = new Log();
        log.setLog("Create robot " + robot.getNumberRobot());
        logService.save(log);
    }

    /**
     * This method adds information about deleted entity {@link Robot} from database to {@link Log}.
     *
     * @param robot - created entity of the robot.
     */
    private void logDestroyRobot(Robot robot) {
        Log log = new Log();
        log.setLog("Robot was name " + robot.getNumberRobot() + " destroyed!");
        logService.save(log);
    }

    /**
     * This method adds information about creation of new entity {@link Work} to {@link Log}.
     *
     * @param work - created entity of work.
     */
    private void logCreateWork(Work work) {
        Log log = new Log();
        log.setLog("Work " + work.getWorks() + " № " + work.getId() + " created!");
        logService.save(log);
    }

    /**
     * This method adds information about successful {@link Work} of robots to {@link Log} .
     *
     * @param work - created entity of work.
     */
    private void logSuccessfulWork(Work work) {
        Log log = new Log();
        log.setLog("Work №" + work.getId() + " " + work.getWorks() + " successfully! " + work.getSizeWork() + " Robots were involved.");
        logService.save(log);
    }

}
