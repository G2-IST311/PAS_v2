/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.EmployeeList;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class LoginUIController implements Initializable {

    @FXML private TextField empIdField;
    @FXML private Button loginButton;
    @FXML private PasswordField passwordField;
    @FXML private Button quitButton;
    @FXML private Label statusLabel;
    
    private String empID;
    private String password;
    private EmployeeList employeeList;
    
    private Employee currentEmployee;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            employeeList = new EmployeeList();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        statusLabel.setText("");

    }  
    
    
    
    public void LoginBtnClicked(ActionEvent event) throws IOException {

        empID = empIdField.getText();
        
        password = passwordField.getText();

        if(empID.equals("") || password.equals("")){
            statusLabel.setText("One of the fields is empty");
        } 
        else 
        {
            boolean isEmpId = employeeList.doesEmpIdExist(empID);

            if(isEmpId){
                Employee tempEmp = employeeList.findEmployee(empID);
                currentEmployee = tempEmp;
                
                if(tempEmp.authenticate(empID, password)) { 

                    resetScreen();
                    navigateToMainMenu(event);
                    
                    
                }
                else 
                {
                    statusLabel.setText("Login failed, incorrect password.");
                } 

            } else {
                statusLabel.setText("Login failed, user does not exist."); 
            }
        }

        
    }
    
    
    public void navigateToMainMenu(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/MainMenuUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        MainMenuController controller = loader.getController();
        controller.initData(currentEmployee, employeeList);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    public void quitBtnClicked(){
        System.exit(0);

    }
    
    private void resetScreen(){
        statusLabel.setText("");
        empIdField.setText("");
        passwordField.setText("");
        
    }
    
    
    
}
