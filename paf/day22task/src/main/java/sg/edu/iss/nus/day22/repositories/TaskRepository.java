package sg.edu.iss.nus.day22.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.iss.Utils;
import sg.edu.iss.nus.day22.models.Task;
import sg.edu.iss.nus.day22.models.TaskSummary;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate template;

    private Logger logger = Logger.getLogger(TaskRepository.class.getName());

    // task_id int, title string, completed boolean default false,
    //check if db contains task, task cannot exist without an id
    public boolean hasTask(int taskId) {
        //queryForRowSet = read from database
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_COUNT_TASK, taskId);

        if(!rs.next()) 
            return false;
        
        int taskCount = rs.getInt("task_count");
        return taskCount > 0;
    }

    //update() returns no. of rows affected, >0 mean more than 1 row actioned/successful?
    public boolean updateTaskAsComplete (boolean completed, int taskId) {
       return template.update(Queries.SQL_UPDATE_TASK_COMPLETED_BY_ID, completed, taskId) > 0;
    }

    //update() sends a update statement for insert, update, delete
    public boolean deleteTaskById (int taskId) {
        return template.update(Queries.SQL_DELETE_TASK_BY_ID, taskId) > 0;
    }

    //insert new summary into database
    public boolean addTaskSum (TaskSummary taskSum) {

        if (hasTask(taskSum.getTaskId()) == false) {
            logger.warning("Insert unsuccessful: Task ID exists");
        }

        logger.info("Inserting new query");
        return template.update(Queries.SQL_INSERT_TASK_AS_SUMMARY,
            taskSum.getTaskId(), taskSum.getTitle(), taskSum.isCompleted()) > 0;
    }

    //retrieve records
    public List<TaskSummary> getTaskAsSummary() {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_TASK_AS_SUMMARY);
        List<TaskSummary> taskSumList = new LinkedList<>();

        //check if record exists
        if (!rs.next()) {
            logger.info("Task summary not found");
            return new LinkedList<>();
        }

        //set cursor position to initial of resultset
        rs.beforeFirst();
        while(rs.next()){
            taskSumList.add(Utils.toTaskSummary(rs));
        }
        return taskSumList;  
    }

    public List<Task> getTasks() {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_TASK);
        List<Task> taskList = new LinkedList<>();

         //check if record exists
        if (!rs.next()) {
            logger.info("Task not found");
            return new LinkedList<>();
        }

        rs.beforeFirst();
        while(rs.next()){
            taskList.add(Utils.toTask(rs));
        }

        return taskList;
    }
    
    public List<TaskSummary> getTaskSummaryById(int taskId, TaskSummary taskSum) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_TASK_AS_SUMMARY_BY_ID, taskId);

        if (!rs.next()) {
            logger.info("Task summary not found");
            return new LinkedList<>();
        }

        List<TaskSummary> taskSumList = new LinkedList<>();

        taskSumList.add(Utils.toTaskSummary(rs));
        return taskSumList;
    }
}
