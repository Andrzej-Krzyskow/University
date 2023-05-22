package tutorial;

public class NoAnswerException extends Exception{

    public NoAnswerException() {
        super("There is now answer");
    }

    public NoAnswerException(String message) {
        super(message);
    }
}
