package pas_v2.Models;

import java.util.Date;

/**
 *
 * @author d.mikhaylov
 */
public class Visit {

    private Swimmer swimmer;
    private Date checkInTime;
    private Date checkOutTime;
    
    public Visit(Swimmer newSwimmer){
        checkInTime = new Date();
        swimmer = newSwimmer;
    };

    /**
     * @return the swimmer
     */
    public Swimmer getSwimmer() {
        return swimmer;
    }

    /**
     * @return the checkInTime
     */
    public Date getCheckInTime() {
        return checkInTime;
    }

    /**
     * @return the checkOutTime
     */
    public Date getCheckOutTime() {
        return checkOutTime;
    }

    /**
     * @param checkOutTime the checkOutTime to set
     */
    public void checkOut(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    
    
    
}
