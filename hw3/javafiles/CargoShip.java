/** 
 * This class represents a container ship which holds stacks of containers. It must contain the following private
 * member variables: stacks (CargoStack[]), maxHeight (int), and maxWeight (double). The CargoShip 
 * class must also feature the following public methods: void pushCargo(Cargo cargo, int stack), Cargo popCargo(int stack), and void findAndPrint(String name).
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

import java.util.Stack;
public class CargoShip {
    //data members
    private Stack <Cargo> [] stacks;    
    private int numStacks;  //number of stacks on cargo ship
    private int maxHeight;  //max height of each stack 
    private double maxWeight;   //max weight the cargo ship can hold
    private Stack <Cargo> dock; //the dock that holds an unlimited amount of cargo

    private double totalWeight = 0; //counter for the total weight currently on the ship

    //constructor
    //do I need a default constructor and why. but on the document is says the constructor below IS the default constructor

    /**
     * Default constructor
     * 
     * @param numStacks
     * The number of stacks this ship can support. This parameter should be used to initialize the stacks array to a fixed size.
     * 
     * @param initMaxHeight
     * The maximum height of any stack on this ship.
     * 
     * @param initMaxWeight
     * The maximum weight for all the cargo on the ship.
     * 
     * @custom.precondition
     * numStacks > 0.
     * initMaxHeight > 0.
     * initMaxWeight > 0.
     * my own custom precondition: strength must be fragile, sturdy or moderate
     * 
     * @custom.postcondition
     * This object has been initialized to a CargoShip object with the indicated number of stacks, maxHeight, and maxWeight.
     * 
     * @throws IllegalArgumentException
     * if either of the parameters are now within the appropriate bounds.
     */
    public CargoShip (int numStacks, int initMaxHeight, double initMaxWeight) throws IllegalArgumentException {
        if (numStacks > 0){
            this.numStacks = numStacks;
        }
        else {
            throw new IllegalArgumentException("numStacks must be greater than 0");
        }
        if (initMaxHeight > 0){
            maxHeight = initMaxHeight;
        }
        else {
            throw new IllegalArgumentException("Max height must be greater than 0");
        }
        if (initMaxWeight > 0){
            maxWeight = initMaxWeight;
        }
        else {
            throw new IllegalArgumentException("Max weight must be greater than 0");
        }
        stacks = new Stack[numStacks];  


        for (int i = 0; i < stacks.length; i++){
             stacks[i] = new Stack<Cargo>();
        }
        dock = new Stack<Cargo>();

    }

    //instance methods
    /**
     * Pushes a cargo container to the indicated stack on the cargo ship
     * 
     * @param cargo
     * The container to place on the stack.
     * 
     * @param stack
     * The index of the stack on the ship to push cargo onto.
     * Note: you may choose to do your actual indexing starting at 0, but from the user's perspective, the leftmost stack must be number 1.
     * 
     * @custom.precondition
     * This CargoShip is initialized and not null.
     * cargo is initialized and not null.
     * 1 ≤ stack ≤ stacks.length.
     * The size of the stack at the desired index is less than maxHeight.
     * The total weight of all Cargo on the ship and cargo.getWeight()is less than maxWeight.
     * 
     * @custom.postcondition
     * The cargo has been successfully pushed to the given stack, or the appropriate exception has been thrown, 
     * in which case the state of the cargo ship should remain unchanged.
     * 
     * @throws IllegalArgumentException
     * If cargo is null or stack is not in the appropriate bounds.
     * 
     * @throws FullStackException
     * If the stack being pushed to is at the max height.
     * 
     * @throws ShipOverweightException
     * If cargo would make the ship exceed maxWeight and sink.
     * 
     * @throws CargoStrengthException
     * If the cargo would be stacked on top of a weaker cargo (see the CargoStrength rules above for reference).
     */
    public void pushCargo (Cargo cargo, int stack) throws IllegalArgumentException, FullStackException, ShipOverweightException, CargoStrengthException{
        //this method pushes a cargo container to the indicated stack on the cargo ship
        //handle exceptions first
        if (cargo == null){
            throw new IllegalArgumentException("Cargo can't be null. ");
        }
        else if (stack < 1 || stack > stacks.length){
            throw new IllegalArgumentException("Stack index not within range. ");
        }
        else if (stacks[stack-1].size() >= maxHeight){
            throw new FullStackException("Operation failed! Cargo stack is at maximum height.");
        }
        else if (cargo.getWeight() + totalWeight > maxWeight){
            throw new ShipOverweightException("Operation failed! Cargo would put ship overweight.");
        }
        //before we check if the current cargo in the stack meets the strength requirements, we have to see if the stack is empty
        //if it is, then we directly push the cargo into that index
        else if (stacks[stack-1].isEmpty()){
            stacks[stack-1].push(cargo);
            totalWeight += cargo.getWeight();
        }
        else if (stacks[stack-1].peek().getStrength() == CargoStrength.FRAGILE && (cargo.getStrength() == CargoStrength.MODERATE || cargo.getStrength() == CargoStrength.STURDY)){
            throw new CargoStrengthException("Operation failed! Cargo at top of stack cannot support weight.");
        }
        else if (stacks[stack-1].peek().getStrength() == CargoStrength.MODERATE && cargo.getStrength() == CargoStrength.STURDY){
            throw new CargoStrengthException("Operation failed! Cargo at top of stack cannot support weight."); 
        }
        else{
        //every time we push a new cargo onto the stack, add the cargo's weight to total weight
            stacks[stack-1].push(cargo);
            totalWeight += cargo.getWeight();
        }

    }

    /**
     * Pops a cargo from one of the specified stack.
     * 
     * @param stack
     * The index of the stack to remove the cargo from.
     * Note: Again, from the user's perspective, the leftmost stack must be indexed 1.
     * 
     * @custom.precondition
     * This CargoShip is initialized and not null.
     * 1 ≤ stack ≤ stacks.length.
     * 
     * @custom.postcondition
     * The cargo has been successfully been popped from the stack, and returned, or the appropriate exception has been thrown,
     * in which case the state of the cargo ship should remain unchanged.
     * 
     * @return
     * the cargo that was popped from the specified stack 
     * 
     * @throws IllegalArgumentException
     * If stack is not in the appropriate bounds.
     * 
     * @throws EmptyStackException
     * If the stack being popped from is empty.
     */
    public Cargo popCargo(int stack) throws IllegalArgumentException, EmptyStackException {
        //everytime we pop a cargo from the stack, subtract cargo's weight from total weight
        //if stack index is not within bounds which means it is less than 1 or it's greater than maxNumStacks, we throw an exception
        if (stack < 1 || stack > numStacks){
            throw new IllegalArgumentException("Stack is not in the appropriate bounds.");
        }
        else if (stacks[stack-1].isEmpty()){
            throw new EmptyStackException("Stack is empty. ");
        }
        else{
            totalWeight -= stacks[stack-1].peek().getWeight();
            return stacks[stack-1].pop();
        }
    }

    // public void unloadCargoToDock(int stack) throws IllegalArgumentException, EmptyStackException, CargoStrengthException {
    //     /* Case U unloads cargo from the ship to the dock
    //     * so this option unloads all the cargo from specific StackIndex onto the dock if it is allowed
    //     * use the popcargo method and use the pushCargoOnDock method
    //     * Parameters: StackIndex */
    //     //for this I will have to use a method within a method
    //     if (stack < 1 || stack > numStacks){
    //         throw new IllegalArgumentException("Stack is not in the appropriate bounds.");
    //     }
    //     else if (stacks[stack-1].isEmpty()){
    //         throw new EmptyStackException("Stack is empty. ");
    //     }
    //     else{
    //         while(!stacks[stack-1].isEmpty()){
    //             Cargo cargo = popCargo(stack-1);
    //             if (dock.isEmpty()){
    //                 dock.push(cargo);
    //             }
            
    //             //we have to compare the newcargo's strength with the topcargo on the dock. if newcargo is greater than, then we have to throw a cargostrength exception
    //             //what are the cases?
    //                 //if peek is fragile and cargo is moderate or study, throw exception
    //                 //if peek is moderate and cargo is sturdy, throw exception
    //             //otherwise we push the cargo onto the dock
                
    //             else{
    //                 if (dock.peek().getStrength() == CargoStrength.FRAGILE && (cargo.getStrength() == CargoStrength.MODERATE || cargo.getStrength() == CargoStrength.STURDY)){
    //                     throw new CargoStrengthException("Operation failed! Cargo at top of stack cannot support weight.");
    //                 }
    //                 else if (dock.peek().getStrength() == CargoStrength.MODERATE && cargo.getStrength() == CargoStrength.STURDY){
    //                     throw new CargoStrengthException("Operation failed! Cargo at top of stack cannot support weight."); 
    //                 }
    //                 else{
    //                     dock.push(cargo);
    //                 }
    //             }
                
    //             }

    //             //ASK HOW TO OPTIIZITE THIS
    //         }
    //     }

    /**
     * Finds and prints a formatted table of all the cargo on the ship with a given name. If the item could not be found in the stacks, notify the user accordingly.
     * 
     * @param name
     * The name of the cargo to find and print.
     * 
     * @custom.precondition
     * This CargoShip is initialized and not null.
     * 
     * @custom.postcondition
     * The details of the cargo with the given name have been found and printed, or the user has been notified that no such cargo has been found.
     * The state of the cargo ship should remain unchanged.
     */
    public void findAndPrint(String name) {
    /*This option searches for cargo
    * Parameters: name
    * 
    * this means we have to go through each stack of the ship
    * so for each stack of the ship
    * we have to create a temporary stack where we store all the stack cargos
    * as we pop each element back into the stack, we check if the cargo's name is the same as target. 
    * if it is, we have to print which stack it is located, and the depth (which is calculated by maxWeight - stack.size()),
    * and then I need the weight and strength
    * 
    * I also need a totalCount that counts the number of cargo of the same name I find, so if I find a cargo of the same name, add to totalCOunt
    * I will also need to add to total weight */

    //go through each stack of the ship
    int totalCount = 0;
    double totalWeight = 0;
    System.out.println();
    int printItemFound = 1;
    for (int i = 0; i < numStacks; i++){
        //create a temporary stack where we store all the stack cargos
        int height = stacks[i].size();
        Stack <Cargo> tempStack = new Stack<>();
        while(!stacks[i].isEmpty()){
            Cargo tempCargo = stacks[i].pop();
            tempStack.push(tempCargo);
        }
        //as we pop each element back into the stack, we check if the cargo's name is the same as target. 

        while(!tempStack.isEmpty()){
            
            Cargo tempCargo = tempStack.pop();
            stacks[i].push(tempCargo);
            if (tempCargo.getName().equals(name)){
                //we have to print which stack it is located, and the depth (which is calculated 
                //by maxHeight - stack.size()),and then I need the weight and strength
                if (printItemFound == 1){
                    System.out.println("Cargo '" + name + "' found at the following locations:");
                    System.out.println();
                    System.out.printf("%-8s%-8s%-9s%-8s", "Stack", "Depth", "Weight", "Strength");
                    System.out.println();
                    System.out.println("=======+=======+========+==========");
                    //print the stack the cargo is on, the depth from the top of the stack, the weight and strength
                    //it will be int, int, int, string
                    printItemFound++;
                }
                System.out.printf("%-3s%-4d%-1s%-3s%-4d%-1s%-2s%-6d%-1s%-2s%-6s", "", i+1, "|", "", height - stacks[i].size(), "|", "", (int)tempCargo.getWeight(), "|", "", tempCargo.getStrength());
                System.out.println();
                totalWeight += tempCargo.getWeight();
                totalCount++;
            }

        }
    }
    if (totalCount == 0){
        System.out.println("Cargo '" + name + "'could not be found on the ship.");
        System.out.println();
    }
    else{
        System.out.println();
        System.out.println("Total Count: " + totalCount);
        System.out.println("Total Weight: " + (int)totalWeight);
        System.out.println();
    }
   
    }

    //dock methods
    /**
     * Custom dock method that returns the dock which is a stack object
     * 
     * @return
     * the dock
     * @throws EmptyStackException
     * if the dock is empty there would be nothing to return
     */
    public Stack<Cargo> getDock() throws EmptyStackException {
        if (dock.isEmpty()){
            throw new EmptyStackException("Dock is empty.");
         }
        else
        return dock;
    }

    /**
     * Sets the dock (I don't use this method but it's nice to have anyway)
     * 
     * @param dock
     * the dock
     */
    public void setDock(Stack<Cargo> dock){
        this.dock = dock;
    }

    /**
     * This clears the dock
     **/
    public void clearDock(){
        while (!(dock.isEmpty())){
            Cargo temp = dock.pop();
        }
    }
    
    
    /**
     * this will push the cargo on the dock after we create new cargo, and when we unload cargo from the ship
     * 
     * @param cargo
     * the cargo we want to push 
     * 
     * @throws CargoStrengthException
     * thrown if the strengths of the cargo isn't in tandem with the strength requirements 
     */
    public void pushCargoOnDock(Cargo cargo) throws CargoStrengthException{
    //how will this work? 	- dock limitations: the strengths of the cargo
    //what if dock is empty?
    if (dock.isEmpty()){
        // System.out.println("Cargo " +cargo.getName() + "' moved from ")
        dock.push(cargo);
    }
    

    //we have to compare the newcargo's strength with the topcargo on the dock. if newcargo is greater than, then we have to throw a cargostrength exception
    //what are the cases?
        //if peek is fragile and cargo is moderate or study, throw exception
        //if peek is moderate and cargo is sturdy, throw exception
    //otherwise we push the cargo onto the dock
    
    else{
        if (dock.peek().getStrength() == CargoStrength.FRAGILE && (cargo.getStrength() == CargoStrength.MODERATE || cargo.getStrength() == CargoStrength.STURDY)){
            throw new CargoStrengthException("Operation failed! Cargo at top of stack cannot support weight.");
        }
        else if (dock.peek().getStrength() == CargoStrength.MODERATE && cargo.getStrength() == CargoStrength.STURDY){
            throw new CargoStrengthException("Operation failed! Cargo at top of stack cannot support weight."); 
        }
        else{
            dock.push(cargo);
        }
    }
    
    }

    // public void removeCargo(String name) throws CargoStrengthException{
    //     /*
    //      * This operation will begin by clearing whatever cargo is currently on the dock, and then proceed to automatically 
    //      * remove all cargo instances with the indicated name from the cargo ship. You may only use the stacks of the ship and 
    //      * the dock to maneuver cargo around, and you must also detail the movement of each cargo object (i.e. display the state 
    //      * of the ship and the dock after each movement during the operation). After the operation completes, all instances of 
    //      * cargo with the indicated name should be removed from the ship and stacked on the dock (these cargo items should be 
    //      * the ONLY cargo on the dock). Note that the resulting configuration of the cargo ship does not matter, so long as no 
    //      * rules are violated and the desired cargo is on the dock. For this operation, you can make the following assumptions:
    //      * 
    //      * The ship will have 3 stacks and a max height of 3, for a potential maximum of 9 cargo objects on the ship.
    //      * All instances of the cargo with the indicated name will have the same CargoStrength (i.e. they can all be stacked 
    //      * on top of one another).
    //      */
    //     clearDock();

    //     for (int i = 0; i < numStacks; i++){
    //         //create a temporary stack where we store all the stack cargos
    //         int height = stacks[i].size();
    //         while(!stacks[i].isEmpty()){
    //             Cargo tempCargo = stacks[i].pop();
    //             if (tempCargo.getName().equals(name)){
    //                 pushCargoOnDock(tempCargo);
    //             }
    //             else{
    //                 stacks[i].push(tempCargo);
    //             }
    
    //         }
    //         //as we pop each element back into the stack, we check if the cargo's name is the same as target. 
    //         }
    //     }

    // }
    

    /**
     * This is a custom method that prints the ship stacks
     */
    public void printShipStacks() /*throws IllegalArgumentException*/ {
        //when we print ship stacks, we are printing the number of each stack +1, and if there is a cargo in a stack, we print out the 
        //container strength
        //so traverse the ship array, for each element in the ship array, print which stack and index it is, and then while
        //there is a cargo element in each cargostack, we print out the container strength and we keep score of the total weight
        //double totalWeight = 0;
        //print each stack's strength
        for (int i = 0; i< stacks.length; i++){
            System.out.print ("Stack " + (i+1) + ": ");
            //how do we print the strength of each?
            //we have to pop each cargo into a temporary cargo stack and then as we pop it back into original stack, we print out the information
            Stack <Cargo> tempStack = new Stack<>();

            //this pops the original stack into a temporary one
            while(!(stacks[i].isEmpty())){
                Cargo tempCarg = (Cargo) stacks[i].pop();
                tempStack.push(tempCarg);   
            }
            //as we pop back to original stack, we have to get the container strength and print it.
                //if its fragile, print F, but how do we get the commas and spacing to work?
                //if there is more than one element in the stack, 
            int tempStackSize = tempStack.size();
            if (!(tempStack.isEmpty())){
                for (int j = 0; j < tempStackSize; j++){
                    Cargo tempCarg = tempStack.pop();
                    if (tempCarg.getStrength() == CargoStrength.FRAGILE)
                    System.out.print("F");
                    else if (tempCarg.getStrength() == CargoStrength.MODERATE)
                    System.out.print("M");
                    else
                    System.out.print("S");
    
                    if (j != tempStackSize-1){
                        System.out.print(", ");
                    }
                    //totalWeight += tempCarg.getWeight();
                    stacks[i].push(tempCarg);
                }
            }
            System.out.println();    
        }

        //print the dock cargo's strength. 
        System.out.print("Dock: ");
        Stack <Cargo> tempStack = new Stack<>();
        int limit = dock.size();

        //move dock into temporary stack so we can look at and print each cargo in order 
        for (int i = 0; i < limit; i++){
            Cargo tempCargo = dock.pop();
            tempStack.push(tempCargo);
        }

        for (int i = 0; i < limit; i++){
            Cargo tempCarg = tempStack.pop();
            if (tempCarg.getStrength() == CargoStrength.FRAGILE)
            System.out.print("F");
            else if (tempCarg.getStrength() == CargoStrength.MODERATE)
            System.out.print("M");
            else if (tempCarg.getStrength() == CargoStrength.STURDY)
            System.out.print("S");
            if (i != limit-1){
                System.out.print(", ");
            }
            dock.push(tempCarg);

        }
        System.out.println();
        System.out.println();
        System.out.println("Total Weight = " + (int)totalWeight + " / " + (int)maxWeight);
    }

}
