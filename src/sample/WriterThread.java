package sample;

import sample.mainServer.Data;
import sample.mainServer.NetworkUtil;

import java.util.Scanner;

public class WriterThread implements Runnable
{
    public NetworkUtil netConnection;
    
    public WriterThread(NetworkUtil nc){
        netConnection=nc;
    }

//    public void WriteMessage(String msg){
//
//    }
    
    @Override
    public void run() {
        
        Data data=new Data();
        
        while(true){
            
            Scanner in=new Scanner(System.in);
            
            data.message=in.nextLine();
            try{
                netConnection.write(data.clone());
            }
            catch(Exception ex){
                System.out.println("sending failed");
            }
        }
    }
}
