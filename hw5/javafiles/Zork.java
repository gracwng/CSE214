
/**
 * Write a fully-documented class named Zork. The class should contain a main method along with two static utility functions, 
 * editGame(StoryTree tree) and playGame(StoryTree tree). These are the two functions a user can choose to run, which will allow 
 * the user to construct and traverse a StoryTree, respectively. When the program begins, it should use the static function 
 * StoryTree.readTree(filename) to build the tree from the file (if the file does not yet, the function should simply return a 
 * tree with a single root node.). After constructing the tree, the program should ask the user whether they wish to edit this 
 * tree or play a game using this tree.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 */
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.File;

public class Zork {
    /**
     * Requests the user to enter a file name and builds a tree from the indicated
     * file (if it exists, otherwise uses an empty tree)
     * and asks the user whether they would like to edit this tree (E), play a game
     * based on this tree (P), or exit the program (Q).
     * Note: The StoryTree should always be saved to the save file which was read
     * upon closing the program.
     */
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        StoryTree tree = new StoryTree();
        String fileName = "";
        System.out.println("Hello and Welcome to Zork!\n");
        System.out.print("Please enter the file name: ");
        // File file = new File("hw5-SampleStory.txt");
        fileName = input.nextLine();
        System.out.println();
        // read tree method creates the tree by loading in the file
        try {
            tree = StoryTree.readTree(fileName);
            System.out.println("Loading game from file...");
            // tests to see if file is in correct form
            // tree.getCursor().traverse();
            System.out.println();
            System.out.println("File loaded!");

            String option = "";
            while (!option.equals("Q")) {
                // now we ask what they want to do with this new tree
                System.out.println();
                System.out.print("Would you like to edit (E), play (P) or quit (Q)? ");
                option = input.nextLine().toUpperCase();
                switch (option) {
                    // the user wants to edit the game
                    case ("E"):
                        editGame(tree);
                        break;
                    // the user wants to play the game
                    case ("P"):
                        // playGame(tree);
                        playGameRecursive(tree);
                        break;
                    // the user wants to quit, so we save
                    case ("Q"):
                        // solved-question!: my save tree method does not go into the traverseAndSave
                        // recursive method. please help.
                        // System.out.println();
                        // System.out.println("Game being saved to " + fileName + " ...");
                        // System.out.println();
                        // System.out.println("Save successful!");
                        StoryTree.saveTree(fileName, tree);
                        break;
                    default:
                        System.out.println("Invalid option");
                }
                // System.out.println();
            }
            System.out.println("Program terminating normally.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NodeNotPresentException e) {
            System.out.println(e.getMessage());
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        } catch (TreeFullException e) {
            System.out.println(e.getMessage());
        }

        // this is where we introduce the game. This is where we ask the user for the
        // file, call the readGame method and it returns a storytree
        // node. we take that node and pass it into the editgame or play game method
        // depending on the users' decision
    }

