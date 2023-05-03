/**
 * This class provides Player's information
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

public class Player {
	private String name;	//the name of the player
	private int numHits;	//the number of hits the player has
	private int numErrors;	//the number or errors the player has
	
	/**
	 * Returns an instance of Player by default
	 * fields name, numHits, and numErrors are set to null or 0 
	 * **/
	
	Player(){
		name = "null";
		numHits = 0;
		numErrors = 0;
	}
	
	/**
	 * Creates an instance of Player depending on the parameters inputed by user
	 * fields name, numHits, and numErrors are set to corresponding inputed string or integer
	 * 
	 * @param name
	 * 	the name of the Player
	 * @param numHits
	 * 	the number of hits by the Player
	 * @param numErrors
	 * 	the number of errors by the Player
	 * @param position
	 * 	the position of the player in the lineup
	 * **/
	
	//ask professor: how do i create a constructor that checks if the input is the right type
	Player(String name, int numHits, int numErrors) {
		this.name = name;
		this.numHits = numHits;
		this.numErrors = numErrors;
	}
	
	/**
	 * This returns the name of the Player
	 * if the user wants to access the name of the Player, they use this method since the name variable is private
	 * 
	 * @return 
	 * 	returns name of the Player
	 * **/
	
	public String getName() {
		return name;
	}
	
	/**
	 * This returns the number of hits by the Player
	 * if the user wants to access the number of hits by the Player, they use this method since the numOfHits variable is private
	 * 
	 * @return 
	 * 	returns number of hits by Player
	 * **/
	
	public int getNumHits() {
		return numHits;
	}
	
	/**
	 * This returns the number of errors by the Player
	 * if the user wants to access the number of errors by the Player, they use this method since the numOfErrors variable is private
	 * 
	 * @return 
	 * 	returns number of errors by Player
	 * **/
	
	public int getNumErrors() {
		return numErrors;
	}

	
	/**
	 * This sets the name of the Player
	 * if the user wants to change the name of the player, they use this method
	 * 
	 * @param name
	 * 	the new name of player
	 * 
	 * **/
	
	public void setName(String name) {
		this.name = name;
		
	/**
	 * This sets the number of hits by the Player
	 * if the user wants to change the number of hits by the player, they use this method
	 * 
	 * @param numHits
	 * 	the new number of hits by the player
	 * 
	 * @custom.precondition
	 * 	numHits can't be less than 0
	 * 
	 * @throws IllegalArgumentException
	 * 	indicates that numHits is less than 0
	 * **/
		
	}
	public void setNumHits(int numHits) throws IllegalArgumentException {
		if(numHits >= 0) {
			this.numHits = numHits;
		}
		else throw new IllegalArgumentException("numHits can't be negative");
	}
	
	/**
	 * This sets the number of errors by the Player
	 * if the user wants to change the number of errors by the player, they use this method
	 * 
	 * @param numErrors
	 * 	the new number of errors by the player
	 * 
	 * custom.precondition
	 * 	numErrors can't be less than 0
	 * 
	 * @throws IllegalArgumentException
	 * 	indicates that numErrors is less than 0
	 * **/
	
	public void setNumErrors(int numErrors) throws IllegalArgumentException {
		if(numErrors >= 0) {
			this.numErrors = numErrors;
		}
		else throw new IllegalArgumentException("numErrors can't be negative");
	}

	/**
	 * This method checks if two Player objects are equal
	 * To check if two player objects are equal, compare their names, number of hits and number of errors 
	 * 
	 * @param o
	 * 	the Player that is being compared
	 * 
	 * @return
	 * 	true if the players are equal to each other and false otherwise
	 **/
	
	public boolean equals(Object o) {
		if (name.equals(((Player)o).getName()) && numHits == ((Player)o).getNumHits() && numErrors == ((Player)o).getNumErrors()){
			return true;
		}
		else return false;
	}
	
	/**
	 * This method deep copies or clones the existing player information
	 * 
	 * @return
	 * 	a copy of the original player that calls this method
	 * **/
	
	public Object clone() {
		Player clonePlayer = new Player(name, numHits, numErrors);
		return clonePlayer;
	}
	
	/**
	 * This method displays the information of each player
	 * 
	 * @return
	 * returns string that gives information of the player
	 * **/
	
	//might have to change this to account for the position which would go first
	
	public String toString() {
		return String.format("%-26s%-19d%-6d", name, numHits, numErrors);
	}
	
}
