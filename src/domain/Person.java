/*
 * Person.java
 */
package domain;

/**
 *
 * @author paulb
 */
public class Person {

    private final String name;
    private final double height;
    private final double weight;
    private double bmi;
    private String result;
    
    
    public Person(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public Person(String name, double height, double weight, double bmi, String result) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getBmi() {
        return bmi;
    }

    public String getResult() {
        return result;
    }

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
