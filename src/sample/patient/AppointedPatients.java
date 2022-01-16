package sample.patient;

public class AppointedPatients
{
    private String pName;
    private String pID;
    private String pDate;
    private String pTime;
    private String pSubject;
    private String pDescription;
    
    public AppointedPatients(String pName, String pID, String pDate, String pTime, String pSubject, String pDescription)
    {
        this.pName = pName;
        this.pID = pID;
        this.pDate = pDate;
        this.pTime = pTime;
        this.pSubject = pSubject;
        this.pDescription = pDescription;
    }
    
    public AppointedPatients()
    {
    }
    
    public String getpName()
    {
        return pName;
    }
    
    public void setpName(String pName)
    {
        this.pName = pName;
    }
    
    public String getpID()
    {
        return pID;
    }
    
    public void setpID(String pID)
    {
        this.pID = pID;
    }
    
    public String getpDate()
    {
        return pDate;
    }
    
    public void setpDate(String pDate)
    {
        this.pDate = pDate;
    }
    
    public String getpTime()
    {
        return pTime;
    }
    
    public void setpTime(String pTime)
    {
        this.pTime = pTime;
    }
    
    public String getpSubject()
    {
        return pSubject;
    }
    
    public void setpSubject(String pSubject)
    {
        this.pSubject = pSubject;
    }
    
    public String getpDescription()
    {
        return pDescription;
    }
    
    public void setpDescription(String pDescription)
    {
        this.pDescription = pDescription;
    }
}
