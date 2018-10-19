package Model.Reports;

import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author d.mikhaylov
 */
public abstract class AbstractReport extends AbstractTableModel {

    Date creationTime;
    protected Report report;
    
    
    public AbstractReport(Report report){
     creationTime = new Date();
     this.report = report; 
    }
    
    @Override
    abstract public int getRowCount();

    @Override
    abstract public int getColumnCount();

    @Override
    abstract public Object getValueAt(int rowIndex, int columnIndex);
    
    abstract public void update();
    
    abstract public String getReportType();

}
