/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author David Ortiz
 */
public class PAS_v2 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/pas_v2/Views/LoginUI.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("PAS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        /*  
            Employee list (retrieved from "employees.txt" in root directory:
            empID is lastname + first 2 chars of firstname.
        
            empID = scottmi  
            password = mypass
        
            empID = shrutedw
            password = sheriff
        
            empID = squarepantssp
            password = pineapple
        
            empID = starpa
            password = rock
                
        */
    }
    
}
