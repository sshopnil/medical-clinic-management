package sample.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;

import javax.print.Doc;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DoctorLogin {

    doctor doctor;

    public Button newReg;

    void goto_dashBord()
    {
        Parent root = null;
        FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/DoctorDashBoard.fxml");

        root = changer.root;
        DoctorDashBoard controller = (DoctorDashBoard) changer.controller;
        controller.defultActiveBtn(doctor);
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);

    }

    @FXML
    private Button backToHome;

    @FXML
    private Button doctorEnter;

    @FXML
    private TextField doctorEmail;

    @FXML
    private TextField doctorPassword;
    

    @FXML
    void backToHomeAction(MouseEvent event) {
        Parent root = null;
        FXMLSceneChanger changer = FXMLSceneChanger.load("logInOption_page.fxml");

        root = changer.root;
        logInOption controller = (logInOption) changer.controller;

        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);

    }

    @FXML
    void doctorEnterAction(ActionEvent event) {
        boolean valid = false;

        ArrayList<doctor> loginList = getInformation();
        for(doctor doctor: loginList)
        {
            if(doctor.email.equals(doctorEmail.getText()) && doctor.password.equals(doctorPassword.getText()))
            {
                this.doctor = doctor;
                valid = true;
                System.out.println(doctor.name+";;"+doctor.email+";;"+doctor.password);
                break;
            }

        }

        if (valid)
        {
            goto_dashBord();
            
            try
            {
                FileWriter fr = new FileWriter(new File("src/sample/mainServer/DoctorsData/lastLoggedIn.txt"));
                BufferedWriter br = new BufferedWriter(fr);
                
                br.write(doctor.name+";;"+doctor.dept);
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please enter correct data");

    }

    public ArrayList<doctor> getInformation()
    {
        ArrayList<doctor> doctorList = new ArrayList<doctor>();
        File file = new File("src/sample/mainServer/DoctorsData/allinfo.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] loginData = str.split(";;");
                doctorList.add(new doctor(loginData[0],loginData[1],loginData[2],loginData[3],loginData[4],loginData[5],loginData[6], loginData[7]));
            }//doctor(String name, String address, String phone, String email, String password, String birthdate, String gender)
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return doctorList;

    }


    @FXML
    void newRegAction(ActionEvent event) {
        Parent root;
        FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/Register.fxml");
        root = changer.root;
        Register controller = (Register) changer.controller;
        Main.primaryStage.setScene(new Scene(root));
    }



}

