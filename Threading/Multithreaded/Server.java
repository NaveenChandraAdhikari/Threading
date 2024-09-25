package Threading.Multithreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

//in thread pool client is same so we are making only server class
public class Server {



    //functional interface
    public Consumer<Socket> getConsumer(){
//        return new Consumer<Socket>() {
//            @Override
//            public void accept(Socket socket) {
//try{
//                PrintWriter toClient =new PrintWriter(clientSocket.getOutputStream());
//             toClient.println("Hellow form the server");
//             toClient.close();
//             clientSocket.close();
//
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//            }
//        }
        return(clientSocket)->{
            try{
                PrintWriter toClient =new PrintWriter(clientSocket.getOutputStream());
             toClient.println("Hellow form the server");
             toClient.close();
             clientSocket.close();

            }catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {


    int port =8010;

    Server server=new Server();

    try{

        ServerSocket serverSocket =new ServerSocket();
        serverSocket.setSoTimeout(10000);
        System.out.println("Serve is listening on port"+port);

        while (true){
            Socket acceptedSocket=serverSocket.accept(); // naya accepted socket
            // now put this socket  into new thread ,,,and communicate throught the client connected thorught the socket
            Thread thread =new Thread(()->server.getConsumer().accept(acceptedSocket));
            thread.start();


        }
    } catch (IOException ex) {
        throw new RuntimeException(ex);
    }

    }
}
