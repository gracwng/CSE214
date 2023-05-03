/**
 * This class tests the methods of the Team class and allows the user to manipulate 5 Team objects by performing operations on it.
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/


import java.util.Scanner;
import java.util.*;
public class TeamManager {
	public static final int MAX_TEAMS = 5;	//the user is only allowed to manipulate 5 Team objects by performing operations on it
	
/**
 * The main method runs a menu driven application which first creates an empty Team and then prompts the user for a menu command selecting 
 * the operation. The required information is then requested from the user based on the selected operation. Following is the list of menu 
 * options and their required information:
 * The main method creates an array of 5 teams set to their default constructor. The
 * current team is set to team 1 by default. we set up the integers, strings, and booleans that will be
 * manipulated throughout the program
 */
	
/**
 * Be sure to add Javadoc comments to the class itself as normal. Then add a Javadoc comment to main():

Describe briefly what the driver does. Perhaps refer to the class where the computation method comes from.
Describe the nature of the input from the keyboard (or file).
Describe the nature of the output to the screen (or file).
Do not bother with @return or @param tags (unless you're using the args parameter).
Example:

/**
 * This driver reads in a number and squares it.  Input (keyboard): an
 * integer; output (screen): the integer squared.
 
public static void main(String[] args) {
  Keyboard theKeyboard = new Keyboard();
  Screen theScreen = new Screen();
  theScreen.print("Enter an integer: ");
  int number = theKeyboard.readInt();
  theScreen.println(number + " squared is " + (number*number) + ".");
}
 * **/
	//ASK IF I HAVE TO WRITE JAVADOC FOR EACH IF LINE/STATEMENT
	
	public static void main(String[]args) {
		Team [] teams = new Team [MAX_TEAMS];
		//The issue is that teams[0] has not been initialized with a Team object, 
		//therefore it is null. To initialize the array element, you can create a new 
		//instance of the Team class and assign it to teams[0]. For example: teams[0] = new Team();
		//You need to do this for each element in the teams array that you want to use.
		
		teams[0] = new Team();
		teams[1] = new Team();
		teams[2] = new Team();
		teams[3] = new Team();
		teams[4] = new Team();

		String operation = "";
		int operations = 0;
		int teamNum = 1;
		Team currentTeam = new Team();
		currentTeam = teams[0];
		String test = "";
		boolean done = false;
		Scanner input = new Scanner(System.in);

		/**
		 * 
		 * **/
		System.out.println("Welcome to Team Manager!");
		do {
			System.out.println("\n\nTeam " + teamNum + " is currently selected\n\n\nPlease select an option:"
					+ "\nA) Add Player\nG) Get Player stats\nL) Get leader in a stat\nR) Remove a player\nP) Print "
					+ "all players\nS) Size of team\nT) Select team\nC) Clone team\nE) Teame equals\nU) Update stat\nQ) Quit\n\nSelect a menu option:");
			
			operation = "";
			if(input.hasNextLine()) {
				operation=input.nextLine();	

			}
			
			if (operation.equals("T") || operation.equals("t")) {
				//ADD PLAYER METHOD
				System.out.println("Enter a team index to select: ");
				teamNum = input.nextInt();
				if (teamNum > MAX_TEAMS) {
					System.out.println("Invalid index for team.");
				}
					else {
						System.out.println("Team " + teamNum + " has been selected.");
						switch(teamNum) {
						case (1):
							currentTeam = teams[0];
						break;
						case (2):
							currentTeam = teams[1];
						break;
						case (3):
							currentTeam = teams[2];
						break;
						case (4):
							currentTeam = teams[3];
						break;
						case (5):
							currentTeam = teams[4];
						break;
						}
					}
				}
				
		//when adding a player, the positions have to be in order. there can't be spaces in between positions
			else if (operation.equals("A") || operation.equals("a")) {
				String name = "";
				System.out.println("Enter the player's name: ");
				while(name.equals("")) {
					name = input.nextLine();
				}
				int hits = 0;
				int errors = 0;
				int position = 0;
				System.out.println("Enter the player's hits: ");
				hits = input.nextInt();
				System.out.println("Enter the player's errors: ");
				errors = input.nextInt();
				System.out.println("Enter the player's position: ");
				position = input.nextInt();
			
			
				try {
					Player x = new Player(name, hits, errors);
					currentTeam.addPlayer(x, position);
					System.out.println("Player added: \n");
					System.out.printf("%-21d", position);
					System.out.print(currentTeam.getPlayer(position));

					System.out.println();
				}
				catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch(FullTeamException e) {
					System.out.println(e.getMessage());
				}  
				name = "";
			}

			else if (operation.equals("P") || operation.equals("p")) {
				System.out.println("Select team index: ");
				test = input.nextLine();
				switch(test) {
				case ("1"):
					teams[0].printAllPlayers();
				break;
				case ("2"):
					teams[1].printAllPlayers();
				break;
				case ("3"):
					teams[2].printAllPlayers();
				break;
				case ("4"):
					teams[3].printAllPlayers();
				break;
				case ("5"):
					teams[4].printAllPlayers();
				break;
				default: 
				System.out.println("There is no such team index");
			}
		}
			
			else if (operation.equals("S") || operation.equals("s")) {
				System.out.println("There are " + currentTeam.size() + " player(s) in the current Team");
			}
			
			else if (operation.equals("R") || operation.equals("r")) {
				System.out.println("Enter the position: ");

				done = false;
				while (!done){
					try {
						operations = input.nextInt();
						done = true;
					}
					catch (InputMismatchException e) {
						input.nextLine(); 	//this clears the buffer
						System.out.println("Input for poistion must be an integer");
					}
				}
				try {
					String name = currentTeam.getPlayer(operations).getName();
					currentTeam.removePlayer(operations);
					System.out.println("Player removed at position " + operations + "\n");
					System.out.println(name + " has been removed from the team");
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("Position is below the valid range");
				}
				catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				
			}
			
			else if (operation.equals("G") || operation.equals("g")) {
				System.out.println("Enter the position: ");
				
				done = false;
				while (!done){
					try {
						operations = input.nextInt();
						done = true;
					}
					catch (InputMismatchException e) {
						input.nextLine(); 	//this clears the buffer
						System.out.println("Input for poistion must be an integer");
					}
				}
				
				try{
					System.out.println(currentTeam.getPlayer(operations));
				}
				catch(IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
			}
			
			else if (operation.equals("U") || operation.equals("u")) {
				System.out.println("Enter the player to update: ");
				test = input.nextLine();
//				int position = currentTeam.playerPosition(test);
//				if (position < 0) {
//					System.out.println("Invalid name");
//				}
					System.out.println("Enter stat to update (hits or errors): ");
					test = input.nextLine();
					if (test.equals("errors")) {
						System.out.println("Enter the new number of errors: ");
						done = false;
						while (!done){
							try {
								operations = input.nextInt();
								done = true;
							}
							catch (InputMismatchException e) {
								input.nextLine(); 	//this clears the buffer
								System.out.println("Input for position must be an integer");
							}
						}
						int position = currentTeam.playerPosition(test);
						if (position < 0) {
							System.out.println("Invalid update error");
						}
						else {
							currentTeam.getTeam()[position].setNumErrors(operations);
							System.out.println("Updated " + currentTeam.getTeam()[position].getName());
						}
						
					}
					else if (test.equals("hits")) {
						System.out.println("Enter the new number of hits: ");
						done = false;
						while (!done){
							try {
								operations = input.nextInt();
								done = true;
							}
							catch (InputMismatchException e) {
								input.nextLine(); 	//this clears the buffer
								System.out.println("Input for poistion must be an integer");
							}
						}
						int position = currentTeam.playerPosition(test);
						if (position < 0) {
							System.out.println("Invalid update error");
						}
						else {
							currentTeam.getTeam()[position].setNumHits(operations);
							System.out.println("Updated " + currentTeam.getTeam()[position].getName());
						}
					}
					else 
						System.out.println("Wrong stat. Select option u again");
			}
		
			else if (operation.equals("L") || operation.equals("l")) {
				System.out.println("Enter the stat: ");
				test = input.nextLine();
				try {
					currentTeam.getLeader(test);
					System.out.println("Leader in " + test + ":");
					System.out.println(currentTeam.getLeader(test));
					}
				catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			
			else if (operation.equals("E") || operation.equals("e")) {
				System.out.println("Select first team index: ");
				int index1 = 0;
				done = false;
				while (!done){
					try {
						index1 = input.nextInt();
						done = true;
					}
					catch (InputMismatchException e) {
						input.nextLine(); 	//this clears the buffer
						System.out.println("Input for poistion must be an integer");
					}
				}
				System.out.println("Select second team index: ");
				int index2 = 0;
				done = false;
				while (!done){
					try {
						index2 = input.nextInt();
						done = true;
					}
					catch (InputMismatchException e) {
						input.nextLine(); 	//this clears the buffer
						System.out.println("Input for poistion must be an integer");
					}
				}
					System.out.println(teams[index1-1].equals(teams[index2-1]) ? "These teams are equal. " : "These teams are not equal.");
				}
			
			else if (operation.equals("C") || operation.equals("c")) {
				System.out.println("Select team to clone from: ");
				int team1 = 0;
				done = false;
				while (!done){
					try {
						team1 = input.nextInt();
						done = true;
					}
					catch (InputMismatchException e) {
						input.nextLine(); 	//this clears the buffer
						System.out.println("Input for team must be an integer");
					}
				}
				System.out.println("Select team to clone to: ");
				int team2 = 0;
				done = false;
				while (!done){
					try {
						team2 = input.nextInt();
						done = true;
					}
					catch (InputMismatchException e) {
						input.nextLine(); 	//this clears the buffer
						System.out.println("Input for poistion must be an integer");
					}
				}
				teams[team2-1] = (Team)teams[team1-1].clone();
				System.out.println("Team " + team1 + " has been copied to team " + team2);
			}
			else if (!(operation.equals("Q")) && !(operation.equals("q"))) {
				System.out.println("Invalid option.");
			}

	}
			
		while(!(operation.equals("Q")) && !(operation.equals("q")));
		System.out.println("Program terminating normally");
		input.close();
		System.exit(0);
	}
	public void addPlayer() {
		
	}


}

