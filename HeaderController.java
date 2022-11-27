package com.example.supplychain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {

    @FXML
    Button loginButton;

    @FXML
    Label email;

    @FXML
    TextField searchtext;

    @FXML
    Button logoutButton;

    @FXML
    public void initialize(){
        if(!HelloApplication.emailid.equals("")){
            loginButton.setOpacity(0);
            email.setText(HelloApplication.emailid);
        }
    }

    @FXML
    public void login(MouseEvent event) throws IOException {
        AnchorPane loginpage = FXMLLoader.load(getClass().getResource("Login page.fxml"));
       HelloApplication.root.getChildren().add(loginpage);
    }


    @FXML
    public void search(MouseEvent event) throws IOException, SQLException {

        Header header = new Header();
        BuyerPage products = new BuyerPage();
        AnchorPane productspage = new AnchorPane();
        productspage.setLayoutX(150);
        productspage.setLayoutY(100);
        productspage.getChildren().add(products.showproductsbyName(searchtext.getText()));
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root, productspage);
    }

    @FXML
    public void logout(MouseEvent event) throws IOException, SQLException {

        if(logoutButton.getOpacity() == 0) {
            logoutButton.setOpacity(1);

            logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.emailid = "";
                    logoutButton.setOpacity(0);


                    Header header = null;
                    try {
                       header = new Header();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    HelloApplication.root.getChildren().add(header.root);
                }
            });
        }
        else{
            logoutButton.setOpacity(0);
        }
    }
}
