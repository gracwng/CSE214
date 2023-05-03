/**
 * This is an Exception class which is thrown if the user attempts to push a Cargo object on to any stack of the CargoShip 
 * which would put it over it's maximum weight limit.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 public class ShipOverweightException extends Exception {
    //Should be thrown if the user attempts to push a Cargo object on to any stack of the CargoShip which would put it over it's maximum weight limit.
    public ShipOverweightException(String message){
        super(message);
    }
}
