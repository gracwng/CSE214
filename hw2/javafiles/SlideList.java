/**
 * This class named SlideList implements a double
 * linked-list data structure. The SlideList maintains a list of 
 * Slides by chaining a series of SlideListNodes between a head and a 
 * tail reference. In addition, a cursor is provided to allow a 
 * user to traverse the list, selecting individual SlideListNodes to allow 
 * for insertion, deletion, and manipulation of the Slides they contain. 
 * Lastly, the class provides methods to query information about the list,
 * such as it’s size, the total duration of all Slides in the list, and the 
 * total bullet points of all Slides in the list.

 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/
public class SlideList extends SlideListNode {

   //member variables 
   private SlideListNode head;   //this is the head node of the list
   private SlideListNode tail;   //this is the tail node of the list
   private SlideListNode cursor;    //this is the cursor node of the list
   //optional additional member variables
   private static int numSlides = 0;   //this counts the total number of slides in the presentation

   /**
   * This is the default constructor
   * 
   * @custom.postcondition
   * this object has been initialized to an empty Slide (title and all bullets are null, duration = 0.0)
   * **/   
  
  public SlideList() {
      head = null;
      tail = null;
      cursor = null;
   }

   /**
   * Gets the reference to the Slide wrapped by the SlideListNode currently referenced by cursor.
   * 
   * @returns 
   * The reference of the Slide wrapped by the SlideListNode currently referenced by cursor. If the 
   * cursor is null, then this method should return null as well (i.e. the cursor does not reference a Slide).
   * **/
   public Slide getCursorSlide(){
      //this just gets the slide information at the cursor node. 
      //would it be: return cursor.getData()
      //if cursor is null, then return null as well because it means the list is empty
      if (cursor == null){
         return null;
      }
      else{
         return cursor.getData();
      }      
   }
    
     /**
     *  Returns the cursor to the start of the list
     * 
     * @custom.postcondition
     * If head is not null, the cursor now references the first SlideListNode in this list.
     * If head is null, the cursor is set to null as well (there are no Slides in this list).
     * **/
   public void resetCursorToHead(){
      //this resets the cursor to the start of the list
      //if head is not null, cursor = head or (head.getNext if I use a dummy head)
      //if head is null, then cursor = null bc empty list and print ("Empty slideshow.")
      if (head == null){
         cursor = null;
         System.out.println("Empty slideshow. \n");
      }
      else {
         cursor = head;
         System.out.println("Cursor has been reset to the head.\n");
      }
   }

   
   /**
    *  Moves the cursor to select the next SlideListNode in the list. If the cursor is at the 
    * tail of the list, this method throws an exception (this includes the case where cursor 
    * and tail are both null).
    * 
    * @throws EndOfListException
    * Thrown if cursor is at the tail of the list.
    * **/
   public void cursorForward() throws EndOfListException {
      //if the cursor is at the tail of the list, then throw exception that says, "End of list cannot move forward. "
      if (cursor == tail){
         throw new EndOfListException("End of list cannot move forward.\n");
      }
      else{
      //otherwise, set cursor = cursor.getNext and print "The cursor moved forward to slide " + cursor.getTitle
         cursor = cursor.getNext();
         System.out.println("The cursor moved forward to slide \"" + cursor.getData().getTitle() + "\"\n");
      }
   }

   
   /**
     *  Moves the cursor to select the previous SlideListNode in the list. If the cursor is at 
     * the head of the list, this method throws an exception (this includes the case where 
     * cursor and head are both null).
     * 
     * @throws EndOfListException
     * thrown if cursor is at the head of the list
   **/
   public void cursorBackward() throws EndOfListException {
      //if cursor is at the head of the list, throw exception that says "End of list cannot move backward"
      if (cursor == head){
         throw new EndOfListException("End of list cannot move backward.");
      }
      //otherwise set cursor = cursor.getPrev and print "The cursor moved backward to slide " + cursor.getTitle
      else{
         cursor = cursor.getPrev();
         System.out.println("The cursor moved backward to slide \"" + cursor.getData().getTitle() + "\"\n");
      }
   }

   /**
     *  Inserts the indicated Slide before the cursor.
     * 
     * @param newSlide
     * The Slide object to be wrapped and inserted into the list before the cursor.
     * 
     * @custom.precondition
     * newSlide is not null
     * 
     * @custom.postcondition
     * newSlide has been wrapped in a new SlideListNode object. If cursor was previously not 
     * null, the newly created SlideListNode has been inserted into the list before the cursor.
     * If cursor was previously null, the newly created SlideListNode has been set as the new head 
     * of the list (as well as the tail). The cursor now references the newly created SlideListNode.
     * You should NOT move data references around between SlideListNodes to accomplish this method. 
     * Instead, you should wrap the newSlide object in a new SlideListNode object, and insert this object 
     * into the list before the cursor.
     * 
     * @throws IllegalArgumentException
     * thrown if newSlide is null
     * **/
   public void insertBeforeCursor(Slide newSlide) throws IllegalArgumentException {
      //this changes the cursor
      if (newSlide == null){
         throw new IllegalArgumentException ("Slide cannot be null.");
      }
      else{
         SlideListNode newNode = new SlideListNode(newSlide);
         
         //what if the cursor is empty
         //set everything: head, tail, cursor to newSlide
         if (cursor == null){
            cursor = newNode;
            head = newNode;
            tail = newNode;
         }

         //what if the cursor is the head
         // 1) set the newSlide's next to head: newSlide.setNext(head)
         // 2) set the newSlide's prev to null: newSlide.setPrev(null)
         // 3) set the head's prev to newSlide : head.setPrev(newSlide)
         // 4) set cursor = newSlide and head = newSlide
         else if (cursor == head){
            newNode.setNext(head);
            newNode.setPrev(null);
            head.setPrev(newNode);
            cursor = newNode;
            head = newNode;
         }

         else {
            //if the cursor is not at the head then set the newSlide's prev to the cursor's prev(bc it creates a connection)
            //then set the newSlide's next to cursor
            //then we change the prevSlide's next to the newSlide
            //then we change the cursor's prev to the newSlide
            //cursor = newSlide
            newNode.setNext(cursor);
            newNode.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(newNode);
            cursor.setPrev(newNode);
            cursor = newNode;
         }
         //add 1 to numSlides
         numSlides++;
      }
      
      //how would I use a dummy head in this case? did we consider all possibilities?


   }
   /**
     *  Inserts the indicated Slide after the tail of the list.
     *  This insertion method does not affect the cursor, unless the list was previously empty.
     *  In that case, head, tail, and cursor should all reference the new SlideListNode.

     * 
     * @param newSlide
     * The Slide object to be wrapped and inserted into the list after the tail of the list.
     * 
     * @custom.precondition
     * newSlide is not null
     *
     * @custom.postcondition
     * newSlide has been wrapped in a new SlideListNode object
     * If tail was previously not null, the newly created SlideListNode has been inserted into the 
     * list after the tail.
     * If tail was previously null, the newly created SlideListNode has been set as the new head of the 
     * list (as well as the tail).
     * The tail now references the newly created SlideListNode.
     * 
     * @throws IllegalArgumentExcpetion
     * thrown if newSlide is null
     * **/
   public void appendToTail(Slide newSlide) throws IllegalArgumentException {
      if (newSlide == null){
         throw new IllegalArgumentException("Slide cannot be null.");
      }
      else{
         //create a newSlideNode
         SlideListNode newNode = new SlideListNode(newSlide);
         
         //start the process of actually appending to tail

         //the insertion doesn't affect cursor 
         //what if tail is null
         //set tail, head, and cursor to node that contains newSlide
         if (tail == null){
            tail = newNode;
            head = newNode;
            cursor = newNode;
         }

         //if tail is not null
         //create a new node that contains new slide
         // set newnode's prev to tail
         // set newnode's next to null
         // set tail's next to new node
         // set tail = newnode
         else{
            newNode.setPrev(tail);
            newNode.setNext(null);
            tail.setNext(newNode);
            tail = newNode;
         }
         //add 1 to numSlides
         numSlides++;
      }

   }

   /** * 
    * Removes the SlideListNode referenced by cursor and returns the Slide inside.
    * 
    * @custom.precondition
    * cursor is not null
    * 
    * @custom.postcondition
    * The SlideListNode referenced by cursor has been removed from the list. 
    * All other SlideListNodes in the list exist in the same order as before.
    * The cursor now references the previous SlideListNode (or the head, if the cursor previously 
    * referenced the head of the list).
    *
    * @returns
    * The reference to the Slide contained within the SlideListNode which was just removed from the list.
    *
    * @throws EndOfListExpcetion
    * thrown if cursor is null
    * **/
   public Slide removeCursor() throws EndOfListException {
      //so it removes the node at the cursor but it also returns the slide that was in the cursor
      if (cursor == null){
         throw new EndOfListException("Empty slideshow.\n");
      }
      else{
         Slide cursorSlide = cursor.getData();
            //so can I create a slide object that stores the cursor's slide information
         //consider if cursor is head, tail, middle, or if there is only one node
         
         //if there is one node
         //which means tail is the cursor and head is the cursor and tail = head
         // then set all of them to null
         if (tail == head){
            head = null;
            tail = null;
            cursor = null;
         }

         else if (cursor == tail){
         //if cursor is tail
         //set prev node to tail by breaking off prev node's next from tail. so set prev node's next to null
         //cursor.getPrev().setPrev(null)
         //tail = cursor.getPrev()
         cursor.getPrev().setNext(null);
         tail = cursor.getPrev();
         //don't forget to update the cursor!
         cursor = tail;
      }

      else if (cursor == head){
         //if cursor is head
         //set cursor's next's prev to null: cursor.getNext().setPrev(null);
         //set cursor's next to head: head = cursor.getNext()
         cursor.getNext().setPrev(null);
         head = cursor.getNext();
         //don't forget to update the cursor!
         cursor = head;
         }
        
         else{
         //if cursor is middle
         //cursor's prev has to be set to cursor's get next: cursor.getPrev().setNext(cursor.getNext)
         //cursor's get next's prev has to be set to cursor's prev: cursor.getNext().setPrev(cursor.getPrev)
         //cursor becomes its prev: cursor = cursor.getPrev()
         cursor.getPrev().setNext(cursor.getNext());
         cursor.getNext().setPrev(cursor.getPrev());
         cursor = cursor.getPrev();

         }


         //in the end, return slide object
         //subtract 1 from numSlides 
         numSlides--;
         return cursorSlide;  
         }
         
   }

   /**
    * Returns the total number of Slides in the slideshow.
    * This method should run on O(1) time.
    * 
    * @returns
    * The count of all Slides in the slideshow.
    **/
   public int size(){
      //list info methods
      //this method should run on O(1) time which means I need a static variable
      //this counts all Slides in the slideshow
      return numSlides;

   }

   /**
    * Returns the total duration of the slideshow.
    * Depending on the implementation, this method may run in O(1) or O(n) time. both will be accepted..
    *
    * @returns
    * The sum of all individual Slide durations in the slideshow.
    **/
   public double duration(){
      SlideListNode temp = new SlideListNode();
      temp = head;
      double count = 0;
      while(temp != null){
         count += temp.getData().getDuration();
         temp = temp.getNext();
      }
      return count;
      
      //for this could I say. could I use the methods inside the slidelist class inside other methods inside the same class. 
      //like if I want to count the total duration of the slides, I would have to go through every node, so could I set
      //temp to cursor to head, and then use the cursorForward method? It seems redundant so when would I use methods within methods?
      //resetCursorToHead();

   }
   /** 
    * Returns the total number of bullet points in the slideshow.
    * Depending on the implementation, this method may run in O(1) or O(n) time. both will be accepted..
    *
    * @returns
    * The sum of all bullet points of all individual Slides in the slideshow.
    **/
   public int numBullets(){
      SlideListNode temp = new SlideListNode();
      temp = head;
      int count = 0;
      while(temp != null){
         count += temp.getData().getNumBullets();
         temp = temp.getNext();
      }
      return count;
   }

   /** 
    * This returns the string of the slide list which includes the title, duration and number of bullets
    * of each slide
    * 
    * @returns
    * Slide, title, duration bullets
    * total: total slides, total minutes, total bullets 
    **/

   //custom methods

   // a method to display the presentation summary
   // if head is null, then print empty presentation
   // else you will traverse the linked list until you reach the tail and display the data for each slidelist node
   // if the link is the cursor, include an arrow next to the slide number

   // slideshow summary requirements: 
   // Slide, title, duration bullets
   // total: total slides, total minutes, total bullets 

   //this is not fully tested yet

   public void slideshowSummary() {
      SlideListNode temp = new SlideListNode();
      // if (head == null)
      //    System.out.println("Empty slideshow.");
      // else{
         temp = head;
         System.out.println("Slideshow Summary:\n" +
         "==============================================\n" +
         String.format("%-10s%-10s%-10s%-10s", "  Slide", "Title", "Duration", "Bullets") +
         "\n=============================================="
         );
         int slideNum = 1;
         while (temp != null){   //cant be temp.getData() != null because you cant access data at all if the entire temp node is null
            if (temp == cursor){
               //print arrow (could be empty string), slide number (int), title(string), duration(double), bullets(int)
               System.out.println(String.format("%-3s%-7d%-10s%-10.1f%-10d", "-> ", slideNum, temp.getData().getTitle(), temp.getData().getDuration(), temp.getData().getNumBullets()));
            }
            else{
               System.out.println(String.format("%-3s%-7d%-10s%-10.1f%-10d", " ", slideNum, temp.getData().getTitle(), temp.getData().getDuration(), temp.getData().getNumBullets()));
            }
            slideNum++;
            temp = temp.getNext();
         }
         System.out.println("==============================================");
         //System.out.println("Total: " + size() + " slide(s), " + duration() + " minute(s), " + numBullets() + " bullet(s)\n");
         System.out.println(String.format("Total: %d slide(s), %.1f minute(s), %d bullet(s).", size(), duration(), numBullets()));
         System.out.println("==============================================");

      //}
   }
}

