/**
 * This is an Exception class which indicates that the user attempts to push a Cargo object onto another 
 * Cargo object that violates the CargoStrength rules indicated above
 * FRAGLIE containers can only support other FRAGILE containers.
 * MODERATE containers can support other MODERATE containers, as well as FRAGILE containers.
 * STURDY containers can support all other containers.
 * The number of containers stacked above any other container does not matter. The only restrictions are the three rules above (Though this is a naïve assumption, the simplification will make the assignment easier).
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 public class CargoStrengthException extends Exception {
    //Should be thrown if the user attempts to push a Cargo object onto another Cargo object that violates the CargoStrength rules indicated above.

    public CargoStrengthException(String message){
        super(message);
    }
    
}
