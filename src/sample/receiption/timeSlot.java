package sample.receiption;

public class timeSlot
{
    private String time;
    private String slot;
    timeSlot(String time, String slot)
    {
        this.time = time;
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
