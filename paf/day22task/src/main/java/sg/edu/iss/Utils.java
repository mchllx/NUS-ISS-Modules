package sg.edu.iss;

//java.sql.Date inherits util.Date
import java.util.Date;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.JsonObject;
import sg.edu.iss.nus.day22.models.Task;
import sg.edu.iss.nus.day22.models.TaskSummary;

public class Utils {

    //int task_id, string title, boolean completed
    public static TaskSummary toTaskSummary(SqlRowSet rs) {
        TaskSummary taskSum = new TaskSummary(0, "untitled", false);

        taskSum.setTaskId(rs.getInt("task_id"));
        taskSum.setTitle(rs.getString("title"));
        taskSum.setCompleted(rs.getBoolean("completed"));
       
        return taskSum;
    }

    public static Date parseJsonNumToDate(JsonObject jsonObj) {
        long rawDate = jsonObj.getJsonNumber("dueDate").longValue();
        // System.out.println(rawDate);
        return new Date(rawDate);
        };

    public static Task jsonToTask(int taskId, Task task, JsonObject jsonObj) {
        // int taskId, String title, Date dueDate, int priority, boolean completed
        task.setTaskId(taskId);
        task.setTitle(jsonObj.getString("title"));
        task.setDueDate(Utils.parseJsonNumToDate(jsonObj));
        // System.out.println(task.getDueDate());
        task.setPriority(jsonObj.getInt("priority"));
        task.setCompleted(false);
        
        return task;
        
    }

    public static TaskSummary jsonToTaskSum(int taskId, TaskSummary taskSum, JsonObject jsonObj) {
        // int taskId, String title, Date dueDate, int priority, boolean completed
        taskSum.setTaskId(taskId);
        taskSum.setTitle(jsonObj.getString("title"));
        taskSum.setCompleted(false);
        
        return taskSum; 
    }

    // int taskId, String title, Date due_date, int priority, boolean completed
    public static Task toTask(SqlRowSet rs) {
        Task task = new Task(0, "untitled", new Date(), 0, false);

        task.setTaskId(rs.getInt("task_id"));
        task.setTitle(rs.getString("title"));
        task.setDueDate(rs.getDate("due_date"));
        task.setPriority(rs.getInt("priority"));
        task.setCompleted(rs.getBoolean("completed"));
       
        return task;
    }
    
}