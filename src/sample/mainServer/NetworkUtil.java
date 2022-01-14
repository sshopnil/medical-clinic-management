package sample.mainServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkUtil
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket clientSock;
    
    public NetworkUtil(Socket clientSock) throws IOException
    {
        this.clientSock = clientSock;
        oos = new ObjectOutputStream(clientSock.getOutputStream());
        ois = new ObjectInputStream(clientSock.getInputStream());
    }
    
    public NetworkUtil(String ip, int port) throws IOException
    {
        clientSock = new Socket(ip, port);
        oos = new ObjectOutputStream(clientSock.getOutputStream());
        ois = new ObjectInputStream(clientSock.getInputStream());
    }
    
    public void write(Object obj)
    {
        try
        {
            oos.writeObject(obj);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public Object read()
    {
        Object obj = null;
        try
        {
            obj = ois.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return obj;
    }
    
    public Socket getSocket()
    {
        return clientSock;
    }
}
