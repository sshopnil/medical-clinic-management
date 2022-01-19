package sample.receiption;

public class regDocs
{
    String name;
    String dept;
    String mail;
    String phone;
    
    public regDocs(String name, String dept, String mail, String phone)
    {
        this.name = name;
        this.dept = dept;
        this.mail = mail;
        this.phone = phone;
    }
    
    public regDocs()
    {
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getDept()
    {
        return dept;
    }
    
    public void setDept(String dept)
    {
        this.dept = dept;
    }
    
    public String getMail()
    {
        return mail;
    }
    
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
