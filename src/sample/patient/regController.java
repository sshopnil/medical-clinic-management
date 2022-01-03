package sample.patient;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.FXMLSceneChanger;
import sample.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class regController
    {
        public Button submitBtn1;
        public TextField mobileNo;
        private LocalDate Birthdate;
        public TextField fName;
        public TextField lName;
        public RadioButton maleSelected;
        public RadioButton femaleSelected;
        public DatePicker DOB;
        File file = new File("src/sample/patient/patientData/newUsers.txt");
        ThePatient user;
        
//        private void getDate(MouseEvent event)
//        {
//            Birthdate = DOB.getValue();
//            System.out.println(Birthdate.toString());
//        }
        public void submitBtnClicked(MouseEvent mouseEvent)
            {
                String gender = "";
                if (maleSelected.isSelected())
                {
                    gender = "Male";
                }
                else if (femaleSelected.isSelected())
                {
                    gender = "Female";
                }
                
                String bDate = "";
                //(!bDate.trim().equals("") & bDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
    
                try
                {
                    if (!(fName.getText().trim().equals("")) && (DOB.getValue() != null) && !(mobileNo.getText().trim().length() > 11))
                    {
                        try
                        {
                            Birthdate = DOB.getValue();
                            bDate = Birthdate.toString();
                            String newPatient = fName.getText().trim() + " " + lName.getText().trim() + ";;" + gender + ";;" + bDate + ";; "+ mobileNo.getText().trim() +"\n";
            
                            FileWriter fw = new FileWriter(file, true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter printWriter = new PrintWriter(bw);
            
                            printWriter.write(newPatient);
                            fName.setText("");
                            lName.setText("");
            
                            printWriter.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Missing Information");
                    }
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please provide correct info!");
                }
                
                gotoLogin();
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
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/newAndOld.fxml");

                root = sceneChanger.root;
                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
            private void gotoLogin()
            {
                Parent root;
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientLogin.fxml");
    
                root = sceneChanger.root;
                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
    }
