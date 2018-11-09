/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author David Ortiz
 */
public class Report_Visits {
    
    private String fullName, operator, checkin, checkout, type;
    private long duration;
    private Date date;
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public Report_Visits(Swimmer s, Visit v, String operator) {
        this.fullName = s.getFullName();
        this.operator = operator;
        this.date = s.getCurrentVisit().getCheckinDate();
//        this.checkin = s.getCurrentVisit().getCheckInTime();
//        this.duration = s.getCurrentVisit().getTotalDuration();
//        this.checkout = s.getCurrentVisit().getCheckOutTime();
       
        
        this.checkin = v.getCheckInTime();
        this.duration = v.getTotalDuration();
        this.checkout = v.getCheckOutTime();
        
        
        //get age
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(s.getDob(), formatter); 
        long age = java.time.temporal.ChronoUnit.YEARS.between( dob , LocalDate.now());
        
        if(age > 13){
            this.type = "Adult";
        } else {
            this.type = "Child";
        }
        
    }

    public String getFullName() {
        return fullName;
    }

    public String getOperator() {
        return operator;
    }

    public String getDate() {
        String reportDate = df.format(date);
        
        return reportDate;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getDuration() {
        return duration + " mins";
    }

    public String getCheckout() {
        return checkout;
    }

    public String getType() {
        return type;
    }
    
    
    
   
}
