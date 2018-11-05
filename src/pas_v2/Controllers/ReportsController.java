/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pas_v2.Models.Swimmer;
import pas_v2.Models.Visit;


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
    private Label messageLbl;
    
    @FXML
    private TableColumn swimmerCol;
    @FXML
    private TableColumn operatorCol;
    @FXML
    private TableColumn dateCol;
    @FXML
    private TableColumn checkinCol;
    @FXML
    private TableColumn durationCol;
    @FXML
    private TableColumn checkoutCol;
    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn numberVisitsCol;
    @FXML
    private TableColumn averageTimeCol;
    @FXML
    private TableColumn totalTimeCol;
    @FXML
    private TableColumn timeDayCol;
    @FXML
    private TableColumn mondayCol;
    @FXML
    private TableColumn tuesdayCol;
    @FXML
    private TableColumn wednesdayCol;
    @FXML
    private TableColumn thursdayCol;
    @FXML
    private TableColumn fridayCol;
    @FXML
    private TableColumn saturdayCol;
    @FXML
    private TableColumn sundayCol;
    
    @FXML private TableView tableView;
    
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
    
    private void generateReport(String reportType, LocalDate from, LocalDate to){
        
        System.out.println("Report: "+reportDropdown.getValue().toString()+"\nFrom: " + from +"\nTo: "+ to);
        
        switch (reportType) {
            case "View Attendance":

                
                

                break;
            case "View Swimmers":
                swimmersReportSelected();
                break;
            case "View Visits":
                
                //TODO: fill these columns with pool.getViewReportsSwimmers(from, to) arraylist
                swimmerCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("fullName"));
                operatorCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("gender"));
                dateCol.setCellValueFactory(new PropertyValueFactory<Visit, String>("day"));
                checkinCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("fullAddress"));
                durationCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("skill"));
                checkoutCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("status"));
                typeCol.setCellValueFactory(new PropertyValueFactory<Swimmer, String>("checkedStatus"));

        
                tableView.getItems().setAll(pool.getViewReportsSwimmers(from, to));
                
                visitsReportSelected();
                break;
            default:
                System.out.println("Selection error");
                break;
        }
    }
    
    public void updateButtonClicked(){
        
        String fromDate = "";
        String toDate = "";
        boolean isAfter = false;
        try {

            fromDate = this.fromDatePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            toDate = this.toDatePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            
        } catch (java.lang.NullPointerException e) {

            fromDate = "";
            toDate = "";

        }
        
        
        
        if(!(fromDate.equals("") || toDate.equals("") || reportDropdown.getSelectionModel().isEmpty())){
            
            if(this.toDatePicker.getValue().isAfter(this.fromDatePicker.getValue())){
                
                generateReport(reportDropdown.getValue().toString(), this.fromDatePicker.getValue(), this.toDatePicker.getValue());
                
                messageLbl.setText("Generate "+reportDropdown.getValue().toString()+" report: " + fromDate +" - "+ toDate);

            } else {
                messageLbl.setText("Invalid date range.");

            }
        
        } else {
            messageLbl.setText("All fields need to be complete");

        }
           
        
        
        
        
    }
    
}
