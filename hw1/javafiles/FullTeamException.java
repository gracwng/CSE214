/**
 * This is an Exception class which indicates a full roster
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

public class FullTeamException extends Exception{
	public FullTeamException() {
		super("Full roster");
	}
	public FullTeamException(String message) {
		super(message);
	}
}
