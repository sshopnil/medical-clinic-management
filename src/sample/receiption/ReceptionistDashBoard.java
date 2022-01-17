package sample.receiption;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.doctor.AppointmentInfo;
import sample.mainServer.NetworkUtil;
import sample.patient.ThePatient;


import javax.swing.*;
import java.io.*;

import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ReceptionistDashBoard
{
    public Button info;
    public Button appointment;
    public Button doctors;
    public Button regPatient;
    public TableView<AppointedPatient> patientTable;
    public Button organize;
    public VBox organizeSubMenu;
    public Button quickView;
    public Button profile;
    public BorderPane adminSubscene;
    public SubScene mainSubScene;
    public Button timeSlots;
    public TextField hour;
    public TextField minute;
    public MenuItem selectAM;
    public MenuItem selectPM;
    public TableView<timeSlot> timeTable = new TableView<>();
    public TableColumn <timeSlot, String>timeCol = new TableColumn<>();
    public TableColumn <timeSlot, String>slotCol = new TableColumn<>();
    public MenuButton amPmBtn;
    public TableColumn<AppointedPatient, String> appPID;
    public TableColumn<AppointedPatient, String> appName;
    public TableColumn<AppointedPatient, String> appTime;
    public TableColumn<AppointedPatient, String> appDate;
    public TableColumn<AppointedPatient, String> appDoc;
    public DatePicker dateToSet;
    public TableView<DuePayment> doctorTable;
    public TableColumn<DuePayment, String> docName;
    public TableColumn<DuePayment, String> docDept;
    public TableColumn<DuePayment, String> docAmount;
    
    @FXML
    private Text ap_fname;
    
    @FXML
    private Text ap_fname1;
    
    @FXML
    private Text adminID;
    
    @FXML
    private Text ap_email;
    
    @FXML
    private Text ap_gender;
    
    @FXML
    private Text ap_bdate;
    
    
    @FXML
    private TableView<ThePatient> tblView;
    @FXML
    private TableColumn<ThePatient, String> tblColPatientId;
    @FXML
    private TableColumn<ThePatient, LocalDate> tblColPatientDob;
    @FXML
    private TableColumn<ThePatient, String> tblColPatientContactNo;
    @FXML
    private TableColumn<ThePatient, String> tblColPatientAddress;
    
    Parent root;
    private int count = 0;
    private String quickViewFromServer;
    
    
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
    
        getQuickViewData();
        String[] qData = quickViewFromServer.split(" ");
        controller.appointmentsCount.setText(qData[0]);
        controller.timeSlotsCount.setText(qData[1]);
        adminSubscene.setCenter(root);
    }
    void getQuickViewData()
    {
        try
        {
            Scanner scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/appointedPatients.txt"));
            int totalApp = 0;
            
            while (scanner.hasNext())
            {
                scanner.nextLine();
                totalApp++;
            }
            scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/timeSlot.txt"));
            int totalSlot = 0;
            
            while (scanner.hasNext())
            {
                scanner.nextLine();
                totalSlot++;
            }
            scanner.close();
            quickViewFromServer = totalApp + " " + totalSlot;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    
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
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            defultActiveBtn();
        }
        else if(btn.equals(profile))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
    
            FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/a_profile.fxml");
            root = FXMLSceneChanger.root;
            adminSubscene.setCenter(root);
    
            ReceptionistDashBoard controller = (ReceptionistDashBoard) FXMLSceneChanger.controller;
            
            controller.ap_fname.setText(Configuration.LOGGED_IN_USER.getFirstName());
            controller.ap_fname1.setText(Configuration.LOGGED_IN_USER.getLastName());
            controller.ap_email.setText(Configuration.LOGGED_IN_USER.getEmail());
            controller.adminID.setText(Configuration.LOGGED_IN_USER.getID());
            controller.ap_bdate.setText(Configuration.LOGGED_IN_USER.getDate());
            controller.ap_gender.setText(Configuration.LOGGED_IN_USER.getGender());
        }
        else if (btn.equals(regPatient))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");

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
            doctors.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color: #4c5159; -fx-text-fill: #ffffff");

            FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/appointmentScene.fxml");
            root = changer.root;
            adminSubscene.setCenter(root);
        }
        else if (btn.equals(organize))
        {
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
        }
        else if(btn.equals(doctors))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            timeSlots.setStyle("-fx-background-color:  #4c5159; -fx-text-fill: #ffffff");
        }
        else if(btn.equals(timeSlots))
        {
            btn.setStyle("-fx-background-color: #FCF6F5FF; -fx-text-fill: #000000");
            quickView.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            profile.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            regPatient.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
            doctors.setStyle("-fx-background-color:  #4c5159; -fx-text-fill: #ffffff");
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
    
        ReceptionistDashBoard controller = (ReceptionistDashBoard) FXMLSceneChanger.controller;
        controller.tblColPatientId.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        controller.tblColPatientDob.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        controller.tblColPatientAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        controller.tblColPatientContactNo.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        ObservableList<ThePatient> list = FXCollections.observableList(Configuration.GetPatientList());
        controller.tblView.setItems(list);
        //controller.tblView.getColumns().addAll(controller.tblColPatientId, controller.tblColPatientDob, controller.tblColPatientAddress, controller.tblColPatientContactNo);
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
    
        FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/doctorsScene.fxml");
        root = changer.root;
        adminSubscene.setCenter(root);
        changeColor(doctors);
    
        ReceptionistDashBoard controller = (ReceptionistDashBoard) FXMLSceneChanger.controller;
    
        controller.docName.setCellValueFactory(new PropertyValueFactory<>("name"));
        controller.docDept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        controller.docAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    
        showDoctors(controller);
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
    
    //select a time slot for showing doctor
    public void timeSlotsAction(MouseEvent mouseEvent)
    {
        changeColor(timeSlots);
        FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/timeSlotsScene.fxml");
        root = changer.root;
        adminSubscene.setCenter(root);
        
        ReceptionistDashBoard controller = (ReceptionistDashBoard) FXMLSceneChanger.controller;
        controller.timeCol.setCellValueFactory(new PropertyValueFactory<timeSlot, String>("time"));
        controller.slotCol.setCellValueFactory(new PropertyValueFactory<timeSlot, String>("slot"));
        showSlots();
    }
    
    public void doctorsAction(MouseEvent mouseEvent)
    {
        changeColor(doctors);
        FXMLSceneChanger changer = FXMLSceneChanger.load("receiption/doctorsScene.fxml");
        root = changer.root;
        adminSubscene.setCenter(root);
        
        ReceptionistDashBoard controller = (ReceptionistDashBoard) FXMLSceneChanger.controller;
        
        controller.docName.setCellValueFactory(new PropertyValueFactory<>("name"));
        controller.docDept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        controller.docAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        
        showDoctors(controller);
    }
    
    private void showDoctors(ReceptionistDashBoard controller)
    {
        ArrayList<DuePayment> myList = new ArrayList<>();
    
        try
        {
            Scanner scanner = new Scanner(new File("src/sample/mainServer/DoctorsData/Payments/allDocsDue.txt"));
            
            while (scanner.hasNext())
            {
                String[] info = scanner.nextLine().split(";;");
                myList.add(new DuePayment(info[0], info[1], info[2]));
            }
            
            ObservableList<DuePayment> allInfo = FXCollections.observableList(myList);
            
            controller.doctorTable.setItems(allInfo);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    
    //adding time to new slots
    public void addTime(MouseEvent mouseEvent)
    {
        String time = "";
        String date = "";
        try
        {
            time = hour.getText() + ":" + minute.getText() + " " + amPmBtn.getText();
            LocalDate date1 = dateToSet.getValue();
            date = date1.toString();
    
            boolean doAppend = true;
            writeSlot(time, date, doAppend);
            showSlots();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please provide valid info");
        }
    }
    
    private void writeSlot(String time, String slot, boolean doAppend)
    {
        try
        {
            FileWriter fr = new FileWriter("src/sample/mainServer/AppointmentData/timeSlot.txt", doAppend);
            BufferedWriter br = new BufferedWriter(fr);

            br.write(time + ";;" + slot + "\n");
            br.close();
            fr.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /*================================================
    current working line
    ==================================================
     */
    public void removeTime(MouseEvent mouseEvent)
    {
        timeTable.getItems().removeAll(timeTable.getSelectionModel().getSelectedItems());
        ObservableList<timeSlot> list = FXCollections.observableArrayList();
        boolean doAppend = false;
        for (timeSlot info: timeTable.getItems())
        {
            list.add(new timeSlot(info.getTime(), info.getSlot()));
        }
        writeSlot(list, doAppend);
    }
    private void writeSlot(List<timeSlot> slotList, boolean doAppend)
    {
        try
        {
            FileWriter fr = new FileWriter("src/sample/mainServer/AppointmentData/timeSlot.txt", doAppend);
            BufferedWriter br = new BufferedWriter(fr);
    
            Iterator<timeSlot> itr = slotList.iterator();
            
            while (itr.hasNext())
            {
                timeSlot ts = itr.next();
                br.write(ts.getTime() + ";;" + ts.getSlot() + "\n");
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
    
    public void showSlots()
    {
        ReceptionistDashBoard controller = (ReceptionistDashBoard) FXMLSceneChanger.controller;
        Scanner scanner = null;
    
        ArrayList<timeSlot> list = new ArrayList<>();
        try
        {
            scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/timeSlot.txt"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        while (scanner.hasNext())
        {
            String str = scanner.nextLine().trim();
            String[] divider = str.split(";;");
            list.add(new timeSlot(divider[0], divider[1]));
        }
//
//        timeCol.setCellValueFactory(new PropertyValueFactory<timeSlot, String>("time"));
//        slotCol.setCellValueFactory(new PropertyValueFactory<timeSlot, String>("slot"));
        scanner.close();
        ObservableList<timeSlot> thisList = FXCollections.observableList(list);
        controller.timeTable.setItems(thisList);
    }
    
    public void appCancelAction(MouseEvent mouseEvent)
    {
        patientTable.getItems().removeAll(patientTable.getSelectionModel().getSelectedItems());
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/appointedPatients.txt"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObservableList<AppointedPatient> patients = FXCollections.observableArrayList();
        while (scanner.hasNext())
        {
            String str = scanner.nextLine().trim();
            String[] divider = str.split(";;");
            patients.add(new AppointedPatient(divider[0], divider[1], divider[3], divider[2], divider[6], divider[4], divider[5], divider[7]));
        }
        scanner.close();
    
        ObservableList<AppointedPatient> update = FXCollections.observableArrayList();
        int i = 0;
        for (AppointedPatient ap: patientTable.getItems())
        {
            if (patients.get(i).id.equals(ap.id))
            {
                update.add(patients.get(i));
            }
            i++;
        }
    
        i = 0;
        try
        {
            FileWriter fr = new FileWriter("src/sample/mainServer/AppointmentData/appointedPatients.txt");
            BufferedWriter br = new BufferedWriter(fr);
        
            while (update.size() > i)
            {
                String str = update.get(i).id + ";;" + update.get(i).name + ";;" + update.get(i).date + ";;" + update.get(i).time + ";;" + update.get(i).subject + ";;" + update.get(i).msg + ";;" + update.get(i).doctor + ";;" + update.get(i).payment;
                br.write(str+"\n");
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
    
    public void appRefreshAction(MouseEvent mouseEvent)
    {
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/appointedPatients.txt"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObservableList<AppointedPatient> patients = FXCollections.observableArrayList();
        while (scanner.hasNext())
        {
            String str = scanner.nextLine().trim();
            String[] divider = str.split(";;");
            String[] docname = divider[6].split("--");
            patients.add(new AppointedPatient(divider[0], divider[1], divider[3], divider[2], docname[0]));
        }
    
        appPID.setCellValueFactory(new PropertyValueFactory<AppointedPatient, String>("id"));
        appName.setCellValueFactory(new PropertyValueFactory<AppointedPatient, String>("name"));
        appTime.setCellValueFactory(new PropertyValueFactory<AppointedPatient, String>("time"));
        appDate.setCellValueFactory(new PropertyValueFactory<AppointedPatient, String>("date"));
        appDoc.setCellValueFactory(new PropertyValueFactory<AppointedPatient, String>("doctor"));
    
        patientTable.setItems(patients);
        scanner.close();
    }
}