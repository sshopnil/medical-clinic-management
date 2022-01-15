package sample.doctor;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;
import sample.patient.ThePatient;
import sample.receiption.AppointedPatient;
import sample.receiption.Configuration;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class DoctorDashBoard
    {


        public Button info;
        public Button appointments;
        public Button finance;
        public Button returnHome;
        public BorderPane workingSubScene;


        public Text D_name;
        public Text D_age;
        public Text D_dob;
        public Text D_gender;
        public Text D_mobile;
        public Text D_email;
        public Text D_address;
        public TableView<AppointmentInfo> appTable;
        public TableColumn<AppointmentInfo, String> tableTime;
        public TableColumn<AppointmentInfo, String> tableDate;
        public TableColumn<AppointmentInfo, String> tableLimit;
        public TextField appHour;
        public DatePicker appDate;
        public TextField appNo;
        public TextField appMin;
        public MenuItem am;
        public MenuItem pm;
        public MenuButton amPmBtn;
        String time = "";
        String date = "";
        String limit = "";
        private ArrayList<AppointmentInfo> appInfoList = new ArrayList<>();
        doctor doc;

        DoctorDashBoard controller;
        void loadData(DoctorDashBoard controller)
        {
            controller.D_name.setText(doc.name);
            controller.D_address.setText(doc.address);
            controller.D_email.setText(doc.email);
            controller.D_gender.setText(doc.gender);
            controller.D_age.setText(String.valueOf(doc.age));
            controller.D_dob.setText(doc.birthdate);
            controller.D_mobile.setText(doc.phone);


        }


        void defultActiveBtn(doctor doc)
        {
            this.doc = doc;
            info.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
            Parent root;

            FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("doctor/infoScene.fxml");
            DoctorDashBoard controller = (DoctorDashBoard) FXMLSceneChanger.controller;

            root = sceneChanger.root;
            workingSubScene.setCenter(root);

            loadData(controller);

        }

        void ChangeScene(Button btn) {
            Parent root;
            if(btn.equals(info))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/infoScene.fxml");
                root = changer.root;
                workingSubScene.setCenter(root);

            }
            else if(btn.equals(finance))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/financeScene.fxml");
                root = changer.root;
                workingSubScene.setCenter(root);
            }
            else if(btn.equals(appointments))
            {
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/appointmentsScene.fxml");
                root = changer.root;
                workingSubScene.setCenter(root);
            }
        }

        //color changing part
        void ChangeColor(Button btn)
        {
            if(btn.equals(info))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                appointments.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
                finance.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");

            }
            else if(btn.equals(appointments))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                info.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
                finance.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
            }
            else if(btn.equals(finance))
            {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                info.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
                appointments.setStyle("-fx-background-color:   #1e3d59; -fx-text-fill: #ffffff");
            }

        }

        //button action perform part
        public void infoAction(ActionEvent actionEvent) {
            ChangeColor(info);//changing button color
            ChangeScene(info);
            defultActiveBtn(doc);
        }


        public void appointmentsAction(ActionEvent actionEvent) {
            ChangeColor(appointments);
            ChangeScene(appointments);

        }
        public void financeAction(ActionEvent actionEvent) {
            ChangeColor(finance);
            ChangeScene(finance);
            
            DoctorDashBoard controller = (DoctorDashBoard) FXMLSceneChanger.controller;
            controller.tableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            controller.tableLimit.setCellValueFactory(new PropertyValueFactory<>("limit"));
            controller.tableTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            refreshTable();
        }


        public void backToHomeAction(ActionEvent actionEvent) {
            Parent root = null;
            FXMLSceneChanger changer = FXMLSceneChanger.load("logInOption_page.fxml");

            root = changer.root;
            logInOption controller = (logInOption) changer.controller;

            Scene scene = new Scene(root);
            Main.primaryStage.setScene(scene);
        }
    
        public void appSubmit(MouseEvent mouseEvent)
        {
            try
            {
                time = appHour.getText() + ":" + appMin.getText() + " " + amPmBtn.getText();
                LocalDate date1 = appDate.getValue();
                date = date1.toString();
                limit = appNo.getText();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please provide valid info");
            }
            boolean doAppend = true;
            writeAppointment(time, date, limit, doAppend);
            refreshTable();
        }
    
        public void appRemove(MouseEvent mouseEvent)
        {
            appTable.getItems().removeAll(appTable.getSelectionModel().getSelectedItems());
            ObservableList<AppointmentInfo> list = FXCollections.observableArrayList();
            boolean doAppend = false;
            for (AppointmentInfo info: appTable.getItems())
            {
                list.add(new AppointmentInfo(info.getTime(), info.getDate(), info.getLimit()));
            }
            writeAppointment(list, doAppend);
            //appTable.getSelectionModel().clearSelection();
        }
        public void refreshTable()
        {
            DoctorDashBoard controller = (DoctorDashBoard) FXMLSceneChanger.controller;
            Scanner scanner = null;
            appInfoList = new ArrayList<>();
            try {
                scanner = new Scanner(new File("src/sample/mainServer/DoctorsData/appLimit.txt"));
                String patient;
                while (scanner.hasNext()) {
                    patient = scanner.nextLine();
                    String[] allInfo = patient.split(";;");
                    appInfoList.add(new AppointmentInfo(allInfo[2], allInfo[3], allInfo[4]));
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    
            ObservableList<AppointmentInfo> list = FXCollections.observableList(appInfoList);
            controller.appTable.setItems(list);
        }
        
        private void writeAppointment(String time, String date, String limit, Boolean doAppend)
        {
            FileWriter fr = null;
            try
            {
                Scanner scanner = new Scanner(new File("src/sample/mainServer/DoctorsData/lastLoggedIn.txt"));
                String[] docInfo = scanner.nextLine().split(";;");
                
                scanner.close();
                fr = new FileWriter(new File("src/sample/mainServer/DoctorsData/appLimit.txt"), doAppend);
                BufferedWriter br = new BufferedWriter(fr);
                
                br.write(docInfo[0]+";;" + docInfo[1] +";;"+ time + ";;" + date + ";;" + limit);
                br.newLine();
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        private void writeAppointment(List<AppointmentInfo> list, Boolean doAppend)
        {
            FileWriter fr = null;
            try
            {
                fr = new FileWriter(new File("src/sample/mainServer/DoctorsData/appLimit.txt"), doAppend);
                BufferedWriter br = new BufferedWriter(fr);
                
                Iterator<AppointmentInfo> itr = list.iterator();
                while (itr.hasNext())
                {
                    AppointmentInfo info = itr.next();
                    br.write(info.getTime() + ";;" + info.getDate() + ";;" + info.getLimit());
                    br.newLine();
                }
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    
        public void amPmAction(MouseEvent mouseEvent)
        {
            am.setOnAction(e ->
                    amPmBtn.setText(am.getText()));
            pm.setOnAction(e->
                    amPmBtn.setText(pm.getText()));
        }
    }
    
