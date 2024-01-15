package sg.edu.iss.nus.day22.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.nus.day22.models.Task;
import sg.edu.iss.nus.day22.models.TaskSummary;
import sg.edu.iss.nus.day22.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    TaskRepository taskRepo;
   
    private Logger logger = Logger.getLogger(TaskService.class.getName());

    //check existing records
    public Integer taskCount() {
        return taskRepo.getTaskAsSummary().size();
    }

    //retrieve by id
    public List<TaskSummary> getTaskSummaryById(int taskId, TaskSummary taskSum) {
        return taskRepo.getTaskSummaryById(taskId, taskSum);
    }

    //retrieve summary
    public List<TaskSummary> getTaskAsSummary() {
        return taskRepo.getTaskAsSummary();
    }

    public List<Task> getTask() {
        return taskRepo.getTasks();
    }

    //update
    public boolean updateTaskAsComplete(boolean completed, int taskId) {
        boolean updateTaskAsComplete = taskRepo.updateTaskAsComplete(completed, taskId);

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
    
    //add summary by id
    public boolean addTaskSum(TaskSummary taskSum) {
        boolean addTaskById = taskRepo.addTaskSum(taskSum);

        if (addTaskById == true) {
            logger.info("Added new task successfully: MySQL");
        }

        return addTaskById;
    }


    
}
