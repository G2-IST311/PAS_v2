package Model;

import Model.Reports.Report;
import java.util.ArrayList;

/**
 *
 * @author d.mikhaylov, David Ortiz
 */
public class Pool {

    private Report report;
    private ArrayList<Swimmer> swimmers;
    private ArrayList<Visit> visits;

    public Pool() {
        swimmers = new ArrayList<>();
        visits = new ArrayList<>();
        report = new Report(this);
    }

    /**
     * @return the report
     */
    public Report getReport() {
        return report;
    }

    /**
     * @return the swimmers
     */
    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }

    /**
     * @return the visits
     */
    public ArrayList<Visit> getVisits() {
        return visits;
    }
    
    public void addSwimmer(Swimmer swimmer){
        swimmers.add(swimmer);
    }
    
    public void addVisit(Swimmer swimmer){
        visits.add(new Visit(swimmer));
    }
    
}
