/**
 * This class stores all Player objects that belong to a particular team
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 ***/

public class Team extends Player {
	
	public static final int MAX_PLAYERS = 40;	//maximum player objects allowed is 40
	private Player[] players;	//player objects are stored in an array called team
	public int count = 0;	//this keeps track of the size of the team, which is the total number of players after being added or removed
	
	/**
	 * This is a default constructor to create a new team
	 * **/

	Team(){
		players = new Player[MAX_PLAYERS];
	}
	
	/**
	 * This is a Constructor used to create a new Team object with no Player objects in it
	 * 
	 * @param x
	 * 	the size of the team
	 * 
	 * @custom.postcondition
	 * This team has been initialized to an empty list of Players
	 * **/
	
	Team(int x) {
		players = new Player[x];
	}
	
	/**
	 * This method returns information about the team and their players
	 * you need a getter method because the team array is private, so to access all the players in the team, you need an accessor method
	 * 
	 * @return team array that contains team players
	 * **/
	
	public Player [] getTeam() {
		return players;
	}
	
	/**
	 * This method generates a clone of this Team
	 * to create a clone, you need to add the players into the specific index of the team array. 
	 * Because I already created a clone method in the player class, I can just call it to clone the player
	 * 
	 * @return 
	 * the return value is a clone of this Team
	 * **/
	
	//instead of adding players, clone the entire team
	public Object clone() {
		Team clone = new Team();
		int i = 0;
		
		while(i < players.length) {
			if (players[i] != null) {
				clone.getTeam()[i] = (Player)players[i].clone();
			}
			i++;
		}
		//Player[] clone = new Player[team.length];
//		for (int i = 0; i < team.length; i++) {
//			clone[i] = team[i];
//		}
		return clone;
		
	}
	
	/**
	 * This method compares this Team to another object for equality
	 * in order to compare the equality of two different teams, I need to access
	 * the information of the team that is passed into the argument so I needed a 
	 * getter method. then, I can access the player and check inside of it 
	 * 
	 * @param obj
	 * 	an object to which this Team is compared
	 * 
	 * @return
	 * 	It returns true if the new Team object has the same Players in the same order as the current Team
	 * **/
	
	public boolean equals(Object obj) {
		if (obj instanceof Team) {
			Team o = (Team)obj;
			for (int i = 0; i < players.length; i++) {
				//if (obj.getPlayer(i).equals(team[i])) {
				if (o.getTeam()[i] != null && players[i] != null) {
					if (!(o.getTeam()[i].equals(players[i]))) {
						return false;
					}
				}
				if ((o.getTeam()[i] == null && players[i] != null) || (o.getTeam()[i] != null && players[i] == null)) {
					return false;
				}
			}
			return true;

				
		}
		return false;
	}
	
	/**
	 * This method determines the number of Players currently in this Team
	 * time complexity has to be O(1), dont use loop!
	 * basically, I can have a static variable that updates every time 
	 * I add or remove a player so I don't have to traverse the entire array 
	 * and increase time complexity

	 * @custom.precondition
	 * 	This Team object has been instantiated
	 * 
	 * @return
	 * 	it returns the number of Players in this Team
	 * **/
	
	public int size() {
		return count;
	}
	
	/**
	 * This method adds a Player to the team at the indicated poistion in the lineup
	 * it also counts the number of players that are added to the team
	 * 
	 * @param p
	 * 	the new Player object to add to this Team
	 * @param position
	 * 	the position in the lineup where the Player will be inserted
	 * 
	 * @custom.precondition
	 * 	this Team object has been instantiated, and 1<= position <= players 
	 * currently in team + 1, and the number of Player objects in this Team 
	 * is less than MAX_PLAYERS
	 * **/
	
//	insertion
	public void addPlayer(Player p, int position) throws IllegalArgumentException, FullTeamException {
		//this deals with players that are trying to be added to a position that doesn't come after current position 
		if(position > size()+1 || position < 0)
			throw new IllegalArgumentException("Invalid position for adding the new player");
		else if (1 + size() > players.length)	
			throw new FullTeamException("Full roster");
		else {
			
			//for add player method, if the current position is null, then we set that (position-1) index to the current position
			//if the current position is not null, we have to go through players to its right and and set current player to its right one
			
			if (players[position-1] != null) {
				Player current = players[position -1];
				players[position - 1] = p;
				for (int i = position; i < players.length; i++) {
					Player past = players[i];
					players[i] = current;
					current = past;
				}
			}
			else 
				players[position - 1] = p;

			
			count++;
		}
		
	}
	