    /**
     * Provides a user interface allowing a user to edit the story followed by the
     * game represented by tree. This function should
     * continue to process user commands until the user enters Q to exit. The
     * following commands should be supported for editing
     * the tree:
     * 
     * @param tree
     */
    public static void editGame(StoryTree tree)
            throws InvalidArgumentException, IllegalArgumentException, NodeNotPresentException, TreeFullException {
        System.out.println();
        String userInput;
        tree.resetCursor();
        // System.out.println(tree.getCursor());
        // tree.setCursor(tree.getCursor().getLeftChild());
        // System.out.println(tree.getCursor());
        do {
            // question: why does my code print the editor twice
            // answer: because i didn't read next line after taking in an integer
            System.out.println(
                    "Zork Editor: \n\t" +
                            "V: View the cursor's position, option and message.\n\t" +
                            "S: Select a child of this cursor (options are 1, 2, and 3).\n\t" +
                            "O: Set the option of the cursor.\n\t" +
                            "M: Set the message of the cursor.\n\t" +
                            "A: Add a child StoryNode to the cursor.\n\t" +
                            "D: Delete one of the cursor's children and all its descendants.\n\t" +
                            "R: Move the cursor to the root of the tree.\n\t" +
                            "Q: Quit editing and return to main menu.\n");
            System.out.print("Please select an option: ");
            // if(input.hasNextLine())
            userInput = input.nextLine();
            userInput = userInput.toUpperCase();

            switch (userInput) {
                case ("V"):
                    // allows user to view the cursor's position, option and message
                    System.out.println();
                    System.out.println(tree.getCursor());
                    System.out.println();
                    break;
                case ("S"):
                    // S: Select a child of this cursor (options are 1, 2, and 3).
                    System.out.println();
                    int numChildren = tree.getCursor().countChildren();
                    if (numChildren == 0) {
                        System.out.println("No child.");
                        System.out.println();
                        break;
                    }
                    System.out.print("Please select a child: [");
                    for (int i = 0; i < numChildren; i++) {
                        System.out.print(i + 1);
                        if (i != numChildren - 1)
                            System.out.print(",");
                    }
                    System.out.print("] ");
                    int child = input.nextInt();
                    input.nextLine();
                    // System.out.println(tree.getCursor());
                    String newPosition = tree.getCursorPosition() + "-" + child;
                    try {
                        // if (child > numChildren){
                        // System.out.println("Error. No child " + child + " for the current node. ");
                        // System.out.println();
                        // break;
                        // }
                        if (numChildren <= 0) {
                            System.out.println("No child.");
                        }
                        tree.selectChild(newPosition);
                        // System.out.println(tree.getCursor());
                    } catch (InvalidArgumentException e) {
                        throw e;
                    } catch (NodeNotPresentException e) {
                        throw e;
                    }
                    // System.out.print()
                    break;
                case ("O"):
                    // allows user to set the option of the cursor.
                    System.out.println();
                    System.out.print("Please enter a new option: ");
                    userInput = input.nextLine();
                    System.out.println();
                    tree.setCursorOption(userInput);
                    System.out.println("Option set.");
                    System.out.println();
                    break;
                case ("M"):
                    // M: Set the message of the cursor.
                    System.out.println();
                    System.out.print("Please enter a new message: ");
                    userInput = input.nextLine();
                    System.out.println();
                    tree.setCursorMessage(userInput);
                    System.out.println("Message set.");
                    System.out.println();
                    break;
                case ("A"):
                    // A: Add a child StoryNode to the cursor
                    try {
                        System.out.print("Enter an option: ");
                        String option = input.nextLine();
                        System.out.print("Enter a message: ");
                        String message = input.nextLine();
                        tree.addChild(option, message);
                    } catch (TreeFullException e) {
                        throw e;
                    }
                    break;
                case ("D"):
                    // D: Delete one of the cursor's children and all its descendants.
                    numChildren = tree.getCursor().countChildren();
                    // System.out.println("Numchildren" + numChildren);
                    System.out.println();
                    if (numChildren == 0) {
                       // HERE:
                        System.out.println("No child");
                        System.out.println();
                        break;
                    }
                    System.out.print("Please select a child: [");
                    for (int i = 0; i < numChildren; i++) {
                        System.out.print(i + 1);
                        if (i != numChildren - 1)
                            System.out.print(",");
                    }
                    System.out.print("] ");
                    child = input.nextInt();
                    input.nextLine();
                    String newPositions = "";
                    int i = 0;

                    if (child > numChildren) {
                        System.out.println("Error. No child " + child + " for the current node.");
                        break;
                    }
                    // this removes any spaces in the position string.
                    while (i < tree.getCursorPosition().length() && !(tree.getCursorPosition().charAt(i) == ' ')) {
                        newPositions += tree.getCursorPosition().charAt(i);
                        i++;
                    }
                    String newPositionWithoutSpace = newPositions + "-" + child;
                    try {
                        // if (child < tree.getOptions().length)
                        // System.out.println("Error. No child " + child + " for the current node.");
                        /* else */ if (numChildren <= 0) {
                            System.out.println("No child.");
                        } else {
                            tree.removeChild(newPositionWithoutSpace);
                            System.out.println();
                            System.out.println("Subtree deleted.");
                        }

                    } catch (NodeNotPresentException e) {
                        throw e;
                    }
                    break;
                case ("R"):
                    // R: Move the cursor to the root of the tree
                    System.out.println();
                    tree.resetCursor();
                    System.out.println("Cursor moved to root.");
                    System.out.println();
                    break;
                // custom case
                case ("T"):
                    tree.getCursor().traverse();
                    break;
                case ("Q"):
                    break;
                // my own
                case ("C"):
                    System.out.println(tree.getCursor());
                    break;
                default:
                    break;
            }

        } while (!(userInput.equals("Q")));

    }

