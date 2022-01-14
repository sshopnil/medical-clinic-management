package sample.mainServer;

import javafx.stage.Stage;

import java.time.LocalDate;

public class PatientInfo
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
    
    PatientInfo(String name, String gender, String DateOfBirth, String mobile, String address, String relWithPatient, String maritalStatus, String religion, int age, String patientID)
    {
        this.name = name;
        this.gender = gender;
        this.DateOfBirth = DateOfBirth;
        this.age = age;
        this.patientID = patientID;
        this.mobile = mobile;
        this.address = address;
        this.relWithPatient = relWithPatient;
        this.maritalStatus = maritalStatus;
        this.religion = religion;
    }
}
