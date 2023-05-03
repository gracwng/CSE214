/**
 * This is a custom exception thrown if all three child spots of the current node are already full.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 */
public class TreeFullException extends Exception {
    public TreeFullException(String message){
        super(message);
    }
}
