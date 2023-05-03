/**
 * This class represents the manager of the simulation -- it does the heavy lifting, per se. The main function's responsibility 
 * is to get the parameters for the simulation and pass them to the simulate() method, either by interactive prompt or command line
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 import java.util.Scanner;
 public class IntersectionSimulator {
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
          }
            //construct an Intersection instance, which will be used to keep track of the light during simulation and allow vehicles 
            //to pass through the intersection
            Intersection intersection = new Intersection(TwoWayRoads);

            //Lastly, a BooleanSourceHW4 object should be created (initializing it's probability member variable to arrivalProbability) 
            //which will be used to determine if vehicles have arrived during simulation.
            BooleanSourceHW4 probability = new BooleanSourceHW4(arrivalProb);
           // }
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
        //int countdownTimer = maxGreenTimes[0];


        //simulation continues while all lanes are not empty. we are simulating each second of time
       // while(!intersection.isIntersectionEmpty()){
        while (timeStep <= simulationTime){
//question: how do we check when simulation time ends?

        System.out.print("################################################################################\n"
        + "Time Step: " + timeStep + "\n\t"
        + "Green Light for " + roadNames[intersection.getLightIndex()] + ".\n\t"
        + "Timer = " + intersection.getCountdownTimer() + "\n\n\t"
        + "ARRIVING CARS:\n");
        
            //enqueue vehicles into lanes only if we are still within the simulation time
            if (timeStep <= simulationTime){
                //EVENT 1: has a car arrived?
                //an intersection contains all the roads. we want to access each roads' queues
                //VehicleQueue enqueuedVehicle = new VehicleQueue();
                for (int i = 0; i < intersection.getNumRoads(); i++){
                    //for each way
                    for (int j = 0; j < TwoWayRoad.NUM_WAYS; j++){
                        //for each direction
                        for (int k = 0; k < TwoWayRoad.NUM_LANES; k++){
                        //try boolean. if true, create new object
                        if (probability.occursHW4()){
                            Vehicle arrivingVehicle = new Vehicle(timeStep);
                            intersection.enqueueVehicle(i, j, k, arrivingVehicle);
                            //enqueuedVehicle.add(arrivingVehicle);
                            System.out.print("\t\t");
                            System.out.print("Car[" + arrivingVehicle.getSerialCounter() + "] entered " + roadNames[i] + ", going " 
                            + ways[j] + " in " + directions[k] + " lane.\n");
                        }
                    }
                }
            }
            //intersection.setEnqueuedVehicles(enqueuedVehicle);
        }
        //check which road currently has green light
        //call proceed method

        /*After all lanes have been considered for arrival, roads[lightIndex] should pass vehicles through the intersection - 
        one vehicle per lane, but only if the lane is allowed to proceed (see the LightValue enum below for more detail on the 
        light rules). When a vehicle is dequeued from a lane, the program should add the vehicle's wait time to the total wait 
        time for the simulation, and increment the number of cars passed through the intersection. The vehicle's wait time can 
        be calculated by subtracting it's arrivalTime from the current time step value. If all lanes are empty after having been 
        dequeued (or ignored if they were empty), then the program should preempt the countdown timer and switch the light to the 
        next road.*/

        //EVENT 2: The countDownTimer is reached or no cars in dequeued lane and greenlight road changes

        //FIRST I am handlign the case when the road changes before the countdown timer is reached. 
        //go into timestep method. if timestep returns red, then we go to next road and call timestep method. 
        //I guess that while timestep returns an empty vehicle array, we move onto the next timestep. 
        //while the current light value at our road is red, we will move onto the next time step 

        //question: should this be if or while
        if (intersection.getRoad()[intersection.getLightIndex()].getLightValue() == LightValue.RED){
            intersection.setLightIndex((intersection.getLightIndex() + 1) % intersection.getNumRoads());
            intersection.setCountDownTimer(maxGreenTimes[intersection.getLightIndex()]);
        }

        Vehicle [] dequeuedVechicles = new Vehicle[4];
        dequeuedVechicles = intersection.timeStep();
        // for (int i = 0; i < dequeuedVechicles.length; i++){
        //     System.out.println("Dequeued vehicles");
        //    // if (dequeuedVechicles[i] != null){
        //         System.out.println(dequeuedVechicles[i].getSerialId());
        //    // }
        // }
        System.out.println();
        System.out.println("\tCARS PASSED: ");
        for (int i = 0; i < dequeuedVechicles.length; i++){
            System.out.print("\t\t");
            if (dequeuedVechicles[i] != null){
                System.out.println("Car[" + dequeuedVechicles[i].getTimeArrived() + "] passes through. Wait time of " + (timeStep - dequeuedVechicles[i].getTimeArrived()) + ".");
                totalWaitTime += timeStep - dequeuedVechicles[i].getTimeArrived();
                carsPassed++;
            }
        }
       

        //call display method
        intersection.display();

        //print statistics
        System.out.println();
        System.out.print("STATISTICS: \n\t" 
        + "Cars currently waiting: " + carsWaiting + " cars\n\t"
        + "Total cars passed: " + carsPassed + " cars\n\t"
        + "Total wait time: " + totalWaitTime + " turns\n\t"  + "Average wait time: ");
        System.out.printf("%.2f", (double)totalWaitTime/(double)carsPassed);
        System.out.print(" turns\n");
        
        //increment timestep 
        timeStep++;
    }
    

}
//this checks if the vehicle array is empty
public static boolean emptyArray(Vehicle [] x){
    for (int i = 0; i < x.length; i++){
        if (x[i] != null)
        return false;
    }
    return true;
}
}
