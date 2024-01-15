package sg.edu.iss.nus.day22.repositories;

public class Queries {

    public static final String SQL_COUNT_TASK = """
        select count(task_id) as task_count
            from task_summary
            where task_id  =?
    """;

    //retrieve record summary
    public static final String SQL_SELECT_TASK_AS_SUMMARY = """
        select task_id, title, completed
            from task_summary
    """;

    //retrieve record summary via task id
    public static final String SQL_SELECT_TASK_AS_SUMMARY_BY_ID = """
        select task_id, title, completed
            from task_summary
            where task_id = ?
    """;
  
    //retrieve whole task
    public static final String SQL_SELECT_TASK = """
        select task_id, title, due_date, priority, completed
            from task
    """;

    //update record by id to set specific task as completed
    //methods should not have overlapping functions
    public static final String SQL_UPDATE_TASK_COMPLETED_BY_ID = """
        update task_summary
            set completed = ?
            where task_id = ? 
    """;

    //delete record
    public static final String SQL_DELETE_TASK_BY_ID = """
        delete from task_summary
            where task_id = ?
    """;

    //insert task
    public static final String SQL_INSERT_TASK = """
        insert into task(task_id, title, due_date, priority, completed)
            values (?,?,?,?,?)
    """;

    //insert task summary
    public static final String SQL_INSERT_TASK_AS_SUMMARY = """
        insert into task_summary(task_id, title, completed)
            values (?,?,?)
    """;

}
