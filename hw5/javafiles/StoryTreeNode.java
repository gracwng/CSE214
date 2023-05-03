/**
 * StoryTreeNode represents a segment of the story. The StoryTreeNode class should contain 3 StoryTreeNode references, leftChild, 
 * middleChild, and rightChild. In addition, the class should contain three String member variables: position, which indicates the 
 * position of the node in the tree; option, which represents the text that should be displayed to the user to describe what choice 
 * this node represents (this will be read from the parent node), and the message, which is the message displayed when the user chooses 
 * this option (i.e: the consequence of taking the option).
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 */
public class StoryTreeNode {
    //member variables
    /*A special string sequence which represents a leaf node which is a winning state. Note that a node is a winning state if 
    and only if it is a leaf AND it's message contains WIN_MESSAGE as a substring. */
    public static final String WIN_MESSAGE = "YOU WIN";
    /* A special string sequence which indicates that a leaf node is a losing state. Note that a node is a losing state if and only if it is a leaf AND it's
     message does NOT contain WIN_MESSAGE as a substring. Having LOSE_MESSAGE as a substring will be better for the user experience, but it is not required for 
     a leaf to be a losing node. ALL leaves which do not contain WIN_MESSAGE as a substring of their message are considered losing states.*/
    public static final String LOSE_MESSAGE = "YOU LOSE";
    /*A String representing the position of the node in the tree. This variable should always be equal to the name of the parent node plus this node's position 
    in the parent's children collection (the exception being the child of the root node, which is always named "1". For example, the left child of a node with 
    name "1-2-3" would be "1-2-3-1, the middle child of the same node would be "1-2-3-2, and the right child of the same node would be "1-2-3-3. */
    private String position;
    /*A String representing the option which will select this node. Note that this option is only displayed when the cursor currently selects the parent of 
    this node - only after the user selects this option does the cursor select this node. */
    private String option;
    /*A String representing the message which is displayed after this node has been selected by the user. */
    private String message;
    /*NOTE: The children of the tree should always be left aligned (i.e: if a child is removed, all the other children should be left shifted, and for each 
    decendent, the decendent's position variable should be updated to reflect the change). This is best done with a recursive traversal of the children of 
    this node placed within the setter(s) for the children. */
    private StoryTreeNode leftChild;
    private StoryTreeNode middleChild;
    private StoryTreeNode rightChild;

    //constructor
    /**
     * Default constructor for the StoryTreeNode class.
     */
    public StoryTreeNode(){
        position = "";
        option = "";
        message = "";
    }
    /**
     * Constructor for the StoryTreeNode class. Note: you may include custom constructors which take any parameters you like.
     * @param position
     * position of the node within the entire tree
     * @param option
     * this represents the option/string that will select this node. this option is only displayed when the cursor currently selects the parent of 
     * this node - only after the user selects this option does the cursor select this node.
     * @param message
     * the message displayed after user selects this node
     */
    public StoryTreeNode(String position, String option, String message){
        this.position = position;
        this.option = option;
        this.message = message;

        //initialize children
        leftChild = null;
        middleChild = null;
        rightChild = null;

    }

    //getters and setters

    /**
     * Gets position of current node
     * @return
     * position
     */
    public String getPosition(){
        return position;
    }

    /**
     * Gets option of current node
     * @return
     * option
     */
    public String getOption(){
        return option;
    }

    /**
     * Gets message of current node
     * @return
     * message
     */
    public String getMessage(){
        return message;
    }

    /**
     * Gets left child of current node
     * @return
     * left child node
     */
    public StoryTreeNode getLeftChild(){
        return leftChild;
    }

    /**
     * Gets right child of current node
     * @return
     * left right node
     */
    public StoryTreeNode getRightChild(){
        return rightChild;
    }

    /**
     * Gets middle child of current node
     * @return
     * middle child node
     */
    public StoryTreeNode getMiddleChild(){
        return middleChild;
    }

    /**
     * Sets position of node
     * @param position
     * new poistion
     */
    public void setPosition(String position){
        this.position = position;
    }

    public void setOption(String option){
        this.option = option;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setLeftChild(StoryTreeNode leftChild){
        this.leftChild = leftChild;
    }

    public void setRightChild(StoryTreeNode rightChild){
        this.rightChild = rightChild;
    }

    public void setMiddleChild(StoryTreeNode middleChild){
        this.middleChild = middleChild;
    }

    //methods
    /**
     * Checks if this node has any children.
     * @custom.precondition
     * This node is initialized
     * @custom.postcondiion
     * The tree remains unchanged
     * @return
     * True if there are no children and false if there is at least one child.
     */
    public boolean isLeaf(){
        if (middleChild == null && leftChild == null && rightChild == null)
        return true;
        else return false;
    }

    /**
     * Checks if this is a winning node. In order to be a winning node, this node must be a leaf and contain the winning message.
     * @custom.precondition
     * This node is initialized
     * @custom.postcondition
     * The tree remains unchanged
     * @return
     * True if this node is a leaf and contains WIN_MESSAGE in the message. False otherwise.
     */
    public boolean isWinningNode(){
        if (isLeaf() && message.contains(WIN_MESSAGE)) return true;
        else return false;
    }

    /**
     * Checks if this is a losing node. In order to be a losing node, this node must be a leaf and contain the losing message.
     * @custom.precondition
     * This node is initialized
     * @custom.postcondition
     * The tree remains unchanged
     *  @return
     * True if this node is a leaf and does NOT contain WIN_MESSAGE in the message. False otherwise.
     */
    public boolean isLosingNode(){
        if (isLeaf() && message.contains(LOSE_MESSAGE))   return true;
        else return false;
    }

    //check how many children the cursor has
    public int countChildren(){
        int count = 0;
        if (leftChild != null)
        count++;
        if (rightChild != null)
        count++;
        if (middleChild != null)
        count++;
        return count;
    }


    //method to traverse the tree in preorder
    public void traverse(){
        System.out.println(position);
        if (leftChild != null)
        leftChild.traverse();
        if (middleChild != null)
        middleChild.traverse();
        if (rightChild != null)
        rightChild.traverse();
    }

    //toString
    public String toString(){
        return "Position: " + position + "\nOption: " + option + "\nMessage: " + message + "\n";
    }


}
