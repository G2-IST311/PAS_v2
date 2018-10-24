/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.Pool;
import pas_v2.Models.RoleEnum;
import pas_v2.Models.Swimmer;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class FindSwimmerController implements Initializable {

    @FXML Button viewProfileBtn;
    @FXML Button registerSwimmerButton;
    @FXML Button checkoutButton;
    @FXML Button checkinButton;
    
    
    @FXML private TableView tableView;
    
    @FXML private TextField searchField;
    
    @FXML private TableColumn<Swimmer, String> nameCol;
    @FXML private TableColumn<Swimmer, String> addressCol;
    @FXML private TableColumn<Swimmer, String> ageCol;
    @FXML private TableColumn<Swimmer, String> skillCol;
    @FXML private TableColumn<Swimmer, String> statusCol;
    @FXML private TableColumn<Swimmer, String> visitStatusCol;
    @FXML private TableColumn<Swimmer, String> noteCol;

    private Employee currentEmployee;
    private Pool pool;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.pool = new Pool();
        
        nameCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("fullName"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("dob"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("fullAddress"));
        skillCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("skill"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("status"));
        visitStatusCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("checkedStatus"));
        noteCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("note"));

        tableView.getItems().setAll(pool.getSwimmers());
        
    } 
    
    
    public void initData(Employee emp, Pool pool){
        this.currentEmployee=emp;
        this.pool = pool;
        
        tableView.getItems().setAll(pool.getSwimmers());

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
    
    public void performSearch(){
        String keyword = searchField.getText();
        
        
        tableView.getItems().setAll(pool.searchSwimmer(keyword));

        
    }
    
    public void viewProfileBtnClicked(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/ViewSwimmerProfileUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ViewSwimmerProfileController controller = loader.getController();
        
        try{
            controller.initData(currentEmployee, (Swimmer)tableView.getSelectionModel().getSelectedItem(), pool);
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch(NullPointerException e){
            System.out.println("No swimmer selected!");
        }
    }
    
    
    public void checkinButtonClicked(){
        try{
            pool.changeSwimmerStatus((Swimmer)tableView.getSelectionModel().getSelectedItem(), true);
            checkinButton.setDisable(true);
            checkoutButton.setDisable(false);
            tableView.getItems().setAll(pool.getSwimmers());

                    
        } catch(NullPointerException e){
            System.out.println("No swimmer selected!");
        }
    }
    
    public void checkoutButtonClicked(){
        try{
            pool.changeSwimmerStatus((Swimmer)tableView.getSelectionModel().getSelectedItem(), false);
            checkinButton.setDisable(false);
            checkoutButton.setDisable(true);
            tableView.getItems().setAll(pool.getSwimmers());

            
        } catch(NullPointerException e){
            System.out.println("No swimmer selected!");
        }
        
    }
    
    
    
    public void userClickedTable(){
        viewProfileBtn.setDisable(false);
        
        try{
            Swimmer temp = (Swimmer)tableView.getSelectionModel().getSelectedItem();
            String status = temp.getCheckedStatus();
            
            switch (status) {
                case "Checked out":
                    checkinButton.setDisable(false);
                    checkoutButton.setDisable(true);
                    break;
                case "Checked in":
                    checkinButton.setDisable(true);
                    checkoutButton.setDisable(false);
                    break;
            }
            
           
        } catch(NullPointerException e){
            System.out.println("No swimmer selected!");
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
        controller.setEmployee(currentEmployee);
        controller.setPool(pool);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
}
