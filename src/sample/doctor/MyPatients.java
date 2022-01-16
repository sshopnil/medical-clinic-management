package sample.doctor;

public class MyPatients
{
    String id;
    String name;
    String sub;
    String des;
    String time;
    String date;
    String type;
    public MyPatients(String id, String name, String sub, String des, String time, String date, String type)
    {
        this.id = id;
        this.name = name;
        this.sub = sub;
        this.des = des;
        this.time = time;
        this.date = date;
        this.type = type;
    }
    
    public MyPatients()
    {
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSub()
    {
        return sub;
    }
    
    public void setSub(String sub)
    {
        this.sub = sub;
    }
    
    public String getDes()
    {
        return des;
    }
    
    public void setDes(String des)
    {
        this.des = des;
    }
    
    public String getTime()
    {
        return time;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
}
