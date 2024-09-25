package Threading.SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port=8010; // for listens
        ServerSocket socket=new ServerSocket(port); //open socket in this port ..ye server ka ip address hoga you can check by ifconfig
        socket.setSoTimeout(10000); //wait for 10s then closes
        while(true){

            try {
                System.out.println("Server is listening on port" + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Conncection accepted from client "+acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream()); // jese cliend se server ko request gaya then making socket then usme output stream is from server side and input stream from client ...printwirter class helps to write in output stream..kyuki inputstream se butes milenge
                // and to read the byted i use bufferreader in input stream ,buffreader ikhata karke bytes ko bhejta hai and output stream bytes me convert karke bhejta hia from text
                BufferedReader fromClient =new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                // buffreader ko stream nahi stream chiye this thats why i  have used inputstreamreader
                toClient.println("Hellow from the server");

                toClient.close();
                fromClient.close();
                acceptedConnection.close();



            }catch(IOException ex){
                ex.printStackTrace();
            }
        }


    }
    public static void main(String[] args) {
        // as run method is not static that is not in  memory so make the instance
        Server server=new Server();

        try{
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
