package sample.patient;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;

public class regController
    {
        public void submitBtnClicked(MouseEvent mouseEvent)
            {
                Parent root;
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientDashBoard.fxml");

                root = sceneChanger.root;
                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }

        public void backBtnAction(MouseEvent mouseEvent)
            {
                Parent root;
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientLogin.fxml");

                root = sceneChanger.root;
                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
    }
