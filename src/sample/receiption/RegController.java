package sample.receiption;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.FXMLSceneChanger;
import sample.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegController {

    @FXML
    private TextField rtf_name;

    @FXML
    private TextField rtf_name2;

    @FXML
    private Button backBtn;

    @FXML
    private Button submitBtn1;

    @FXML
    private TextField rtf_email;

    @FXML
    private TextField rtf_pass;

    @FXML
    private RadioButton radio_1;

    @FXML
    private RadioButton radio_2;

    @FXML
    private Text myDate;
    @FXML

    private DatePicker rtxt_date;


    @FXML
    void backBtnAction(ActionEvent event) {
        goBack();
    }

    @FXML
    void submitBtnClicked(ActionEvent event) {

        String r = "";
        if (radio_1.isSelected()) {
            r = "male";
        }

        if(radio_2.isSelected()){
            r = "female";
        }



        if ((!rtf_name.getText().trim().equals(""))
                && (!rtf_name2.getText().trim().equals(""))
                && (!rtf_email.getText().trim().equals(""))
                && (!rtf_pass.getText().trim().equals(""))) {
            try {
                FileWriter fw = new FileWriter("src/sample/receiption/adminauth.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                String addNewPerson = rtf_name.getText().trim() + ";;" + rtf_name2.getText().trim() + ";;" + rtf_email.getText().trim() + ";;" + rtf_pass.getText() + ";;" + r;
                rtf_name.setText("");
                rtf_name2.setText("");
                rtf_email.setText("");
                rtf_pass.setText("");
                out.println(addNewPerson);
                out.close();
                bw.close();
                fw.close();

                goBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void goBack() {
        Parent root;
        FXMLSceneChanger sceneChanger = FXMLSceneChanger.load("receiption/adminLogin.fxml");

        root = sceneChanger.root;
        Scene scene = new Scene(root);
        Main.primaryStage.setScene(scene);
    }

}
