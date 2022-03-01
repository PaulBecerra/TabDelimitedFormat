/*
 * TabDelimitedFormatServer.java
 */
package server;

import domain.Person;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UDP Server 
 * @author paulb
 */
public class TabDelimitedFormatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(999);
            
            byte buffer[] = new byte[1024];
            
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            
            socket.receive(packet);
            
            buffer = packet.getData();
            
            String personText = new String(buffer);
            
            String[] split = personText.split("\t");
            
            Person person = new Person(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]));
            
            System.out.println(person.toString());
            
        } catch (SocketException ex) {
            Logger.getLogger(TabDelimitedFormatServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TabDelimitedFormatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
