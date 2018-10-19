/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class FindSwimmerController implements Initializable {

    @FXML Button registerSwimmerButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }   
    
    public void RegisterSwimmerButtonClicked(ActionEvent event) throws IOException{
        System.out.println("Register Swimmer");
        /*
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/pas_v2/Views/RegisterSwimmerUI.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage newEmployeeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();

        newEmployeeWindow.setScene(tableViewScene);
        newEmployeeWindow.show();
        */
        
    }
    
}
