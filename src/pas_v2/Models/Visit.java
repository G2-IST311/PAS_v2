package pas_v2.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author d.mikhaylov, David Ortiz
 */
public class Visit implements Serializable{

    private Swimmer swimmer;
    private Date checkInTime;
    private Date checkOutTime;
    private double totalDuration = 0.0;
    private String day;
    
    
    
    public Visit(Swimmer newSwimmer){
        checkInTime = new Date();
        swimmer = newSwimmer;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDateTime now = LocalDateTime.now(); 
        day = dtf.format(now);
        checkInTime = new Date();
        
    }
    
    public Visit(Date checkin){
        checkInTime = checkin;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDateTime now = LocalDateTime.now(); 
        day = dtf.format(now);
    }
    
    public String getDay(){
        return this.day;
    }
    
    public long getTotalDuration(){
        
        TimeUnit timeUnit= TimeUnit.MINUTES;
        
        long diffInMillies = checkOutTime.getTime() - checkInTime.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
  
    }
    
    public String getCurrentDuration(){
        ZoneId zoneid = ZoneId.systemDefault();
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime
                
        Instant instant = this.checkInTime.toInstant();
        
        LocalDateTime checkIn = instant.atZone(zoneid).toLocalDateTime();
        
        Duration dur = Duration.between(checkIn, now);
        
        return dur.toMinutes() + " mins";
    }
    

    /**
     * @return the swimmer
     */
    public Swimmer getSwimmer() {
        return swimmer;
    }

    public Date getCheckinDate(){
        return this.checkInTime;
    }
    
    public Date getCheckoutDate(){
        return this.checkOutTime;
    }
    
    /**
     * @return the checkInTime
     */
    public String getCheckInTime() {
        
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); 
        LocalDateTime now = LocalDateTime.now(); 
        return formatter.format(checkInTime);
        
    }

    /**
     * @return the checkOutTime
     */
    public String getCheckOutTime() {
        
       //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); 
        LocalDateTime now = LocalDateTime.now(); 
        return formatter.format(checkOutTime);
         
    }

    /**
     * @param checkOutTime the checkOutTime to set
     */
    public void setCheckOut(Date checkOutTime) {
        this.checkOutTime = checkOutTime;       
        this.totalDuration = (checkInTime.getTime() - checkOutTime.getTime()) / (60 * 1000) % 60;      
    }
    
    public void setCheckOut(){
        this.setCheckOut(new Date());            
    }
    
    
    
}
