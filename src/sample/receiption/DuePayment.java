package sample.receiption;

public class DuePayment
{
    String name = "";
    String dept = "";
    String amount = "";
    String paid = "";
    public DuePayment(String name, String dept, String amount, String paid)
    {
        this.name = name;
        this.dept = dept;
        this.amount = amount;
        this.paid = paid;
    }
    
    public DuePayment()
    {
    }
    
    public String getPaid()
    {
        return paid;
    }
    
    public void setPaid(String paid)
    {
        this.paid = paid;
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
