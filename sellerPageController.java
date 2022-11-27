package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerPageController {

    @FXML
    TextField name;

    @FXML
    TextField price;

    @FXML
    TextField email;

    @FXML
    public void Add(MouseEvent event) throws Exception {
        ResultSet res = HelloApplication.connection.executeQuery("Select max(productid) from product");
        int productID = 0;
        if(res.next()){
            productID = res.getInt("max(productid)") + 1;
        }
        String query = String.format("Insert Into product Values( '%s', %s, '%s' ,%s)", name.getText(),productID,email.getText(),price.getText());
        int response = HelloApplication.connection.executeUpdate(query);

        if(response > 0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Add Products");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("A new product is added");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Add Products");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("A new product is not added");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }


    public void Back(MouseEvent event) throws Exception {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("login page.fxml"));
        HelloApplication.root.getChildren().add(loginPage);
    }
    }


