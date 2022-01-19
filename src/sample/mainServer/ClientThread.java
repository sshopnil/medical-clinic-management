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
            
            String[] checkExtraInfo = serverMsg.split(";;");
            //reads doctor names
            if (serverMsg.contains("doclist"))
            {
                serverMsg = getDoctorInfo();
            }
            
            //read registered doctor departments
            else if(serverMsg.contains("docDepartment"))
            {
                serverMsg = getDoctorInfo();
            }
            
            //reads available time slots given by admins
            else if(checkExtraInfo[0].contains("timeSlot"))
            {
                String docName = checkExtraInfo[1];
                String date = checkExtraInfo[2];
                if (docName.contains("Choose"))
                {
                    serverMsg = "Please select your doctor first";
                }
                else if(date.contains("Choose"))
                {
                    serverMsg = "Select date first";
                }
                else
                {
                    serverMsg = readTimeSlot(docName, date);
                }
            }
            
            //reads available dates given by doctor
            else if(checkExtraInfo[0].contains("dateSlot"))
            {
                String docName = checkExtraInfo[1];
                if (docName.equals("Choose"))
                {
                    serverMsg = "Please select your doctor first";
                }
                else
                {
                    serverMsg = readDateSlot(docName);
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
    
    //===============================READING FROM FILES START=========================================
    
    
    private void readDocData() throws FileNotFoundException
    {
        File file = new File("src/sample/mainServer/DoctorsData/allinfo.txt");
        Scanner scanner = new Scanner(file);
        
        while(scanner.hasNext())
        {
            String[] info = scanner.nextLine().split(";;");
            Information information = new Information(info[0], info[7], clientSocket);
            System.out.println(information.name);
            doctorsList.put(information.name, information);
        }
    }
    private String readTimeSlot(String docName, String date)
    {
        String slt = "";
        String[] docAndDept = docName.split("--");
        String path = "src/sample/mainServer/DoctorsData/DoctorsAppointmentInfo/" + docAndDept[0] + "_" + docAndDept[1] + ".txt";
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File(path));
            while (scanner.hasNext())
            {
                String[] tslot = scanner.nextLine().split(";;");
                if (tslot[3].equals(date))
                {
                    slt += tslot[2] + ";;";
                }
            }
            scanner.close();
        }
        catch (Exception e)
        {
            slt = "No Slots available";
        }
        
        return slt;
    }
    private String readDateSlot(String docName)
    {
        String slt = "";
        String[] docAndDept = docName.split("--");
        String path = "src/sample/mainServer/DoctorsData/DoctorsAppointmentInfo/" + docAndDept[0] + "_" + docAndDept[1] + ".txt";
        try
        {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext())
            {
                String[] tslot = scanner.nextLine().split(";;");
                slt += tslot[3] + ";;";
            }
            scanner.close();
        }
        catch (Exception e)
        {
            slt = "No Slots available";
        }
        return slt;
    }
    //===============================READING FROM FILES ENDS=========================================
    private String getDoctorInfo()
    {
        String info = "";
        Set set = doctorsList.keySet();
        Iterator itr = set.iterator();
        while (itr.hasNext())
        {
            String k = (String) itr.next();
            info += k + "--" + doctorsList.get(k).deptName + ";;";
            System.out.println(info);
        }
        return info;
    }
}

