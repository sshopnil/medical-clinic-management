package sample.patient;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
                
                }
                catch (Exception e)
                {
                    bDate = "null";
                    submitBtn1.setOnAction(new EventHandler<javafx.event.ActionEvent>()
                    {
                        @Override
                        public void handle(javafx.event.ActionEvent actionEvent)
                        {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("Please provide birthdate!!");
                            alert.showAndWait();
                        }
                    });
                }
                
                if (!(fName.getText().trim().equals("")) && (DOB.getValue() != null))
                {
                    try
                    {
                        Birthdate = DOB.getValue();
                        bDate = Birthdate.toString();
                        String newPatient = fName.getText().trim() + " " + lName.getText().trim() + ";;" + gender + ";;" + bDate + "\n";
                        
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
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("patient/patientLogin.fxml");

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
