package sample.doctor;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.logInOption;
import sample.patient.AppointedPatients;

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
//        public TextField appHour;
//        public DatePicker appDate;
        public TextField appNo;
        public MenuButton approvedBtn;
        public TableView<MyPatients> appointedTable;
        public TableColumn<MyPatients, String> patID;
        public TableColumn<MyPatients, String> patName;
        public TableColumn<MyPatients, String> patTime;
        public TableColumn<MyPatients, String> patDate;
        public TableColumn<MyPatients, String> patSubject;
        public TableColumn<MyPatients, String> patDes;
        public TableColumn<MyPatients, String> patType;
        public Text patStatus;
        String time = "";
        String date = "";
        String limit = "";
        private ArrayList<AppointmentInfo> appInfoList = new ArrayList<>();
        private ArrayList<MyPatients> appointedPatients = new ArrayList<>();
        doctor doc;
        File myFile = new File(getMyAppointmentPath());
        
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
                FXMLSceneChanger changer = FXMLSceneChanger.load("doctor/patientsScene.fxml");
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
    
            DoctorDashBoard controller = (DoctorDashBoard) FXMLSceneChanger.controller;
            controller.patID.setCellValueFactory(new PropertyValueFactory<>("id"));
            controller.patName.setCellValueFactory(new PropertyValueFactory<>("name"));
            controller.patTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            controller.patDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            controller.patSubject.setCellValueFactory(new PropertyValueFactory<>("sub"));
            controller.patDes.setCellValueFactory(new PropertyValueFactory<>("des"));
            controller.patType.setCellValueFactory(new PropertyValueFactory<>("type"));
            //String id, String name, String sub, String des, String time, String date
            
            refreshPatientTable();
            controller.patStatus.setText("Waiting");
        }
    
        private void refreshPatientTable()
        {
            DoctorDashBoard controller = (DoctorDashBoard) FXMLSceneChanger.controller;
            Scanner scanner = null;
            appointedPatients = new ArrayList<>();
            try {
                //reading current doctor info
                
                Scanner scan = new Scanner(new File("src/sample/mainServer/DoctorsData/lastLoggedIn.txt"));
                String[] docInfo = scan.nextLine().split(";;");
    
                scan.close();
                
                
                //setting up the table
                
                scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/appointedPatients.txt"));
                String patient;
                while (scanner.hasNext()) {
                    patient = scanner.nextLine();
                    String[] allInfo = patient.split(";;");
                    String[] docName = allInfo[6].split("--");
                    
                    if (docName[0].contains(docInfo[0]))
                    {
                        appointedPatients.add(new MyPatients(allInfo[0], allInfo[1], allInfo[4], allInfo[5], allInfo[3], allInfo[2], allInfo[7]));
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    
            ObservableList<MyPatients> list = FXCollections.observableList(appointedPatients);
            controller.appointedTable.setItems(list);
        }
    
        public void removeFromPatientAction(MouseEvent mouseEvent)
        {
            appointedTable.getItems().removeAll(appointedTable.getSelectionModel().getSelectedItems());
            ObservableList<MyPatients> list = FXCollections.observableArrayList();
            boolean doAppend = false;
            for (MyPatients info: appointedTable.getItems())
            {
                list.add(new MyPatients(info.id, info.name, info.sub, info.des, info.time, info.date, info.type));
            }
            writeMyPatientAppointments(list);
            //String id, String name, String sub, String des, String time, String date
        }
    
        private void writeMyPatientAppointments(List<MyPatients> list)
        {
            try
            {
                Scanner scan = new Scanner(new File("src/sample/mainServer/DoctorsData/lastLoggedIn.txt"));
                String[] docInfo = scan.nextLine().split(";;");
    
                scan.close();
                
                String path = "src/sample/mainServer/AppointmentData/appointedPatients.txt";
                Scanner scanner = new Scanner(new File(path));
                
                int count = 0;
                ArrayList<String> otherDocs = new ArrayList<>();
                while (scanner.hasNext())
                {
                    String str = scanner.nextLine();
                    String[] id = str.split(";;");
                    String[] docName = id[6].split("--");
                    
                    if(!(docName[0].equals(docInfo[0])))
                    {
                        otherDocs.add(str);
                    }
                }
                scanner.close();
    
                FileWriter fr = new FileWriter(path);
                BufferedWriter br = new BufferedWriter(fr);
                
                Iterator<String> itr = otherDocs.iterator();
                for (MyPatients patients: list)
                {
                    while (itr.hasNext())
                    {
                        br.write(itr.next() + "\n");
                    }
                    String info = patients.id + ";;" + patients.name + ";;" + patients.date + ";;" + patients.time + ";;" + patients.sub + ";;" + patients.des + ";;" + docInfo[0] + "--" + docInfo[1] + ";;" + patients.type;
                    br.write(info+"\n");
                }
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    
        public void patVisitedAction(MouseEvent mouseEvent)
        {
            MyPatients patient = appointedTable.getSelectionModel().getSelectedItem();
    
            Scanner scan = null;
            try
            {
                scan = new Scanner(new File("src/sample/mainServer/DoctorsData/lastLoggedIn.txt"));
                String[] docInfo = scan.nextLine().split(";;");
    
                scan.close();
                
                scan = new Scanner(new File("src/sample/mainServer/DoctorsData/Payments/allDocsDue.txt"));
                boolean newPat = true;
                
                while (scan.hasNext())
                {
                    String[] patDetail = scan.nextLine().split(";;");
    
                    if(docInfo[0].equals(patDetail[0]) && patDetail[3].equals(patient.id) && patDetail[4].equals(patient.date) && patDetail[5].equals(patient.time))
                    {
                        newPat = false;
                    }
                }
                scan.close();
                
                if (newPat)
                {
                    FileWriter fr = new FileWriter("src/sample/mainServer/DoctorsData/Payments/allDocsDue.txt", true);
                    BufferedWriter br = new BufferedWriter(fr);
    
                    double amount = 0;
                    switch (patient.type)
                    {
                        case "general":
                            amount = 1000.00;
                            break;
                        case "private":
                            amount = 1500.00;
                            break;
                        case "home visit":
                            amount = 2500.00;
                            break;
                    }
                    br.write(docInfo[0] + ";;" + docInfo[1] + ";;" + String.valueOf(amount) +";;" +patient.id + ";;" + patient.date + ";;" + patient.time + "\n");
    
    
                    br.close();
                    fr.close();
                }
                String ol = patient.type;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    
        public void tableItemAction(MouseEvent mouseEvent)
        {
            MyPatients patient = appointedTable.getSelectionModel().getSelectedItem();
            DoctorDashBoard controller = (DoctorDashBoard) FXMLSceneChanger.controller;
            
            try
            {
                Scanner scan = new Scanner(new File("src/sample/mainServer/DoctorsData/lastLoggedIn.txt"));
                String[] docInfo = scan.nextLine().split(";;");
    
                scan.close();
                
                scan = new Scanner(new File("src/sample/mainServer/DoctorsData/Payments/allDocsDue.txt"));
                boolean newPat = true;
    
                while (scan.hasNext())
                {
                    String[] patDetail = scan.nextLine().split(";;");
        
                    if(patDetail[0].equals(docInfo[0]) && patDetail[3].equals(patient.id) && patDetail[4].equals(patient.date) && patDetail[5].equals(patient.time))
                    {
                        newPat = false;
                    }
                }
                if (!newPat)
                {
                    controller.patStatus.setText("Visited");
                }
                else
                {
                    controller.patStatus.setText("Waiting");
                }
                scan.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
    
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
        
        
        
        
        
        //=============================setting up time starts============================================
        //===============================================================================================
        private String readSlot()
        {
            String allslots = "";
            try
            {
                Scanner scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/timeSlot.txt"));
                while (scanner.hasNext())
                {
                    allslots += scanner.nextLine() + "\n";
                }
                scanner.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            return allslots;
        }
        
        //derived time and dates from admin
        public void approvedBtnAction(MouseEvent mouseEvent)
        {
            String[] timeSlots = readSlot().split("\n");
            approvedBtn.getItems().clear();
            
            int count = 0;
    
            for(String str: timeSlots)
            {
                approvedBtn.getItems().add(new javafx.scene.control.MenuItem(str));
                approvedBtn.getItems().get(count).setOnAction(e ->{
                    approvedBtn.setText(str);
                });
                count++;
            }
        }
        public void appSubmit(MouseEvent mouseEvent)
        {
            try
            {
                String[] str = approvedBtn.getText().split(";;");
                time = str[0];
                date = str[1];
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
                scanner = new Scanner(new File(getMyAppointmentPath()));
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
                
                fr = new FileWriter(new File(getMyAppointmentPath()), doAppend);
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
                Scanner scanner = new Scanner(new File("src/sample/mainServer/DoctorsData/lastLoggedIn.txt"));
                String[] docInfo = scanner.nextLine().split(";;");
    
                scanner.close();
                
                fr = new FileWriter(new File(getMyAppointmentPath()), doAppend);
                BufferedWriter br = new BufferedWriter(fr);
                
                Iterator<AppointmentInfo> itr = list.iterator();
                while (itr.hasNext())
                {
                    AppointmentInfo info = itr.next();
                    br.write(docInfo[0]+ ";;" + docInfo[1] + ";;" + info.getTime() + ";;" + info.getDate() + ";;" + info.getLimit());
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
    
        
        private String getMyAppointmentPath()
        {
            Scanner scanner = null;
            try
            {
                scanner = new Scanner(new File("src/sample/mainServer/DoctorsData/lastLoggedIn.txt"));
                String[] docInfo = scanner.nextLine().split(";;");
    
                scanner.close();
                return "src/sample/mainServer/DoctorsData/DoctorsAppointmentInfo/" + docInfo[0] + "_" + docInfo[1] + ".txt";
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    
        //=============================setting up time ends============================================
        //===============================================================================================
    }
    
