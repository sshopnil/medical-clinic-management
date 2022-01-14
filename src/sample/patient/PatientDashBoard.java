package sample.patient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.ReaderThread;
import sample.WriterThread;
import sample.mainServer.NetworkUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.MenuItem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class PatientDashBoard
    {

        public Button currentStatus;
        public Button myInfo;
        public Button appointment;
        public AnchorPane patientDashBoardPane;
        public SubScene patientSubScene;
        public Text Pserial;
        public Text pID;
        public Text Pname;
        public Text pGender;
        public Text pDOB;
        public Text pMob;
        public Text pMarital;
        public Text pReligion;
        public Text pAddress;
        public BorderPane workingSubScene;
        public RadioButton feeGeneral;
        public RadioButton feePrivate;
        public RadioButton feeHomeVisit;
        public CustomMenuItem customDepartment;
        public MenuButton chooseDoc = new MenuButton();
//        public CustomMenuItem customMenuDoc;
        public Menu docMenu = new Menu("select your doctor..");
        private ArrayList<MenuItem> docs = new ArrayList<MenuItem>();
        private String docsFromServer;
        Parent root;
        ThePatient patient;
        
        private int count = 0;
        
        void readDocData() throws IOException
        {
            Socket socket = new Socket("127.0.0.1", 5000);
    
            System.out.println("Patient Client Started--- ");
            System.out.println(socket.getLocalAddress().getHostAddress());
            NetworkUtil nc=new NetworkUtil(socket);
    
            nc.write("doclist");
            docsFromServer = (String) nc.read();
            docsFromServer = docsFromServer.trim();
            System.out.println("Server: " + docsFromServer);
            nc.write("exit");
            socket.close();
            System.out.println("Patient Client closed...");
        }
        void defultActiveBtn(ThePatient thisPatient)
            {
                myInfo.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                

                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/infoScene.fxml");

                root = FXMLSceneChanger.root;
                workingSubScene.setCenter(root);
                patient = thisPatient;
                
                //setting fields with controller
                PatientDashBoard controller = (PatientDashBoard) FXMLSceneChanger.controller;
                controller.Pname.setText(patient.name);
                controller.pID.setText(Integer.toString(patient.age));
                controller.pGender.setText(patient.gender);
                controller.pDOB.setText(patient.DateOfBirth);
                controller.pMob.setText("+88 " + patient.mobile);
                controller.pMarital.setText(patient.maritalStatus);
                controller.pAddress.setText(patient.address);
                controller.pReligion.setText(patient.religion);
                controller.Pserial.setText(patient.patientID);
            }

        void changeColor(Button btn)
            {
                if (btn.equals(myInfo))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        currentStatus.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");

                        defultActiveBtn(patient);
                        
                    }
                else if (btn.equals(appointment))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        myInfo.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        currentStatus.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                    }
                else if (btn.equals(currentStatus))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        myInfo.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                    }
            }

        public void myInfoAction(MouseEvent mouseEvent)
            {
                changeColor(myInfo);
            }

        public void appointmentAction(MouseEvent mouseEvent)
            {
                changeColor(appointment);
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/appointmentScene.fxml");
                root = FXMLSceneChanger.root;
                workingSubScene.setCenter(root);
            }

        public void currentStatusAction(MouseEvent mouseEvent)
            {
                changeColor(currentStatus);
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/currentStatus.fxml");
    
                root = FXMLSceneChanger.root;
                workingSubScene.setCenter(root);
            }

        public void returnHomeAction(MouseEvent mouseEvent)
            {
                Parent root;

                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("logInOption_page.fxml");

                root = sceneChanger.root;
                Scene scene = new Scene(root);

                Main.primaryStage.setScene(scene);
            }
    
        public void editInfoAction(MouseEvent mouseEvent)
        {
            FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/RegScene.fxml");
            
            root = sceneChanger.root;
            Main.primaryStage.setScene(new Scene(root));
        }
    
        public void bookNowAction(MouseEvent mouseEvent)
        {
            String feetype = "";
            if (feeGeneral.isSelected())
            {
                feetype = "general";
            }
            else if(feePrivate.isSelected())
            {
                feetype = "private";
            }
            else if(feeHomeVisit.isSelected())
            {
                feetype = "homevisit";
            }
            else
            {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please provide fee-type");
            }
            
        }
    
        public void doctorMenuBtnAction(MouseEvent mouseEvent)
        {
            //reading doctors data from the server
            try
            {
                readDocData();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            String[] docts = docsFromServer.split(" ");
//            Iterator itr = docs.iterator();
            chooseDoc.getItems().clear();
            int count = 0;
            for(String str: docts)
            {
                chooseDoc.getItems().add(new javafx.scene.control.MenuItem(str));
                chooseDoc.getItems().get(count).setOnAction(e ->{
                    chooseDoc.setText(str);
                });
                System.out.println(str);
                count++;
            }
        }
    }

    