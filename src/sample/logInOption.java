package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.patient.PatientLogin;

import java.io.IOException;

public class logInOption
    {

        public ImageView patient_login;
        public ImageView receiptionist_login;

        public void receiptionist_clicked(MouseEvent mouseEvent)
            {
                try
                    {
                        Stage recLogin = (Stage) receiptionist_login.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("receiption/adminLogin.fxml"));

                        recLogin.setScene(new Scene(root));
                    }
                    catch (Exception e)
                        {
                            System.out.println("Error in receiption method");
                        }
            }

        public void patient_clicked(MouseEvent mouseEvent)
            {
                Parent root = null;
                FXMLSceneChanger changer = FXMLSceneChanger.load("patient/patientLogin.fxml");

                root = changer.root;
                PatientLogin patientLogin = (PatientLogin)changer.controller;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }

        public void doctor_clicked(MouseEvent mouseEvent)
            {
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("doctor/DoctorLogin.fxml");
                Parent root = sceneChanger.root;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
    }
