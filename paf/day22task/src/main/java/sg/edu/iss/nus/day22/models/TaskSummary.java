package sg.edu.iss.nus.day22.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class TaskSummary {

    private int taskId;
    private String title;
    private boolean completed;

    @Override
    public String toString() {
        return "TaskSummary [taskId=" + taskId + ", title=" + title + ", completed=" + completed + "]";
    }

    public TaskSummary() {
    }

    public TaskSummary(int taskId, String title, boolean completed) {
        this.taskId = taskId;
        this.title = title;
        this.completed = completed;
    }
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public JsonObject toJson () {
        return Json.createObjectBuilder()
            .add("taskId", getTaskId())
            .add("title", getTitle())
            .add("completed", isCompleted())
            .build();
    }

}
