/**
 * This is a Enum named LightValue, which lists the phases a particular stoplight lane may be in. 
 * 
 * These states should include GREEN, RED, and LEFT_SIGNAL (we are considering yellow to be part of green). 
 * Each TwoWayRoad instance (defined below) will have its own LightValue, which should correspond to the following rules: 
 * 
 * GREEN indicates that the right and middle lanes may proceed, but the left lane cannot (for both directions).
 * RED indicates that no lane may proceed (for both directions).
 * LEFT_SIGNAL indicates that left can proceed, but the right and middle lanes cannot (for both directions).
 * Cars in a particular lane may proceed (dequeue one car per time interval) when it is their turn to go, according to the rules above.
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

public enum LightValue {
    GREEN, //indicates that the right and middle lanes may proceed but left cannot
    RED,    //indicates that no lane may proceed
    LEFT_SIGNAL;    //indicates that left can proceed but th eright and middle lanes cannot
}
