/**
 * This is a custom exception thrown if position is empty or null.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 */
public class InvalidArgumentException extends Exception {
    public InvalidArgumentException (String message){
        super(message);
    }
}
