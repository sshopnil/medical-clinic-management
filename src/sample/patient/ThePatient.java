package sample.patient;

import javafx.stage.Stage;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class ThePatient implements Serializable
{
    public static Stage patientStage;
    public String name;
    public String gender;
    public String DateOfBirth;
    public int age;
    public String mobile;
    public String patientID;
    public String maritalStatus;
    public String religion;
    public String address;
    public String relWithPatient;
    LocalDate birthdate;
    
    ThePatient(String patientID, String name, String gender, String DateOfBirth, String mobile, String address, String relWithPatient, String maritalStatus, String religion)
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
