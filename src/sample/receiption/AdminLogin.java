package sample.receiption;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;

public class AdminLogin {

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_pass;

    @FXML
    private Button btn_login;

    @FXML
    private Button backToHome;

    void gotoDashBoard()
    {
        Parent root = null;
        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/receiptionist_page.fxml");

        root = sceneChanger.root;


        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);

    }

    @FXML
    public void adminEnterActionKeyBoard(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER))
        {
            gotoDashBoard();
        }
    }

    @FXML
   public void adminEnterAction(MouseEvent event) {

        gotoDashBoard();
    }

    @FXML
    public void backToHomeAction(MouseEvent event) {

        Parent root = null;
        FXMLSceneChanger changer = FXMLSceneChanger.load("logInOption_page.fxml");

        root = changer.root;
        logInOption LogInOption = (logInOption) changer.controller;

        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
    }



}
