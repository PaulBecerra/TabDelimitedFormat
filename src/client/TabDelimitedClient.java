/*
 * TabDelimitedClient.java
 */
package client;

import domain.Person;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author paulb
 */
public class TabDelimitedClient {
    
    public Person send(Person person, int port) throws IOException {
        
            DatagramSocket socket = new DatagramSocket();
            // root project
            Path path = Paths.get("personClient.txt");
            
            // data to be sent
            byte[] bufferReceived = new byte[1024];
           
            Files.write(path, person.toString().getBytes());
            
            // File to Bytes
            byte[] bufferSend = Files.readAllBytes(path);
            
            System.out.println("Client sends: " + person.toString());
            // connection
            DatagramPacket packet = 
                    new DatagramPacket(bufferSend, bufferSend.length, InetAddress.getLocalHost(), port);
            
            socket.send(packet);
            
            packet = new DatagramPacket(bufferReceived, bufferReceived.length);
            socket.receive(packet);
            
            String personText = new String(packet.getData());
            String[] split = personText.split("\t");
            
            String name = split[0];
            double height = Double.parseDouble(split[1]);
            double weight = Double.parseDouble(split[2]);
            double bmi = Double.parseDouble(split[3]);
            String result = split[4];
            
            Person person1 = new Person(name, height, weight, bmi, result);
            System.out.println("client receives: " + person1.toString());
            socket.close();
            return person1;
    }
    
}
