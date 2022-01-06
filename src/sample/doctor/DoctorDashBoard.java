package sample.doctor;


import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
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

        void defultActiveBtn()
        {
            info.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
            Parent root;

            FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("doctor/infoScene.fxml");

            root = sceneChanger.root;
            subscene.setRoot(root);
        }

        void ChangeScene(Button btn) {
            Parent root;
            if(btn.equals(info))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/infoScene.fxml");
                root = changer.root;
                subscene.setRoot(root);
            }
            else if(btn.equals(finance))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/financeScene.fxml");
                root = changer.root;
                subscene.setRoot(root);
            }
            else if(btn.equals(appointments))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/appointmentsScene.fxml");
                root = changer.root;
                subscene.setRoot(root);
            }
        }

        //color changing part
        void ChangeColor(Button btn)
        {
            if(btn.equals(info))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                appointments.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
                finance.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");

            }
            else if(btn.equals(appointments))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                info.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
                finance.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
            }
            else if(btn.equals(finance))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                info.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
                appointments.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
            }

        }

        //button action perform part
        public void infoAction(ActionEvent actionEvent) {
            ChangeColor(info);//changing button color
            ChangeScene(info);
        }


        public void appointmentsAction(ActionEvent actionEvent) {
            ChangeColor(appointments);
            ChangeScene(appointments);

        }
        public void financeAction(ActionEvent actionEvent) {
            ChangeColor(finance);
            ChangeScene(finance);
        }


        public void backToHomeAction(ActionEvent actionEvent) {
            Parent root = null;
            FXMLSceneChanger changer = FXMLSceneChanger.load("logInOption_page.fxml");

            root = changer.root;
            logInOption controller = (logInOption) changer.controller;

            Scene scene = new Scene(root);
            Main.primaryStage.setScene(scene);
        }


    }