	/**
	 * This method removes a Player from the team at the indicated position in the lineup
	 * it also counts the number of players that are removed from the team

	 * 
	 * @param position
	 * 	the position in the lineup from which the Player is to be removed
	 * 
	 * @custom.precondition
	 * 	this Team object has been instantiated, 1<= position <= playersCurrentlyInTeam
	 * 
	 * @custom.postcondition
	 * 	The Player at the desired position in the Team has been removed.
	 * 	All Players that were originally in positions greater than or equal to position are moved forward one position.
	 * 	E.g. If there are 5 Players in a Team, positions 1-5, and you remove the Player at position 4, the Player 
	 * 	that was at position 5 will be moved to position 4.
	 * 
	 * @throws IllegalArgumentException
	 * 	Indicates that position is not within the valid range
	 * **/
	
	 //ASK WHY ITS NOT THROWING AN EXCEPTION!!!!!!
	public void removePlayer(int position) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
		if (position > players.length) {
			throw new IllegalArgumentException("Position is is above valid range");
		}
		
		else if (position < 0)
			throw new ArrayIndexOutOfBoundsException("Position is below the valid range");
		
		else if (players[position-1] == null)
			throw new IllegalArgumentException("No player at position to remove");
		else {
			for (int i = position-1; i < players.length-2; i++) {
				players[i] = players[i+1];
			}
			players[players.length-2] = null;	
			count--;
		}
	}
	
	/**
	 * This method returns a reference to a player in the lineup at the indicated position
	 * 
	 * @para position
	 * 	the position in the lineup from which the Player is to be retrieved.
	 * 
	 * @custom.precondition
	 * 	This Team object has been instantiated.
	 * 
	 * @return
	 * 	The Player from the given index
	 * 
	 * @throws IllegalArgumentException
	 * 	Indicates that position is not within the valid range.
	 * **/
	
	public Player getPlayer(int position) throws IllegalArgumentException{
		if (position > players.length || players[position-1] == null || position < 0)
			throw new IllegalArgumentException("Position is not within the valid range");
		else
			return players[position-1];
	}
	
	/**
	 * This method the Player with the best value in the given statistic ("hits" or "errors").
	 * cannot but try catch block inside method block, must be thrown into main method because that's the point of try catch, to be dealt with by
	 * the method that calls it
	 * 
	 * @param stat
	 * stat - either "hits" or "errors".
	 * 
	 * @custom.precondition
	 * 	This Team object has been instantiated.
	 * 
	 * @return
	 * 	The Player with the best stat
	 * 
	 * @throws IllegalArgumentException
	 * 	Indicates that indicated stat was neither "hits" nor "errors".
	 * **/
	
	
	public Player getLeader(String stat) throws IllegalArgumentException {
		if ((!stat.equals("hits")) && !(stat.equals("errors" )))
			throw new IllegalArgumentException("No such statistic.");
		else {
			if (stat.equals("hits")) {
				int maxhits = 0;
				int maxPlayer = 0;
				for (int i = 0; i < players.length; i++) {
					if (players[i] !=null) {
						if (maxhits < players[i].getNumHits()) {
							maxhits = players[i].getNumHits();
							maxPlayer = i;
						}
					}
				}
				return players[maxPlayer];
			}
			else {
				int minError = 1000;
				int player = 0;
				for (int i = 0; i < players.length; i++) {
					if (players[i] !=null) {
						if (minError > players[i].getNumErrors()) {
							minError = players[i].getNumErrors();
							player = i;
						}
					}
				}
				return players[player];

			}
		}
	}
	
	/**
	 * Prints a neatly formatted table of each Player in the Team on its own line with its 
	 * position number as shown in the sample output.
	 * 
	 * @custom.precondition
	 * This Team object has been instantiated
	 * 
	 * @custom.postcondition
	 * A neatly formatted table of each Player in the Team on its own line with its position
	 * number has been displayed to the user.
	 * **/
	
	public void printAllPlayers() {
		System.out.println(toString());
	}
	
	//find position of player
	public int playerPosition(String name) {
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null && (players[i].getName().equals(name))){
				return i;
			}
		}
		return -1;
	}
		
	
	/**
	 * Gets the String representation of this Team object, which is a neatly formatted table of 
	 * each Player in the Team on its own line with its position number as shown in the sample output.
	 * 
	 * @return
	 * The String representation of this Team object
	 * **/
	
	public String toString() {
		String s = String.format("%-21s%-26s%-19s%6s\n", "Player#", "Name", "Hits","Errors");
		int j = 1;
		for (int i = 0; i < players.length; i++) {
			if (players[i]!=null) {
				s+= String.format("%-21d%-26s%-19d%-6d\n", j, players[i].getName(), players[i].getNumHits(), players[i].getNumErrors());
				j++;	
			}
		}
		return s;
	}
	
}
