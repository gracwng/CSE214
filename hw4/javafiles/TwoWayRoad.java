/**
 * This class represents one of the roads in our intersection. Each road it bi-directional, with each direction having three lanes - 
 * a left turn lane, a middle lane, and a right turn lane. Lanes, modelled by VehicleQueues, hold the vehicles before they pass 
 * through the intersection. These lanes will be stored in a two dimensional array - the first index indicates the direction of 
 * travel on the road, and the second index indicates the lane on the road. In order to access a specific direction, you should use 
 * the constants FORWARD_WAY and BACKWARD_WAY to access the directions of the road. In order to access specific lanes in a particular 
 * direction, you should use the second dimension of the array, accessed by the consants LEFT_LANE, MIDDLE_LANE, RIGHT_LANE. Your 
 * TwoWayRoad class must be able to check whether any of the lanes in the road have Vehicle objects in them by using the boolean 
 * isEmpty(int wayIndex, int laneIndex) method. It should also be able to add Vehicle objects to the lanes using the void 
 * enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle) method. Furthermore, you should allow vehicles to pass through 
 * the intersection, adding the Vehicles that have been dequeued to an array to returned to the caller.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/
//this class is for a single road, which has a total of 6 lanes. 
 public class TwoWayRoad {
    //final variables
    public static final int FORWARD_WAY = 0;
    public static final int BACKWARD_WAY = 1;
    public static final int NUM_WAYS = 2;

    public static final int LEFT_LANE = 0;
    public static final int MIDDLE_LANE = 1;
    public static final int RIGHT_LANE = 2;
    public static final int NUM_LANES = 3;

    //member variables
    private String name;
    //the max  total number of steps this road can be active (this number is INCLUSIVE of the leftSignalGreenTIme)
    private int greenTime;
    //The number of steps this road remains in the LEFT_SIGNAL state. Should be initialized to 1/NUM_LANES*greenTime in the constructor
    private int leftSignalGreenTime;
    private VehicleQueue lanes[][] = new VehicleQueue[NUM_WAYS][NUM_LANES];
    private LightValue lightValue;


    //custom default constructor:

    
    //constructor:
    //this initialzies the lane array and all of its member objects and initializes leftSignalGreenTime to 1/NUM_LANES*greenTime 
   /**
    * Default Constructor. You should automatically initialize the array and all of its member objects, as well as initializing leftSignalGreenTime to 1.0/NUM_LANES * initGreenTime.
    * @param initName
    The name of the road.
    * @param initGreenTime
    The amount of time that the light will be active for this particular road. This is the total of the time the light should display green for cars going forward/turning right, as well as for cars going left.
    * @throws IllegalArgumentException
    If initGreenTime ≤ 0 or initName=null.
    */
    public TwoWayRoad(String initName, int initGreenTime) throws IllegalArgumentException{
        if (initGreenTime <= 0)
        throw new IllegalArgumentException("Invalid greenTime.");
        if  (initName == null)
        throw new IllegalArgumentException("Invalid name.");
        name = initName;
        greenTime = initGreenTime;
        leftSignalGreenTime = 1/NUM_LANES * initGreenTime;
        for (int i = 0; i < lanes.length; i++){
            for (int j = 0; j < lanes[i].length; j++){
                lanes[i][j] = new VehicleQueue();
            }
        }
        lightValue = LightValue.GREEN;
    }

    //getters
    /**
     * Returns greenTime
     * @return greenTime
     */
    public int getGreenTime(){
        return greenTime;
    }

    /**
     * Returns lightValue
     * @return lightValue
     */
    public LightValue getLightValue(){
        return lightValue;
    }
    
    //custom getters
    //get the name of the road
    /**
     * Returns the name of road
     * @return name
     */
    public String getName(){
        return name;
    }

    //methods
    //this method removes the cars from the lanes where the light is green
    //timerVal represents the current value of a countdown timer counting down total green time steps

    /**
     * Executes the passage of time in the simulation. The timerVal represents the current value of a countdown timer counting down
     *  total green time steps. The light should be in state GREEN any time the timerval is greater than leftSignalGreenTime. 
     * When timerVal is less than or equal to leftSignalGreenTime, the light should change to LEFT_SIGNAL. After the execution 
     * of timerVal == 1, or if there are no vehicles left the light should change state to RED.
     * @param timerVal
     * The current timer value, determines the state of the light.
     * @return
     * An array of Vehicles that has been dequeued during this time step.
     * @throws IllegalArgumentException
     * If timerval ≤ 0.
     */
    public Vehicle[] proceed (int timerVal) throws IllegalArgumentException{
        if (timerVal <=0)
        throw new IllegalArgumentException("Invalid timer value.");

        int countVehicles = 0;
        Vehicle[] dequeued = new Vehicle[4];    //4 because a max of 4 vehicles can pass through an intersection
        //int x = 0;
        for (int i = 0; i< dequeued.length; i++){
            dequeued[i] = new Vehicle();
        }
        
        
        //if the timerVal is greater than leftSignalGreen, change light to GREEN, and dequeue vehicle into dequeued array
        if (lightValue == LightValue.GREEN || timerVal > leftSignalGreenTime){
            lightValue = LightValue.GREEN;  //green means right and middle lanes can proceed            
            
            //what if there are no cars in the right and middle lanes for both ways? then we set the light value to left signal
            if (lanes[FORWARD_WAY][MIDDLE_LANE].isEmpty() && lanes[FORWARD_WAY][RIGHT_LANE].isEmpty() && 
            lanes[BACKWARD_WAY][MIDDLE_LANE].isEmpty() && lanes[BACKWARD_WAY][RIGHT_LANE].isEmpty())
            lightValue = LightValue.LEFT_SIGNAL; 



            //so if there are cars in the right and middle lanes for both ways, dequeue them and add them to the dequeued array
            else{
                //if there is a car in the forward way, middle lane, dequeue it
                if (!lanes[FORWARD_WAY][MIDDLE_LANE].isEmpty()){
                    Vehicle x = lanes[FORWARD_WAY][MIDDLE_LANE].dequeue();
                    dequeued[countVehicles] = x;
                    countVehicles++;

                }
                //if there is a car in the forward way, right lane, dequeue it                
                if (!lanes[FORWARD_WAY][RIGHT_LANE].isEmpty()){
                    Vehicle x = lanes[FORWARD_WAY][RIGHT_LANE].dequeue();
                    dequeued[countVehicles] = x;
                    countVehicles++;
                }
                 //if there is a car in the backward way, middle lane, dequeue it               
                if (!lanes[BACKWARD_WAY][MIDDLE_LANE].isEmpty()){
                    Vehicle x = lanes[BACKWARD_WAY][MIDDLE_LANE].dequeue();
                    dequeued[countVehicles] = x;
                    countVehicles++;

                }
                //if there is a car in the backward way, right lane, dequeue it
                if (!lanes[BACKWARD_WAY][RIGHT_LANE].isEmpty()){
                    Vehicle x = lanes[BACKWARD_WAY][RIGHT_LANE].dequeue();
                    dequeued[countVehicles] = x;
                    countVehicles++;

                }
            }
        }
        //if the timerVal is less than or equal to leftSignalGreenTime, the light should change to LEFT_SIGNAL
        if (lightValue == LightValue.LEFT_SIGNAL || timerVal <= leftSignalGreenTime) {     
            lightValue = LightValue.LEFT_SIGNAL;    //left signal means only left can proceed
            
            //what if there are no cars in the left lane for both ways? I'm guessing then we should set the light to RED
            if (lanes[FORWARD_WAY][LEFT_LANE].isEmpty() && lanes[BACKWARD_WAY][LEFT_LANE].isEmpty())
                lightValue = LightValue.RED;

            else{
            //so if there are cars in the left lane for both ways, dequeue them and add them to the dequeued array
                //if there is a car in the forward way, left lane, dequeue it
                if (!lanes[FORWARD_WAY][LEFT_LANE].isEmpty()){
                    Vehicle x = lanes[FORWARD_WAY][LEFT_LANE].dequeue();
                    dequeued[countVehicles] = x;
                    countVehicles++;
                    System.out.println(x.getSerialId());

                }
                //if there is a car in the backward way, left lane, dequeue it
                if (!lanes[BACKWARD_WAY][LEFT_LANE].isEmpty()){
                    Vehicle x = lanes[BACKWARD_WAY][LEFT_LANE].dequeue();
                    dequeued[countVehicles] = x;
                    countVehicles++;

                }
            }  
        }
        //if the timerVal is 0 or if there are no vehicles left then turn light to RED
        if (lightValue == LightValue.RED || timerVal == 1 || isRoadEmpty()) {   //MIGHT NEED TO FIX How do I check this?
            lightValue = LightValue.RED;    //red means no lanes can proceed
            //I think the method that calls this should check if the lightvalue is red, and if it is, then it will move onto the next road
        }
        // for (int i = 0; i < NUM_LANES; i++){
        //     for (int j = 0; j <NUM_WAYS; j++){
        //         if (!lanes[i][j].isEmpty()){
        //             lightValue = LightValue.GREEN;
        //             break;
                       
        //             }
        //             else 
        //             lightValue = LightValue.RED;
        //         }
        //     }

        //dequeue middle and right lane
    //     if (timerVal )   
    //     }


        return dequeued;
      
    
}

    //this checks to see if a specific lane in the road has a vehicle object in it

    /**
     * Checks if a specified lane is empty.
     * @param wayIndex
     * The direction of the lane.
     * @param laneIndex
     * The index of the lane to check.
     * @return
     * true if the lane is empty, else false.
     * @throws IllegalArgumentException
     * If wayIndex &gt 1 || wayIndex &lt 0 || laneIndex &lt 0 || laneIndex &gt 2.
     */
    public boolean isLaneEmpty(int wayIndex, int laneIndex) throws IllegalArgumentException {
        if (wayIndex > 1 || wayIndex < 0 || laneIndex > 2 || laneIndex < 0)
        throw new IllegalArgumentException("Invalid index.");
        return (lanes[wayIndex][laneIndex].isEmpty());
    }

    //this method adds the vehicle into a specific lane

    /**
     * Enqueues a vehicle into a the specified lane.
     * @param wayIndex
     * The direction the car is going in.
     * @param laneIndex
     * The lane the car arrives in.
     * @param vehicle
     * The vehicle to enqueue; must not be null.
     * @throws IllegalArgumentException
     * If wayIndex &gt 1 || wayIndex &lt 0 || laneIndex &lt 0 || laneIndex &gt 2 or vehicle==null
     */
    public void enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle) throws IllegalArgumentException {
        if (wayIndex > 1 || wayIndex < 0 || laneIndex > 2 || laneIndex < 0 || vehicle == null)
        throw new IllegalArgumentException("Invalid index.");
        else{
            // for (int i = 0; i < lanes.length; i++){
            //     for (int j = 0; j < lanes[i].length; j++){
            //         System.out.println(lanes[i][j] + " ");
            //     }
            // }
            // System.out.println("wayIndex" + wayIndex);
            // System.out.println("lane" + laneIndex);

            lanes[wayIndex][laneIndex].enqueue(vehicle);
            //System.out.println(lanes[wayIndex][laneIndex].toString());
        }
    }

    //custom methods
    /**
     * checks if road is empty
     * @return true of false
     */
    public boolean isRoadEmpty() {
        //for each way
        for (int j = 0; j < TwoWayRoad.NUM_WAYS; j++){
            //for each direction
            for (int k = 0; k < TwoWayRoad.NUM_LANES; k++){
                if (!isLaneEmpty(j, k)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * prints the string of the road
     * @param way
     * which way the vehicle is going
     * @param lane
     * which lane the vehicle is in
     * @return string format of the road
     */
    public String forwardWay(int way, int lane){
         return lanes[way][lane].toString();
    //     //have a displays method inside the vehicle queue class
     }

    // public String backwardWay(int way, int lane){

    // }

    //have a to string method
   
}
