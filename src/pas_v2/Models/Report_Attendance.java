package pas_v2.Models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author David Ortiz
 */
public class Report_Attendance {
    
    private int monday, tuesday, wednesday, thursday, friday, saturday, sunday, weekTotal;
    private String hour, statistic;
    private ArrayList<Visit> visits;
    //private double mondayTally=0, tuesdayTally=0, wednesdayTally=0, thursdayTally=0, fridayTally=0, saturdayTally=0, sundayTally=0;

    
    public Report_Attendance(ArrayList<Visit> _visits, int _hour){
        
        this.visits = _visits;
        
        if(_hour == 8){
            this.hour =  "0 - " + _hour+":59";
        } else if(_hour == 20) {
            this.hour =  _hour + " - 24";
        } else {
            this.hour = _hour + " - " + _hour+":59";
        }
        
            
        for(Visit v : this.visits){

            LocalDate checkDate = v.getCheckinDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            final String[] checkInHour = v.getCheckInTime().split(Pattern.quote(":"));

            switch (checkDate.getDayOfWeek().toString()) {

                case "MONDAY":

                    if(_hour==Integer.parseInt(checkInHour[0])){
                        monday++;
                    }
                    if(_hour == 8 && Integer.parseInt(checkInHour[0]) <= _hour){
                        monday++;
                    }
                    break;

                case "TUESDAY":

                    if(_hour==Integer.parseInt(checkInHour[0])){
                        tuesday++;
                    }
                    if(_hour == 8 && Integer.parseInt(checkInHour[0]) <= _hour){
                        tuesday++;
                    }
                    break;

                case "WEDNESDAY":

                    if(_hour==Integer.parseInt(checkInHour[0])){
                        wednesday++;
                    }
                    if(_hour == 8 && Integer.parseInt(checkInHour[0]) <= _hour){
                        wednesday++;
                    }
                    break;

                case "THURSDAY":

                    if(_hour==Integer.parseInt(checkInHour[0])){
                        thursday++;
                    }
                    if(_hour == 8 && Integer.parseInt(checkInHour[0]) <= _hour){
                        thursday++;
                    }   
                    break;

                case "FRIDAY":

                    if(_hour==Integer.parseInt(checkInHour[0])){
                        friday++;
                    }
                    if(_hour == 8 && Integer.parseInt(checkInHour[0]) <= _hour){
                        friday++;
                    }
                    break;

                case "SATURDAY":

                    if(_hour==Integer.parseInt(checkInHour[0])){
                        saturday++;
                    }
                    if(_hour == 8 && Integer.parseInt(checkInHour[0]) <= _hour){
                        saturday++;
                    }
                    break;

                case "SUNDAY":

                    if(_hour==Integer.parseInt(checkInHour[0])){
                        sunday++;
                    }
                    if(_hour == 8 && Integer.parseInt(checkInHour[0]) <= _hour){
                        sunday++;
                    }
                    break;

            }


        } // end Visits for
        
                
        this.weekTotal = monday + tuesday + wednesday + thursday + friday + saturday + sunday;
        
    }
    
    public Report_Attendance(ArrayList<Integer> days){
        
        this.hour = "Whole Day Total";
        this.monday = days.get(0);
        this.tuesday = days.get(1);
        this.wednesday = days.get(2);
        this.thursday = days.get(3);
        this.friday = days.get(4);
        this.saturday = days.get(5);
        this.sunday = days.get(6);
        this.weekTotal = monday + tuesday + wednesday + thursday + friday + saturday + sunday;

    }
   
    public int getMonday() {
        return monday;
    }

    public int getTuesday() {
        return tuesday;
    }

    public int getWednesday() {
        return wednesday;
    }

    public int getThursday() {
        return thursday;
    }

    public int getFriday() {
        return friday;
    }

    public int getSaturday() {
        return saturday;
    }

    public int getSunday() {
        return sunday;
    }

    public int getWeekTotal() {
        return weekTotal;
    }

    public String getHour() {
        return hour;
    }
    
    
}
