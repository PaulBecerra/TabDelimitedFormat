/* Java program to illustrate the client side of the architecture project using TAB
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
 * @author Paul B. Carlos A. Julian P.
 */
public class TabDelimitedClient {
    
    public Person send(Person person, int port) throws IOException {
            
            // Creates the socket object for carrying the data
            DatagramSocket socket = new DatagramSocket();
            
            // The root directory of the project
            Path path = Paths.get("personClient.txt");
            
            // A byte type Array which will store the data that will be sent
            byte[] bufferReceived = new byte[1024];
           
            // Writes the path to the root directory and the person a file
            Files.write(path, person.toString().getBytes());
            System.out.println("File created from client");
            
            // Converts the String text from the file into bytes and stores it
            byte[] bufferSend = Files.readAllBytes(path);
            
            // Creates the datagramPacket for sending the data(connection)
            DatagramPacket packet = 
                    new DatagramPacket(bufferSend, bufferSend.length, InetAddress.getLocalHost(), port);
            System.out.println("Client is sending file in bytes");
            
            // Send the Data
            socket.send(packet);
            
            // Create a DatgramPacket to receive the data return from the server
            packet = new DatagramPacket(bufferReceived, bufferReceived.length);
            
            // Revieves the data in byte buffer
            socket.receive(packet);
            
            // Converts the packet's byte data to a String
            String personText = new String(packet.getData());
            
            // An array which saves the persons data 
            // (Which is spilt and each attribute is saved in a different index)
            String[] split = personText.split("\t");
            
            // Assigns the attributes from the array to their according variable
            String name = split[0];
            double height = Double.parseDouble(split[1]);
            double weight = Double.parseDouble(split[2]);
            double bmi = Double.parseDouble(split[3]);
            String result = split[4];
            
            //Creates a person object with the infromation received from the server
            Person person1 = new Person(name, height, weight, bmi, result);
            
            System.out.format("%-20s %s\n", "client receives: ", person1.toString());
            
           //Closes the socket
            socket.close();
            System.out.println("client desconnected");
            
            //Return the person with the infromation received from the server
            return person1;
    }
    
}
