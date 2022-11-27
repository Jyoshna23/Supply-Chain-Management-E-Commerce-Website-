package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.format;

public class loginPageController {

    @FXML
    TextField email;

    @FXML
    PasswordField password;

    public void login(MouseEvent event) throws SQLException, IOException {
        String query = String.format("Select * from user where emailiD = '%s' AND password = '%s'", email.getText(), password.getText());
        ResultSet res = HelloApplication.connection.executeQuery(query);

        if (res.next()) {
            String usertype = res.getString("UserType");
            HelloApplication.emailid = res.getString("emailid");
            if (usertype.equals("Buyer")) {
                System.out.println("logged in as buyer");

                BuyerPage products = new BuyerPage();

                ListView<HBox> productsList = products.showproducts();
                Header header = new Header();

                AnchorPane productpane = new AnchorPane();
                productpane.setLayoutX(150);
                productpane.setLayoutY(100);

                productpane.getChildren().add(productsList);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root, productpane);
            } else {
                System.out.println("Logged in as seller");
                AnchorPane sellerPage = FXMLLoader.load(getClass().getResource("sellerpage.fxml"));
                HelloApplication.root.getChildren().add(sellerPage);
            }

        } else {
            Dialog<String> dialog = new Dialog<>();
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Login failed!! Please try again");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }


    @FXML
    public void SignUp(MouseEvent event) throws IOException {
        AnchorPane signup = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        HelloApplication.root.getChildren().add(signup);
    }

    public void Back(MouseEvent event) throws Exception {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("Header.fxml"));
        HelloApplication.root.getChildren().add(loginPage);
    }
}

