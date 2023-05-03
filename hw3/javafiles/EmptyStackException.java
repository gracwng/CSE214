/**
 * This is an Exception class which is thrown if the user attempts to pop a CargoStack that currently has no Cargo items on it
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 public class EmptyStackException extends Exception{
    //should be thrown if the user attempts to pop a CargoStack that currently has no Cargo items on it
    public EmptyStackException(String message){
        super(message);
    }
}
