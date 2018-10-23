/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pas_v2.Models.Employee;
import pas_v2.Models.Pool;
import pas_v2.Models.RoleEnum;
import pas_v2.Models.Swimmer;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class ViewSwimmerProfileController implements Initializable {
    private Employee currentEmployee;
    private Swimmer selectedSwimmer;
    private Pool pool;
    
    @FXML Label userLabel;
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
    @FXML ImageView imageView;
    @FXML TextArea note;

    @FXML Button uploadButton;
    @FXML Button updateButton;

    @FXML Label messageLbl;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(Employee emp, Swimmer swimmer, Pool pool){
        this.currentEmployee = emp;
        this.selectedSwimmer = swimmer;
        this.pool = pool;
        
        updateButton.setDisable(!currentEmployee.isFunctionPermitted(RoleEnum.EDIT_PROFILE));
        setFields(swimmer);
    }
    
    private void setFields(Swimmer swimmer){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	
	
	//convert String to LocalDate
	LocalDate localDate = LocalDate.parse(swimmer.getDob(), formatter);
        
        userLabel.setText(swimmer.getName());
        firstName.setText(swimmer.getName());
        surname.setText(swimmer.getLastname());
        dob.setValue(localDate);
        address.setText(swimmer.getAddress());
        city.setText(swimmer.getCity());
        zip.setText(swimmer.getZip());
        state.setText(swimmer.getState());
        phone.setText(swimmer.getPhone());
        em_firstname.setText(swimmer.getEm_firstname());
        em_surname.setText(swimmer.getEm_lastname());
        em_phone.setText(swimmer.getEm_phone());
        skill.setValue(swimmer.getSkill());
        status.setValue(swimmer.getStatus());
        note.setText(swimmer.getNote());
        
        try{
            
            //String filePath = "src/pas_v2/Resources/SwimmerImages/"+swimmer.getPhotoPath();
            File filePath = new File("src/pas_v2/Resources/SwimmerImages/"+swimmer.getPhotoPath());

            
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            imageView.setImage(image);
            
            
        }catch(IOException e){
            System.out.println("Failed up upload image.");
        }
        
       
    }
    
    public void deleteButtonClicked(ActionEvent event) throws IOException
    {
        System.out.println("Delete");

        /*
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
                */
        
    }
    
    public void backBtnClicked(ActionEvent event) throws IOException
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
    
    
    
}
