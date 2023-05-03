/**
 * This is an Enum named CargoStrength, which lists the strength values a cargo container may have
 * 
 * The rules for the CargoStrength type is described as follows:
 * FRAGLIE containers can only support other FRAGILE containers.
 * MODERATE containers can support other MODERATE containers, as well as FRAGILE containers.
 * STURDY containers can support all other containers.
 * The number of containers above any given containers does not matter. The only restrictions are the three rules above 
 * 
 * @author Grace Wang
 * Stony Brook ID: 115083013
 * Recitation: CSE 214.R01
 * **/

public enum CargoStrength {
   FRAGILE, MODERATE, STURDY;
}
