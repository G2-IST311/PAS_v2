/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.Pool;
import pas_v2.Models.Swimmer;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class RegisterSwimmerController implements Initializable {

    
    @FXML TextField firstName;
    @FXML TextField surname;
    @FXML DatePicker dob;
    @FXML TextField address;
    @FXML TextField city;
    @FXML TextField zip;
    @FXML TextField state;
    @FXML TextField phone;
    @FXML TextField em_firstname;
    @FXML TextField em_surname;
    @FXML TextField em_phone;
    
    @FXML ChoiceBox skill;
    @FXML ChoiceBox status;
    @FXML ImageView image;
    @FXML TextArea note;

    @FXML Button cancelBtn;
    @FXML Button uploadButton;
    @FXML Button createButton;
    
    @FXML Label messageLbl;
    
    
    
    private Employee currentEmployee;
    private Swimmer createdSwimmer;
    private Pool pool;
    
    
    private String selectedDOB;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void initData(Employee emp, Pool pool){
        this.currentEmployee = emp;
        this.pool = pool;
    }
    
    
    public void createSwimmerButtonClicked(ActionEvent event) throws IOException 
    {
        if(checkFields()){
            
            createdSwimmer = new Swimmer(firstName.getText(),surname.getText(), selectedDOB,address.getText(),city.getText(),zip.getText(),state.getText(),phone.getText(),em_firstname.getText(),em_surname.getText(),em_phone.getText(),skill.getValue().toString(), status.getValue().toString() );
           
            if(!note.getText().equals("")){
                createdSwimmer.setNote(note.getText());
            } 
            
            pool.addSwimmer(createdSwimmer);
            messageLbl.setText("");
 
            navigateToFindSwimmer(event);
            
            
        } else {
            messageLbl.setText("All fields need to be filled out");

        }
        
    }
    
    public void navigateToFindSwimmer(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/FindSwimmerUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        FindSwimmerController controller = loader.getController();
        controller.initData(currentEmployee, pool);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    
            
    private boolean checkFields(){
        boolean check = false;
        selectedDOB = "";
                
        try{
            
            selectedDOB = dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
 
        } catch(java.lang.NullPointerException e){
            
            selectedDOB = "";
            
        }
        
        
        if(!(skill.getSelectionModel().isEmpty() || status.getSelectionModel().isEmpty() || firstName.getText().equals("") || surname.getText().equals("") || selectedDOB.equals("") || address.getText().equals("") || city.getText().equals("") || zip.getText().equals("") || state.getText().equals("") || phone.getText().equals("") || em_firstname.getText().equals("") || em_surname.getText().equals("") || em_phone.getText().equals(""))){
            check = true;
        } 
        

        return check;
    }
    
    
}
