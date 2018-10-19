package pas_v2.Models.Reports;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import pas_v2.Models.Pool;
import pas_v2.Models.Visit;

/**
 *
 * @author d.mikhaylov
 */
public class Report {
    
    private Date startDate;
    private Date endDate;
    AbstractReport reportVisits;
    private AbstractReport activeReport;
    private Pool pool;
    
    public Report(Pool pool) {
        
        this.pool = pool;
        
        reportVisits = new ReportVisits(this);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = df.parse("2018-09-10");
        } catch (ParseException p) {
            System.out.println("The string is unparsable to Date.");
        }
        
        endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600000); //1 hour ahead
        activeReport = reportVisits;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the activeReport
     */
    public AbstractReport getActiveReport() {
        return activeReport;
    }

    /**
     *
     * @return
     */
    public ArrayList<Visit> getAllVisits() {
        return pool.getVisits();
    }
    
}
