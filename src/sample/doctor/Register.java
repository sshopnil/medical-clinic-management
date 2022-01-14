package sample.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Register {
    public TextField name;
    public TextField address;
    public TextField phone;
    public TextField email;
    public TextField password;
    public DatePicker birthdate;
    public RadioButton male_btn;
    public RadioButton female_btn;
    public String gender;
    public MenuButton deptStatus;
    public MenuItem ent;
    public MenuItem medical;
    public MenuItem derma;
    LocalDate date;

    String Name;
    String Address;
    String Phone;
    String Email;
    String Password;



    void backToLogin()
    {
        Parent root;
        FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/DoctorLogin.fxml");
        root = changer.root;
        DoctorLogin controller = (DoctorLogin) changer.controller;
        Main.primaryStage.setScene(new Scene(root));
    }


    void writeAllInfo ()
    {
        try{
            FileWriter fr = new FileWriter("src/sample/doctor/server/allinfo.txt",true);
            BufferedWriter br = new BufferedWriter(fr);

            doctor doctorData = new doctor(Name,Address,Phone,Email,Password,date.toString(),gender, deptStatus.getText());

            br.write(doctorData.toString());
            br.newLine();


            System.out.println(doctorData.toString());
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Successful");

            backToLogin();

                                                         //closing writing process.
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void logininfo()
    {
        try{
            FileWriter fr = new FileWriter("src/sample/doctor/server/loginInfo.txt",true);
            BufferedWriter br = new BufferedWriter(fr);
            doctor data = new doctor(Name,Address,Phone,Email,Password,date.toString(),gender, deptStatus.getText());
            String str = data.getName()+";;"+data.getEmail()+";;"+data.password;

            br.write(str);
            br.newLine();
            System.out.println(str);

            br.close();
            fr.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }




    public void submitAction(ActionEvent actionEvent) {


        date = birthdate.getValue();
        if(male_btn.isSelected())
            gender = "Male";
        else if(female_btn.isSelected())
        {
            gender = "Female";
        }

        Name = name.getText();
        Address = address.getText();
        Phone = phone.getText();
        Email = email.getText();
        Password = password.getText();


        try {
            writeAllInfo();             //wring data into allinfo.txt file..

            logininfo();                 //writing data into logininfo.txt file...

            System.out.println();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Do not blank any field");
        }


    }


    public void backToLogin(ActionEvent actionEvent) {
        backToLogin();
    }
    
    public void depAction(MouseEvent mouseEvent)
    {
        ent.setOnAction(e ->{
            deptStatus.setText(ent.getText());
        });
        medical.setOnAction(e ->{
            deptStatus.setText(medical.getText());
        });
        derma.setOnAction(e ->{
            deptStatus.setText(derma.getText());
        });
    }
}
