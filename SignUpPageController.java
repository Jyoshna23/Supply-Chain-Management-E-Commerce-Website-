package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;

public class SignUpPageController {


    @FXML
    TextField UserName;

    @FXML
    TextField emailId;

    @FXML
    PasswordField password;

    @FXML
    TextField UserType;

    @FXML
    public void SignUp(MouseEvent event) throws Exception {


        String query = String.format("Insert Into User Values( '%s', '%s', '%s' ,'%s')", UserName.getText(),emailId.getText(),UserType.getText(),password.getText());
        int response = HelloApplication.connection.executeUpdate(query);

        if(response > 0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("SignUp");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Signed Up Successfully!!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }

        String query1 = String.format("Select * from user");
        ResultSet res = HelloApplication.connection.executeQuery(query1);

        if(res.next()){
            HelloApplication.emailid = res.getString("emailid");
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("SignUp");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Already Signed!! Try Another emailid.");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }


        if(!HelloApplication.emailid.equals("")){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("SignUp");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Please Enter the details to SignUp");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }

    @FXML

    public void Continue(MouseEvent event) throws IOException {

        AnchorPane Continue = FXMLLoader.load(getClass().getResource("Login page.fxml"));
        HelloApplication.root.getChildren().add(Continue);
    }


}
