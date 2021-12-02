package sample.patient;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import sample.FXMLSceneChanger;
import sample.Main;

import java.awt.*;

public class PatientDashBoard
    {

        public Button currentStatus;
        public Button myInfo;
        public Button appointment;

        void changeColor(Button btn)
            {
                if (btn.equals(myInfo))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        appointment.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
                        currentStatus.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
                    }
                else if (btn.equals(appointment))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        myInfo.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
                        currentStatus.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
                    }
                else if (btn.equals(currentStatus))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        appointment.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
                        myInfo.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
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
