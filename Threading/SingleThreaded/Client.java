package Threading.SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


//we use JMeter for multiple thread spawning not using client

public class Client {
    public void run() throws UnknownHostException,IOException {
        int port =8010; // jiss port se connectkarna hai
        InetAddress address =InetAddress.getByName("localhost"); // ill make run in localhost
        Socket socket =new Socket(address,port); //client socket
        PrintWriter toSocket=new PrintWriter(socket.getOutputStream()); // toSocket(yaha pe write karna tha we use printwriter) or toServer and fromsocket (so we use buffer for read)
        BufferedReader fromSocket =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toSocket.println("Hellow from the client");

        String line = fromSocket.readLine(); // jo bhi sockets se aaraha hai
        System.out.println("Response from the socket is :"+line);


        toSocket.close();
        fromSocket.close();
        socket.close();
    }

    public static void main(String[] args) {
        try{
            Client client =new Client();
            client.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
