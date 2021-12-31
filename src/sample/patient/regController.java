package sample.patient;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class regController implements Serializable
    {
        private LocalDate Birthdate;
        public transient TextField fName;
        public transient TextField lName;
        public transient RadioButton maleSelected;
        public transient RadioButton femaleSelected;
        public transient DatePicker DOB;
        
        static ArrayList<Object> users = new ArrayList<Object>();
        
//        private void getDate(MouseEvent event)
//        {
//            Birthdate = DOB.getValue();
//            System.out.println(Birthdate.toString());
//        }
        public void submitBtnClicked(MouseEvent mouseEvent)
            {
                ThePatient user;
                String gender = "";
                if (maleSelected.isSelected())
                {
                    gender = "Male";
                }
                else if (femaleSelected.isSelected())
                {
                    gender = "Female";
                }
                Birthdate = DOB.getValue();
                String bDate = Birthdate.toString();
                
                user = new ThePatient(fName.getText() + " " + lName.getText(), gender, bDate);
                regController.users.add(user);
                try
                {
                    FileOutputStream fOut = new FileOutputStream(new File("src/sample/patient/patientData/newUsers.txt"), true);
                    ObjectOutputStream oOut = new ObjectOutputStream(fOut);
                    oOut.writeObject(users);
                    
                    fOut.close();
                    oOut.close();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                getUser();
//                Parent root;
//                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientDashBoard.fxml");
//
//                root = sceneChanger.root;
//                Scene scene = new Scene(root);
//                Main.primaryStage.setScene(scene);
            }

        public void backBtnAction(MouseEvent mouseEvent)
            {
                Parent root;
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientLogin.fxml");

                root = sceneChanger.root;
                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
            void getUser()
            {
                ArrayList<ThePatient> user;
                try
                {
                    FileInputStream fIn = new FileInputStream(new File("src/sample/patient/patientData/newUsers.txt"));
                    ObjectInputStream oIn = new ObjectInputStream(fIn);
                    
                    
                    user = (ArrayList<ThePatient>) oIn.readObject();
                    
                    for (ThePatient patient: user)
                    {
                        System.out.println("Name: " + patient.name);
                        System.out.println("Gender: " + patient.gender);
                        System.out.println("DOB: " + patient.DateOfBirth);
                    }
                    
                    fIn.close();
                    oIn.close();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
    }
