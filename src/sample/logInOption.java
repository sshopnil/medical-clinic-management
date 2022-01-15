package sample;

import com.sun.glass.ui.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.doctor.DoctorLogin;
import sample.patient.PatientLogin;
import sample.receiption.AdminLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class logInOption implements Initializable
    {

        public ImageView patient_login;
        public ImageView receiptionist_login;
        public PieChart pieChart;
    
        
        void analyzeChart()
        {
            DoctorLogin doctorData = new DoctorLogin();
            PatientLogin patientData = new PatientLogin();
            AdminLogin admins = new AdminLogin();
            int totalD = doctorData.getInformation().size();
            int totalP = patientData.getPatientInfo().size();
            int totalA = admins.getLogInfo().size();
            ObservableList<PieChart.Data> allData = FXCollections.observableArrayList(
                    new PieChart.Data("Registered Patients", totalP),
                    new PieChart.Data("Registered Admins", totalA),
                    new PieChart.Data("Registered Doctors", totalD)
                                                                                      );
            allData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(data.getName() + ": " + (int)data.getPieValue())
                           ));
            pieChart.setData(allData);
        }
        public void receiptionist_clicked(MouseEvent mouseEvent)
            {
                try
                    {
                        Stage recLogin = (Stage) receiptionist_login.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("receiption/adminLogin.fxml"));

                        recLogin.setScene(new Scene(root));
                    }
                    catch (Exception e)
                        {
                            System.out.println("Error in receiption method");
                        }
            }

        public void patient_clicked(MouseEvent mouseEvent)
            {
                Parent root = null;
                FXMLSceneChanger changer = FXMLSceneChanger.load("patient/newAndOld.fxml");

                root = changer.root;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }

        public void doctor_clicked(MouseEvent mouseEvent)
            {
                FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("doctor/DoctorLogin.fxml");
                Parent root = sceneChanger.root;

                Scene scene = new Scene(root);
                Main.primaryStage.setScene(scene);
            }
    
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
        {
            analyzeChart();
        }
    }
