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
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.EmployeeList;
import pas_v2.Models.RoleEnum;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class StaffUIController implements Initializable {

    @FXML Button newEmployeeButton;
    
    private Employee currentEmployee;
    private EmployeeList employeeList;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            employeeList = new EmployeeList();
            employeeList.refreshEmployeeList();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StaffUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void initData(Employee emp, EmployeeList employeeList){
        this.currentEmployee = emp;
        this.employeeList = employeeList;
        

        newEmployeeButton.setDisable(!currentEmployee.isFunctionPermitted(RoleEnum.CREATE_EMPLOYEE));
       
    }
   
    
    public void NewEmployeeButtonClicked(ActionEvent event) throws IOException{
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/NewEmployeeUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        NewEmployeeController controller = loader.getController();
        controller.initData(currentEmployee, employeeList);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
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
    
}
