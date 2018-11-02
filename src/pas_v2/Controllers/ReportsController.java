/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.Pool;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TableColumn;


/**
 * FXML Controller class
 *
 * @author David Ortiz
 */
public class ReportsController implements Initializable {

    @FXML
    private ChoiceBox reportDropdown;
    @FXML
    private ChoiceBox statisticDropdown;
    @FXML 
    private DatePicker fromDatePicker;
    @FXML 
    private DatePicker toDatePicker;
    @FXML
    private Button updateButton;
    @FXML
    private HBox statisticSection;
    
    @FXML
    TableColumn swimmerCol;
    @FXML
    TableColumn operatorCol;
    @FXML
    TableColumn dateCol;
    @FXML
    TableColumn checkinCol;
    @FXML
    TableColumn durationCol;
    @FXML
    TableColumn checkoutCol;
    @FXML
    TableColumn typeCol;
    @FXML
    TableColumn numberVisitsCol;
    @FXML
    TableColumn averageTimeCol;
    @FXML
    TableColumn totalTimeCol;
    @FXML
    TableColumn timeDayCol;
    @FXML
    TableColumn mondayCol;
    @FXML
    TableColumn tuesdayCol;
    @FXML
    TableColumn wednesdayCol;
    @FXML
    TableColumn thursdayCol;
    @FXML
    TableColumn fridayCol;
    @FXML
    TableColumn saturdayCol;
    @FXML
    TableColumn sundayCol;
    
    
    
    private Employee currentEmployee;
    private Pool pool;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statisticSection.setVisible(false);
        toggleAllColumns(false);
        reportDropdown.getSelectionModel().selectedIndexProperty().addListener(new ReportDropdownChangeListener(reportDropdown));

    } 
    
    public void initData(Employee emp, Pool pool){
        this.currentEmployee = emp;
        this.pool = pool;
        
    }
    
    private class ReportDropdownChangeListener implements ChangeListener<Number>{
        final ChoiceBox<Number> cb;

        ReportDropdownChangeListener(ChoiceBox<Number> cb) {
            this.cb = cb;
         }

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            
            String selection = reportDropdown.getItems().get((Integer)newValue).toString();
            
            switch (selection) {
                case "View Attendance":
                    attendanceReportSelected();
                    break;
                case "View Swimmers":
                    swimmersReportSelected();
                    break;
                case "View Visits":
                    visitsReportSelected();
                    break;
                default:
                    System.out.println("Selection error");
                    break;
            }
            
        }

    }
   
    
    private void visitsReportSelected(){
        statisticSection.setVisible(false);
        toggleVisitsColumns(true);
        
        System.out.println("View Visits selected");
    }
    
    private void swimmersReportSelected(){
        statisticSection.setVisible(false);
        toggleSwimmersColumns(true);
        System.out.println("View Swimmers selected");
    }
    
    private void attendanceReportSelected(){
        statisticSection.setVisible(true);
        toggleAttendanceColumns(true);
        System.out.println("View Attendance selected");

    }
    
    private void toggleVisitsColumns(boolean toggle){
        
        toggleAllColumns(false);
        
        swimmerCol.setVisible(toggle);
        operatorCol.setVisible(toggle);
        dateCol.setVisible(toggle);
        checkinCol.setVisible(toggle);
        durationCol.setVisible(toggle);
        checkoutCol.setVisible(toggle);
        typeCol.setVisible(toggle);
                
    }
    
    private void toggleSwimmersColumns(boolean toggle){
        toggleAllColumns(false);

        
        swimmerCol.setVisible(toggle);
        numberVisitsCol.setVisible(toggle);
        averageTimeCol.setVisible(toggle);
        totalTimeCol.setVisible(toggle);
        typeCol.setVisible(toggle);

        
    }
    
    private void toggleAttendanceColumns(boolean toggle){
        toggleAllColumns(false);

        
        timeDayCol.setVisible(toggle);
        mondayCol.setVisible(toggle);
        tuesdayCol.setVisible(toggle);
        wednesdayCol.setVisible(toggle);
        thursdayCol.setVisible(toggle);
        fridayCol.setVisible(toggle);
        saturdayCol.setVisible(toggle);
        sundayCol.setVisible(toggle);
    }
    
    private void toggleAllColumns(boolean toggle){
        swimmerCol.setVisible(toggle);
        operatorCol.setVisible(toggle);
        dateCol.setVisible(toggle);
        checkinCol.setVisible(toggle);
        durationCol.setVisible(toggle);
        checkoutCol.setVisible(toggle);
        typeCol.setVisible(toggle);
        numberVisitsCol.setVisible(toggle);
        averageTimeCol.setVisible(toggle);
        totalTimeCol.setVisible(toggle);
        timeDayCol.setVisible(toggle);
        mondayCol.setVisible(toggle);
        tuesdayCol.setVisible(toggle);
        wednesdayCol.setVisible(toggle);
        thursdayCol.setVisible(toggle);
        fridayCol.setVisible(toggle);
        saturdayCol.setVisible(toggle);
        sundayCol.setVisible(toggle);
    }
    
    
    
    
    
    
    public void navigateToMainMenu(ActionEvent event) throws IOException{
        
        
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
