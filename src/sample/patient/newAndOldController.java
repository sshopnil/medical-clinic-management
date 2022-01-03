package sample.patient;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;

public class newAndOldController
{
    public Button oldPatient;
    public Button newPatient;
    public Button backToHome;
    
    public void oldClicked(MouseEvent mouseEvent)
    {
        Parent root = null;
        FXMLSceneChanger changer = FXMLSceneChanger.load("patient/patientLogin.fxml");
    
        root = changer.root;
    
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
    }
    
    public void newClicked(MouseEvent mouseEvent)
    {
        Parent root = null;
        FXMLSceneChanger changer = FXMLSceneChanger.load("patient/RegScene.fxml");
    
        root = changer.root;
    
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
    }
    
    public void backClicked(MouseEvent mouseEvent)
    {
        Parent root = null;
        FXMLSceneChanger changer = FXMLSceneChanger.load("logInOption_page.fxml");
    
        root = changer.root;
    
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
    }
}
