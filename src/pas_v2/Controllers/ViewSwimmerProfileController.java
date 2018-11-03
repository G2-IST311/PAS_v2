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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pas_v2.Models.Employee;
import pas_v2.Models.FieldTypeEnum;
import pas_v2.Models.GenderEnum;
import pas_v2.Models.Pool;
import pas_v2.Models.RoleEnum;
import pas_v2.Models.Swimmer;
import pas_v2.Models.Validator;
import pas_v2.Models.Visit;

/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class ViewSwimmerProfileController implements Initializable {

    private Employee currentEmployee;
    private Swimmer selectedSwimmer;
    private Pool pool;
    private String selectedDOB;
    private String backScene;

    private FileChooser fileChooser;
    private File filePathFromUser;

    private boolean photoChanged = false;
    private ArrayList<Visit> visits;

    @FXML
    Label statusLabel;
    @FXML
    Label userLabel;
    @FXML
    TextField firstName;
    @FXML
    TextField surname;
    @FXML
    DatePicker dob;
    @FXML
    TextField address;
    @FXML
    TextField city;
    @FXML
    TextField zip;
    @FXML
    TextField state;
    @FXML
    TextField phone;
    @FXML
    TextField em_firstname;
    @FXML
    TextField em_surname;
    @FXML
    TextField em_phone;

    @FXML
    ChoiceBox gender;
    @FXML
    ChoiceBox skill;
    @FXML
    ChoiceBox status;
    @FXML
    ImageView imageView;
    @FXML
    TextArea note;

    @FXML
    Button uploadButton;
    @FXML
    Button updateButton;
    @FXML
    Button deleteButton;

    @FXML
    Label messageLbl;

    @FXML
    TableView historyTable;
    @FXML
    private TableColumn<Visit, String> dateCol;
    @FXML
    private TableColumn<Visit, String> checkinCol;
    @FXML
    private TableColumn<Visit, String> durationCol;
    @FXML
    private TableColumn<Visit, String> checkoutCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        visits = new ArrayList<>();
        gender.getItems().setAll(GenderEnum.values());
        
        dateCol.setCellValueFactory(new PropertyValueFactory<Visit, String>("day"));
        checkinCol.setCellValueFactory(new PropertyValueFactory<Visit, String>("checkInTime"));
        durationCol.setCellValueFactory(new PropertyValueFactory<Visit, String>("totalDuration"));
        checkoutCol.setCellValueFactory(new PropertyValueFactory<Visit, String>("checkOutTime"));
        
        //validation rules
        firstName.textProperty().addListener(new Validator(firstName, FieldTypeEnum.NAME));     
        address.textProperty().addListener(new Validator(address, FieldTypeEnum.ADDRESS));
        city.textProperty().addListener(new Validator(city, FieldTypeEnum.NAME));
        zip.textProperty().addListener(new Validator(zip, FieldTypeEnum.ZIP));
        state.textProperty().addListener(new Validator(state, FieldTypeEnum.STATE));
        phone.textProperty().addListener(new Validator(phone, FieldTypeEnum.PHONE));
        em_firstname.textProperty().addListener(new Validator(em_firstname, FieldTypeEnum.NAME));
        em_surname.textProperty().addListener(new Validator(em_surname, FieldTypeEnum.NAME));
        em_phone.textProperty().addListener(new Validator(em_phone, FieldTypeEnum.PHONE));
        
        
    }

    public void initData(Employee emp, Swimmer swimmer, Pool pool, String backScene) {
        this.currentEmployee = emp;
        this.selectedSwimmer = swimmer;
        this.pool = pool;
        this.backScene = backScene;
        
        visits = selectedSwimmer.getVisits();
        historyTable.getItems().setAll(visits);

        setFields(this.selectedSwimmer);
        toggleEditableFields(currentEmployee.isFunctionPermitted(RoleEnum.EDIT_PROFILE));
        
        if(backScene.equals("ViewPool")){
            this.updateButton.setVisible(false);
            this.deleteButton.setVisible(false);
        }

    }
    
    public void setCheckedStatusLabel(boolean value){
        if(value){
            this.statusLabel.setText("Checked in");
        }else {
            this.statusLabel.setText("Checked out");

        }
        
    }

    public void toggleEditableFields(boolean value) {
        firstName.setEditable(value);
        surname.setEditable(value);
        dob.setDisable(!value);
        address.setEditable(value);
        city.setEditable(value);
        zip.setEditable(value);
        state.setEditable(value);
        phone.setEditable(value);
        em_firstname.setEditable(value);
        em_surname.setEditable(value);
        em_phone.setEditable(value);
        gender.setDisable(!value);
        skill.setDisable(!value);
        status.setDisable(!value);
        uploadButton.setDisable(!value);
        note.setEditable(value);

        updateButton.setDisable(!value);
        deleteButton.setDisable(!value);
    }

    public void selectPhotoButtonClicked(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        this.filePathFromUser = fileChooser.showOpenDialog(stage);

        try {

            if (filePathFromUser != null) {
                photoChanged = true;
                BufferedImage bufferedImage = ImageIO.read(filePathFromUser);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                imageView.setImage(image);
            }

        } catch (IOException e) {
            this.messageLbl.setText("Failed up upload image. Try again.");
        }

    }

    private void setFields(Swimmer swimmer) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(swimmer.getDob(), formatter);

        statusLabel.setText(swimmer.getCheckedStatus());
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
        gender.setValue(swimmer.getGender());
        skill.setValue(swimmer.getSkill());
        status.setValue(swimmer.getStatus());
        note.setText(swimmer.getNote());

        try {

            File filePath = new File("src/pas_v2/Resources/SwimmerImages/" + swimmer.getPhotoPath());

            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            imageView.setImage(image);

        } catch (IOException e) {
            System.out.println("Failed up upload image.");
        }

    }

    public void saveToFile(BufferedImage image) {

        try {
            // retrieve image
            BufferedImage bi = image;
            File outputfile = new File("src/pas_v2/Resources/SwimmerImages/" + this.filePathFromUser.getName());
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println("Could not save file");
        }
    }

    public void updateSwimmerButtonClicked(ActionEvent event) throws IOException {
        if (checkFields()) {

            LocalDate today = LocalDate.now(); 
            if(!(dob.getValue().isAfter(today) || (dob.getValue().isEqual(today)))){

                String photoName;
                if (photoChanged) {
                    photoName = this.filePathFromUser.getName();
                    saveToFile(ImageIO.read(filePathFromUser));
                } else {
                    photoName = selectedSwimmer.getPhotoPath();
                }

                Swimmer updatedSwimmer = new Swimmer(firstName.getText(),
                        surname.getText(),
                        (GenderEnum) gender.getValue(),
                        selectedDOB,
                        address.getText(),
                        city.getText(),
                        zip.getText(),
                        state.getText(),
                        phone.getText(),
                        em_firstname.getText(),
                        em_surname.getText(),
                        em_phone.getText(),
                        skill.getValue().toString(),
                        status.getValue().toString(),
                        photoName);

                if (!note.getText().equals("")) {
                    updatedSwimmer.setNote(note.getText());
                }
                if(!selectedSwimmer.getVisits().isEmpty()){
                    updatedSwimmer.setVisits(selectedSwimmer.getVisits());
                }

                pool.updateSwimmer(selectedSwimmer, updatedSwimmer);

                messageLbl.setText("");

                pool.writeSwimmerListFile();

                navigateToFindController(event);
            }
            else{
                messageLbl.setText("You cannot be born in the future.");
            }

        } else {
            messageLbl.setText("All fields need to be filled out");

        }

    }

    private boolean checkFields() {
        boolean check = false;
        selectedDOB = "";

        try {

            selectedDOB = dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        } catch (java.lang.NullPointerException e) {

            selectedDOB = "";

        }

        if (!(imageView.getImage() == null || skill.getSelectionModel().isEmpty()
                || status.getSelectionModel().isEmpty() || firstName.getText().equals("")
                || surname.getText().equals("") || selectedDOB.equals("") || address.getText().equals("")
                || city.getText().equals("") || zip.getText().equals("") || state.getText().equals("")
                || phone.getText().equals("") || em_firstname.getText().equals("") || gender.getValue() == null
                || em_surname.getText().equals("") || em_phone.getText().equals(""))) {
            check = true;
        }

        return check;
    }

    public void deleteButtonClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        String s = "Delete " + selectedSwimmer.getName() + "?";
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {

            File file = new File("src/pas_v2/Resources/SwimmerImages/" + selectedSwimmer.getPhotoPath());

            if (file.exists()) {
                file.delete();
            } else {
                System.out.println(file + " was not found.");
            }

            pool.deleteSwimmer(selectedSwimmer);
            pool.writeSwimmerListFile();

            navigateToFindController(event);

        }

    }

    public void backBtnClicked(ActionEvent event) throws IOException {
        if(backScene.equals("FindSwimmer")){
            navigateToFindController(event);
        }
        else if(backScene.equals("ViewPool")){
            navigateToViewController(event);
        }

    }

    public void navigateToFindController(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/FindSwimmerUI.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        FindSwimmerController controller = loader.getController();
        controller.initData(currentEmployee, pool);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
    
        public void navigateToViewController(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pas_v2/Views/ViewPoolUI.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ViewPoolController controller = loader.getController();
        controller.initData(currentEmployee, pool);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        
        window.setScene(tableViewScene);
        window.show();
    }

}
