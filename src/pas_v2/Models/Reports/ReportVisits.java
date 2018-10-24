package pas_v2.Models.Reports;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import pas_v2.Models.Visit;

/**
 *
 * @author d.mikhaylov
 */
public class ReportVisits extends AbstractReport {

    List<Visit> allVisits;
    List<Visit> selectedVisits;
    Date startDate;
    Date endDate;

    public ReportVisits(Report report) {
        super(report);
        update();

    }

    @Override
    public void update() {
        allVisits = super.report.getAllVisits();

        startDate = super.report.getStartDate();
        
        endDate = super.report.getEndDate();
        selectedVisits = allVisits.stream()
                .filter(v -> (v.getCheckinDate().after(startDate) && v.getCheckoutDate().before(endDate)))
                .collect(Collectors.toList());
    }

    @Override
    public int getRowCount() {
        return selectedVisits.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return selectedVisits.get(rowIndex).getSwimmer().getName();
            case 1:
                return selectedVisits.get(rowIndex).getCheckInTime().toString();
            case 2:
                return selectedVisits.get(rowIndex).getCheckOutTime().toString();
            default:
                return null;
        }

    }

    @Override
    public String getReportType() {
        return "Visits report";
    }

}
