import java.util.Scanner;

public class Test {

    // public static void main(String[]args){
    //     Vehicle vehicle  = new Vehicle(4);
    //     VehicleQueue queue = new VehicleQueue();
    //     queue.enqueue(vehicle);
    //     System.out.println(queue.isEmpty());
    //     TwoWayRoad road1 = new TwoWayRoad("216", 5);
    //     TwoWayRoad road2 = new TwoWayRoad("214", 5);

    //     TwoWayRoad [] roads = new TwoWayRoad[2];
    //     roads[0] = road1;
    //     roads[1] = road2;
    //     Intersection intersection = new Intersection(roads);
    //     intersection.display();
    //     //System.out.println(road1.isLaneEmpty(1,2));
    // }

    /**
 * This class represents the manager of the simulation -- it does the heavy lifting, per se. The main function's responsibility 
 * is to get the parameters for the simulation and pass them to the simulate() method, either by interactive prompt or command line
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

    //static functions:
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        int simulationTime;
        double arrivalProb;
        int numOfStreets;
        String roadName;
        int greenTime;

        System.out.println();
        System.out.println("Welcome to IntersectionSimulator 2023");
        System.out.println();
        System.out.print("Input the simulation time: ");    //what if simulation time is 0 or less than 0
        simulationTime = input.nextInt();
        input.nextLine();
        System.out.print("Input the arrival probability: ");    //what if arrival prob is less than 0 or greater than 1
        arrivalProb = input.nextDouble();  
        input.nextLine();
        System.out.print("for all lanes.");
        System.out.println();
        System.out.print("Input number of Streets: ");  //what if number of roads is greater than 4 or less than 0
        numOfStreets = input.nextInt();
        input.nextLine();
        while(numOfStreets > 4 || numOfStreets < 0){
            System.out.println("There can only be at most 4 streets and at least 1 street."); 
            System.out.print("Input number of Streets: ");  
            numOfStreets = input.nextInt();
            input.nextLine();
        }
        //initialize roadNames String
        String[] roadNames = new String[numOfStreets];

        for (int i = 1; i <= numOfStreets; i++){
            System.out.print("Input Street " + i + " name: ");
            roadName = input.nextLine();
            for (int j = 0; j < roadNames.length; j++){
                while (roadNames[j] != null && roadNames[j].equals(roadName)){
                    System.out.println("Duplicate Detected.");
                    System.out.print("Input Street " + i + " name: ");
                    roadName = input.nextLine();
                }
            }
            roadNames[i-1] = roadName;

        }

        //for each road, ask for the max green time
        //first initialize maxgreenTime array to size of number of roads
        int[] maxGreenTimes = new int[numOfStreets];
        for (int i = 0; i < maxGreenTimes.length; i++){
            System.out.print("Input max green time for " + roadNames[i] + ": ");
            greenTime = input.nextInt();
            input.nextLine();
            maxGreenTimes[i] = greenTime;
        }
        System.out.println();
        System.out.print("Starting Simulation...");
        System.out.println();
        //ASK ABOUT HOW reading in user input works


        for (int i  = 0; i< roadNames.length; i++){
            System.out.println(roadNames[i]);
        }

        try{
            simulate(simulationTime, arrivalProb, roadNames, maxGreenTimes);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
    //this method does the actual simulation
    public static void simulate(int simulationTime, double arrivalProb, String[] roadNames, int[] maxGreenTimes) throws IllegalArgumentException{
        if (simulationTime <=0 || arrivalProb > 1 || arrivalProb < 0){
            throw new IllegalArgumentException("SimulationTime must be greater than 0 and arrival probability must be between 0 and 1.");
        }
        /* I declared the "intersection, probability and TwoWayRoads" variable outside the try-catch block and initialize it inside the block. 
        This way, the variable will have a wider scope and be accessible throughout the entire method. */

        // Intersection intersection = null;
        // BooleanSourceHW4 probability = null;
        // TwoWayRoad [] TwoWayRoads = null;
        String [] ways = new String [TwoWayRoad.NUM_WAYS];
        String [] directions = new String[TwoWayRoad.NUM_LANES];

        ways[0] = "FORWARD";
        ways[1] = "BACKWARD";

        directions[0] = "LEFT";
        directions[1] = "MIDDLE";
        directions[2] = "RIGHT";

        
       // try{
            //create a new array of roads
            TwoWayRoad[] TwoWayRoads = new TwoWayRoad [roadNames.length];
            //a new twoWayRoad object should be created at each index of this new array, initializing teach new road with the name
            //and greenTime at the corresponding index of the array parameters
            for (int i = 0; i < TwoWayRoads.length; i++){
                TwoWayRoads[i] = new TwoWayRoad(roadNames[i], maxGreenTimes[i]);
          //  }
            //construct an Intersection instance, which will be used to keep track of the light during simulation and allow vehicles 
            //to pass through the intersection
            Intersection intersection = new Intersection(TwoWayRoads);

            //Lastly, a BooleanSourceHW4 object should be created (initializing it's probability member variable to arrivalProbability) 
            //which will be used to determine if vehicles have arrived during simulation.
            BooleanSourceHW4 probability = new BooleanSourceHW4(arrivalProb);
            }
        // catch(IllegalArgumentException e){
        //     System.out.println(e.getMessage());
        // }
        //when does simulation end: 
        /*On each time step, the program should determine if a vehicle has arrived for each lane in the intersection (all 12). 
        This can be accomplished by calling the occursHW4() method on the BooleanSourceHW4 object for each lane. If a vehicle 
        has arrived (i.e. occursHW4() returns true), the program should create a new Vehicle object, initialize it's timeArrived 
        member variable to the current time step value, and enqueue the vehicle onto the appropriate lane. */
        int timeStep = 1;
        //simulation variables
        int carsWaiting = 0;
        int carsPassed = 0;
        int totalWaitTime = 0;
        double avgWaitTime;
        int countdownTimer = 0;

        

   
}
}
