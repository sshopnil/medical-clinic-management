package sample.mainServer;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ClientThread implements Runnable
{
    NetworkUtil clientSocket;
    Thread t;
    HashMap<String, Information> doctorsList;
    ClientThread(NetworkUtil clientSocket, HashMap<String, Information> doctorsList){
        this.doctorsList = doctorsList;
        this.clientSocket = clientSocket;
        t= new Thread(this);
        t.start();
    }
    
    
    @Override
    public void run() {
    
        //            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
//            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        try
        {
            readDocData();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        while (true) {
            //read from client...
            Object cMsg = clientSocket.read();
            if(cMsg==null)
                break;
            System.out.println("From Client: " + (String) cMsg);
            
            String serverMsg = (String) cMsg;
            if (serverMsg.contains("doclist"))
            {
                serverMsg = "";
                Set set = doctorsList.keySet();
                Iterator itr = set.iterator();
                while (itr.hasNext())
                {
                    serverMsg += itr.next() + " ";
                }
            }
            else if (serverMsg.contains("exit"))
            {
                break;
            }
            
            //send to client..
            clientSocket.write(serverMsg);
        }
    
        try {
            clientSocket.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readDocData() throws FileNotFoundException
    {
        File file = new File("src/sample/doctor/server/allinfo.txt");
        Scanner scanner = new Scanner(file);
        
        while(scanner.hasNext())
        {
            String[] info = scanner.nextLine().split(";;");
            Information information = new Information(info[0], info[7], clientSocket);
            doctorsList.put(information.name, information);
        }
    }
    
}

