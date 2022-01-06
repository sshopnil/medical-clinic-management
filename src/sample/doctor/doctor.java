package sample.doctor;


public class doctor {

    String  name;
    String  address;
    String  phone;
    String  email;
    String  password;
    String birthdate;
    String gender;

    doctor()
    {

    }


    doctor(String name, String address, String phone, String email, String password, String birthdate, String gender)
    {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
    }
    public String toString()
    {
        return (name+";;"+ gender +";;"+ email +";;"+ password +";;"+ phone +";;"+ address +";;"+ birthdate);

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




}
