package sample.receiption;

public class AppointedPatient
{
    String id;
    String name;
    String time;
    String date;
    String doctor;
    String subject;
    String msg;
    String payment;
    
    public String getSubject()
    {
        return subject;
    }
    
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public String getPayment()
    {
        return payment;
    }
    
    public void setPayment(String payment)
    {
        this.payment = payment;
    }
    
    AppointedPatient()
    {
    
    }
    
    AppointedPatient(String id, String name, String time, String date, String doctor)
    {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.doctor = doctor;
    }
    
    AppointedPatient(String id, String name, String time, String date, String doctor, String subject, String msg, String payment)
    {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.doctor = doctor;
        this.msg = msg;
        this.subject =subject;
        this.payment = payment;
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
    
    public String getDoctor()
    {
        return doctor;
    }
    
    public void setDoctor(String doctor)
    {
        this.doctor = doctor;
    }
}
