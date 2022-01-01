package sample.patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientLogin
    {
        public Button newReg;
        @FXML
        private TextField patientName;
        @FXML
        private TextField patientID;
        
        File file = new File("src/sample/patient/patientData/newUsers.txt");
    
        void gotoDashBoard()
            {
                Parent root = null;
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientDashBoard.fxml");

                root = sceneChanger.root;
                PatientDashBoard pd = (PatientDashBoard) sceneChanger.controller;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);

                pd.deafultActiveBtn();
            }
        public void patientEnterActionKeyBoard(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    {
                        gotoDashBoard();
                    }
            }
        private ArrayList<ThePatient> getPatientInfo()
        {
            ArrayList<ThePatient> patients;
            try
            {
                Scanner scanner = new Scanner(file);
                
                while (scanner.hasNext())
                {
                
                }
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        public void patientEnterAction(MouseEvent mouseEvent)
            {
            
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
                FXMLSceneChanger changer = FXMLSceneChanger.load("logInOption_page.fxml");

                root = changer.root;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
    }
