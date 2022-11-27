package com.example.supplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerPage {

    ListView<HBox> products;

    ListView<HBox> showproductsbyName(String search) throws SQLException {
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        products = new ListView<>();


        Label name = new Label();
        Label productprice = new Label();
        Label id = new Label();

        HBox details = new HBox();

        name.setMinWidth(60);
        productprice.setMinWidth(60);
        id.setMinWidth(60);
        name.setText(" name ");
        productprice.setText(" price ");
        id.setText(" productID ");

        details.getChildren().addAll(id, name, productprice);
        productsList.add(details);


        while (res.next()) {
            if (res.getString("productName").toLowerCase().contains(search.toLowerCase())) {

                Label productName = new Label();
                Label price = new Label();
                Label productID = new Label();
                Button buy = new Button();

                HBox productDetails = new HBox();

                productName.setMinWidth(60);
                price.setMinWidth(60);
                productID.setMinWidth(60);
                buy.setText("buy");

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailid.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Please Login to Buy");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {
                            Orders placeorder = new Orders();
                            try {
                                placeorder.orders(productID.getText());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("You clicked on buy button");
                        }
                    }
                });
                productName.setText(res.getString("productName"));
                price.setText(res.getString("price"));
                productID.setText("" + res.getInt("productID"));

                productDetails.getChildren().addAll(productID, productName, price, buy);
                productsList.add(productDetails);

            }
            if(productsList.size() == 1){
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Search Result");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("No product found!!");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }

        }
        products.setItems(productsList);
        return products;
    }

    ListView<HBox> showproducts()  throws SQLException{
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        products = new ListView<>();


        Label name = new Label();
        Label productprice = new Label();
        Label id = new Label();

        HBox details = new HBox();

        name.setMinWidth(60);
        productprice.setMinWidth(60);
        id.setMinWidth(60);
        name.setText(" name ");
        productprice.setText(" price ");
        id.setText(" productID ");

        details.getChildren().addAll(id,name,productprice);
        productsList.add(details);




        while(res.next()){
            Label productName = new Label();
            Label price = new Label();
            Label productID = new Label();
            Button buy = new Button();

            HBox productDetails = new HBox();

            productName.setMinWidth(60);
            price.setMinWidth(60);
            productID.setMinWidth(60);
            buy.setText("buy");

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (HelloApplication.emailid.equals("")) {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Please Login to Buy");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else
                    {
                        Orders placeorder = new Orders();
                        try {
                            placeorder.orders(productID.getText());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("You clicked on buy button");
                    }
                }
            });
            productName.setText(res.getString("productName"));
            price.setText(res.getString("price"));
            productID.setText(""+ res.getInt("productID"));

            productDetails.getChildren().addAll(productID,productName,price,buy);
            productsList.add(productDetails);

        }
        products.setItems(productsList);
        return products;
    }
}
