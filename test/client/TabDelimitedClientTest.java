/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import domain.Person;
import java.net.SocketException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.TabDelimitedServer;

/**
 *
 * @author paulb
 */
public class TabDelimitedClientTest {
    
    private TabDelimitedClient client;
    
    public TabDelimitedClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SocketException {
        client = new TabDelimitedClient();
        new TabDelimitedServer(9999).start();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of send method, of class TabDelimitedClient.
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("test send person");
        Person person1 = new Person("Julian", 1.7, 70);
        Person person = new Person("Ricardo", 1.80, 60);
        Person person2 = new Person("Paul", 1.75, 80);
        Person person3 = new Person("Ricardo", 1.80, 100);
        
        
        Person personResult = client.send(person, 9999);
        Person personResult1 = client.send(person1, 9999);
        Person personResult2 = client.send(person2, 9999);
        Person personResult3 = client.send(person3, 9999);
        //System.out.println(personResult1.getResult());
        // TODO review the generated test code and remove the default call to fail.
        assertNotEquals("Thin", personResult.getResult());
        assertNotEquals("Healthy", personResult1.getResult());
        assertNotEquals("Overweight", personResult2.getResult());
        assertNotEquals("Obese", personResult3.getResult());
    }
    
}
