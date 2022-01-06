package sample.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;

public class DoctorLogin {
    public Button newReg;

    void goto_dashBord()
    {
        Parent root = null;
        FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/DoctorDashBoard.fxml");

        root = changer.root;
        DoctorDashBoard controller = (DoctorDashBoard) changer.controller;
        controller.defultActiveBtn();
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);

    }

    @FXML
    private Button backToHome;

    @FXML
    private Button doctorEnter;

    @FXML
    private TextField doctorID;

    @FXML
    private TextField doctorPassword;
    

    @FXML
    void backToHomeAction(MouseEvent event) {
        Parent root = null;
        FXMLSceneChanger changer = FXMLSceneChanger.load("loginOption_page.fxml");

        root = changer.root;
        logInOption controller = (logInOption) changer.controller;

        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);

    }

    @FXML
    void doctorEnterAction(ActionEvent event) {
        goto_dashBord();
    }


    @FXML
    void newRegAction(ActionEvent event) {
        Parent root;
        FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/Register.fxml");
        root = changer.root;
        Register controller = (Register) changer.controller;
        Main.primaryStage.setScene(new Scene(root));
    }

}
