package sample.patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;
import sample.ReaderThread;
import sample.WriterThread;
import sample.mainServer.NetworkUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.MenuItem;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class PatientDashBoard
    {
        
        public Button myInfo;
        public Button appointment;
        public AnchorPane patientDashBoardPane;
        public SubScene patientSubScene;
        public Text Pserial;
        public Text pID;
        public Text Pname;
        public Text pGender;
        public Text pDOB;
        public Text pMob;
        public Text pMarital;
        public Text pReligion;
        public Text pAddress;
        public BorderPane workingSubScene;
        public RadioButton feeGeneral;
        public RadioButton feePrivate;
        public RadioButton feeHomeVisit;
        public MenuButton chooseDoc = new MenuButton();
        public MenuButton chooseSlot = new MenuButton();
        public TextField appSubject;
        public TextArea appDescription;
        public MenuButton chooseDate;
        public Button appHistory;
        public TableView<AppHistoryClass> appHistoryTable;
        public TableColumn<AppHistoryClass, String> historyDate;
        public TableColumn<AppHistoryClass, String> historyTime;
        public TableColumn<AppHistoryClass, String> historyDoctor;
        public TableColumn<AppHistoryClass, String> historyDept;
        private String docsFromServer;
        public LocalDate AppDate;
        private String slotFromServer;
        private String dateFromServer;
        Parent root;
        ThePatient patient;
        String PID = "";
        String name = "";
        
        private int count = 0;
        
        
        //======================COMMUNICATION WITH SERVER STARTS FROM HERE ================================
        //================================================================================================
        
        void readSlot(String docName) throws IOException
        {
            Socket socket = new Socket("127.0.0.1", 5000);
    
//            System.out.println("Patient Client Started--- ");
            System.out.println(socket.getLocalAddress().getHostAddress());
            NetworkUtil nc=new NetworkUtil(socket);
    
            nc.write("timeSlot" + " " + docName);
            slotFromServer = (String) nc.read();
            slotFromServer = slotFromServer.trim();
//            System.out.println("Server: " + slotFromServer);
            nc.write("exit");
            socket.close();
//            System.out.println("Patient Client closed...");
        }
        void readDocData() throws IOException
        {
            Socket socket = new Socket("127.0.0.1", 5000);
    
//            System.out.println("Patient Client Started--- ");
            System.out.println(socket.getLocalAddress().getHostAddress());
            NetworkUtil nc=new NetworkUtil(socket);
    
            nc.write("doclist");
            docsFromServer = (String) nc.read();
            docsFromServer = docsFromServer.trim();
//            System.out.println("Server: " + docsFromServer);
            nc.write("exit");
            socket.close();
//            System.out.println("Patient Client closed...");
        }
        
        void readAppDate(String docName) throws IOException
        {
            Socket socket = new Socket("127.0.0.1", 5000);
    
//            System.out.println("Patient Client Started--- ");
            System.out.println(socket.getLocalAddress().getHostAddress());
            NetworkUtil nc=new NetworkUtil(socket);
    
            nc.write("dateSlot" + " " + docName);
            dateFromServer = (String) nc.read();
            dateFromServer = dateFromServer.trim();
//            System.out.println("Server: " + dateFromServer);
            nc.write("exit");
            socket.close();
//            System.out.println("Patient Client closed...");
        }
    
        //======================COMMUNICATION WITH SERVER ENDS HERE ================================
        //==========================================================================================
        
        
        void defultActiveBtn(ThePatient thisPatient)
            {
                myInfo.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                

                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/infoScene.fxml");

                root = FXMLSceneChanger.root;
                workingSubScene.setCenter(root);
                patient = thisPatient;
                name = thisPatient.name;
                PID = thisPatient.patientID;
                //setting fields with controller
                PatientDashBoard controller = (PatientDashBoard) FXMLSceneChanger.controller;
                controller.Pname.setText(patient.name);
                controller.pID.setText(Integer.toString(patient.age));
                controller.pGender.setText(patient.gender);
                controller.pDOB.setText(patient.DateOfBirth);
                controller.pMob.setText("+88 " + patient.mobile);
                controller.pMarital.setText(patient.maritalStatus);
                controller.pAddress.setText(patient.address);
                controller.pReligion.setText(patient.religion);
                controller.Pserial.setText(patient.patientID);
            }

        void changeColor(Button btn)
            {
                if (btn.equals(myInfo))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        appHistory.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        defultActiveBtn(patient);
                        
                    }
                else if (btn.equals(appointment))
                    {
                        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                        myInfo.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                        appHistory.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                    }
                else if(btn.equals(appHistory))
                {
                    btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
                    myInfo.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                    appointment.setStyle("-fx-background-color: #1e3d59; -fx-text-fill: #ffffff");
                }
            }

        public void myInfoAction(MouseEvent mouseEvent)
            {
                changeColor(myInfo);
            }

        public void appointmentAction(MouseEvent mouseEvent)
            {
                changeColor(appointment);
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/appointmentScene.fxml");
                root = FXMLSceneChanger.root;
                workingSubScene.setCenter(root);
            }
            

        public void returnHomeAction(MouseEvent mouseEvent)
            {
                Parent root;

                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("logInOption_page.fxml");

                root = sceneChanger.root;
                Scene scene = new Scene(root);

                Main.primaryStage.setScene(scene);
            }
    
        public void editInfoAction(MouseEvent mouseEvent)
        {
            FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/RegScene.fxml");
            
            root = sceneChanger.root;
            Main.primaryStage.setScene(new Scene(root));
        }
    
        public void bookNowAction(MouseEvent mouseEvent)
        {
            String feetype = "";
            String docAndDep = "";
            String appDate = "";
            
            appDate = chooseDate.getText();
            String slots = chooseSlot.getText();
            docAndDep = chooseDoc.getText();
            if (feeGeneral.isSelected())
            {
                feetype = "general";
            }
            else if(feePrivate.isSelected())
            {
                feetype = "private";
            }
            else if(feeHomeVisit.isSelected())
            {
                feetype = "homevisit";
            }
            else
            {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please provide fee-type");
            }
            
            if (checkIfDoctorAvailable(docAndDep, slots, appDate))
            {
                try
                {
                    Scanner scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/currentLoggedIn.txt"));
                    String[] inf = scanner.nextLine().split(";;");
        
                    scanner.close();
        
                    
                    FileWriter fr = new FileWriter(new File("src/sample/mainServer/AppointmentData/appointedPatients.txt"), true);
                    BufferedWriter br = new BufferedWriter(fr);
                    String appInfo = inf[1] + ";;" + inf[0] + ";;" + appDate + ";;" + slots + ";;" + appSubject.getText() + ";;" + appDescription.getText() + ";;" + docAndDep + ";;" + feetype;
                    br.append(appInfo + "\n");
        
                    br.close();
                    fr.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Booked!!");
                    chooseSlot.setText("choose");
                    chooseDoc.setText("choose");
                    appSubject.setText("");
                    appDescription.setText("");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Doctor is not available");
            }
        }
    
        //==============APPOINTMENT SELECTION MENU BUTTON ACTIONS START===========================
        
        
        public void doctorMenuBtnAction(MouseEvent mouseEvent)
        {
            //reading doctors data from the server
            try
            {
                readDocData();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            String[] docts = docsFromServer.split(" ");
//            Iterator itr = docs.iterator();
            chooseDoc.getItems().clear();
            int count = 0;
            for(String str: docts)
            {
                chooseDoc.getItems().add(new javafx.scene.control.MenuItem(str));
                chooseDoc.getItems().get(count).setOnAction(e ->{
                    chooseDoc.setText(str);
                });
                System.out.println(str);
                count++;
            }
        }
        
    
        public void chooseSlotAction(MouseEvent mouseEvent)
        {
            String docName = chooseDoc.getText();
            try
            {
                readSlot(docName);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            String[] timeslots = slotFromServer.split(";;");
//            Iterator itr = docs.iterator();
            chooseSlot.getItems().clear();
            int count = 0;
            for(String str: timeslots)
            {
                chooseSlot.getItems().add(new javafx.scene.control.MenuItem(str));
                chooseSlot.getItems().get(count).setOnAction(e ->{
                    chooseSlot.setText(str);
                });
                System.out.println(str);
                count++;
            }
        }
        public void chooseDateAction(MouseEvent mouseEvent)
        {
            String docName = chooseDoc.getText();
            
            try
            {
                readAppDate(docName);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    
            String[] dateslots = dateFromServer.split(";;");
//            Iterator itr = docs.iterator();
            chooseDate.getItems().clear();
            int count = 0;
            int i = 0;
            for(String str: dateslots)
            {
                chooseDate.getItems().add(new javafx.scene.control.MenuItem(str));
                chooseDate.getItems().get(count).setOnAction(e ->{
                    chooseDate.setText(str);
                });
                System.out.println(str);
                count++;
            }
        }
    
    
        //==============APPOINTMENT SELECTION MENU BUTTON ACTIONS END===========================
        
        
        
        //====================CHECK IF THE DOCTOR IS AVAILABLE==================================
        boolean checkIfDoctorAvailable(String doc, String time, String date)
        {
            String[]docName = doc.split("--");
    
            try
            {
                String path = "src/sample/mainServer/DoctorsData/DoctorsAppointmentInfo/" + docName[0] + "_" + docName[1] + ".txt";
                Scanner scanner = new Scanner(new File(path));
                while (scanner.hasNext())
                {
                    String[] docInfo = scanner.nextLine().split(";;");
                    if (docInfo[2].equals(time) && docInfo[3].equals(date))
                    {
                        if (Integer.parseInt(docInfo[4]) > 0)
                        {
                            updateDocInfo(docInfo[0], docInfo[1], docInfo[2], docInfo[3]);
                            System.out.println("is available");
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
                scanner.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            return false;
        }
        
        void updateDocInfo(String name, String dept, String time, String date)
        {
            try
            {
                String path = "src/sample/mainServer/DoctorsData/DoctorsAppointmentInfo/" + name + "_" + dept + ".txt";
                ArrayList <String> allDocs = new ArrayList<>();
                Scanner scanner = new Scanner(new File(path));
                while (scanner.hasNext())
                {
                    allDocs.add(scanner.nextLine());
                }
                
                scanner.close();
                System.out.println("Updating...");
                FileWriter fr = new FileWriter(new File(path));
                BufferedWriter br = new BufferedWriter(fr);
                
                Iterator<String> iterator = allDocs.iterator();
                
                while (iterator.hasNext())
                {
                    String str1 = iterator.next();
                    String[] str = str1.split(";;");
                    if ((str[2].equals(time)) && (str[3].equals(date)))
                    {
                        int appLimit = Integer.parseInt(str[4]);
                        appLimit--;
                        System.out.println("updating limit");
                        br.write(str[0]+";;" + str[1] + ";;" + str[2] + ";;" + str[3] + ";;"+ appLimit);
                        br.newLine();
                    }
                    else
                    {
                        br.write(str1);
                        br.newLine();
                    }
                }
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        //====================CHECK IF THE DOCTOR IS AVAILABLE ENDS==================================
    
    
        //=======================Appointment History Start==========================================
        //==========================================================================================
        
        public void appointmentHistoryAction(MouseEvent mouseEvent)
        {
            changeColor(appHistory);
            FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/appHistoryScene.fxml");
            root = FXMLSceneChanger.root;
            workingSubScene.setCenter(root);
            
            PatientDashBoard controller = (PatientDashBoard) FXMLSceneChanger.controller;
            controller.historyDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            controller.historyTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            controller.historyDoctor.setCellValueFactory(new PropertyValueFactory<>("doctorname"));
            controller.historyDept.setCellValueFactory(new PropertyValueFactory<>("depname"));
            
            showHistory(controller);
            //    String time;
            //    String date;
            //    String doctorname;
            //    String depname;
        }
        
        private void showHistory(PatientDashBoard controller)
        {
            try
            {
                Scanner scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/currentLoggedIn.txt"));
                
                String[] info = scanner.nextLine().split(";;");
                
                scanner.close();
                
                
                scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/appointedPatients.txt"));
                ArrayList<AppHistoryClass> myList = new ArrayList<>();
                
                while (scanner.hasNext())
                {
                    String[] str = scanner.nextLine().split(";;");
                    if (info[1].equals(str[0]) && info[0].equals(str[1]))
                    {
                        String[] docInfo = str[6].split("--");
                        myList.add(new AppHistoryClass(str[3], str[2], docInfo[0], docInfo[1]));
                    }
                }
                scanner.close();
    
                ObservableList<AppHistoryClass> list = FXCollections.observableArrayList(myList);
                controller.appHistoryTable.setItems(list);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
    
        }
        
        private String getPatInfo()
        {
            String info = "";
            Scanner scanner = null;
            try
            {
                scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/currentLoggedIn.txt"));
                 info = scanner.nextLine();
    
                scanner.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            
            return info;
        }
        
        public void cancelAppAction(MouseEvent mouseEvent)
        {
            AppHistoryClass removeThis = appHistoryTable.getSelectionModel().getSelectedItem();
            appHistoryTable.getItems().removeAll(appHistoryTable.getSelectionModel().getSelectedItems());
            
            ArrayList<String> allPatients = new ArrayList<>();
    
            try
            {
                Scanner scanner = new Scanner(new File("src/sample/mainServer/AppointmentData/appointedPatients.txt"));
                
                String[] currentPat = getPatInfo().split(";;");
                
                while (scanner.hasNext())
                {
                    String str = scanner.nextLine();
                    String[] temp = str.split(";;");
                    
                    //checking -- > name                                 id                           date                             time
                    if ((temp[0].equals(currentPat[1])) && (temp[1].equals(currentPat[0])) && (temp[2].equals(removeThis.date)) && (temp[3].equals(removeThis.time)))
                    {
                        String docAdep = removeThis.doctorname + "--" + removeThis.depname;
                        if (temp[6].equals(docAdep))
                        {
                            continue;
                        }
                    }
                    allPatients.add(str);
                }
                scanner.close();
                
                FileWriter fr = new FileWriter("src/sample/mainServer/AppointmentData/appointedPatients.txt");
                BufferedWriter br = new BufferedWriter(fr);
    
                for (String allPatient : allPatients)
                {
                    String str = allPatient + "\n";
                    br.write(str);
                }
                br.close();
                fr.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    