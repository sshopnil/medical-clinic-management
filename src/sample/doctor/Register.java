package sample.doctor;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;

public class Register {
    public TextField name;
    public TextField address;
    public DatePicker age;
    public TextField phone;
    public TextField email;
    public TextField password;

    public void submitAction(ActionEvent actionEvent) {
        Parent root;
        FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/DoctorDashBoard.fxml");
        root = changer.root;
        Main.primaryStage.setScene(new Scene(root));
    }

    public void backToHome(ActionEvent actionEvent) {

        Parent root;
        FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/DoctorLogin.fxml");
        root = changer.root;
        DoctorLogin controller = (DoctorLogin) changer.controller;
        Main.primaryStage.setScene(new Scene(root));
    }
}
