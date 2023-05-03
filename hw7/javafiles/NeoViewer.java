/**
 * Write a fully-documented class named NeoViewer which allows a user to view datasets obtained from the NASA NeoW API. This class 
 * should contain a main() method which creates a database and prompts the user to add a page to the database, sort the current 
 * database, and display the database.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * April 22, 2023
 */

import java.util.Scanner;

public class NeoViewer {
    /**
     * The main method runs a menu driven application which creates a NeoDatabase instance and then prompts the user for a menu 
     * command selecting the operation. The required information is then requested from the user based on the selected operation. 
     * Following is the list of menu options and their required information:
     */
    static Scanner input = new Scanner(System.in);
    public static void main(String[]args){
//        Scanner input = new Scanner(System.in);
//        NeoDatabase neoDataBase = new NeoDatabase();
//        String url = neoDataBase.buildQueryURL(1);
//        System.out.println(url);
//        neoDataBase.addAll(url);
//        neoDataBase.printTable();
//        System.out.println();
//        System.out.println();
//
//        DiameterComparator comp = new DiameterComparator();
//        neoDataBase.sort(comp);
//        neoDataBase.printTable();
    NeoDatabase dataBase = new NeoDatabase();
    String URL = "";
    String option = "";

        do {
            System.out.print(
                    "Welcome to NEO Viewer!\n\n" +
                            "Option Menu: \n" +
                            "\tA) Add a page to the databse \n" +
                            "\tS) Sort the database \n" +
                            "\tP) Print the database as a table\n"  +
                            "\tQ) Quit\n\n" +
                            "Select a menu option: "
            );
            option = input.nextLine().toUpperCase();
            switch(option){
 //---------------------------------------------------------------------------------------------------------------------------------------------- //
                case("A"):
                    //Add a page to the databse
                    try{
                        System.out.println();
                        System.out.print("Enter the page to load: ");
                        int page = input.nextInt();
                        input.nextLine();
                        URL = dataBase.buildQueryURL(page);
                        dataBase.addAll(URL);
                        System.out.println();
                        System.out.println("Page loaded successfully!");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e);
                    }
                    break;
 //---------------------------------------------------------------------------------------------------------------------------------------------- //
                case("S"):
                    //Sort the database
                    System.out.println();
                    System.out.println(
                        "R) Sort by referenceID\n" +
                        "D) Sort by diameter\n" +
                        "A) Sort by approach date\n" +
                        "M) Sort by miss distance\n"
                    );
                    System.out.print("Select a menu option: ");
                    option = input.nextLine().toUpperCase();
                    switch(option){
                        case("R"):
                            //Sort by referenceID
                            try{
                                ReferenceIDComparator referenceID = new ReferenceIDComparator();
                                dataBase.sort(referenceID);
                            }
                            catch(IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }

                        break;
                        case("D"):
                            //Sort by diameter
                            try{
                                DiameterComparator diameter = new DiameterComparator();
                                dataBase.sort(diameter);
                            }
                            catch(IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        break;
                        case("A"):
                            //Sort by approach date
                            try{
                                ApproachDateComparator approachDate = new ApproachDateComparator();
                                dataBase.sort(approachDate);
                            }
                            catch(IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        break;
                        case("M"):
                            //Sort by miss distance
                            try{
                                MissDistanceComparator missDistance = new MissDistanceComparator();
                                dataBase.sort(missDistance);
                            }
                            catch(IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }
                        break;
                    }
                    break;
 //---------------------------------------------------------------------------------------------------------------------------------------------- //
                case("P"):
                    //Print the database as a table
                    dataBase.printTable();
                    break;
 //---------------------------------------------------------------------------------------------------------------------------------------------- //
                case("Q"):
                    // Quit
                    System.out.println("Program terminating normally...");
                    break;
 //---------------------------------------------------------------------------------------------------------------------------------------------- //
            }
            System.out.println();
        }
        while (!option.equals("Q"));

    }
}
