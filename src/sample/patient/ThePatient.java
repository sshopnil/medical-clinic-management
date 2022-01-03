package sample.patient;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class ThePatient implements Serializable
{
    public String name;
    public String gender;
    public String DateOfBirth;
    public int age;
    public int id;
    public String mobile;
    public String serialNo;
    
    ThePatient(String name, String gender, String DateOfBirth, String mobile)
    {
        this.name = name;
        this.gender = gender;
        this.DateOfBirth = DateOfBirth;
        generateAge();
        this.mobile = mobile;
    }
    public void generateAge()
    {
        String[] date = DateOfBirth.split("-");
        LocalDate today = LocalDate.now();
        
        LocalDate birthdate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        
        age = Period.between(birthdate, today).getYears();
    }
}
