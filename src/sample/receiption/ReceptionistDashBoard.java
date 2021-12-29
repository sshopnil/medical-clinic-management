package sample.receiption;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;

public class ReceptionistDashBoard
{
    public Button info;
    public Button appointment;
    public Button doctors;
    public Button livecount;

    void changeColor(Button btn)
    {
        if (btn.equals(info))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            livecount.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
        else if (btn.equals(appointment))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            info.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            livecount.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
        else if (btn.equals(doctors))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            info.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            livecount.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
        else if (btn.equals(livecount))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            info.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
    }

    public void infoAction(MouseEvent mouseEvent)
    {
        changeColor(info);
    }

    public void appointmentAction(MouseEvent mouseEvent)
    {
        changeColor(appointment);
    }

    public void doctorsAction(MouseEvent mouseEvent)
    {
        changeColor(doctors);
    }
    public void livecountAction(MouseEvent mouseEvent)
    {
        changeColor(livecount);
    }

    public void returnHomeAction(MouseEvent mouseEvent)
    {
        Parent root;

        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("logInOption_page.fxml");

        root = sceneChanger.root;
        Scene scene = new Scene(root);

        Main.primaryStage.setScene(scene);
    }
}