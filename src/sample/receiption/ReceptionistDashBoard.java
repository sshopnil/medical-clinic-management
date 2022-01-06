package sample.receiption;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.SubSceneChanger;

public class ReceptionistDashBoard
{
    public Button info;
    public Button appointment;
    public Button doctors;
    public Button regPatient;
    public TreeTableView patientTable;
    public Button organize;
    public VBox organizeSubMenu;
    public Button quickView;
    public Button report;
    public Button profile;
    public BorderPane adminSubscene;
    public Text timeSlotsCount;
    public Text appointmentsCount;
    public Text departmentCount;
    Parent root;
    private int count = 0;
    
    void defultActiveBtn()
    {
        quickView.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
    
    
        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/quickviewScene.fxml");
        ReceptionistDashBoard controller = (ReceptionistDashBoard) sceneChanger.controller;
        controller.timeSlotsCount.setText("00");
        controller.appointmentsCount.setText("00");
        controller.departmentCount.setText("00");
        
        SubSceneChanger subSceneChanger = new SubSceneChanger();
        Pane view = subSceneChanger.getSubScene("receiption/quickviewScene.fxml");
        adminSubscene.setCenter(view);
    }
    void changeColor(Button btn)
    {
        // "-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000"
        // "-fx-background-color: #1e3d59; -fx-text-fill: #ffffff"
        if (btn.equals(quickView))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            organize.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            defultActiveBtn();
        }
        else if(btn.equals(profile))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            organize.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
        else if (btn.equals(regPatient))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            organize.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
        else if (btn.equals(appointment))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            organize.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
        else if (btn.equals(report))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            organize.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
        else if (btn.equals(organize))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
    }
    public void quickViewAction(MouseEvent mouseEvent)
    {
        changeColor(quickView);
        
    }
    public void profileAction(MouseEvent mouseEvent)
    {
        changeColor(profile);
    }

    public void appointmentAction(MouseEvent mouseEvent)
    {
        changeColor(appointment);
        SubSceneChanger subSceneChanger = new SubSceneChanger();
        Pane view = subSceneChanger.getSubScene("receiption/appointmentScene.fxml");
        adminSubscene.setCenter(view);
    }
    
    
    public void regPatient(MouseEvent mouseEvent)
    {
        changeColor(regPatient);
    }
    public void reportAction(MouseEvent mouseEvent)
    {
        changeColor(report);
    }
    
    public void organizeAction(MouseEvent mouseEvent)
    {
        changeColor(organize);
        if (count%2 == 0)
        {
            organizeSubMenu.setVisible(true);
        }
        else
        {
            organizeSubMenu.setVisible(false);
        }
        count++;
    }
    
    //logout button action
    public void returnHomeAction(MouseEvent mouseEvent)
    {
        Parent root;

        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("logInOption_page.fxml");

        root = sceneChanger.root;
        Scene scene = new Scene(root);

        Main.primaryStage.setScene(scene);
    }
    
    
    
    public void departmentsAction(MouseEvent mouseEvent)
    {
    }
    
    public void timeSlotsAction(MouseEvent mouseEvent)
    {
    }
    
    public void doctorsAction(MouseEvent mouseEvent)
    {
    }
}