/**
 * This is an Exception class which is thrown if the user attempts to push a Cargo object onto a stack 
 * which is currently at the maximum height.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

public class FullStackException extends Exception {
    //Should be thrown if the user attempts to push a Cargo object onto a stack which is currently at the maximum height.

    public FullStackException(String message){
        super(message);
    }
}
