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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class StaffUIController implements Initializable {

    @FXML Button newEmployeeButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void NewEmployeeButtonClicked(ActionEvent event) throws IOException{
        
        //SampleController controller = loader.getController();
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/pas_v2/Views/NewEmployeeUI.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage newEmployeeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();

        newEmployeeWindow.setScene(tableViewScene);
        newEmployeeWindow.show();
        
        
    }
    
}
