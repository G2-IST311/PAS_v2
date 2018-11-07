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
 *
 * Checks with the user to confirm they want to logout
 * @author Drew Hopkins
 */
public class RemoveEmpPopoupController implements Initializable {
    @FXML Button confirmBtn;
    @FXML Button cancelBtn;
    @FXML Label empNameLbl;
    
    private String decision;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void confirmBtnClicked(ActionEvent event) throws IOException{
        decision = "yes";
        Stage popup = (Stage)((Node)event.getSource()).getScene().getWindow();
        popup.close();
    }
    
    public void cancelBtnClicked(ActionEvent event) throws IOException{
        decision = "no";
        Stage popup = (Stage)((Node)event.getSource()).getScene().getWindow();
        popup.close();
    }

    public String getDecision() {
        return decision;
    }
    
}
