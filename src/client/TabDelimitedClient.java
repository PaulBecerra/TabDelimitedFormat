/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import domain.Person;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paulb
 */
public class TabDelimitedClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
            DatagramSocket socket = new DatagramSocket();
            // root project
            Path path = Paths.get("personClient.txt");
            
            // data to be sent
            byte[] buffer = new byte[1024];
            // first step: business object 
            Person person = new Person("Samuel", 5, 5);
            // second step: BO to File
            Files.write(path, person.toString().getBytes());
            // File to Bytes
            buffer = Files.readAllBytes(path);
            
            // connection
            DatagramPacket packet = 
                    new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 999);
            socket.send(packet);
            socket.close();
    }
    
}
