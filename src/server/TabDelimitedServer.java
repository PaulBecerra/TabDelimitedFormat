/*
 * TabDelimitedFormatServer.java
 */
package server;

import domain.Person;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UDP Server
 *
 * @author paulb
 */
public class TabDelimitedServer extends Thread {

    private DatagramSocket socket;

    public TabDelimitedServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void run() {

        while (true) {
            try {
                byte buffer[] = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                String personText = new String(buffer);
                System.out.println("server receives: " + personText);
                String[] split = personText.split("\t");

                String name = split[0];
                double height = Double.parseDouble(split[1]);
                double weight = Double.parseDouble(split[2]);

                double bmi = weight / (Math.pow(height, 2));
                String result;

                if (bmi < 18.6) {
                    result = "Thin";
                } else if (bmi >= 18.6 && bmi < 25) {
                    result = "Healthy";
                } else if (bmi >= 25 && bmi < 30) {
                    result = "Overweight";
                } else {
                    result = "Obese";
                }

                Person person = new Person(name, height, weight, bmi, result);
                
                Path path = Paths.get("personServer.txt");

                Files.write(path, person.toString().getBytes());

                // File to Bytes
                buffer = Files.readAllBytes(path);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                
                System.out.println("server sends: " + person.toString());
                
                packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);

            } catch (IOException ex) {
                Logger.getLogger(TabDelimitedServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
