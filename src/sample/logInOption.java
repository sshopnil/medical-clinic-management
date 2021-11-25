package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class logInOption
    {

        public ImageView patient_login;
        public ImageView receiptionist_login;

        public void receiptionist_clicked(MouseEvent mouseEvent)
            {
                try
                    {
                        Stage recLogin = (Stage) receiptionist_login.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("receiptionist_page"));

                        recLogin.setScene(new Scene(root));
                    }
                    catch (Exception e)
                        {
                            System.out.println("Error in receiption method");
                        }
            }

        public void patient_clicked(MouseEvent mouseEvent)
            {
                try
                    {
                        Stage patientLog = (Stage) patient_login.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("patient/patient_page.fxml"));
                        
                        patientLog.setScene(new Scene(root));
                        patientLog.setTitle("Patient Dashboard");
                        patientLog.show();
                    }
                    catch (IOException e)
                        {
                            System.out.println("Error in patient method");
                        }
            }

        public void doctor_clicked(MouseEvent mouseEvent)
            {
            }
    }
