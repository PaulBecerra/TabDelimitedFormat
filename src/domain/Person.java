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

    public Person(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = height;
    }

    @Override
    public String toString() {
        return name + "\t" + height + "\t" + weight;
    }
    
    
}
