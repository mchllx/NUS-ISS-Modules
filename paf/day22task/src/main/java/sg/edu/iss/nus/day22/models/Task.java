package sg.edu.iss.nus.day22.models;

import java.sql.Date;

public class Task {

    private int taskId;
    private String title;
    private Date dueDate;
    private int priority;
    private boolean completed;

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", title=" + title + ", dueDate=" + dueDate + ", priority=" + priority
                + ", completed=" + completed + "]";
    }

    public Task() {
        
    }

    public Task(int taskId, String title, Date dueDate, int priority, boolean completed) {
        this.taskId = taskId;
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = completed;
    }
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    
}
