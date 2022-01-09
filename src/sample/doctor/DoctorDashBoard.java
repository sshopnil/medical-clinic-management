package sample.doctor;


import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;



public class DoctorDashBoard
    {


        public Button info;
        public Button appointments;
        public Button finance;
        public Button returnHome;
        public BorderPane workingSubScene;


        public Text D_name;
        public Text D_age;
        public Text D_dob;
        public Text D_gender;
        public Text D_mobile;
        public Text D_email;
        public Text D_address;

        doctor doc;

        DoctorDashBoard controller;
        void loadData(DoctorDashBoard controller)
        {
            controller.D_name.setText(doc.name);
            controller.D_address.setText(doc.address);
            controller.D_email.setText(doc.email);
            controller.D_gender.setText(doc.gender);
            controller.D_age.setText(String.valueOf(doc.age));
            controller.D_dob.setText(doc.birthdate);
            controller.D_mobile.setText(doc.phone);


        }


        void defultActiveBtn(doctor doc)
        {
            this.doc = doc;
            info.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
            Parent root;

            FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("doctor/infoScene.fxml");
            DoctorDashBoard controller = (DoctorDashBoard) FXMLSceneChanger.controller;

            root = sceneChanger.root;
            workingSubScene.setCenter(root);

            loadData(controller);

        }

        void ChangeScene(Button btn) {
            Parent root;
            if(btn.equals(info))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/infoScene.fxml");
                root = changer.root;
                workingSubScene.setCenter(root);

            }
            else if(btn.equals(finance))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/financeScene.fxml");
                root = changer.root;
                workingSubScene.setCenter(root);
            }
            else if(btn.equals(appointments))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/appointmentsScene.fxml");
                root = changer.root;
                workingSubScene.setCenter(root);
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
            defultActiveBtn(doc);


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
