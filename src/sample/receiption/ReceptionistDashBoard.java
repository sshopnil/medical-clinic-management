package sample.receiption;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.SubSceneChanger;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    public TextField hour;
    public TextField minute;
    public MenuItem selectAM;
    public MenuItem selectPM;
    public TableView<timeSlot> timeTable = new TableView<>();
    public TableColumn <timeSlot, String>timeCol = new TableColumn<>();
    public TableColumn <timeSlot, String>slotCol = new TableColumn<>();
    public MenuButton amPmBtn;
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
    
    public void addTime(MouseEvent mouseEvent)
    {
        ObservableList<timeSlot> myTableData = FXCollections.observableArrayList(
                new timeSlot(hour.getText(), minute.getText(), amPmBtn.getText())
                );
        timeCol.setCellValueFactory(new PropertyValueFactory<timeSlot, String>("time"));
        slotCol.setCellValueFactory(new PropertyValueFactory<timeSlot, String>("slot"));
        
        timeTable.getItems().addAll(myTableData);
    
    
        try
        {
            FileWriter fr = new FileWriter("src/sample/mainServer/AppointmentData/timeSlot.txt", true);
            BufferedWriter br = new BufferedWriter(fr);
            
            br.append(hour.getText() + ":" + minute.getText() + ":" + amPmBtn.getText() + "\n");
            br.close();
            fr.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void removeTime(MouseEvent mouseEvent)
    {
        timeTable.getItems().removeAll(timeTable.getSelectionModel().getSelectedItems());
        
        ObservableList<timeSlot> updated = FXCollections.observableArrayList();
        for (timeSlot ts: timeTable.getItems())
        {
            updated.add(ts);
        }
        
        int i = 0;
        try
        {
            FileWriter fr = new FileWriter("src/sample/mainServer/AppointmentData/timeSlot.txt");
            BufferedWriter br = new BufferedWriter(fr);
            
            while (updated.size() > i)
            {
                br.write(updated.get(i).getTime()+":"+updated.get(i).getSlot()+"\n");
                i++;
            }
            br.close();
            fr.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void ampmClicked(MouseEvent mouseEvent)
    {
        selectAM.setOnAction(e ->
                amPmBtn.setText(selectAM.getText()));
        selectPM.setOnAction(e ->
                amPmBtn.setText(selectPM.getText()));
    }
    
    public void showSlots(MouseEvent mouseEvent)
    {
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/timeSlot.txt"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObservableList<timeSlot> slots = FXCollections.observableArrayList();
        while (scanner.hasNext())
        {
            String str = scanner.nextLine().trim();
            String[] divider = str.split(":");
            slots.add(new timeSlot(divider[0], divider[1], divider[2]));
        }
        
        timeCol.setCellValueFactory(new PropertyValueFactory<timeSlot, String>("time"));
        slotCol.setCellValueFactory(new PropertyValueFactory<timeSlot, String>("slot"));
    
        timeTable.setItems(slots);
        scanner.close();
    }
}