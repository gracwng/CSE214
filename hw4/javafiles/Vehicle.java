/**
 * This class represents a car which passes through the intersection. Each instance must contain the serialId 
 * (the first car to arrive at the intersection is serialId 1, the second car to arrive is serialId 2, the n'th 
 * car to arrive will have serialId >n) and the time it arrived (stored as an int). The car must be initialized 
 * with a serialId and the time it arrived. The serial counter is static and stores the number of vehicles that 
 * have arrived at the intersection so far. It is the only variable that is modifiable
 * 
 * The Vehicle class itself is actually immutable. This means once it has been constructed, no data within the 
 * instance can be changed (the static serialCounter can and should be incremented on each constructor call). 
 * From the UML diagram, note that the public constructor takes all the member variables as arguments. 
 * Data can still be read via the getter methods.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 public class Vehicle {
    private static int serialCounter = 0;
    private int serialId;
    private int timeArrived;

/**
 * Default Constructor. You should automatically increment the serialCounter, and set the serialId to its new value.
 * @param initTimeArrived
 * Time the vehicle arrived at the intersection.
 * @throws IllegalArgumentException
 * If initTimeArrived ≤ 0.
 */

    public Vehicle(){
        serialId = 0;
    }
    public Vehicle (int initTimeArrived) throws IllegalArgumentException {
        serialCounter++;
        serialId = serialCounter;
        if (initTimeArrived <= 0)
            throw new IllegalArgumentException("Time must be greater than 0.");
        timeArrived = initTimeArrived;
    }
/**
 * Returns serial id
 * @return serialID
 */
    //optional getter methods
    public int getSerialId(){
        return serialId;
    }

    /**
     * returns time arrived
     * @return
     * time arrived
     */
    public int getTimeArrived(){
        return timeArrived;
    }

    /**
     * Returns serial counter
     * @return
     * serial counter
     */
    public int getSerialCounter(){
        return serialCounter;
    }
}
