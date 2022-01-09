package sample.receiption;

import javafx.fxml.FXMLLoader;
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

import java.io.IOException;

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
    public SubScene mainSubScene;
    public Button departments;
    public Button timeSlots;
    Parent root;
    private int count = 0;

    void defultActiveBtn()
    {
        quickView.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
    
//        SubSceneChanger subSceneChanger = new SubSceneChanger();
//        Pane view = subSceneChanger.getSubScene("receiption/quickviewScene.fxml");
        
//        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/quickviewScene.fxml");
//        ReceptionistDashBoard controller = (ReceptionistDashBoard) sceneChanger.controller;
//        root = sceneChanger.root;
//        controller.timeSlotsCount.setText("00");
//        controller.appointmentsCount.setText("00");
//        controller.departmentCount.setText("00");
//        mainSubScene.setRoot(root);
//        adminSubscene.setCenter(mainSubScene);
        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/quickviewScene.fxml");
        
        root = FXMLSceneChanger.root;
        quickViewController controller = (quickViewController) FXMLSceneChanger.controller;
        controller.appointmentsCount.setText("00");
        controller.timeSlotsCount.setText("00");
        controller.departmentCount.setText("00");
        adminSubscene.setCenter(root);
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
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            departments.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            defultActiveBtn();
        }
        else if(btn.equals(profile))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            departments.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");

            FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/adminProfile.fxml");
            root = changer.root;
            adminSubscene.setCenter(root);
        }
        else if (btn.equals(regPatient))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            departments.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");

            FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/patientScene.fxml");
            root = changer.root;
            adminSubscene.setCenter(root);
        }
        else if (btn.equals(appointment))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            departments.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");

            FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/appointmentScene.fxml");
            root = changer.root;
            adminSubscene.setCenter(root);
        }
        else if (btn.equals(report))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            departments.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");

            FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/reportScene.fxml");
            root = changer.root;
            adminSubscene.setCenter(root);
        }
        else if (btn.equals(organize))
        {
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            departments.setStyle("-fx-background-color:  #FCF6F5FF; -fx-text-fill: #000000");
        }
        else if(btn.equals(doctors))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color:  #4c5159; -fx-text-fill: #ffffff");
            departments.setStyle("-fx-background-color:  #4c5159; -fx-text-fill: #ffffff");
        }
        else if(btn.equals(timeSlots))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color:  #4c5159; -fx-text-fill: #ffffff");
            departments.setStyle("-fx-background-color:  #4c5159; -fx-text-fill: #ffffff");
        }
        else if(btn.equals(departments))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            report.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color:  #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color:  #4c5159; -fx-text-fill: #ffffff");
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
        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/appointmentScene.fxml");
        root = FXMLSceneChanger.root;
//        adminSubscene.setCenter(view);
//        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/appointmentScene.fxml");
//        root = sceneChanger.root;
//        mainSubScene.setRoot(view);
        adminSubscene.setCenter(root);
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
    
        FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/departmentScene.fxml");
        root = changer.root;
        adminSubscene.setCenter(root);
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
        changeColor(departments);
        FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/departmentScene.fxml");
        root = changer.root;
        adminSubscene.setCenter(root);
    }
    
    public void timeSlotsAction(MouseEvent mouseEvent)
    {
        changeColor(timeSlots);
        FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/timeSlotsScene.fxml");
        root = changer.root;
        adminSubscene.setCenter(root);
    }
    
    public void doctorsAction(MouseEvent mouseEvent)
    {
        changeColor(doctors);
        FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/doctorsScene.fxml");
        root = changer.root;
        adminSubscene.setCenter(root);
    }
}