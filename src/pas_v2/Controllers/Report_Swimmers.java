/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author David Ortiz
 */
public class Report_Swimmers {
    private String fullName, type;
    private long numberVisits, averageTime, totalTime;
  
    public Report_Swimmers(Swimmer s){
        this.fullName = s.getFullName();
        
        
        //get age
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(s.getDob(), formatter); 
        long age = java.time.temporal.ChronoUnit.YEARS.between( dob , LocalDate.now());
        
        if(age > 13){
            this.type = "Adult";
        } else {
            this.type = "Child";
        }
        
        
        this.numberVisits = s.getVisits().size();
        long total = 0;
        
        for(Visit v : s.getVisits()){
            total += v.getTotalDuration();
        }
        
        this.totalTime = total;
        
        this.averageTime = total/numberVisits;
        
        
    }

    public String getFullName() {
        return fullName;
    }

    public String getType() {
        return type;
    }

    public long getNumberVisits() {
        return numberVisits;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public long getTotalTime() {
        return totalTime;
    }
    
    
    
    
}
