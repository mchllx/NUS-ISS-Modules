package sg.edu.iss.nus.day22.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.nus.day22.models.TaskSummary;
import sg.edu.iss.nus.day22.services.TaskService;

@Controller
@RequestMapping(path={"/", "index"})
public class TaskController {

    @Autowired
    TaskService taskSvc;

    private Logger logger = Logger.getLogger(TaskController.class.getName());

    @GetMapping
    public ModelAndView showLandingPage() {
        ModelAndView mav = new ModelAndView();

        List<TaskSummary> taskSumList = taskSvc.getTaskAsSummary();

        if (taskSumList == null) {
            logger.info("No existing task summary records found");
        } else {
            logger.info("Task summary records found:" + taskSumList);
        }

        //OK = 200
        mav.setStatus(HttpStatus.OK);
        mav.setViewName("index");
        mav.addObject("task", taskSumList);
        return mav; 
    }

    //add task: after receive param(s, insert()

    //update task: after receive params, update()

    //delete task: after receive param, delete()
}
