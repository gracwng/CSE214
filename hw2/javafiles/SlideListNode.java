/**
 * This is a class named SlideListNode that wraps a Slide object to allow it 
 * to be inserted into a doubly linked-list data structure. The Slide object reference 
 * is contained in a field called data, and there are two SlideListNode 
 * references serving as ‘links’ to the previous and next SlideListNodes in the list. 

 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 public class SlideListNode extends Slide {
    
    //member variables
    private Slide data; //this stores the slide information
    private SlideListNode next; //this stores the link to the next node
    private SlideListNode prev; //this stores the link to the previous node

    //constructors

    /**
     * This is the default constructor
     * 
     * @param initData
     * The data to be wrapped by this SlideListNode. This parameter should not be null, 
     * since we should never have a SlideListNode with null data (remember, this class 
     * serves only as a wrapper for the Slide class).
     * 
     * @custom.precondition 
     * initData is not null
     * 
     * @custom.postcondition
     * This SlideListNode has been initialized to wrap the indicated Slide, and prev and 
     * next have been set to null.
     * 
     * @throws IllegalArgumentException
     * Thrown if initData is null
     * **/
    public SlideListNode(Slide initData) throws IllegalArgumentException {
        if (initData == null){
            throw new IllegalArgumentException("Slide cannot be null.");
        }
        else{
            data = initData;
            next = null;
            prev = null;
        }
    }
    //optional custom constructors
    /**
     * This is the second default constructor that doesnt take an argument
     * **/
    public SlideListNode(){
        data = null;
        next = null;
        prev = null;
    }


    //getters
    /**
     *  Gets the reference to the data memeber variable of the list node
     * 
     * @returns
     * the reference of the data member variable
     * **/
    public Slide getData(){
        //does this mean I have to return a toString method of the information of the Slides object (contains title, bullets and duration?) (seems like yes)
        return data;
    }

    /**
     * Gets the reference to the next member variable of the list node.
     * 
     * @returns
     * The reference of the next member variable. Note that this return value can be null, 
     * meaning that there is no next SlideListNode in the list.
     * **/
    public SlideListNode getNext(){
        //does this just return next slide (which includes all the slide information for that slide object/node) so is it return next.getdata, which will output a string?
        //now that I think about it, no, it returns the next node which contains the data and its own next and prev
        return next;
    }

    /**
     *  Gets the reference to the prev member variable of the list node.
     *  
     * @returns
     * The reference of the prev member variable. Note that this return value can be null, 
     * meaning that there is no previous SlideListNode in the list. (this means we’ve 
     * reached the head of the list!).
     * **/
    public SlideListNode getPrev(){
        return prev;
    }

    //setters
    /**
     * Updates the data member variable with a reference to a new Slide.
     *  
     * @param newData
     *  Reference to a new Slide object to update the data member variable. This parameter 
     * should not be null, since we should never have a SlideListNode with null data 
     * (remember, this class serves only as a wrapper for the Slide class).
     *  
     * @custom.precondition
     * newData is not null
     * 
     * @throws IllegalArgumentException
     * Thrown if newData is null.
     * **/
    public void setData(Slide newData) throws IllegalArgumentException {
         //so we just take the new slide object and replace it with the old slide object in the variable data
        //so something like: data = newData?
        if (newData == null){
            throw new IllegalArgumentException("New slide can't be null.");
        }
        else{

        }
    }

     /**
     *  Updates the next member variable with a new SlideListNode reference.
     * 
     * @param newNext
     * Reference to a new SlideListNode object to update the next member variable. 
     * This parameter may be null, since it is okay to have no next SlideListNode 
     * (this means we’ve reached the tail of the list!).
     * **/
    public void setNext(SlideListNode newNext){
        //so this method sets the current node's next node's reference to a new node
        next = newNext;
    }

    /**
     *  Updates the prev member variable with a new SlideListNode reference.
     *  @param newPrev
     * Reference to a new SlideListNode object to update the prev member variable. 
     * This parameter may be null, since it is okay to have no previous SlideListNode 
     * (this means we’ve reached the head of the list!).
     * **/
    public void setPrev(SlideListNode newPrev){
        prev = newPrev;

    }
   
}
