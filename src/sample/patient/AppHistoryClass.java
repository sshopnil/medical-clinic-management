package sample.patient;

public class AppHistoryClass
{
    String time;
    String date;
    String doctorname;
    String depname;
    
    public AppHistoryClass(String time, String date, String doctorname, String depname)
    {
        this.time = time;
        this.date = date;
        this.doctorname = doctorname;
        this.depname = depname;
    }
    
    public AppHistoryClass()
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
    
    public String getDoctorname()
    {
        return doctorname;
    }
    
    public void setDoctorname(String doctorname)
    {
        this.doctorname = doctorname;
    }
    
    public String getDepname()
    {
        return depname;
    }
    
    public void setDepname(String depname)
    {
        this.depname = depname;
    }
}
