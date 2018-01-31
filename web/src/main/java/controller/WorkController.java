package controller;

import entity.StatusWork;
import entity.Work;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.serviceInteface.RobotService;
import service.serviceInteface.WorkService;

@Controller
public class WorkController {

    private final RobotService robotService;
    private final WorkService workService;


    public WorkController(RobotService robotService, WorkService workService) {
        this.robotService = robotService;
        this.workService = workService;
    }

    @ModelAttribute("work")
    public Work work(){
        return new Work();
    }

    @GetMapping ("/add-work")
    public String addWorkGet (Work work){
        return "add-work-page";
    }

    @PostMapping("/add-work")
    public String addWorkPost (Work work) {
        work.setStatusWork(StatusWork.NOT_DONE);
        workService.save(work);
        return "redirect:/home";
    }
}
