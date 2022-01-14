package sample.receiption;

public class timeSlot
{
    private String time;
    private String slot;
    timeSlot(String hour, String minute, String slot)
    {
        this.time = hour + ":" + minute;
        this.slot = slot;
    }
    public String getTime()
    {
        return time;
    }
    
    public void setTime(String time)
    {
        this.time = time;
    }
    
    public String getSlot()
    {
        return slot;
    }
    
    public void setSlot(String slot)
    {
        this.slot = slot;
    }
}
