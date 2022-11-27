package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Orders {

    public void orders(String productId) throws Exception {
        ResultSet res = HelloApplication.connection.executeQuery("Select max(orderId) from orders");
        int orderId = 0;
        if(res.next()){
           orderId =  res.getInt("max(orderId)") + 1;
        }
        String query = String.format("Insert into orders values(%s,%s,'%s')",orderId,productId,HelloApplication.emailid);
        int response = HelloApplication.connection.executeUpdate(query);
        if(response > 0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Orders");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("A new order is placed!!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
            System.out.println("order is placed");
        }
    }
}
