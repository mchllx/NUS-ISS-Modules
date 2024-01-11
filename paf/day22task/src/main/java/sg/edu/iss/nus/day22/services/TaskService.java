package sg.edu.iss.nus.day22.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.nus.day22.models.TaskSummary;
import sg.edu.iss.nus.day22.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    TaskRepository taskRepo;
   
    private Logger logger = Logger.getLogger(TaskService.class.getName());

    //retrieve by id
    public List<TaskSummary> getTaskSummaryById(int taskId, TaskSummary taskSum) {
        List<TaskSummary> taskSumList = taskRepo.getTaskSummaryById(taskId, taskSum);
        return taskSumList;
    }

    //retrieve summary
    public List<TaskSummary> getTaskAsSummary() {
        List<TaskSummary> taskSumList = taskRepo.getTaskAsSummary();
        return taskSumList;
    }

    //update
    public boolean updateTaskAsComplete(int taskId, boolean completed) {
        boolean updateTaskAsComplete = taskRepo.updateTaskAsComplete(taskId, completed);

        if (updateTaskAsComplete == true) {
            logger.info("Updated task as complete successfully: MySQL");  
        }

        return updateTaskAsComplete;
    }
    
    //delete
    public boolean deleteTaskById(int taskId) {
        boolean deleteTaskById = taskRepo.deleteTaskById(taskId);

        if (deleteTaskById == true) {
            logger.info("Deleted task successfully: MySQL"); 
        }

        return deleteTaskById;
    }
    
    //add by id
    public boolean addTaskById(int taskId, TaskSummary taskSum) {
        boolean addTaskById = taskRepo.addTaskById(taskId, taskSum);

        if (addTaskById == true) {
            logger.info("Added new task successfully: MySQL");
        }

        return addTaskById;
    }
    
}
