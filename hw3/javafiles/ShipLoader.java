/**
 * This class named ShipLoader which allows a user to create an instance of the CargoShip class, and also provides an 
 * interface for a user to manipulate the ship by creating, adding, and moving Cargo objects. In addition to the CargoShip, 
 * the ShipLoader utilizes another CargoStack called dock, which is the loading/unloading stack for Cargo being moved to/from the ship.
 * 
 * When cargo is created, it is initially placed on the dock to be moved to the ship. Note that this dock is just another instance of the CargoStack and is thus subject to the same CargoStrength rules listed above (i.e. a STURDY Cargo cannot be created if a FRAGILE Cargo is currently at the top of the dock stack). However, the dock is not limited to the maxHeight limitation of the CargoShip. The dock can stack as many items as necessary, as long as the CargoStrength rules are not violated. Please see the sample I/O for an example.
 * Any time cargo is created or moved between stacks, the program should automatically print the ship stacks immediately after performing the reqested operation.
 * For option S), the program should only search the stacks on the ship (the dock should not be included in the search).
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

import java.util.Scanner;
public class ShipLoader {
    static Scanner input = new Scanner(System.in);
    public static void main(String[]args){
        // this is where we create an instance of the CargoShip claShip. But we also create a CargoStack called dock which 
        // loads and unloads the stack so it can be moved to cargoShip
        
        //First we ask user to build the ship by taking in the number of stacks, max height, and max weight (1)
	    // CargoShip ship = new CargoShip(numStacks, maxHeight, maxWeight)
        // for numStacks times, create a new cargoStack object with maxHeight length
        // for (int i = 0; i < numStacks; i++){
        // CargoStack i = new CargoStack(maxHeight)}

        //build ship:
        String option = "";
        int numStacks = 0;
        int maxHeight = 0;
        int maxWeight = 0;
        System.out.print("Welcome to ShipLoader!\n\n" 
        + "Cargo Ship Parameters\n"
        + "--------------------------------------------------\n"
        + "Number of stacks: "
        );
        numStacks = input.nextInt();
        input.nextLine();
        System.out.print("Maximum height of stacks: ");
        maxHeight = input.nextInt();
        input.nextLine();
        System.out.print("Maximum total cargo weight: ");
        maxWeight = input.nextInt();
        input.nextLine();

        //try to build the cargo ship
        try{
            CargoShip ship = new CargoShip(numStacks, maxHeight, maxWeight);
            //after creating the ship, we have to initialize the size/length of each cargoStack on the ship
            
            
            System.out.println();
            System.out.print("Cargo ship created.\n"
            + "Pulling ship into dock...\n" 
            + "Cargo ship ready to be loaded.\n");

            //what do we do after the exception is thrown? does that mean the progrma just stops running

            System.out.println();

            do{
                System.out.print(
                    "Please select an option: \n"
                    + "C) Create new cargo\n"
                    + "L) Load cargo from dock\n"
                    + "U) Unload cargo from ship\n"
                    + "M) Move cargo between stacks\n"
                    + "K) Clear dock\n"
                    + "P) Print ship stacks\n"
                    + "S) Search for cargo\n"
                    + "Q) Quit\n\n"
                    + "Select a menu option: "
                );
                //if(input.hasNextLine())     //THIS LINE IS CRITICAL FOR CODEGRADE
                option = input.nextLine().toUpperCase();
                System.out.println();
    
                switch(option){
                    case ("C"):
                    /*
                     * Case C creates new cargo, pushes it onto the dock, and the ship stacks are immediately printed 
                     * parameters: name, weight, strength
                     * ship.getDock().push(newCargo)
                     * print "Cargo -nameofCargo- pushed onto the dock."
                     * 
                     * maybe have a method that prints the ship stacks
                     
                     * 
                     */
                    optionC(ship);
                    break;
                    case ("L"):
                    /*
                     * Case L loads cargo from the dock
                     * parameter: stackIndex
                     * user will be able to select which cargoStack they want to put cargo in
                     * will need a try catch block when loading cargo
                     * will use the pushCargo() method inside the cargoShip class
                     * and then print the cargo ship information
                     * ship.pushCargo(cargo)
                     * 
                     * how do we load cargo from the dock:
                     * peek the cargo from the dock, push the cargo into the specified stack of the cargo ship. if it works, then pop the 
                     * cargo from the dock
                     * 
                     * stack = input.nextInt();
                     * Cargo loadCargo = dock.peek();   //this will throw exception if dock is empty, so catch EmptyStackException
                     * ship.pushCargo(loadCargo, stack)
                     * loadCargo = dock.pop();
                     * print the ship stacks
                     */
                    optionL(ship);
                    break;
                    case ("U"):
                    /*
                     * Case U unloads cargo from the ship to the dock
                     * so this option unloads all the cargo from specific StackIndex onto the dock if it is allowed
                     * use the popcargo method and use the pushCargoOnDock method
                     * Parameters: StackIndex
                     */
                    optionU(ship);
                    break;
                    case ("M"):
                    /*
                     * this option moves cargo between stacks. 
                     * what do we have to check for? 
                     * parameters: sourceStackIndex and destinationStackIndex
                     * do we have to check if the indexes are valid?
                     * we will need to popCargo() from source index and then pushCargo into the destination index
                     */
                    optionM(ship);
                    break;
                    case ("K"):
                    /*
                     * This option clears the dock
                     * how can we do this? 
                     * I'm going to setDock to a new dock, actually no because I don't think that is allowed. it specifically says clear not replace
                     * I have to traverse the dock stack and pop each element until the dock is empty. so i will write a method for this inside the cargoShip class
                     */
                    optionK(ship);
                    break;
                    case ("P"):
                    /*
                     * 
                     */
                    optionP(ship);
                    break;
                    case ("S"):
                    /*
                     * This option searches for cargo
                     * Parameters: name
                     * ok this option will be lengthy since we have to search for a specific container
                     * this means we have to go through each stack of the ship
                     * so for each stack of the ship
                     * we have to create a temporary stack where we store all the stack cargos
                     * as we pop each element back into the stack, we check if the cargo's name is the same as target. 
                     * if it is, we have to print which stack it is located, and the depth (which is calculated by maxWeight - stack.size()),
                     * and then I need the weight and strength
                     * 
                     * I also need a totalCount that counts the number of cargo of the same name I find, so if I find a cargo of the same name, add to totalCOunt
                     * I will also need to add to total weight
                     */
                    optionS(ship);
                    break;
                    case ("Q"):
                    // System.out.println("Program terminating normally...");
                    break;
                    default: 
                    System.out.println("Invalid option " + option);
                }
            }
            while (!(option.equals("Q")));
            System.out.println("Program terminating normally...");
            input.close();
            System.exit(0);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static void optionC(CargoShip ship){
        /*Case C creates new cargo, pushes it onto the dock, and the ship stacks are immediately printed 
        * parameters: name, weight, strength
        * ship.getDock().push(newCargo)
        * print "Cargo -nameofCargo- pushed onto the dock."
        * 
        * maybe have a method that prints the ship stacks */
        
        //create a new cargo object
        String name = "";
        double weight = 0;
        String stringStrength = "";
        CargoStrength strength = null;
        System.out.print("Enter the name of the cargo: ");
        name = input.nextLine();
        System.out.print("Enter the weight of the cargo: ");
        weight = input.nextDouble();
        input.nextLine();
        System.out.print("Enter the container strength (F/M/S): ");  
        stringStrength = input.nextLine().toUpperCase();

        //now we have to switch the container strength string into the actual enum variable
        switch(stringStrength){
            case("F"):
            strength = CargoStrength.FRAGILE;
            break;
            case("M"):
            strength = CargoStrength.MODERATE;
            break;
            case("S"):
            strength = CargoStrength.STURDY;
            break;
            default: System.out.println("Not a valid container strength");  //what should I do if they give us an invalid container strength
        }

        try{
            Cargo newCargo = new Cargo (name, weight, strength);
            System.out.println();
            //now we will push this new cargo into the dock
            ship.pushCargoOnDock(newCargo);
            System.out.println("Cargo '" + newCargo.getName() + "' pushed onto the dock.");
            System.out.println();
            ship.printShipStacks();
            System.out.println();

        }

        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(CargoStrengthException e){
            System.out.println(e.getMessage());
            System.out.println();
        }


    }

    public static void optionL(CargoShip ship){
        //this option takes the cargo from the dock and moves it into the ship
        int destination = 0;
        System.out.print("Select the load destination stack index: ");
        destination = input.nextInt();
        input.nextLine();
        try{
            //this pushes the cargo into the ship
            ship.pushCargo(ship.getDock().peek(), destination);
            System.out.println();
            System.out.println("Cargo '" + ship.getDock().peek().getName() + "' moved from dock to stack " + destination + ".");
            ship.getDock().pop();
            //this prints the ship information
            ship.printShipStacks();
            System.out.println();
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(FullStackException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(ShipOverweightException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(CargoStrengthException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(EmptyStackException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void optionU(CargoShip ship){
        /* Case U unloads cargo from the ship to the dock
        * so this option unloads the top cargo from specific StackIndex onto the dock if it is allowed
        * use the popcargo method and use the pushCargoOnDock method
        * Parameters: StackIndex */
        int stackIndex = 0;
        System.out.print("Select the unload source stack index: ");
        stackIndex = input.nextInt();
        input.nextLine();
        try{
            ship.pushCargoOnDock(ship.popCargo(stackIndex));
            System.out.println("Cargo '" + ship.getDock().peek().getName() + "' moved from stack " + stackIndex + " to dock.");
            System.out.println();
        }
       catch(CargoStrengthException e){
        System.out.println(e.getMessage());
        System.out.println();
       }
       catch(IllegalArgumentException e){
        System.out.println(e.getMessage());
        System.out.println();
       }
       catch(EmptyStackException e){
        System.out.println(e.getMessage());
        System.out.println();
       }
        ship.printShipStacks();
        System.out.println();

        

        

    }

    public static void optionM(CargoShip ship){
        //this method moves the cargo between stacks
        //we will need to popCargo() from source index and then pushCargo into the destination index
        int source = 0;
        int destination = 0;
        System.out.print("Source stack index: ");
        source = input.nextInt();
        input.nextLine();
        System.out.println();
        System.out.print("Destination stack index: ");
        destination = input.nextInt();
        input.nextLine();

        try{
            Cargo poppedCargo = ship.popCargo(source);
            ship.pushCargo(poppedCargo, destination);
            System.out.println("Cargo '" + poppedCargo.getName() + "' moved from stack " + source + " to stack " + destination + "."); 
            ship.printShipStacks();
            System.out.println();

        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(FullStackException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(ShipOverweightException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(CargoStrengthException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
        catch(EmptyStackException e){
            System.out.println(e.getMessage());
            System.out.println();
        }
       
    }

    public static void optionK(CargoShip ship){
        ship.clearDock();
        System.out.println("Dock cleared.");
        System.out.println();
        ship.printShipStacks();
        System.out.println();

    }

    public static void optionP(CargoShip ship){
        ship.printShipStacks();
        System.out.println();

    }

    public static void optionS(CargoShip ship){
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
        String name = "";
        System.out.print("Enter the name of the cargo: ");
        name = input.nextLine();
        ship.findAndPrint(name);
            
        }

    // public static void optionR(CargoShip ship){
    //     System.out.print("Name of cargo you want to remove: ");
    //     String name = input.nextLine();
    //     try{
    //         ship.removeCargo(name);
    //     }
    //     catch(IllegalArgumentException e){
    //         System.out.println(e.getMessage());
    //         System.out.println();
    //     }
    //     catch(CargoStrengthException e){
    //         System.out.println(e.getMessage());
    //         System.out.println();
    //     }
        /*
         * This operation will begin by clearing whatever cargo is currently on the dock, and then proceed to automatically 
         * remove all cargo instances with the indicated name from the cargo ship. You may only use the stacks of the ship and 
         * the dock to maneuver cargo around, and you must also detail the movement of each cargo object (i.e. display the state 
         * of the ship and the dock after each movement during the operation). After the operation completes, all instances of 
         * cargo with the indicated name should be removed from the ship and stacked on the dock (these cargo items should be 
         * the ONLY cargo on the dock). Note that the resulting configuration of the cargo ship does not matter, so long as no 
         * rules are violated and the desired cargo is on the dock. For this operation, you can make the following assumptions:
         * 
         * The ship will have 3 stacks and a max height of 3, for a potential maximum of 9 cargo objects on the ship.
         * All instances of the cargo with the indicated name will have the same CargoStrength (i.e. they can all be stacked 
         * on top of one another).
         */

   // }
}   

    




