package sample.doctor;

public class AppointmentInfo
{
    private String time;
    private String date;
    private String limit;
    
    public AppointmentInfo(String time, String date, String limit)
    {
        this.time = time;
        this.date = date;
        this.limit = limit;
    }
    
    public AppointmentInfo()
    {
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
    
    public String getLimit()
    {
        return limit;
    }
    
    public void setLimit(String limit)
    {
        this.limit = limit;
    }
}
