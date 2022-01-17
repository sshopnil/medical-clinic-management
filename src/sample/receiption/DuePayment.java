package sample.receiption;

public class DuePayment
{
    String name;
    String dept;
    String amount;
    
    public DuePayment(String name, String dept, String amount)
    {
        this.name = name;
        this.dept = dept;
        this.amount = amount;
    }
    
    public DuePayment()
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
    
    public String getAmount()
    {
        return amount;
    }
    
    public void setAmount(String amount)
    {
        this.amount = amount;
    }
}
