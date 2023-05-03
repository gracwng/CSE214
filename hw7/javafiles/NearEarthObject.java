/**
 * Write a fully documented class named NearEarthObject. This class represents a record in the database of asteroids currently tracked 
 * by NASA. It should be noted that this class will always be constructed by the BigData library, and serves as a data container for the 
 * information that is already hosted by the NeoW API. Please refer to the BigData section below for more information on how the library 
 * will use this constructor to extract NearEarthObjects out of the JSON document returned by the NASA NeoW API.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * April 22, 2023
 */

import java.util.Date;
import java.lang.*;
import java.text.SimpleDateFormat;
//question: when i try to import import java.text.SimpleDateFormat; to convert the date constructed by the date class into a month-day-year format
//the import just disappears. the same happens when I want to import the Math class to round the distance to the nearest whole number.
//answer: It is because I have the feature on where if I don't use the package I import, then it will simply disappear. so if I use it before it is
//imported, then my program will know that I am using it and only then will my imported statement remain

public class NearEarthObject {
    private int referenceID;
    private String name;
    private double absoluteMagnitude;
    private double averageDiameter;
    private boolean isDangerous;
    private Date closestApproachDate;
    private double missDistance;
    //Question: the UML Diagram says orbitingBody is of type double, but we are reading it in as a string. so which is it supposed to be?
    //private double orbitingBody;
    private String orbitingBody;

