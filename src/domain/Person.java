/*
 * Person.java
 */
package domain;

/**
 * This domain class is for creating an object which represents a person
 * @author Paul B. Carlos A. Julian P.
 */
public class Person {
//Declartion of variables
    private final String name;
    private final double height;
    private final double weight;
    private double bmi;
    private String result;
    
/**
 * This is a constructor to create a person object with their name,height and weight
 * @param name of the person
 * @param height of the person
 * @param weight of the person
 */
    public Person(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }
/**
 * This is a constructor to create a person object with their name,height,weight,bmi and result
 * @param name of the person
 * @param height of the person
 * @param weight of the person
 * @param bmi of the person
 * @param result the meaning of the bmi result
 */
    public Person(String name, double height, double weight, double bmi, String result) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.result = result;
    }
/**
 * @return the name of the person
 */
    public String getName() {
        return name;
    }
/**
 * @return the height of the person
 */
    public double getHeight() {
        return height;
    }
/**
 * @return the weight of the person
 */
    public double getWeight() {
        return weight;
    }
/**
 * @return the Bmi calculation result
 */
    public double getBmi() {
        return bmi;
    }
/**
 * @return the meaning of the BMI calculation result
 */
    public String getResult() {
        return result;
    }
/**
 * @return THe format in which an object type person gets printed as a String
 */
    @Override
    public String toString() {
        if (result == null){
            return name + "\t" + height + "\t" + weight;
        }
        else{
            return name + "\t" + height + "\t" + weight + "\t" + bmi + "\t" + result;
        }
    }
}
