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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.EmployeeRoleEnum;

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
    @FXML TextField currentPWField;
    @FXML TextField newPWField;
    @FXML TextField confPWField;
    @FXML Label msgLabel;
    @FXML RadioButton rbAdmin;
    @FXML RadioButton rbOperator;

    
    private String decision;
    private Employee currentEmployee;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Employee emp){
        this.currentEmployee = emp;
        fNameTxtField.setText(currentEmployee.getFirstName());
        lNameTxtField.setText(currentEmployee.getLastName());
        msgLabel.setText("");
    }
    
    public void saveBtnClicked(ActionEvent event) throws IOException{
        decision = "yes";
        Stage popup = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        
        if (checkFields()) {
            msgLabel.setText("One or more necessary fields are not completed");
        } 
        
        else if(containsForbiddenCharacters()){
            msgLabel.setText(" the characters ~ and ; cannot be contained in any field");
        }
        
        //only commit changes if the user enters the correct current password
        else if(!validPW()){
            msgLabel.setText("Current Password input does not match existing password");
        }
        
        //only commit changes if the new password fields match
        else if(!comparePW()){
            msgLabel.setText("The new password does not match the confirmation password");
        }
        
        else{
            currentEmployee.setFirstName(fNameTxtField.getText());
            currentEmployee.setLastName(lNameTxtField.getText());   
            
            if(!newPWField.getText().equals("")){
            currentEmployee.getCredential().setPassword(newPWField.getText());
            }

            if (rbAdmin.isSelected()){
                currentEmployee.setRole(EmployeeRoleEnum.Admin);
            }

            else if (rbOperator.isSelected()){
                currentEmployee.setRole(EmployeeRoleEnum.Operator);
            }   
            
            popup.close();
        }
    }
    
    public void cnclBtnClicked(ActionEvent event) throws IOException{
        decision = "no";
        Stage popup = (Stage)((Node)event.getSource()).getScene().getWindow();
        popup.close();
    }

    public String getDecision() {
        return decision;
    }
    
    public boolean containsForbiddenCharacters() {
        boolean badchar;
        badchar = fNameTxtField.getText().contains(";") || lNameTxtField.getText().contains(";") || newPWField.getText().contains(";") || fNameTxtField.getText().contains("~") || 
                lNameTxtField.getText().contains("~") || newPWField.getText().contains("~");

        return badchar;
    }
    
    private boolean checkFields() {
        boolean anyFieldEmpty;

        String pass = new String(currentPWField.getText());
        if (fNameTxtField.getText().equals("") || lNameTxtField.getText().equals("") || pass.equals("")) {
            anyFieldEmpty = true;
        } else {
            anyFieldEmpty = false;
        }
        return anyFieldEmpty;
    }
    
    private boolean validPW(){
        return currentPWField.getText().equals(currentEmployee.getCredential().getPassword());
    }
    
    private boolean comparePW(){
        return newPWField.getText().equals(confPWField.getText());
    }
}
