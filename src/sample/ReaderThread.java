package sample;

import sample.mainServer.NetworkUtil;

public class ReaderThread implements Runnable
{
    public NetworkUtil netConnection;
    String msg="";
    public static boolean check = false;
    public ReaderThread(NetworkUtil nc){
        netConnection=nc;
    }
    public void setMessage(String msg){
        this.msg=msg;
    }
    public void StartThread(){
        new Thread(this).start();
        //this.start();
    }
    public String getMessage() {
        if(msg!=null)
            return msg;
        else return "";
    }
    
    
    @Override
    public void run() {
        while(true){
            String msg;
            Object obj=netConnection.read();
            msg = (String) obj;
            //Data dataObj=(Data)obj;
            if(!getMessage().equals(msg));
            {
                setMessage(msg);
                System.out.println("Received : "+msg);
                check = false;
            }
//            if(check==true){
//            setMessage(msg);
//            System.out.println("Received : "+msg);
//            check = false;
//            }
        
        }
    }
}
