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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    @FXML ImageView imageView;
    @FXML TextArea note;

    @FXML Button cancelBtn;
    @FXML Button uploadButton;
    @FXML Button createButton;
    
    @FXML Label messageLbl;
    @FXML Label waitLbl;
    
    
    private FileChooser fileChooser;
    private File filePathFromUser;
    
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
    
    public void selectPhotoButtonClicked(ActionEvent event) throws IOException 
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        this.filePathFromUser = fileChooser.showOpenDialog(stage);
        
        
        try{
            
            if (filePathFromUser != null) {
                BufferedImage bufferedImage = ImageIO.read(filePathFromUser);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                imageView.setImage(image);
            }
            
        }catch(IOException e){
            this.messageLbl.setText("Failed up upload image. Try again.");
        }
        
        
        
    }
    
    public void saveToFile(BufferedImage image) {
        
            
        try {
            // retrieve image
            BufferedImage bi = image;
            File outputfile = new File("src/pas_v2/Resources/SwimmerImages/"+this.filePathFromUser.getName());
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {

        }

    }
    
  
    
    
    public void createSwimmerButtonClicked(ActionEvent event) throws IOException 
    {
        if(checkFields()){
            
            //waitLbl.setText("Please wait..");
            createdSwimmer = new Swimmer(firstName.getText(),surname.getText(), selectedDOB,address.getText(),city.getText(),zip.getText(),state.getText(),phone.getText(),em_firstname.getText(),em_surname.getText(),em_phone.getText(),skill.getValue().toString(), status.getValue().toString(), this.filePathFromUser.getName());
           
            if(!note.getText().equals("")){
                createdSwimmer.setNote(note.getText());
            } 
            
            pool.addSwimmer(createdSwimmer);
            
            
            messageLbl.setText("");
            saveToFile(ImageIO.read(filePathFromUser));
            
            pool.writeSwimmerListFile();
 
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
        
        
        if(!(imageView.getImage()==null || skill.getSelectionModel().isEmpty() || status.getSelectionModel().isEmpty() || firstName.getText().equals("") || surname.getText().equals("") || selectedDOB.equals("") || address.getText().equals("") || city.getText().equals("") || zip.getText().equals("") || state.getText().equals("") || phone.getText().equals("") || em_firstname.getText().equals("") || em_surname.getText().equals("") || em_phone.getText().equals(""))){
            check = true;
        } 
        

        return check;
    }
    
    
}
