package sample.receiption;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminLogin {

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_pass;

    @FXML
    private Button btn_login;

    @FXML
    private Button backToHome;
    @FXML
    private Button btn_login1;


    void gotoDashBoard()
    {
        Parent root = null;
        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/receiptionist_page.fxml");

        root = sceneChanger.root;
        ReceptionistDashBoard db = (ReceptionistDashBoard) FXMLSceneChanger.controller;
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
        
        db.defultActiveBtn();
    }

    public List<Admin> getLogInfo() {
        List<Admin> admins = new ArrayList<Admin>();
        {
            try {
                File file = new File("src/sample/receiption/adminauth.txt");
                Scanner scanner = new Scanner(file);

                for (int i = 0; scanner.hasNext(); i++) {
                    String inline = scanner.next();

                    String[] line = inline.split(";;");

                    Admin rAdmin = new Admin();
                    rAdmin.setF_name(line[0]);
                    rAdmin.setL_name(line[1]);
                    rAdmin.setAemail(line[2]);
                    rAdmin.setPass(line[3]);
                    admins.add(rAdmin);
                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return admins;
        }
    }


    @FXML
    public void adminEnterActionKeyBoard(KeyEvent event) {
        performLogin();
    }

    @FXML
   public void adminEnterAction(MouseEvent event) {
        performLogin();
    }


    public void performLogin(){

        List<Admin> admins = getLogInfo();
        String email = tf_email.getText().trim();
        String pass = tf_pass.getText();
        boolean isAuthenticated = false;
        for (Admin admin : admins) {
            if (admin.getAemail().equals(email) && admin.getPass().equals(pass)){
                isAuthenticated = true;
                break;
            }
        }

        if (isAuthenticated){
            gotoDashBoard();
        } else {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Incorrect Information");
        }
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

    @FXML
    void signup(ActionEvent event) {

        Parent root = null;
        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/regScene.fxml");
        root = sceneChanger.root;
        Main.primaryStage.setScene(new Scene(root));
    }

}
