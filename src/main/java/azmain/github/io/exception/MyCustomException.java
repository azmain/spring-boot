package azmain.github.io.exception;

public class MyCustomException extends RuntimeException {

    public MyCustomException(){}

    public MyCustomException(String message) {
        super(message);
    }
}
