package sample.patient;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.FXMLSceneChanger;
import sample.Main;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;

public class regController
    {
        public Button submitBtn1;
        public TextField mobileNo;
        public MenuItem menuSelf;
        public MenuItem menuParent;
        public MenuItem menuSibling;
        public MenuItem menuHusband;
        public MenuItem menuWife;
        public MenuItem menuOther;
        public MenuButton maritalStatus;
        public MenuItem mSingle;
        public MenuItem mMarried;
        public MenuItem mOther;
        public MenuButton religion;
        public MenuItem relIslam;
        public MenuItem relHindu;
        public MenuItem relChristian;
        public MenuItem relBuddha;
        public MenuItem relAnimist;
        public MenuItem relOther;
        public RadioButton feeGeneral;
        public RadioButton feePrivate;
        public RadioButton feeHomeVisit;
        private LocalDate Birthdate;
        public TextField fName;
        public RadioButton maleSelected;
        public RadioButton femaleSelected;
        public DatePicker DOB;
        @FXML
        private RadioButton otherSelected;
        @FXML
        private TextArea pAddress;
        @FXML
        private MenuButton patientRelation;
        public String patientID;
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
                else if (otherSelected.isSelected())
                {
                    gender = "Other";
                }
                
                String bDate = "";
                //(!bDate.trim().equals("") & bDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
    
                try
                {
                    if (!(fName.getText().trim().equals("")) && (DOB.getValue() != null))
                    {
                        try
                        {
                            System.out.println("havent done yet..");
                            Birthdate = DOB.getValue();
                            bDate = Birthdate.toString();
                            
//                            patientID = String.valueOf(fName.getText().trim().charAt(0) + Birthdate.getYear() + (int)(Math.random()*500));
                            generateID();
                            System.out.println("done son!");
                            
                            String newPatient = patientID+";;"+fName.getText().trim() + ";;" + gender + ";;" + bDate + ";;"+ mobileNo.getText().trim() + ";;" + pAddress.getText().trim()+";;"+patientRelation.getText()+";;"+maritalStatus.getText()+";;"+religion.getText()+"\n";
            
                            FileWriter fw = new FileWriter(file, true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter printWriter = new PrintWriter(bw);
            
                            printWriter.write(newPatient);
                            fName.setText("");
            
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
                    e.printStackTrace();
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
    
            
            //relationship with patient action
        public void relWPatient(MouseEvent mouseEvent)
        {
            menuSelf.setOnAction(e ->{
                patientRelation.setText(menuSelf.getText());
            });
            menuParent.setOnAction(e ->{
                patientRelation.setText(menuParent.getText());
            });
            menuSibling.setOnAction(e ->{
                patientRelation.setText(menuSibling.getText());
            });
            menuHusband.setOnAction(e ->{
                patientRelation.setText(menuHusband.getText());
            });
            menuWife.setOnAction(e ->{
                patientRelation.setText(menuWife.getText());
            });
            menuOther.setOnAction(e ->{
                patientRelation.setText(menuOther.getText());
            });
        }
    
        //Marital status button action
        public void marStatusAction(MouseEvent mouseEvent)
        {
            mSingle.setOnAction(e ->
            {
                maritalStatus.setText(mSingle.getText());
            });
            mMarried.setOnAction(e ->
            {
                maritalStatus.setText(mMarried.getText());
            });
            mOther.setOnAction(e ->
            {
                maritalStatus.setText(mOther.getText());
            });
            
            
        }
    
        //religion button action
        
        public void relAction(MouseEvent mouseEvent)
        {
            relIslam.setOnAction(e ->
            {
                religion.setText(relIslam.getText());
            });
            relHindu.setOnAction(e ->
            {
                religion.setText(relHindu.getText());
            });
            relChristian.setOnAction(e ->
            {
                religion.setText(relChristian.getText());
            });
            relBuddha.setOnAction(e ->
            {
                religion.setText(relBuddha.getText());
            });
            relAnimist.setOnAction(e ->
            {
                religion.setText(relAnimist.getText());
            });
            relOther.setOnAction(e ->
            {
                religion.setText(relOther.getText());
            });
        }
        public void generateID()
        {
            String name = fName.getText().trim();
            if (name.length() < 2)
            {
                patientID = name.toLowerCase()+ Birthdate.getYear()+ (int) (Math.random() * 500);
                System.out.println(patientID);
            }
            else
            {
                patientID = name.charAt(0) + fName.getText().trim().toLowerCase() + Birthdate.getYear() + (int) (Math.random() * 500) +1;
                System.out.println(patientID);
            }
        }
    }
