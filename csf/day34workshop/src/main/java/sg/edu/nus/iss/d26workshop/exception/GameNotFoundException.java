package sg.edu.nus.iss.d26workshop.exception;

public class GameNotFoundException extends Exception {

    public GameNotFoundException() {
    }

    public GameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    
}
