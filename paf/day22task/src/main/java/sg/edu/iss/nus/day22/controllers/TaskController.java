package sg.edu.iss.nus.day22.controllers;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.http.HttpSession;
import sg.edu.iss.Utils;
import sg.edu.iss.nus.day22.models.Task;
import sg.edu.iss.nus.day22.models.TaskSummary;
import sg.edu.iss.nus.day22.services.TaskService;

@Controller
@RequestMapping(path={"/", "index"})
public class TaskController {

    @Autowired
    TaskService taskSvc;

    private Logger logger = Logger.getLogger(TaskController.class.getName());

    @GetMapping
    public ModelAndView viewLandingPage(HttpSession sess) {
        ModelAndView mav = new ModelAndView();

        List<Task> taskList = taskSvc.getTask();
        List<TaskSummary> taskSumList = taskSvc.getTaskAsSummary();

        if (taskList == null) {
            logger.info("No task records retrieved");
        } else {
            logger.info("Tasks found:" + taskList);
        }

        if (taskSumList == null) {
            logger.info("No task summary records retrieved");
        } else {
            logger.info("Task summary found:" + taskSumList);
        }

        //OK = 200
        mav.setStatus(HttpStatus.OK);
        mav.setViewName("index");
        mav.addObject("task", taskList);
        mav.addObject("taskSum", taskSumList);
        sess.setAttribute("task", taskList);
        sess.setAttribute("taskSum", taskSumList);
        // System.out.println("Landing:" + sess.getAttribute("taskSum"));
        return mav; 
    }

    //add/insert task from view to server to database
    //{title: "", dueDate: 1705042462921, priority: 3}
    //receive request sent to api/task, sets values to "task" in session
    //@requestbody to retrieve payload without a restcontroller
    @PostMapping(value="/api/task", consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> addTask(@RequestBody String payload, HttpSession sess) {

        //check if payload if null
        if (payload.isEmpty() == true) {
            logger.info("Payload is empty/null");
        }

        logger.info("Payload found" + payload);

        //check if session is empty
        if (sess.equals(null)) {
            logger.info("Starting a new session");
            new LinkedList<>();
        }

        logger.info("Session found" + sess.getAttribute("taskSum"));
        List<TaskSummary> taskSumList = (List<TaskSummary>)sess.getAttribute("taskSum");

        Task newTask = new Task(); 
        TaskSummary newTaskSum = new TaskSummary(); 

        JsonReader jsonReader = Json.createReader(new StringReader(payload));
        JsonObject jsonObj = jsonReader.readObject();
        // System.out.println(jsonObj);

        int taskId = taskSumList.size()+1;
        // System.out.println(taskId);

        newTask = Utils.jsonToTask(taskId, newTask, jsonObj);
        newTaskSum = Utils.jsonToTaskSum(taskId, newTaskSum, jsonObj);

        taskSumList.add(newTaskSum);

        sess.setAttribute("task", newTask);
        sess.setAttribute("taskSum", taskSumList);
        // System.out.println("New Session" + sess.getAttribute("taskSum"));

        //add to database
        taskSvc.addTaskSum(newTaskSum);

        return ResponseEntity.ok(payload);
        }

    //get tasks from database to view
    //Front-end .get<TaskSummary[]>
    @GetMapping("/api/tasks")
    @ResponseBody
    public ResponseEntity<String> getTasks() {
        //retrieve all records from database
        List<TaskSummary> taskSumList = taskSvc.getTaskAsSummary();

        if (taskSumList == null) {
            logger.info("No task summary records loaded");
        }

        logger.info("Task summary records loaded");
        
        //[..[...],[...],[...]]
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        taskSumList.stream()
            .forEach(taskSum -> jsonArrayBuilder.add(taskSum.toJson())); 

        return ResponseEntity.ok(jsonArrayBuilder.build().toString());
    }
 
    //update task
    @PatchMapping("/api/task/{taskId}")
    @ResponseBody
    public ResponseEntity<String> insertTaskToDB(
        @PathVariable int taskId, @RequestBody String payload) {

        // System.out.println("TaskId" + taskId);

        //check if payload is null
        if (payload.isEmpty() == true) {
            logger.info("Payload is empty/null");
        }

        //{"completed": true}
        JsonReader jsonReader = Json.createReader(new StringReader(payload));
        JsonObject jsonObj = jsonReader.readObject();

        boolean result = taskSvc.updateTaskAsComplete(jsonObj.getBoolean("completed"), taskId);
        // System.out.println("New update" + result);

        return ResponseEntity.ok(jsonObj.toString());
    }

    //delete task
    @DeleteMapping(value ="/api/task/{taskId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Integer> deleteTaskfromDB(
        @PathVariable int taskId) {

       //check if payload is null
        if (taskId < 0) {
            logger.info("TaskId is invalid");
        }

        taskSvc.deleteTaskById(taskId);
        return ResponseEntity.ok(taskId); 
    }
}
