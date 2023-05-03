/**
 * This class represents a container which holds the ‘cargo’ the program will place on the ship. 
 * It contains the following private member variables: name (String), weight (double) and strength (CargoStrength).
 * The Cargo class itself is actually immutable. This means once it has been constructed, no data within can be changed. 
 * From the UML diagram, note that the public constructor takes all the member variables as arguments. 
 * Data can still be read via the getter methods.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

public class Cargo{
    private String name;
    private double weight;
    private CargoStrength strength;

    //do I need a default constructor for the class that extends this one?
    // public Cargo(){
    //     name = null;
    //     weight = 0;
    //     strength = null;
    // }

    /**
     * Default Constructor
     * 
     * @param initName
     * Non-null name for the cargo item.
     * 
     * @param initWeight
     * The weight for the cargo. The weight should be greater than 0, so an exception should be thrown if initWeight ≤ 0 (We do not deal with massless cargo).
     * 
     * @param initStrength
     * Either FRAGILE, MODERATE, or STURDY.
     * 
     * @custom.precondition
     * initName is not null. 
     * initWeight > 0.
     * 
     * @custom.postcondition
     * This object has been initialized to a Cargo object with the given name, weight, and strength.
     * 
     * @throws IllegalArgumentException
     * If initName is null.
     * If initWeight ≤ 0.
    */

    public Cargo(String initName, double initWeight, CargoStrength initStrength) throws IllegalArgumentException {
        if (initName == null){
            throw new IllegalArgumentException("Name cannot be null.");
        }
        else name = initName;
        if (initWeight <= 0){
            throw new IllegalArgumentException("Weight cannot be less than or equal to 0.");
        }
        else weight = initWeight;
        if (initStrength == CargoStrength.FRAGILE || initStrength == CargoStrength.MODERATE || initStrength == CargoStrength.STURDY){
            strength = initStrength; //but what if they input the wrong initStrength?
        }
        else{
            throw new IllegalArgumentException("Invalid cargo strength.");
        }
        
    }

    /**
     * Getter method for name
     * @return
     * the name of the cargo
     */
    public String getName(){
        return name;
    }

    /**
     * Getter method for weight
     * @return
     * the weight of the cargo
     */
    public double getWeight(){
        return weight;
    }

    /**
     * Getter method for strength
     * @return
     * the strength of the cargo
     */
    public CargoStrength getStrength(){
        return strength;
    }

    
}