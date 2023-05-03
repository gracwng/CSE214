/**
 * This is an Exception class which indicates that the user attempted to access a SlideListNode that 
 * does not exist (either before the head node or after the tail node). This exception can also be 
 * thrown when an operation is performed on an empty list (i.e. head, tail, and cursor are all equal to null).
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

public class EndOfListException extends Exception {
   //constructors
   //thrown when  the user attempted to access the SlideListNode that doesn't exist (this means before the head node or after the tail node)
    //thrown when an operation is performed on an empty list (ie, head tail, and cursor all equal null)
    // is it like super("the SlideListNode doesn't exist or the operation is performed on an empty list")
    //ask how to write an EndOfListException for these two cases, would the constructor just be fore the same case?
    // and this would be printed out in the PresentationManager class right?
   public EndOfListException(String message){
      super(message);
   }
}
