package sample.patient;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientLogin
    {
        public Button newReg;
        public RadioButton femalebtn;
        public RadioButton maleBtn;
        public Button patientEnter;
        @FXML
        private TextField patientName;
        @FXML
        private TextField patientID;
        public String name;
        public int age;
        public String gender;
        public String dob;
        
        File file = new File("src/sample/patient/patientData/newUsers.txt");
        ThePatient currentPatient;
        
        void gotoDashBoard()
            {
                Parent root = null;
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientDashBoard.fxml");

                root = sceneChanger.root;
                PatientDashBoard pd = (PatientDashBoard) sceneChanger.controller;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);

                pd.defultActiveBtn(currentPatient);
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
                    patients.add(new ThePatient(allInfo[0], allInfo[1], allInfo[2]));
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
                    age = Integer.parseInt(patientID.getText().trim());
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Incorrect Information");
                }
                gender = "";
                if (maleBtn.isSelected())
                {
                    gender = "Male";
                }
                else
                {
                    gender = "Female";
                }
                dob = "";
                ArrayList<ThePatient> patients = getPatientInfo();
                boolean valid = false;
                for (ThePatient patient: patients)
                {
                    if (patient.name.trim().equals(name) && patient.gender.equals(gender))
                    {
                        valid = true;
                        dob = patient.DateOfBirth;
                        break;
                    }
                }
                if (valid)
                {
                    currentPatient = new ThePatient(name, gender, dob);
                    gotoDashBoard();
                    PatientDashBoard controller = (PatientDashBoard) FXMLSceneChanger.controller;
                    controller.Pname.setText(name);
                    controller.pID.setText(Integer.toString(age));
                    controller.pGender.setText(gender);
                    controller.pDOB.setText(dob);
                }
                else
                {
                    currentPatient = new ThePatient("null", "null", "null");
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
