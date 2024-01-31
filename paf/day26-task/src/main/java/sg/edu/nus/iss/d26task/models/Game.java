package sg.edu.nus.iss.d26task.models;

import java.util.List;

public record Game(int id, String name, int ranking, List<Comment> comments) { 
}
