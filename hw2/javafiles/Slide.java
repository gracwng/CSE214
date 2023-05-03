/**
 * This is a Slide class which contains the title (String), the array of bullet points (String[]), 
 * and the duration of the slide in minutes (double). Each slide is limited to a maximum of 
 * 5 bullet points, which is reflected in a static constant named MAX_BULLETS. It also provides
 * getter and setter methods for all member variables, with the getter/setter method for bullets taking 
 * an additional index parameter (see the provided UML diagram for further detail). In addition, there contains
 * a constructor as detailed below. Lastly, there is  a toString() method that returns a 
 * printable representation of the Slide and it’s data members (title, duration, and bullets).
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 public class Slide {

  //static variables
  public static final int MAX_BULLETS = 5;

  //member variables
  private String title; //this is the title of the slide
  private String[] bullets = new String[MAX_BULLETS];   //this is the array of bullet points in each slide
  private double duration;  //this is the duration of the slide in minutes
  //optional additional member variables


  //constructors
  /**
   * This is the default constructor
   * 
   * @custom.postcondition
   * this object has been initialized to an empty Slide (title and all bullets are null, duration = 0.0)
  * **/
  public Slide(){
    title = null;
    //can't set bullets array to null because then that cuts off all access to the array. You have to set the objects inside the array each to null. Ask prof about this
    for (int i = 0; i < bullets.length; i++) {
      bullets[i] = null;
    }
    duration = 0.0;
  }
  //optional custom constructors

  //getters
  /**
   * This is a public getter method for the title member variable
   * 
   * @return
   * The title of the Slide
  * **/
  public String getTitle(){
    return title;

  }
  
  /**
   * This method gets the bullet point at index i
   * 
   * @param i
   * The index to retrieve from the array. This value is between 1 and MAX_BULLETS, inclusive
   * 
   * @custom.precondition
   * 1 <= i <= MAX_BULLETS
   * 
   * @return
   * The String representing the bullet point at the given index (may be null, meaning there is no  bullet point at this index)
   * 
   * @throws IllegalArgumentException
   * thrown if i is not in the valid range
  * **/
  public String getBullet(int i) throws IllegalArgumentException {
    if (i < 1 || i > MAX_BULLETS)
      throw new IllegalArgumentException("This index is not in the valid range.\n");
    else
      return bullets[i-1];
  }

  /**
   * This method gets the total number of bullet points in the Slides. 
   * More specifically, this method counts the number of non-null elements 
   * in the bullets array 
   * 
   * @return 
   * THe number of filled bullet points in the Slide
   * **/
  public int getNumBullets(){
    int count = 0;
    for (int i = 0; i < bullets.length; i++){
      if (bullets[i] != null)
        count++;
    }
    return count;
  }

  /**
   * This is a public getter method for the duration member variable
   * 
   * @return
   * The duration of the slide
  * **/
  public double getDuration(){
    return duration;
  }

  //setters
  /**
   * This is a public setter method for the title member variable
   * 
   * @param newTitle
   * The new title of this slide. This parameter should not be null
   * 
   * @custom.precondition
   * newTitle is not null
   * 
   * @throws IllegalArgumentException
   * thrown if newTitle is null
  * **/
  public void setTitle(String newTitle) throws IllegalArgumentException {
    if (newTitle.equals(null))
      throw new IllegalArgumentException ("The new title cannot be null.\n");
    else{
      title = newTitle;
      }
    }
  
  /**
   * This method sets bullet as the i'th bullet point for bullets. If bullet is null, this
   * method erases the bullet point at index i and pushes all bullet points greater than i
   * back one index (ie leaves no "holes" in the bullets array)
   * 
   * @param bullet
   * The String to place as the i'th bullet point in bullets. This parameter may be null, indicating 
   * that the bullet at index i is to be erased from the slide
   * @param i
   * The index to place bullet in the array. This value must be between 1 and MAX_BULLETS, inclusive
   * 
   * @custom.precondition
   * i <= i and i <= MAX_BULLETS
   * 
   * @custom.postcondition
   * The bullet point at index i has been updated to the String bullet. There are no holes in the bullets
   * array. All bullet points occupy the lowest possible indices of the array
   * 
   * @throws IllegalArgumentException
   * thrown if i is not in the valid range
  * **/
  public void setBullet(String bullet, int i) throws IllegalArgumentException {
    if (i > MAX_BULLETS || i < 1)
        throw new IllegalArgumentException ("Invalid index.\n");
    else{
      if (bullet == null){
        for (int j = i; j < MAX_BULLETS; j++)
          bullets[j-1] = bullets[j];
        bullets[4] = null;
      }
      else
        bullets[i-1] = bullet;
    }
  }

  /**
   * This is a public setter method for the duration member variable
   * 
   * @param newDuration
   * The new duration of this slide
   * 
   * @custom.precondition
   * newDuration is greater than 0
   * 
   * @throws IllegalArgumentException
   * thrown if newDuration is less than or equal to 0
  * **/
  public void setDuration(double newDuration) throws IllegalArgumentException {
    if (newDuration <= 0)
      throw new IllegalArgumentException("Invalid duration.\n");
    else{
      duration = newDuration;
    }
  }

  /**
   * This is a to String method that returns information about the Slide
   * toString method for display cursor slide (option d)
   * 
   * @return
   * the String of the title and the bullet points of the slide
  * **/
  public String toString(){
    String s = "==============================================\n" 
    + getTitle()
     +"\n==============================================\n";
     int i = 1;
     int j = 1; //this counts the number of bullets there are
     while(i <= MAX_BULLETS){
      if (bullets[i-1] != null){
        s += (j + ". " + getBullet(i));
        j++;
      }
      if (i < MAX_BULLETS && bullets[i] == null) {      //if we reached the end of the list and there is nothing in the list
        break;
      }
      if (i == MAX_BULLETS) //if we reach the end of the list and there are bullets for each point, then just break
      break;
            
     s += "\n";
      i++;
    }
      //How do I make sure that after the last bullet point, the pointer doesn't go to the next
      // by checking to make sure the current bullet is the last one
      s += "\n==============================================\n";
      return s;
  }

  
}
