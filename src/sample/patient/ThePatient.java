package sample.patient;

import javafx.stage.Stage;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class ThePatient implements Serializable
{
    public static Stage patientStage;
    public String name= "";
    public String gender= "";
    public String DateOfBirth= "";
    public int age;
    public String mobile= "";
    public String patientID = "";
    public String maritalStatus= "";
    public String religion= "";
    public String address= "";
    public String relWithPatient= "";
    LocalDate birthdate;
    
    public static Stage getPatientStage()
    {
        return patientStage;
    }
    
    public static void setPatientStage(Stage patientStage)
    {
        ThePatient.patientStage = patientStage;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    public String getDateOfBirth()
    {
        return DateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth)
    {
        DateOfBirth = dateOfBirth;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getPatientID()
    {
        return patientID;
    }
    
    public void setPatientID(String patientID)
    {
        this.patientID = patientID;
    }
    
    public String getMaritalStatus()
    {
        return maritalStatus;
    }
    
    public void setMaritalStatus(String maritalStatus)
    {
        this.maritalStatus = maritalStatus;
    }
    
    public String getReligion()
    {
        return religion;
    }
    
    public void setReligion(String religion)
    {
        this.religion = religion;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getRelWithPatient()
    {
        return relWithPatient;
    }
    
    public void setRelWithPatient(String relWithPatient)
    {
        this.relWithPatient = relWithPatient;
    }
    
    public LocalDate getBirthdate()
    {
        return birthdate;
    }
    
    public void setBirthdate(LocalDate birthdate)
    {
        this.birthdate = birthdate;
    }
    
    ThePatient()
    {
    
    }
    public ThePatient(String patientID, String name, String gender, String DateOfBirth, String mobile, String address
            , String relWithPatient, String maritalStatus, String religion)
    {
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.DateOfBirth = DateOfBirth;
        generateAge();
        this.mobile = mobile;
        this.address = address;
        this.relWithPatient = relWithPatient;
        this.maritalStatus = maritalStatus;
        this.religion = religion;
    }
    public void generateAge()
    {
        String[] date = DateOfBirth.split("-");
        LocalDate today = LocalDate.now();
        
        birthdate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        
        age = Period.between(birthdate, today).getYears();
    }
}
