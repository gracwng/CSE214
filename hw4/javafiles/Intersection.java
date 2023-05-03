/**
 * This class represents a crossing of two or more roads at a stop light in our simulation. The class consists of an array of 
 * TwoWayRoad objects representing the crossing roads, as well as a countdown timer and a light index. Intersection must contain 
 * the following private member variables: roads (TwoWayRoad[]), lightIndex (int), and countdownTimer (int). The Intersection 
 * class must also feature the following public methods: void timeStep(), and void enqueueVehicle(int roadIndex, int wayIndex, 
 * int laneIndex), as well as a display() method which prints the intersection to the terminal.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 public class Intersection {
    //member variables 
    private TwoWayRoad[] roads; //array of roads which cross at this intersection
    private int lightIndex; //indicaates the road in roads with the activelight 
    private int countdownTimer; //tracks the remaining greenTime available for the road currently indicated by lightIndex
    public final int MAX_ROADS = 4;
    private VehicleQueue enqueuedVehicles;

    //constructor
    //this constructor initializes the roads array
    /**
     * Constructor which initializes the roads array.
     * @param initRoads
     * Array of roads to be used by this intersection.
     * @throws IllegalArgumentException
     * This object has been initialized to a Intersection object managing the roads array.
     */
    public Intersection(TwoWayRoad[] initRoads) throws IllegalArgumentException{
        if (initRoads == null || initRoads.length > MAX_ROADS)
            throw new IllegalArgumentException("Roads can't be null and there must be less than 4 roads");
        //iterate through the roads array to check each road is not null
        for (int i = 0; i < initRoads.length; i++){
            if (initRoads[i] == null)
            throw new IllegalArgumentException("Road can't be null.");
        }
        roads = initRoads; 
        /*During execution, the 'light' will be simulated using the lightIndex member variable of the Intersection instance. 
        This variable indicates the index of the road in the roads array which currently has the active light (the current active 
        light may be in the GREEN state or LEFT_TURN state, as described by the LightValue enum below). When the Intersection 
        instance is constructed, it initializes the lightIndex member variable to 0 and the countdownTimer to the greenTime member 
        variable of roads[lightIndex]. After each time step, the timer is decremented by 1. Once the timer reaches 0, the lightIndex 
        is incremented, returning back to 0 if it equals the size of the roads array (i.e. modular arithmetic), and the countdownTimer 
        is again set to roads[lightIndex]. This process repeats continually until the simulation ends. */
        lightIndex = 0; //light index is determined by the countdown timer. when timer reaches 0, light index is incremented which means
        //the greentime is over. 
        countdownTimer = roads[lightIndex].getGreenTime();
    }

    //getters
    /**
     * returns road length
     * @return
     * length
     */
    public int getNumRoads(){
        return roads.length;
    }

    /**
     * returns light index
     * @return light index integer
     */
    public int getLightIndex(){
        return lightIndex;
    }
    
    /**
     * returns countdowntimer
     * @return countdowntimer
     */
    public int getCountdownTimer(){
        return countdownTimer;
    }

    /**
     * returns current light value
     * @return current light value
     */
    public LightValue getCurrentLightValue(){
        return roads[getLightIndex()].getLightValue();  //not sure if this is right
    }

    //custom getters

    /**
     * gets road
     * @return road
     */
    public TwoWayRoad[] getRoad(){
        return roads;
    }

    /**
     * gets enqueued vehicles
     * @return enqueued vehicles
     */
    public VehicleQueue getEnqueuedVehicles(){
        return enqueuedVehicles;
    }

    //custom setters

    /**
     * sets countdown timer
     * @param x
     * new countdown timer
     */
    public void setCountDownTimer(int x){
        x = countdownTimer;
    }

    /**
     * sets light index
     * @param x
     * new light index
     */
    public void setLightIndex(int x){
        x = lightIndex;
    }

    /**
     * set Enqueued Vehicles 
     * @param x
     * vehicle queue
     */
    public void setEnqueuedVehicles(VehicleQueue x){
        x = enqueuedVehicles;
    }

    //methods

    //time step is what happens at one iteration. It will return an array of vehicles that have been dequeued at the intersection (so 
    //there can be multiple roads that have been dequeued)
    /**
     * Performs a single iteration through the intersection. This method should apply all the logic defined in this specification 
     * related to the passing of cars through the intersection and switching the selected road (Note: LightValue changes for a particular 
     * road should be handled within the TwoWayRoad class itself and not within this method). Please refer to the Simulation Procedure section above for instructions on how to apply this procedure.
     * @return
     * An array of Vehicles which have passed though the intersection during this time step.
     */
    public Vehicle[] timeStep(){
        if (countdownTimer <= 1){
            lightIndex = (lightIndex + 1) % roads.length;
            countdownTimer = roads[lightIndex].getGreenTime();
            countdownTimer--;
        }
        else{
            countdownTimer--;
        }
       Vehicle[] dequeued = roads[lightIndex].proceed(countdownTimer);

       return dequeued;
        // this method returns the array of dequeued vehicles. mastermind of
    }

    //this method enqueues a vehicle onto a lane in the intersection

    /**
     * Enqueues a vehicle onto a lane in the intersection.
     * @param roadIndex
     * Index of the road in roads which contains the lane to enqueue onto.
     * @param wayIndex
     * Index of the direction the vehicle is headed. Can either be TwoWayRoad.FORWARD or TwoWayRoad.BACKWARD
     * @param laneIndex
     * Index of the lane on which the vehicle is to be enqueue. Can either be TwoWayRoad.RIGHT_LANE, TwoWayRoad.MIDDLE_LANE, or TwoWayRoad.LEFT_LANE.
     * @param vehicle
     * The Vehicle to enqueue onto the lane.
     * @throws IllegalArgumentException
     * If vehicle is null. If any of the index parameters above are not within the valid range..
     */
    public void enqueueVehicle(int roadIndex, int wayIndex, int laneIndex, Vehicle vehicle) throws IllegalArgumentException {
        if (vehicle == null || roadIndex < 0 || roadIndex >= roads.length || wayIndex < 0 || wayIndex >= TwoWayRoad.NUM_WAYS || laneIndex < 0
        || laneIndex >= TwoWayRoad.NUM_LANES)
        throw new IllegalArgumentException("Invalid index or vehicle.");
        // System.out.println("wayIndex" + wayIndex);
        //     System.out.println("lane" + laneIndex);

        roads[roadIndex].enqueueVehicle(wayIndex, laneIndex, vehicle);
        //use queue to store the arriving cars
    }

   
    /**
     * Prints the intersection to the terminal in a neatly formatted manner. See the sample I/O for an example of what this method should display.
     */
    public void display(){
        //starts from PASSING CARS: the ARRIVING CARS will be displayed by the Intersection Simulator since the probability instance is located there
        //for each road
        System.out.println();
       // for (int i = 0; i < roads.length; i++){
            System.out.println("                           FORWARD               BACKWARD        \n" +
            "    ==============================               ===============================        \n")
            ;
            // for (int j = 0; j < TwoWayRoad.NUM_WAYS; j++){
            //     for (int k = 0; k < TwoWayRoad.NUM_LANES; k++){
            //         System.out.println(roads[i].forwardWay(j, k));
            //     }
            // }

            
       // }
       

        //go through each road
        //for each road, for each way
        //for each direction
    }

    //custom methods
    //check if all lanes in intersection is empty
    /**
     * checks if interseciton is empty
     * @return
     * if interseciton is empty
     */
    public boolean isIntersectionEmpty(){
        //for each road
        for (int i = 0; i <roads.length; i++){
            if (!roads[i].isRoadEmpty())
            return false;
        }
        return true;
    }
    //this method displays which cars have entered
    public void displayBeforeDequeue(){
        //go through entire intersection. if a car is in the lane/queue, print out car name, and say which road they entered by using 
        //the names array and the index of the intersection roads array, and which direction they are going 
        
    }

    //this method adds the vehicles enqueued at each time step to an array so they can be printed. the idea is the array would be 
    //reset every time the countdown timer reduces or if it is reset

}