    /**
     * Default Constructor.
     * Note: It is very important that your constructor exactly match this signature. This constructor will be used by the BigData 
     * library to fetch the NearEarthObject records from the NeoW API.
     * 
     * @param referenceID
     * Unique the ID of the NEO.
     * Fetched using the "near_earth_objects/neo_reference_id" identifier.
     * @param name
     * Unique name of the asteroid or orbital body.
     * Fetched using the "near_earth_objects/name" identifier
     * @param absoluteMagnitude
     * Absolute brightness of the asteroid or orbital body in the sky.
     * Fetched using the "near_earth_objects/absolute_magnitude_h" identifier.
     * @param minDiameter
     * Estimated minimum diameter of the asteroid or orbital body in kilometers. This parameter should be used in conjunction with the maxDiameter parameter to calculate and initialize the averageDiameter member variable.
     * Fetched using the "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min" identifier.
     * @param maxDiameter
     * Estimated maximum diameter of the asteroid or orbital body in kilometers. This parameter should be used in conjunction with the minDiameter parameter to calculate and initialize the averageDiameter member variable.
     * Fetched using the "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max" identifier.
     * @param isDangerous
     * Boolean value indicating whether the astroid or orbital body is a potential impact threat.
     * Fetched using the "near_earth_objects/is_potentially_hazardous_asteroid" identifier.
     * @param closestDateTimestamp
     * Unix timestamp representing the date of closest approach. Note that this can be used to directly construct the closestApproachDate member variable, as the Date class provides a constructor taking a timestamp as a parameter.
     * Fetched using the "near_earth_objects/close_approach_data/epoch_date_close_approach" identifier.
     * @param missDistance
     * Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of it's closest approach.
     * Fetched using the "near_earth_objects/close_approach_data/miss_distance/kilometers" identifier.
     * @param orbitingBody
     * Planet or other orbital body which this NEO orbits.
     * Fetched using the "near_earth_objects/close_approach_data/orbiting_body" identifier.
     */
    public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter, double maxDiameter, 
    boolean isDangerous, long closestDateTimestamp, double missDistance, String orbitingBody){
        this.referenceID = referenceID;
        this.name = name;
        this.absoluteMagnitude = absoluteMagnitude;
        this.averageDiameter = (minDiameter + maxDiameter)/2;
        this.isDangerous = isDangerous;
        this.closestApproachDate = new Date(closestDateTimestamp);
        this.missDistance = missDistance;
        this.orbitingBody = orbitingBody;

    }

    //public getters and setters
    /**
     * Gets unique the ID of the NEO.
     * @return
     * ID of the NEO.
     */
    public int getReferenceID(){
        return referenceID;
    }

    /**
     * gets Unique name of the asteroid or orbital body.
     * @return
     * Unique name of the asteroid or orbital body.
     */
    public String getName(){
        return name;
    }

    /**
     * gets Absolute brightness of the asteroid or orbital body in the sky.
     * @return
     * Absolute brightness of the asteroid or orbital body in the sky.
     */
    public double getAbsoluteMagnitude(){
        return absoluteMagnitude;
    }

    /**
     * gets average diameter of the asteroid or orbital body in the sky.
     * @return
     * average diameter of the asteroid or orbital body in the sky.
     */
    public double getAverageDiameter(){
        return averageDiameter;
    }

    /**
     * gets Boolean value indicating whether the astroid or orbital body is a potential impact threat.
     * @return
     * Boolean value indicating whether the astroid or orbital body is a potential impact threat.
     */
    public boolean getIsDangerous(){
        return isDangerous;
    }

    /**
     * gets Unix timestamp representing the date of closest approach. Note that this can be used to directly construct the closestApproachDate member variable, as the Date class provides a constructor taking a timestamp as a parameter.
     * @return
     * Unix timestamp representing the date of closest approach. Note that this can be used to directly construct the closestApproachDate member variable, as the Date class provides a constructor taking a timestamp as a parameter.
     */
    public Date getClosestApproachDate(){
        return closestApproachDate;
    }
    
    /**
     * gets distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of it's closest approach.
     * @return
     * Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of it's closest approach.
     */
    public double getMissDistance(){
        return missDistance;
    }

    /**
     * gets Planet or other orbital body which this NEO orbits.
     * @return
     * Planet or other orbital body which this NEO orbits.
     */
    public String getOrbitingBody(){
        return orbitingBody;
    }

    /**
     * sets Unique the ID of the NEO.
     * @param referenceID
     * Unique the ID of the NEO.
     */
    public void setReferenceID(int referenceID){
        this.referenceID = referenceID;
    }

    /**
     * sets Unique name of the asteroid or orbital body.
     * @param name
     * Unique name of the asteroid or orbital body.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * sets Absolute brightness of the asteroid or orbital body in the sky.
     * @param absoluteMagnitude
     * Absolute brightness of the asteroid or orbital body in the sky.
     */
    public void setAbsoluteMagnitude(double absoluteMagnitude){
        this.absoluteMagnitude = absoluteMagnitude;
    }

    /**
     * sets Estimated average diameter of the asteroid or orbital body in kilometers.
     * @param averageDiameter
     * Estimated average diameter of the asteroid or orbital body in kilometers
     */
    public void setAverageDiameter(double averageDiameter){
        this.averageDiameter = averageDiameter;
    }

    /**
     * sets Boolean value indicating whether the astroid or orbital body is a potential impact threat.
     * @param isDangerous
     * Boolean value indicating whether the astroid or orbital body is a potential impact threat.
     */
    public void setIsDangerous(boolean isDangerous){
        this.isDangerous = isDangerous;
    }

    /**
     * sets Unix timestamp representing the date of closest approach. 
     * @param closestApproachDate
     * Unix timestamp representing the date of closest approach.
     */
    public void setClosestApproachDate(Date closestApproachDate){
        this.closestApproachDate = closestApproachDate;
    }

    /**
     * sets Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of it's closest approach.
     * @param missDistance
     * Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of it's closest approach.
     */
    public void setMissDistance(double missDistance){
        this.missDistance = missDistance;
    }

    /**
     * sets Planet or other orbital body which this NEO orbits.
     * @param orbitingBody
     * Planet or other orbital body which this NEO orbits.
     */
    public void setOrbitingBody(String orbitingBody){
        this.orbitingBody = orbitingBody;
    }

    //convert date produced by the constructor of the Date class to a month-day-year format
    public String toString() {
        //this is to convert the date produced by the date constructor in the date class into the format month, day and year
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String dateString = dateFormat.format(closestApproachDate);

        //String s = String.format("%-9d%-29s%7.1f%10.3f%8b%12s%11d    %s", (referenceID / 10), ((referenceID / 1000) % 1000) + " " + name, absoluteMagnitude, averageDiameter, isDangerous, dateString, Math.round(missDistance), orbitingBody);
        String s = String.format("%-9d%-26.26s%-8.1f%-10.3f%-8b%-13s%-13d%-20s", (referenceID), /*((referenceID / 1000) % 1000) + " " + */ name, absoluteMagnitude, averageDiameter, isDangerous, dateString, Math.round(missDistance), orbitingBody);
//
//        String s = String.format("%d", (referenceID)/10);
//        s += String.format("%-8.30s ", (referenceID/1000)%1000 + " " + name);
//        s += String.format("%-8.1f ", absoluteMagnitude);
//        s += String.format("%-10.3f ", averageDiameter);
//        s += String.format("%10b ", isDangerous);
//        s += String.format("%10s ", dateString);
//        //question: why isn't this math.round method working?
//        //answer: it was fixed. chatgpt to the rescue
//        s += String.format("%10d ", Math.round(missDistance));
//       // s += String.format("%f ", (missDistance));
//        s += String.format("%10s ", orbitingBody);

        return s;

//        String output = String.format("Reference ID: %d", referenceID);
//        output += String.format("Name: %s", name);
//        output += String.format("Absolute Magnitude: %.2f", absoluteMagnitude);
//        output += String.format("Average Diameter: %.2f", averageDiameter);
//        output += String.format("Is Dangerous: %s", isDangerous);
//        output += String.format("Closest Approach Date: %s", closestApproachDate);
//        output += String.format("Miss Distance: %.2f km", missDistance);
//        output += String.format("Orbiting Body: %s", orbitingBody);
//        return output;


    }

}