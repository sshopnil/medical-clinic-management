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
            
            //reads doctor names
            if (serverMsg.contains("doclist"))
            {
                serverMsg = "";
                Set set = doctorsList.keySet();
                Iterator itr = set.iterator();
                while (itr.hasNext())
                {
                    String k = (String) itr.next();
                    serverMsg += k + "--" + doctorsList.get(k).deptName + " ";
                }
            }
            
            //read registered doctor departments
            else if(serverMsg.contains("docDepartment"))
            {
                serverMsg = "";
                Set set = doctorsList.keySet();
                Iterator itr = set.iterator();
                while (itr.hasNext())
                {
                    serverMsg += doctorsList.get(itr.next()).deptName + " ";
                }
            }
            
            //reads available time slots given by admins
            else if(serverMsg.contains("timeSlot"))
            {
                try
                {
                    serverMsg = readTimeSlot();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
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
        File file = new File("src/sample/mainServer/DoctorsData/allinfo.txt");
        Scanner scanner = new Scanner(file);
        
        while(scanner.hasNext())
        {
            String[] info = scanner.nextLine().split(";;");
            Information information = new Information(info[0], info[7], clientSocket);
            doctorsList.put(information.name, information);
        }
    }
    private String readTimeSlot() throws FileNotFoundException
    {
        String slt = "";
        Scanner scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/timeSlot.txt"));
        while (scanner.hasNext())
        {
            slt += scanner.nextLine() + ";;";
        }
        scanner.close();
        return slt;
    }
}

