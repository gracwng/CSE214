/**
 * This class is named PresentationManager which creates an instance of the SlideList class and provides 
 * an interface for a user to manipulate the list by adding, removing, and editing slides. 

 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

 /**
  * The main method runs a menu driven application which first creates an empty SlideList and then prompts the user for a
  * menu command selecting the operation. The required information is then requested from the user based on the selected 
  * operation. Following is the list of menu options and their required information:
  **/
   import java.util.Scanner;
   public class PresentationManager {
    //static functions
    //we can only have one scanner object, so if I am creating extra methods outside of the main method, set the scanner object to static
    static Scanner input = new Scanner(System.in);
    public static void main(String[]args){
      String option = "";
       //create an empty SlideList 
      SlideList presentation = new SlideList();
      System.out.println("Welcome to PresentationManager!\n");
   
       //prompt user for a menu command
      do {
         System.out.print(
            "Please select an option: \n" + 
            "F) Move cursor forward\n" +
            "B) Move cursor backward\n" +
            "D) Display cursor slide\n" +
            "E) Edit Cursor slide\n" + 
            "P) Print presentation summary\n" +
            "A) Append new slide to tail\n" +
            "I) Insert new slide before cursor\n" +
            "R) Remove slide at cursor\n" + 
            "H) Reset cursor to head\n" +
            "Q) Quit\n\n" +
            "Select a menu option: " 
         );
         if(input.hasNextLine()) 
            option = input.nextLine().toUpperCase();
         
         switch (option) {
            case ("F"):
            optionF(presentation);
            // this moves the cursor forward
            // presentation.cursorForward() method
            // print "The cursor moved forward to slide " + cursor.getTitle();
            break;
            case("B"):
            optionB(presentation);
            // this moves the cursor backward
            // presentation.cursorBackward();
            // print "The cursor moved backward to slide " + cursor.getTitle();
            break;
      /*still have this*/
            case ("D"):
            optionD(presentation);
            // this displays the cursor slide
            // presentation.getCursorSlide(). this returns a slide, so if the slide is null, then print out "Empty slideshow"
            // or would this just be cursor.getData();
            break;
            case("E"):
            optionE(presentation);
            // this edits cursor slide
            // take input of user to ask if they want to change title, duration or bullets (t/d/b)
            // if they want to change the title, take in a new string title and set the cursor title to new title 
            // if they want to change the duration, take a new double and set the cursor duration to that new time 
            // if they want to change the bullet, ask which bullet index (take in an int) ask if they want to delete or edit it (HOW TO USE METHODS TO DO THIS?)
            //    if they want to edit it print (bullet x: ) and then read in new string and set that new string to bullet
            //    if they want to delete it, set that bullet to null and print (bullet x has been deleted)
            //    if they don't select d or e, print out invalid option and break. 
            // if the user does not input t/d/b, then output Invalid option
            break;
      /*still have this*/
            case ("P"):
            optionP(presentation);
            // this prints the presentation summary
            // what if there are no slides?
            // create a method inside the slidelist class that traverses the linked list and displays the data for each slidelistnode
   
            break;
      /*still have this*/
            case("A"):
            optionA(presentation);
            // this appends a new slide to the tail. To append a new slide, we would need to create a new 
            // slide object and input information for the slide
   
            //if createNewSlide() == null, then print invalid option
   
   
            // use the presentation.appendToTail(Slide newSlide) to add the new slide to the tail
            // then print "Slide "" + newSlide.getTitle() + "" added to presentation. "
            break;
      /*still have this*/
            case ("I"):
            optionI(presentation);
            // this inserts a new slide before the cursor
            // use the edit slide method inside this class, or the setData method (I'm confused by how I would use this method if my newData slide is initially null)
            // this uses the insertBeforeCursor(Slide newSlide) method
            // print "Slide "" + newSlide.getTitle() + "" added to presentation. "
            break;
            case("R"):
            optionR(presentation);
            // this removes the slide at the cursor
            // get Title of the slide and store it in a string
            // use cursor.removeCursor(); (ASK ABT THIS: presentation manager wants us to print that Slide -title- has been removed but 
            // the remove cursor method returns the slide. what is the purpose of returning the slide?)
            // print that "Slide """ + title + "" has been removed."
            break;
            case ("H"):
            optionH(presentation);
            // this resets the cursor to head
            // use cursor.resetCursorToHead()
            // print "Cursor has been reset to the head."
            break;
            case("Q"):
            // this quits the program
            break;
            default:
            System.out.println("Invalid option.\n");
         }
      }

      while(!(option.equals("Q")));
      System.out.println();
      System.out.println("Program terminating normally...");
      
      input.close();
		System.exit(0);
    }

    public static void optionF(SlideList presentation){
      // this moves the cursor forward
      try{
         presentation.cursorForward();
      }
      catch(EndOfListException e){
         System.out.println(e.getMessage());
      }

    }

    public static void optionB(SlideList presentation){
    //this moves the cursor backward
      try{
      presentation.cursorBackward();
      }
      catch(EndOfListException e){
      System.out.println(e.getMessage());
      }


    }

    public static void optionD(SlideList presentation){
      //this displays cursor slide
      // presentation.getCursorSlide(). this returns a slide, so if the slide is null, then print out "Empty slideshow"
         // or would this just be cursor.getData();
      if (presentation.getCursorSlide() == null){
         System.out.println("Empty slideshow.\n");
      }
      else{
         System.out.println(presentation.getCursorSlide());
      }
       
   }
   
   public static void optionE(SlideList presentation){
      //edit cursor slide
      // take input of user to ask if they want to change title, duration or bullets (t/d/b)
      //Scanner input = new Scanner(System.in);
      //check to see if this cursor is not null. This doesn't work. I need a getter method for the cursor 
      if (presentation.getCursorSlide() == null){
         System.out.println("Empty slideshow.\n");
      }
      //if cursor is not null:
      else{
         try{
         String s = "";
         double newDuration = 0;
         int bulletIndex = 0;
         System.out.print("Edit title, duration, or bullets? (t/d/b) ");
         s = input.nextLine();
         
            // if they want to change the title, take in a new string title and set the cursor title to new title 
          if (s.equals("t")){
             System.out.print("Enter the slide title: ");
             s = input.nextLine();
             presentation.getCursorSlide().setTitle(s);
          }
          // if they want to change the duration, take a new double and set the cursor duration to that new time 
          else if (s.equals("d")){
             System.out.print("Enter a duration: ");
             newDuration = input.nextDouble();
             input.nextLine();
             presentation.getCursorSlide().setDuration(newDuration);
          }
          // if they want to change the bullet, ask which bullet index (take in an int) ask if they want to delete or edit it (HOW TO USE METHODS TO DO THIS?)
          //    if they want to edit it print (bullet x: ) and then read in new string and set that new string to bullet
          //    if they want to delete it, set that bullet to null and print (bullet x has been deleted)
          //    if they don't select d or e, print out invalid option and break. 
          else if (s.equals("b")){
             System.out.print("Bullet index: ");
             bulletIndex = input.nextInt();
             input.nextLine();
    
             // it seems like the sample input/output wants me to check if bullet index is within valid range even 
             // before I try to use the setBullet method in the slide class. But can I also just take in an invalid bullet index, 
             // let the user choose if they want to delete or edit it, basically take in all the information needed to edit or delete
             // a particular slide, and pass these invalid arguments into the setBullet method since this method already handles the case when there is an invalid index
             if (bulletIndex <= presentation.getCursorSlide().getNumBullets() && bulletIndex > 0){
                System.out.print("Delete or edit? (d/e): ");
                s = input.nextLine();
                // if they want to edit it print (bullet x: ) and then read in new string and set that new string to bullet
                if (s.equals("e")){
                   System.out.print("Enter new bullet: ");
                   s = input.nextLine();
                   presentation.getCursorSlide().setBullet(s, bulletIndex);
                }
                // if they want to delete it, set that bullet to null and print (bullet x has been deleted)
                else if (s.equals("d")){
                   presentation.getCursorSlide().setBullet(null, bulletIndex);
                   System.out.println("Bullet " + bulletIndex + " has been deleted.\n");
                }
                // if they don't select d or e, print out invalid option and break. 
                else {
                   System.out.println("Invalid option.\n");
                }
    
             }
             else{
                System.out.println("Invalid index.\n");
             }
          }
          
          // if the user does not input t/d/b, then output Invalid option
    
          else{
             System.out.println("Invalid option.\n");
          }
              
          }
          catch(IllegalArgumentException e){
             System.out.println(e.getMessage());
          }
         
      }
     
}

   public static void optionP(SlideList presentation){
      presentation.slideshowSummary();

   }
   
   public static void optionA(SlideList presentation){
      //append new slide to tail
       // this appends a new slide to the tail. To append a new slide, we would need to create a new 
         // slide object and input information for the slide
      
      //this segment of code creates a new slide object
      Slide newSlide = createSlide();
      if (newSlide != null){
         //append this new slide to tail
         try{
            presentation.appendToTail(newSlide);
            System.out.println("Slide \"" + newSlide.getTitle() + "\" added to presentation.\n" );
         }
         catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
         }
      }

   }

   public static void optionI(SlideList presentation){
      //insert new slide before cursor

      //this segment of code creates a new slide object

      Slide newSlide = createSlide();
      if (newSlide != null){
         try{
            presentation.insertBeforeCursor(newSlide);
            System.out.println("Slide \"" + newSlide.getTitle() + "\" added to presentation." );
         }
         catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
         }
      }
   }
   
   public static void optionR(SlideList presentation){
      //remove slide at cursor
      // get Title of the slide and store it in a string
         // use cursor.removeCursor(); 
         // this method returns a slide, so if the slide is not null, then say that the slide has been removed. 
         // if the slide is null, print empty slideshow
         // print that "Slide """ + title + "" has been removed."
         try{
            Slide removedSlide = presentation.removeCursor();
            System.out.println("Slide \"" + removedSlide.getTitle() + "\" has been removed.\n");
         }
         catch(EndOfListException e){
            System.out.println(e.getMessage());
         }
   }

   public static void optionH(SlideList presentation){
      //reset cursor to head
      presentation.resetCursorToHead();
   }

   //this method creates the slide so it can be used in multiple cases
   public static Slide createSlide(){
      // this method is for the appendToTail and insertBeforeCursor

         // so we need to take in a string input for the title (Enter the slide title: )
         // take in a double input for the duration (Enter the slide duration: ) (this uses the newSlide.getData().setData() method which catches exceptions)
         // create a for loop when setting the bullets.
         
         // int i = 1; boolean flag = true;
         // take input for first bullet outside of the while loop because we have to ask and its the only bullet
         // where we're not giving the user an option to add another bullet 
         // add that bullet to the slide using the setBullet(bulletString, i) method
         // after that, add 1 to 1
         // while (i <=MAX_BULLETS && flag){ /*ASK: do I have to include i <=MAX_BULLETS if my setBullet method handles
         //  the case when i is not within valid range */
         //    ask (Add another bullet point? (y/n))
         //    if yes, then print ("Bullet " + i + ": ") and take input for that bullet. then newSlide.setBullet(bulletString, i)
         //    if no, set flag to false
         //    if its not yes or no, print Invalid option and then set flag to false
         // }

         //if the user inputs invalid option, then return null and the method that takes this return should have an if statement to handle it
         //so if the return is null,
         //Scanner input = new Scanner(System.in);   //is this ok
         Slide newSlide = new Slide();
         String title = "";
         double duration = 0;
         String [] bullets = new String[5];

         //read in title
         System.out.println("Enter the slide title: ");
         title = input.nextLine();
         try{
            newSlide.setTitle(title);
         }
         catch(IllegalArgumentException e){  //if title is null, then we have to go back to menu options
            System.out.println(e.getMessage());
            return null;
         }

         //read in duration
         System.out.println("Enter the slide duration: ");
         duration = input.nextDouble();   
         input.nextLine(); //this is important to include because nextDouble doesn't implement the newLine character
         try{
            newSlide.setDuration(duration);
         }
         catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
         }
        
         //read bullet points
         String bullet = "";
         int i = 1; 
         boolean flag = true;
         System.out.print("Bullet 1: ");
         bullet = input.nextLine();
         try{  
            newSlide.setBullet(bullet, i);
            i++;
         while (i <= 5 && flag){
            System.out.println("Add another bullet point? (y/n)");
            bullet = input.nextLine();
            if (bullet.equals("y")){
               System.out.print("Bullet " + i + ": ");
               bullet = input.nextLine();
               newSlide.setBullet(bullet, i);
               if (i == 5){
                  System.out.println("No more bullets allowed. Slide is full.\n ");
               }
            }
            else if (bullet.equals("n")){
               flag = false;
            }
            else {
               System.out.println("Invalid option.\n");
               flag = false;
               return null;
            }
            i++;
         }
         return newSlide;
         }
         catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
         }
         
         }

        // add that bullet to the slide using the setBullet(bulletString, i) method
         // after that, add 1 to 1
         // while (i <=MAX_BULLETS && flag){ /*ASK: do I have to include i <=MAX_BULLETS if my setBullet method handles
         //  the case when i is not within valid range */
         //    ask (Add another bullet point? (y/n))
         //    if yes, then print ("Bullet " + i + ": ") and take input for that bullet. then newSlide.setBullet(bulletString, i)
         //    if no, set flag to false
         //    if its not yes or no, print Invalid option and then set flag to false
         // }
   }
 

