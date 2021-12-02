package sample.patient;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;

public class PatientLogin
    {
        void gotoDashBoard()
            {
                Parent root = null;

            }
        public void patientEnterActionKeyBoard(KeyEvent keyEvent)
            {
                if (keyEvent.getCode().equals(KeyCode.ENTER))
                    {
                        gotoDashBoard();
                    }
            }

        public void patientEnterAction(MouseEvent mouseEvent)
            {
                gotoDashBoard();
            }

        public void newRegAction(MouseEvent mouseEvent)
            {
            }

        public void backToHomeAction(MouseEvent mouseEvent)
            {
                Parent root = null;
                FXMLSceneChanger changer = FXMLSceneChanger.load("logInOption_page.fxml");

                root = changer.root;
                logInOption LogInOption = (logInOption) changer.controller;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
    }
