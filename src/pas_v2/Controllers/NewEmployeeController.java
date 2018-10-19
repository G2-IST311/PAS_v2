/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class NewEmployeeController implements Initializable {

    @FXML private Label MsgLabel;
    //private javax.swing.JList<String> RoleList;
    @FXML private Button cnclBtn;
    @FXML private PasswordField empPWField;
    @FXML private TextField fNameTxtField;
   
    @FXML private TextField lNameTxtField;
    @FXML private Button submitBtn;
    
    private MainMenuController MMC; 
    public NewEmployeeController(MainMenuController _MMC)
    {
      MMC = _MMC;  
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    public void submitButtonClicked(ActionEvent ae){ 

          
        if(checkFields())
        {    
           MsgLabel.setText("One or more fields are not completed");     
        }
        else
        {
           if (containsForbiddenCharacters())
            {
                MsgLabel.setText(" the characters ~ and ; cannot be contained in any field");
                resetFields();
            }

            else                
            {    

                MsgLabel.setText("Submitted Successfully");
                //MMC.saveEmployee(sdf.getRole(), fNameTxtField.getText(), lNameTxtField.getText(), empPWField.getText());
                
            }     
        }    
       
    }
    
    public void resetFields()
    {
        fNameTxtField.setText("");
        lNameTxtField.setText("");
        empPWField.setText("");
        

    }  
    
    public boolean containsForbiddenCharacters()
    {
        boolean badchar; 
        badchar = fNameTxtField.getText().contains(";") || lNameTxtField.getText().contains(";") || empPWField.getText().contains(";") || fNameTxtField.getText().contains("~") || lNameTxtField.getText().contains("~") || empPWField.getText().contains("~");

        return badchar;
    } 
    
    private boolean checkFields()
    {
        boolean anyFieldEmpty; 

        String pass = new String (empPWField.getText());
        if (fNameTxtField.getText().equals("") || lNameTxtField.getText().equals("") || pass.equals(""))
        {
            anyFieldEmpty = true;
        }    
        else
        {
            anyFieldEmpty = false; 
        }
        return anyFieldEmpty;
    } 
    
}
