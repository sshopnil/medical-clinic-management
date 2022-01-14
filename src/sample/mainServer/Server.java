package sample.mainServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server
{
    private ServerSocket serverSocket;
    public Server() throws IOException
    {
        serverSocket = new ServerSocket(5000);
        System.out.println("server started successfully...");
        HashMap<String, Information> doctorstList = new HashMap<String, Information>();
        HashMap<String, Information> patientList = new HashMap<String, Information>();
        while (true)
        {
            Socket clientSock = serverSocket.accept();
            
            NetworkUtil nu = new NetworkUtil(clientSock);
            new ClientThread(nu, doctorstList);
        }
    }
    
    public static void main(String[] args)
    {
        try
        {
            Server server = new Server();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
