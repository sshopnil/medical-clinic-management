package sample.doctor;


import java.time.LocalDate;
import java.time.Period;

public class doctor {

    String  name;
    String  address;
    String  phone;
    String  email;
    String  password;
    String birthdate;
    String gender;
    int age;
    LocalDate bDate;
    String dept;

    doctor()
    {

    }


    doctor(String name, String address, String phone, String email, String password, String birthdate, String gender, String dept)
    {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.dept = dept;
    }


    String getName()
    {

        return  name;
    }

    String getAddress()
    {
        return address;

    }
    String getPhone()
    {
        return phone;

    }
    String getEmail()
    {


        return email;
    }
    String getPassword()
    {

        return password;
    }
    String getBirthdate()
    {

        return birthdate;
    }
    String getGender()
    {

        return gender;
    }

    public void generateAge()
    {
        String[] date = birthdate.split("-");
        LocalDate today = LocalDate.now();

        bDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));

        age = Period.between(bDate,today).getYears();
    }

    public String toString()
    {
        return (name+";;"+ address +";;"+ phone +";;"+ email +";;"+ password +";;"+ birthdate +";;"+ gender + ";;" + dept);


    }

}