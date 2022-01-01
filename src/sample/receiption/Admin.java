package sample.receiption;

public class Admin {
    String f_name;
    String l_name;
    String aemail;
    String pass;
    String date;
    String gender;

    public Admin(String f_name, String l_name, String aemail, String pass, String date, String gender) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.aemail = aemail;
        this.pass = pass;
        this.date = date;
        this.gender = gender;
    }

    public Admin() {

    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}