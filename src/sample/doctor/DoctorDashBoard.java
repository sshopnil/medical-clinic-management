package sample.doctor;

import javafx.event.ActionEvent;
import javafx.scene.Camera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;



public class DoctorDashBoard
    {

        public Button info;
        public Button appointments;
        public Button finance;
        public Button returnHome;
        public SubScene subscene;


        //color changing part
        void ChangeColor(Button btn)
        {
            if(btn.equals(info))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                appointments.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
                finance.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
            }
            else if(btn.equals(appointments))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                info.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
                finance.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
            }
            else if(btn.equals(finance))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                info.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
                appointments.setStyle("-fx-background-color: #5b919e; -fx-text-fill: #ffffff");
            }

        }

        //button action perform part
        public void infoAction(ActionEvent actionEvent) {
            ChangeColor(info);//changing button color
        }

        public void appointmentsAction(ActionEvent actionEvent) {
            ChangeColor(appointments);
        }
        public void financeAction(ActionEvent actionEvent) {
            ChangeColor(finance);
        }


        public void backToHomeAction(ActionEvent actionEvent) {
            Parent root = null;
            FXMLSceneChanger changer = FXMLSceneChanger.load("loginOption_page.fxml");

            root = changer.root;
            logInOption controller = (logInOption) changer.controller;

            Scene scene = new Scene(root);
            Main.primaryStage.setScene(scene);
        }


    }
