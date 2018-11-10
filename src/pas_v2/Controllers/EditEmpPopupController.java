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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class EditEmpPopupController implements Initializable {
    @FXML Button cnclBtn;
    @FXML Button saveBtn;
    @FXML TextField fNameTxtField;
    @FXML TextField lNameTxtField;
    @FXML ToggleGroup roleSelector;
    @FXML RadioButton rbAdmin;
    @FXML RadioButton rbOperator;

    
    private String decision;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void saveBtnClicked(ActionEvent event) throws IOException{
        decision = "yes";
        Stage popup = (Stage)((Node)event.getSource()).getScene().getWindow();
        popup.close();
    }
    
    public void cnclBtnClicked(ActionEvent event) throws IOException{
        decision = "no";
        Stage popup = (Stage)((Node)event.getSource()).getScene().getWindow();
        popup.close();
    }

    public String getDecision() {
        return decision;
    }
}
