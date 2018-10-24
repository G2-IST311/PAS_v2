package pas_v2.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author d.mikhaylov
 */
public class Visit implements Serializable{

    private Swimmer swimmer;
    private Date checkInTime;
    private Date checkOutTime;
    private double duration = 0.0;
    private String day;
    
    
    
    public Visit(Swimmer newSwimmer){
        checkInTime = new Date();
        swimmer = newSwimmer;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
        LocalDateTime now = LocalDateTime.now(); 
        day = dtf.format(now);
        
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
    
    public double getDuration(){
        double diff = checkOutTime.getTime() - checkInTime.getTime();

        return diff / (60 * 1000) % 60;
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
        
        this.duration = (checkInTime.getTime() - checkOutTime.getTime()) / (60 * 1000) % 60;
        
        
    }
    
    
    
}
