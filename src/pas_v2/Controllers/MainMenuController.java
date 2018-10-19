/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import Model.Employee;
import Model.EmployeeList;
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
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class MainMenuController implements Initializable {

    @FXML MenuBar menuBar;
    
//    private Employee currentEmployee;
//    private EmployeeList employeeList;
//    public MainMenuController(Employee emp, EmployeeList employeeList){
//        this.currentEmployee = emp;
//        this.employeeList = employeeList;
//        
//        System.out.println("in main menu");
//       
//    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void logoutBtnClicked(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/pas_v2/Views/LoginUI.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        //Stage logoutWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage logoutWindow = (Stage)menuBar.getScene().getWindow();

        logoutWindow.setScene(tableViewScene);
        logoutWindow.show();
    }
    
}
