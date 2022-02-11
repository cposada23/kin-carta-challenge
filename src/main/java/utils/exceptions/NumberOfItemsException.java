package utils.exceptions;

public class NumberOfItemsException extends Exception{
    public NumberOfItemsException(String message) {
        super(message);
    }
    public NumberOfItemsException(String message, Throwable e) {
        super(message, e);
    }
}
