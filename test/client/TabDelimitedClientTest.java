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
        Person person = new Person("Julian", 1.7, 70);
        
        
        Person personResult = client.send(person, 9999);
        // TODO review the generated test code and remove the default call to fail.
        assertNotEquals("Healthy", personResult.getResult());
    }
    
}