    /**
     * Provides a user interface allowing a player to play the game represented by
     * tree This method will allow a user to traverse the
     * tree by continually displaying messages and allowing the user to select
     * options until a leaf is reached.
     * 
     * @param tree
     *             takes in story tree to be played
     */
    public static void playGame(StoryTree tree) {
        // //keep score of the user's string of positions. initially set to 1
        // String userPosition = "1";
        // tree.selectChild(userPosition);
        System.out.println();
        tree.resetCursor();
        System.out.println(tree.getCursor().getOption());
        System.out.println();

        // the game state changes if the message in the node contains YOU_WIN or
        // YOU_LOSE.
        // how does it work?
        // let cursor go to position 1
        // display the options of the first node
        // below will be a while loop
        // so while the gamestate is in "game not over", we continue to play the game
        System.out.println(tree.getCursor().getMessage());
        System.out.println();
        while (tree.getGameState() == GameState.GAME_NOT_OVER) {

            // print out cursor's options
            String[][] options = tree.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println(i + 1 + ") " + options[i][1]);
            }
            System.out.println();
            System.out.print("Please make a choice. ");
            int userPosition = input.nextInt();
            input.nextLine();
            if (userPosition > options.length)
                throw new IllegalArgumentException("choice must be within the choices of story.");
            switch (userPosition) {
                case (1): // if user enters 1, go into left subtree.
                    tree.setCursor(tree.getCursor().getLeftChild());
                    break;
                case (2): // if user enters 2, go into middle subtree.
                    tree.setCursor(tree.getCursor().getMiddleChild());
                    break;
                case (3): // if user enters 3, go into right subtree.
                    tree.setCursor(tree.getCursor().getRightChild());
                    break;
                default:
            }
            System.out.println();
            System.out.println(tree.getCursor().getMessage());
            // if the message contains a gamestate keyword, change game state and break.
            if (tree.getCursor().isWinningNode())
                tree.setGameState(GameState.GAME_OVER_WIN);
            if (tree.getCursor().isLosingNode())
                tree.setGameState(GameState.GAME_OVER_LOSE);
        }
        System.out.println();
        System.out.println("Thanks for playing.");
    }

    // hw5-SampleStory.txt
    // question: how can we do this recurisvely?
    // answer: I did it!
    public static void playGameRecursive(StoryTree tree) {
        // first print
        if (tree.getCursor().isWinningNode())
            tree.setGameState(GameState.GAME_OVER_WIN);
        if (tree.getCursor().isLosingNode())
            tree.setGameState(GameState.GAME_OVER_LOSE);
        if (tree.getGameState() == GameState.GAME_OVER_WIN || tree.getGameState() == GameState.GAME_OVER_LOSE) {
            System.out.println();
            System.out.println(tree.getCursorMessage());
            System.out.println();
            System.out.println("Thanks for playing.");
            return;
        } else {
            System.out.println();
            System.out.println(tree.getCursor().getMessage());
            System.out.println();
            String[][] options = tree.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println(i + 1 + ") " + options[i][1]);
            }
            System.out.println();
            System.out.print("Please make a choice. "); // the problem is in here.
            int userPosition = input.nextInt();
            input.nextLine();
            if (userPosition > options.length)
                throw new IllegalArgumentException("choice must be within the choices of story.");
            switch (userPosition) {
                case (1): // if user enters 1, go into left subtree.
                    tree.setCursor(tree.getCursor().getLeftChild());
                    playGameRecursive(tree);
                    break;
                case (2): // if user enters 2, go into middle subtree.
                    tree.setCursor(tree.getCursor().getMiddleChild());
                    playGameRecursive(tree);
                    break;
                case (3): // if user enters 3, go into right subtree.
                    tree.setCursor(tree.getCursor().getRightChild());
                    playGameRecursive(tree);
                    break;
                default:
            }
            System.out.println();
            // System.out.println();
            // System.out.println(tree.getCursor().getMessage());
        }
    }

    // }

}