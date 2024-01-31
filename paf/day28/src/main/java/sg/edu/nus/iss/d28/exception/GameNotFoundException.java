package sg.edu.nus.iss.d28.exception;

public class GameNotFoundException extends Exception {

    public GameNotFoundException() {
    }

    public GameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    
}
