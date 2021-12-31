package sample.patient;

import java.io.Serializable;

public class ThePatient implements Serializable
{
    String name;
    String gender;
    String DateOfBirth;
    ThePatient(String name, String gender, String DateOfBirth)
    {
        this.name = name;
        this.gender = gender;
        this.DateOfBirth = DateOfBirth;
    }
}
