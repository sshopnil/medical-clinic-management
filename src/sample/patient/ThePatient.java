package sample.patient;

import java.io.Serializable;
import java.time.LocalDate;

public class ThePatient implements Serializable
{
    public String name;
    public String gender;
    public String DateOfBirth;
    public int age;
    ThePatient(String name, String gender, String DateOfBirth)
    {
        this.name = name;
        this.gender = gender;
        this.DateOfBirth = DateOfBirth;
        generateAge();
    }
    public void generateAge()
    {
        String[] bDate = DateOfBirth.split(";;");
        
        int birthYear = Integer.parseInt(bDate[0]);
        String currentDate = java.time.LocalDate.now().toString();
        
        String[] today = currentDate.split("-");
        int currentYear = Integer.parseInt(today[0]);
        
        age = currentYear-birthYear;
    }
}
