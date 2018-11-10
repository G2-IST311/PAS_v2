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
import java.util.ArrayList;
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
import javafx.stage.Stage;
import pas_v2.Models.Employee;
import pas_v2.Models.Pool;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pas_v2.Models.EmployeeList;
import pas_v2.Models.Report_Attendance;
import pas_v2.Models.Report_Swimmers;
import pas_v2.Models.Report_Visits;
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
    private DatePicker fromDatePicker;
    @FXML 
    private DatePicker toDatePicker;
    @FXML
    private Button updateButton;
    
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
    @FXML
    private TableColumn weekCol;
    
    @FXML
    private TableView tableView;
    
    private Employee currentEmployee;
    private Pool pool;
    private EmployeeList employeeList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toggleAllColumns(false);
        reportDropdown.getSelectionModel().selectedIndexProperty().addListener(new ReportDropdownChangeListener(reportDropdown));

    } 
    
    public void initData(Employee emp, EmployeeList empList, Pool pool){
        this.currentEmployee = emp;
        this.employeeList = empList;
        this.pool = pool;
        
    }
    
    private class ReportDropdownChangeListener implements ChangeListener<Number>{
        final ChoiceBox<Number> cb;

        ReportDropdownChangeListener(ChoiceBox<Number> cb) {
            this.cb = cb;
         }

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            
            messageLbl.setText("");
            tableView.getItems().clear();

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
   
    private void generateReport(String reportType, LocalDate from, LocalDate to){
        
        switch (reportType) {
            case "View Attendance":
                
                timeDayCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("hour"));
                mondayCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("monday"));
                tuesdayCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("tuesday"));
                wednesdayCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("wednesday"));
                thursdayCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("thursday"));
                fridayCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("friday"));
                saturdayCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("saturday"));
                sundayCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("sunday"));
                weekCol.setCellValueFactory(new PropertyValueFactory<Report_Attendance, String>("weekTotal"));
                
                ArrayList<Report_Attendance> reportAttendance = new ArrayList<>();
                ArrayList<Visit> _visits = pool.getPoolVisits(from, to);

                int hour = 8;
                
                for(int i =8; i <=20; i++){
                    Report_Attendance tempVisit = new Report_Attendance(_visits, hour);
                    reportAttendance.add(tempVisit);
                    hour++;
                }
                
                tableView.getItems().setAll( generateLastRow(reportAttendance) );

                break;
                
            case "View Swimmers":
                
                swimmerCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("fullName"));
                numberVisitsCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("numberVisits"));
                averageTimeCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("averageTime"));
                totalTimeCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("totalTime"));
                typeCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("type"));

                ArrayList<Report_Swimmers> reportSwimmers = new ArrayList<>();

                for(Swimmer s : pool.getViewReportsSwimmers(from, to)){
                    Report_Swimmers tempVisit = new Report_Swimmers(s);
                    reportSwimmers.add(tempVisit);
                }
                
                tableView.getItems().setAll(reportSwimmers);

                break;
                
            case "View Visits":
                
                swimmerCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("fullName"));
                operatorCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("operator"));
                dateCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("date"));
                checkinCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("checkin"));
                durationCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("duration"));
                checkoutCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("checkout"));
                typeCol.setCellValueFactory(new PropertyValueFactory<Report_Visits, String>("type"));

                ArrayList<Report_Visits> reportVisits = new ArrayList<>();

                for(Swimmer s : pool.getViewReportsSwimmers(from, to)){
                    for(Visit v : s.getVisits()){
                        Report_Visits tempVisit = new Report_Visits(s, v, this.currentEmployee.getLastName());
                        reportVisits.add(tempVisit);
                    }
                    
                }
                
                tableView.getItems().setAll(reportVisits);
                
                break;
                
            default:
                System.out.println("Selection error");
                break;
        }
    }
    
    private ArrayList<Report_Attendance> generateLastRow(ArrayList<Report_Attendance> _reportAttendance){
        
        int _mon = 0, _tues = 0, _wed = 0, _thur = 0, _fri = 0, _sat = 0, _sun = 0;
        
        for(Report_Attendance visit : _reportAttendance){
            _mon += visit.getMonday();
            _tues += visit.getTuesday();
            _wed += visit.getWednesday();
            _thur += visit.getThursday();
            _fri += visit.getFriday();
            _sat += visit.getSaturday();
            _sun += visit.getSunday();
        }
        
        ArrayList<Integer> days = new ArrayList<>();
        days.add(_mon);
        days.add(_tues);
        days.add(_wed);
        days.add(_thur);
        days.add(_fri);
        days.add(_sat);
        days.add(_sun);
        
        Report_Attendance temp = new Report_Attendance(days);
        _reportAttendance.add(temp);
        
        return _reportAttendance;
    }
    
    
    private void visitsReportSelected(){
        toggleVisitsColumns(true);
    }
    
    private void swimmersReportSelected(){
        toggleSwimmersColumns(true);
    }
    
    private void attendanceReportSelected(){
        toggleAttendanceColumns(true);
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
        weekCol.setVisible(toggle);
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
        weekCol.setVisible(toggle);
    }
    
    public void navigateToMainMenu(ActionEvent event) throws IOException{
        
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
    
    public void updateButtonClicked(){
        
        String fromDate = "";
        String toDate = "";
        
        try {

            fromDate = this.fromDatePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            toDate = this.toDatePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            
        } catch (java.lang.NullPointerException e) {

            fromDate = "";
            toDate = "";

        }
        
        if(!(fromDate.equals("") || toDate.equals("") || reportDropdown.getSelectionModel().isEmpty())){
            
            if((this.toDatePicker.getValue().isAfter(this.fromDatePicker.getValue())) || (this.toDatePicker.getValue().isEqual(this.fromDatePicker.getValue()))){
                
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
