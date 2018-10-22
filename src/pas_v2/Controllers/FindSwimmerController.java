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
import javafx.scene.control.*;
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.Pool;
import pas_v2.Models.RoleEnum;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class FindSwimmerController implements Initializable {

    @FXML Button registerSwimmerButton;
    private Employee currentEmployee;
    private Pool pool;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }   
    
    public void initData(Employee emp, Pool pool){
        this.currentEmployee=emp;
        this.pool = pool;
        
        registerSwimmerButton.setDisable(!currentEmployee.isFunctionPermitted(RoleEnum.CREATE_PROFILE));
    }
    
    public void RegisterSwimmerButtonClicked(ActionEvent event) throws IOException{
        System.out.println("Register Swimmer");
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/RegisterSwimmerUI.fxml"));
        Parent viewParent = loader.load();
        
        Scene registerSwimmerScene = new Scene(viewParent);
        
        //access the controller and call a method
        RegisterSwimmerController controller = loader.getController();
        controller.initData(currentEmployee, pool);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(registerSwimmerScene);
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
        controller.setEmployee(currentEmployee);
        controller.setPool(pool);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
}
