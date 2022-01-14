package sample.patient;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.ReaderThread;
import sample.mainServer.NetworkUtil;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientLogin
    {
        public Button newReg;
        public RadioButton femalebtn;
        public RadioButton maleBtn;
        public Button patientEnter;
        public DatePicker loginDOB;
        @FXML
        private TextField patientName;
        @FXML
        private TextField patientID;
        public String name;
        public int age;
        public String gender;
        public String dob;
        public String mobileNo;
        public String maritalStatus;
        public String religion;
        public String address;
        public String relWithPatient;
        private LocalDate birthdate;
        public String pID;
        File file = new File("src/sample/patient/patientData/newUsers.txt");
        ThePatient currentPatient;
        
        void gotoDashBoard()
            {
                Parent root = null;
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientDashBoard.fxml");

                root = sceneChanger.root;
                PatientDashBoard pd = (PatientDashBoard) sceneChanger.controller;

                Scene scene = new Scene(root);
                
                ThePatient.patientStage = (Stage) patientEnter.getScene().getWindow();
                ThePatient.patientStage.setScene(scene);
                ThePatient.patientStage.setTitle("Patient DashBoard");
                pd.defultActiveBtn(currentPatient);
                pd.patient = currentPatient;
    
                try
                {
                    FileWriter fr = new FileWriter("src/sample/mainServer/AppointmentData/currentLoggedIn.txt");
                    BufferedWriter br = new BufferedWriter(fr);
                    br.write(currentPatient.name+";;"+currentPatient.patientID);
                    
                    br.close();
                    fr.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                ThePatient.patientStage.show();
            }
        public void patientEnterActionKeyBoard(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    {
                        gotoDashBoard();
                    }
            }
            
            //custom method for reading from patient data
        public ArrayList<ThePatient> getPatientInfo()
        {
            ArrayList<ThePatient> patients = new ArrayList<ThePatient>();
            try
            {
                Scanner scanner = new Scanner(file);
                String patient;
                while (scanner.hasNext())
                {
                    patient = scanner.nextLine();
                    String[] allInfo = patient.split(";;");
                    patients.add(new ThePatient(allInfo[0], allInfo[1], allInfo[2], allInfo[3], allInfo[4], allInfo[5], allInfo[6], allInfo[7], allInfo[8]));
                }
                scanner.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            return patients;
        }
        
        //actions after clicking login button
        public void patientEnterAction(MouseEvent mouseEvent)
            {
                name = "";
                age = 0;
                try
                {
                    name = patientName.getText().trim();
                    birthdate = loginDOB.getValue();
                    dob = birthdate.toString();
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Select Correct date with the picker");
                }
                
                ArrayList<ThePatient> patients = getPatientInfo();
                boolean valid = false;
                for (ThePatient patient: patients)
                {
                    if ((patient.name.trim().equals(name) || patient.patientID.equals(name)) && patient.DateOfBirth.equals(dob))
                    {
                        valid = true;
                        name = patient.name;
                        maritalStatus = patient.maritalStatus;
                        religion = patient.religion;
                        address = patient.address;
                        relWithPatient = patient.relWithPatient;
                        mobileNo = patient.mobile;
                        pID = patient.patientID;
                        gender = patient.gender;
                        age = patient.age;
                        break;
                    }
                }
                if (valid)
                {
                    currentPatient = new ThePatient(pID,name, gender, dob, mobileNo, address, relWithPatient, maritalStatus, religion);
                    gotoDashBoard();
                    PatientDashBoard controller = (PatientDashBoard) FXMLSceneChanger.controller;
                    controller.Pname.setText(name);
                    controller.pID.setText(Integer.toString(age));
                    controller.pGender.setText(gender);
                    controller.pDOB.setText(dob);
                    controller.pMarital.setText(maritalStatus);
                    controller.pAddress.setText(address);
                    controller.pReligion.setText(religion);
                    controller.Pserial.setText(pID);
                }
                else
                {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Incorrect Information");
                }
            }

        @FXML
        public void newRegAction(MouseEvent mouseEvent)
            {
                Parent root = null;

                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/RegScene.fxml");

                root = sceneChanger.root;
                Main.primaryStage.setScene(new Scene(root));
            }

        public void backToHomeAction(MouseEvent mouseEvent)
            {
                Parent root = null;
                FXMLSceneChanger changer = FXMLSceneChanger.load("patient/newAndOld.fxml");

                root = changer.root;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
    }