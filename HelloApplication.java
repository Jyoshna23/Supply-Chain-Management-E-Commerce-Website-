package com.example.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {

    public static DatabaseConnection connection;
    public static Group root;

    public static String emailid;

    @Override
    public void start(Stage stage) throws Exception {
        emailid = "";
        connection = new DatabaseConnection();

        root = new Group();
        Header header = new Header();

        BuyerPage products = new BuyerPage();
        ListView<HBox> productsList = products.showproducts();


        AnchorPane productpane = new AnchorPane();
        productpane.setLayoutX(150);
        productpane.setLayoutY(100);
        productpane.getChildren().add(productsList);

        root.getChildren().addAll(header.root,productpane);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("SupplyChain !");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{

            try {
                connection.con.close();
                System.out.println("connection closed");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });
    }

    public static void main(String[] args) {
        launch();
    }
}