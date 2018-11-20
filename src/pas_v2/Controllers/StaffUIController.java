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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.EmployeeList;
import pas_v2.Models.Pool;
import pas_v2.Models.RoleEnum;
import pas_v2.Models.Swimmer;

/**
 * FXML Controller class
 *
 * @author David Ortiz, Drew Hopkins
 */
public class StaffUIController implements Initializable {

    @FXML Button newEmpBtn;
    @FXML Button editBtn;
    @FXML Button removeBtn;
    
    @FXML private TableView tableView;
    
    @FXML private TextField searchField;
    
    @FXML private TableColumn<Employee, String> nameCol;
    @FXML private TableColumn<Employee, String> userNameCol;
    @FXML private TableColumn<Employee, String> roleCol;
    
    private Pool pool;
    private Employee currentEmployee;
    private EmployeeList employeeList;
    
    private boolean isAdmin = false;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.employeeList = new EmployeeList();       
        nameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("fullName"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("userName"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("roleAsString"));
        
        tableView.getItems().setAll(employeeList.getEmployees());
        
        newEmpBtn.setDisable(true);
        removeBtn.setDisable(true);
        editBtn.setDisable(true);
    }  
    
    public void initData(Employee emp, EmployeeList employeeList, Pool pool){
        this.currentEmployee = emp;
        this.employeeList = employeeList;
        this.pool = pool;
        
        tableView.getItems().setAll(employeeList.getEmployees());
        isAdmin = currentEmployee.isFunctionPermitted(RoleEnum.CREATE_EMPLOYEE); 
        
        newEmpBtn.setDisable(!isAdmin);
    }
   
    
    public void newEmpBtnClicked(ActionEvent event) throws IOException{
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/NewEmployeeUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        NewEmployeeController controller = loader.getController();
        controller.initData(currentEmployee, employeeList, pool);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    /**
     * Directs user to employee edit screen to change password or privileges 
     * @param event occurs when edit button is clicked
     * @throws IOException 
     */
    public void editEmpBtnClicked(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/EditEmpPopup.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene scene = new Scene(tableViewParent);
        
        //access the controller and call a method
        EditEmpPopupController controller = loader.getController();
        
        Employee targetEmp = (Employee)tableView.getSelectionModel().getSelectedItem();
        controller.initData(targetEmp);
        
        //This line gets the Stage information
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        window.setScene(scene);
        window.showAndWait();
        
        if (controller.getDecision().equals("yes")){
            employeeList.writeEmployeeList();
            tableView.getItems().setAll(employeeList.getEmployees());    
        }
    }
    
    /**
     * Removes an employee from the list of employees
     * @param event occurs when remove button is clicked
     * @throws IOException 
     */
    public void removeEmpBtnClicked (ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/RemoveEmpPopup.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene scene = new Scene(tableViewParent);
        
        //access the controller and call a method
        RemoveEmpPopoupController controller = loader.getController();
        
        //This line gets the Stage information
        Stage window = new Stage();
        Employee targetEmp = (Employee)tableView.getSelectionModel().getSelectedItem();
        controller.getEmpNameLbl().setText(targetEmp.getFullName());
        window.initModality(Modality.APPLICATION_MODAL);
        window.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        window.setScene(scene);
        window.showAndWait();
        
        if (controller.getDecision().equals("yes")){
            employeeList.removeEmployee((Employee)tableView.getSelectionModel().getSelectedItem());
            employeeList.writeEmployeeList();
            tableView.getItems().setAll(employeeList.getEmployees());         
        }
    }
    
    public void navigateToMainMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/MainMenuUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        MainMenuController controller = loader.getController();
        controller.initData(currentEmployee, employeeList);
        controller.setPool(this.pool);
        
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void userClickedTable(){
        if(isAdmin){
            removeBtn.setDisable(false);
            editBtn.setDisable(false);
        }
    }
    
    public void performSearch(){
        String keyword = searchField.getText();
        
        tableView.getItems().setAll(employeeList.searchEmployees(keyword));
    }
}
