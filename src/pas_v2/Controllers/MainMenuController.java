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
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.EmployeeList;
import pas_v2.Models.RoleEnum;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class MainMenuController implements Initializable {

    @FXML MenuBar menuBar;
    @FXML Label greetingLable;
    @FXML Button findSwimmerBtn;
    @FXML Button viewPoolBtn;
    @FXML Button reportsBtn;
    @FXML Button staffBtn;
    
    private Employee currentEmployee;
    private EmployeeList employeeList;

    
    public void initData(Employee emp, EmployeeList employeeList){
        this.currentEmployee = emp;
        this.employeeList = employeeList;
        
        welcome(emp.getFirstName());
        
        //System.out.println(currentEmployee.getFirstName());

        //reportsBtn.setDisable(!currentEmployee.isFunctionPermitted(RoleEnum.VIEW_REPORTS));
        
    }
    
    public void setEmployee(Employee emp){
        this.currentEmployee = emp;
        
        welcome(emp.getFirstName());
    }
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void findSwimmerBtnClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/FindSwimmerUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        FindSwimmerController controller = loader.getController();
        controller.initData(currentEmployee);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    
    public void staffButtonClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/StaffUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        StaffUIController controller = loader.getController();
        controller.initData(currentEmployee, employeeList);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    public void viewPoolButtonClicked(ActionEvent event) throws IOException
    {
        System.out.println("View Pool Clicked");
        
    }
    
    public void reportsButtonClicked(ActionEvent event) throws IOException
    {
        System.out.println("Staff Button Clicked");
        
    }
    
   
    public void welcome(String name){
        greetingLable.setText("Welcome, " + name + "!");
    }
    
    
    public void logoutBtnClicked(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/pas_v2/Views/LoginUI.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage logoutWindow = (Stage)menuBar.getScene().getWindow();

        logoutWindow.setScene(tableViewScene);
        logoutWindow.show();
    }
    
}
