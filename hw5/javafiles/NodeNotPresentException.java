/**
 * This is a custom exception which is called when the Node with indicated position variable was not found.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 */
public class NodeNotPresentException extends Exception {
    public NodeNotPresentException(String message){
        super(message);
    }
}
