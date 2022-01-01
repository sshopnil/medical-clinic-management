package sample.patient;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;

import java.awt.*;

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
        
        void defultActiveBtn()
            {
                myInfo.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                Parent root;

                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/infoScene.fxml");

                root = sceneChanger.root;
                patientSubScene.setRoot(root);
            }

        void changeColor(Button btn)
            {
                if (btn.equals(myInfo))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        currentStatus.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");

                        defultActiveBtn();
                    }
                else if (btn.equals(appointment))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        myInfo.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        currentStatus.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");

                        Parent root;

                        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/appointmentScene.fxml");

                        root = sceneChanger.root;
                        patientSubScene.setRoot(root);
                    }
                else if (btn.equals(currentStatus))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        myInfo.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");

                        Parent root;

                        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/currentStatus.fxml");

                        root = sceneChanger.root;
                        patientSubScene.setRoot(root);
                    }
            }

        public void myInfoAction(MouseEvent mouseEvent)
            {
                changeColor(myInfo);
            }

        public void appointmentAction(MouseEvent mouseEvent)
            {
                changeColor(appointment);
            }

        public void currentStatusAction(MouseEvent mouseEvent)
            {
                changeColor(currentStatus);
            }

        public void returnHomeAction(MouseEvent mouseEvent)
            {
                Parent root;

                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("logInOption_page.fxml");

                root = sceneChanger.root;
                Scene scene = new Scene(root);

                Main.primaryStage.setScene(scene);
            }
    }
