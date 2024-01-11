package sg.edu.iss;

import org.springframework.jdbc.support.rowset.SqlRowSet;

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

    


    
}
