/* Java program to illustrate the server side of the architecture project using TAB
 * TabDelimitedFormatServer.java
 */
package server;

import domain.Person;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Paul B. Carlos A. Julian P.
 */
public class TabDelimitedServer extends Thread {
    
    // Declares the socket object
    private DatagramSocket socket;

    
    public TabDelimitedServer(int port) throws SocketException {
        //Create as socket to listen at a certain port
        socket = new DatagramSocket(port);
        System.out.println("Server listens on port: "+ port);
    }

    public void run() {

        while (true) {
            try {
                //Array to store the information that is received
                byte buffer[] = new byte[1024];
                
                // Creates a DatgramPacket to receive the data
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                
                // Revieves the data in byte buffer
                socket.receive(packet);
                System.out.println("Server: Client connected");
                
                // Converts the packet's byte data to a String
                String personText = new String(buffer);
                System.out.format("%-20s %s\n", "server receives: ", personText);
                
                // An array which saves the persons data 
                // (Which is spilt and each attribute is saved in a different index)
                String[] split = personText.split("\t");

                // Assigns the attributes from the array to their according variable
                String name = split[0];
                double height = Double.parseDouble(split[1]);
                double weight = Double.parseDouble(split[2]);

                // Calculates and stores the BMI from the weight and height revieved
                double bmi = weight / (Math.pow(height, 2));
                
                /** format decimal numbers **/
                // double to BigDecimal
                BigDecimal bmiBG = BigDecimal.valueOf(bmi);
                // BigDecimal rounded to 2 decimals
                BigDecimal bmiRounded = bmiBG.setScale(2, RoundingMode.FLOOR);
                // BigDecimal to Double
                bmi = bmiRounded.doubleValue();
                
                // Declaration of variable which stores the meaning of the
                // result from the BMI calculation
                String result;
                
                // For determining the meaning of the result of BMI calculation
                if (bmi < 18.6) {
                    result = "Thin";
                } else if (bmi >= 18.6 && bmi < 25) {
                    result = "Healthy";
                } else if (bmi >= 25 && bmi < 30) {
                    result = "Overweight";
                } else {
                    result = "Obese";
                }

                // Creates a person object with the infromation received from
                // the client with the addition of the BMI and result
                Person person = new Person(name, height, weight, bmi, result);
                
                // The root directory of the project
                Path path = Paths.get("personServer.txt");

                // Writes the path to the root directory and the person a file
                Files.write(path, person.toString().getBytes());

                // Converts the String text from the file into bytes and stores it
                buffer = Files.readAllBytes(path);
                
                //To get the IP address from the packet
                InetAddress address = packet.getAddress();
                
                //Declares the port which the data will be sent from the packet
                // that was sent by the client
                int port = packet.getPort();
                
                // Creates the datagramPacket for sending the data(connection)
                packet = new DatagramPacket(buffer, buffer.length, address, port);
                System.out.println("Server is sending response");
                
                 // Send the Data
                socket.send(packet);

            } catch (IOException ex) {
                Logger.getLogger(TabDelimitedServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
