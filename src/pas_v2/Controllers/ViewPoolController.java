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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pas_v2.Models.ActiveSwimmerData;
import pas_v2.Models.Employee;
import pas_v2.Models.EmployeeList;
import pas_v2.Models.Pool;
import pas_v2.Models.Swimmer;

/**
 * FXML Controller class
 *
 * @author Drew Hopkins
 */
public class ViewPoolController implements Initializable {
    
    @FXML Button checkoutButton;
    @FXML Button viewProfileBtn;
    
    @FXML private TableColumn<ActiveSwimmerData, String> nameCol;
    @FXML private TableColumn<ActiveSwimmerData, String> ageCol;
    @FXML private TableColumn<ActiveSwimmerData, String> checkInCol;
    @FXML private TableColumn<ActiveSwimmerData, String> durationCol;
    @FXML private TableColumn<ActiveSwimmerData, String> genderCol;
    
    @FXML private TableView tableView;
    
    @FXML private TextField searchField;
    
    private Employee currentEmployee;
    private Pool pool;
    private EmployeeList employeeList;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.pool = new Pool();
        
        nameCol.setCellValueFactory(new PropertyValueFactory<ActiveSwimmerData, String>("fullName"));
        ageCol.setCellValueFactory(new PropertyValueFactory<ActiveSwimmerData, String>("age"));
        genderCol.setCellValueFactory(new PropertyValueFactory<ActiveSwimmerData, String>("gender"));
        checkInCol.setCellValueFactory(new PropertyValueFactory<ActiveSwimmerData, String>("checkIn"));
        durationCol.setCellValueFactory(new PropertyValueFactory<ActiveSwimmerData, String>("duration"));
        
        
        pool.constructActivePool(pool.getActiveSwimmers());
        tableView.getItems().setAll(pool.getActiveSwimmers());
        
        
    }
    
    public void initData(Employee emp, EmployeeList empList, Pool pool){
        this.currentEmployee = emp;
        this.employeeList = empList;
        this.pool = pool;
        pool.constructActivePool(pool.getActiveSwimmers());
        tableView.getItems().setAll(pool.getActivePool());
    } 
    
    public void viewProfileBtnClicked(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/ViewSwimmerProfileUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ViewSwimmerProfileController controller = loader.getController();
        
        try{
            ActiveSwimmerData tempActive = (ActiveSwimmerData)tableView.getSelectionModel().getSelectedItem();
            
            Swimmer tempSwimmer = new Swimmer(tempActive.getName(), tempActive.getLastName(),tempActive.getGenderEnum(), tempActive.getDob(),tempActive.getAddress() ,tempActive.getCity(), tempActive.getZip(),tempActive.getState(),tempActive.getPhone(), tempActive.getEm_firstname(),tempActive.getEm_lastname(), tempActive.getEm_phone(), tempActive.getSkill(),tempActive.getStatus(), tempActive.getPhotoPath());
            tempSwimmer.setVisits(tempActive.getVisits());
            tempSwimmer.setNote(tempActive.getNote());
            
            controller.initData(currentEmployee, tempSwimmer, employeeList, pool, "ViewPool");
            controller.setCheckedStatusLabel(true);
            controller.toggleEditableFields(false);     
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch(NullPointerException e){
            System.out.println("No swimmer selected!");
        }
    }
        
    public void checkoutButtonClicked(){
        try{
            ActiveSwimmerData targetData = (ActiveSwimmerData)tableView.getSelectionModel().getSelectedItem();
            pool.changeSwimmerStatus(targetData.getSwimmer(), false);
            
            pool.constructActivePool(pool.getActiveSwimmers());
            tableView.getItems().setAll(pool.getActivePool());
           
            this.viewProfileBtn.setDisable(true);
            this.checkoutButton.setDisable(true);
            
        } catch(NullPointerException e){
            System.out.println("No swimmer selected!");
        }  
    }
    
    public void userClickedTable(){
        viewProfileBtn.setDisable(false);
        checkoutButton.setDisable(false);
    }
    
       
    
    public void performSearch(){
        String keyword = searchField.getText();
        
        pool.constructActivePool(pool.getActiveSwimmers());
        
        
        tableView.getItems().setAll(pool.searchActiveSwimmers(keyword));
    }
    
    public void navigateToMainMenu(ActionEvent event) throws IOException{
        pool.clearActiveSwimmers();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/MainMenuUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        MainMenuController controller = loader.getController();
        controller.initData(currentEmployee, employeeList);
        controller.setPool(pool);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
}
