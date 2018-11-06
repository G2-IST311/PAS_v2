/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Checks with the user to confirm they want to quit the program.
 * @author Drew Hopkins
 */
public class QuitPopupController implements Initializable {

    @FXML Button confirmBtn;
    @FXML Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }   
    
    //exit program if user selects confirm button
    public void confirmBtnClicked(ActionEvent event) throws IOException{
            System.exit(0);
    }
    
    //return to login screen if user selects cancel
    public void cancelBtnClicked(ActionEvent event) throws IOException{
        Stage popup = (Stage)((Node)event.getSource()).getScene().getWindow();
        popup.close();
    }
    
}
