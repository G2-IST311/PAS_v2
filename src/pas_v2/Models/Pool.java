package pas_v2.Models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import pas_v2.Models.Reports.Report;

/**
 *
 * @author d.mikhaylov, David Ortiz
 */
public class Pool {

    private Report report;
    private ArrayList<Swimmer> swimmers;
    private ArrayList<Visit> visits;
    
    //serializeable files
    private String listOfSwimmersFileName = "swimmers.ser";
    


    public Pool() {
        swimmers = new ArrayList<>();
        visits = new ArrayList<>();
        report = new Report(this);
        
                this.readSwimmerListFile();

                if(swimmers.isEmpty() || swimmers == null){

                    this.writeSwimmerListFile();
                    this.readSwimmerListFile();

                }
        
        //printSwimmerList();
    }
    //selectedSwimmer, updatedSwimmer
    public void updateSwimmer(Swimmer originalSwimmer, Swimmer updatedSwimmer){
        swimmers.set(swimmers.indexOf(originalSwimmer), updatedSwimmer);
        
    }
    
    public void changeSwimmerStatus(Swimmer tempSwimmer, boolean status){
        swimmers.get(swimmers.indexOf(tempSwimmer)).setCheckedStatus(status);
        
        this.writeSwimmerListFile();
    }
    
    
    
    public void deleteSwimmer(Swimmer swimmer){
        swimmers.remove(swimmer);
        swimmers.trimToSize();
    }
    
    public ArrayList<Swimmer> searchSwimmer(String keyword){
        ArrayList<Swimmer> tempList = new ArrayList<>();
        
        for(Swimmer s : swimmers){
            if(s.getSwimmerInformation().toLowerCase().contains(keyword.toLowerCase())){
                tempList.add(s);
            }
        }
        
        return tempList;
    }
    
    public void readSwimmerListFile(){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(listOfSwimmersFileName);
            in = new ObjectInputStream(fis);
            swimmers = (ArrayList)in.readObject();
            in.close();
//            if(!swimmers.isEmpty()){
//                System.out.println("There are swimmers in the swimmer list");
//            }
        }
        catch(IOException ex){
            System.out.println(listOfSwimmersFileName + " not found, creating.");
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    public void writeSwimmerListFile(){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(listOfSwimmersFileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(swimmers);
            out.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void printSwimmerList(){
        System.out.println("The SwimmerList has these swimmers:");
        for(int i = 0; i < swimmers.size(); i++){
            Swimmer currentUser = (Swimmer) swimmers.get(i);
            System.out.println(currentUser.getSwimmerInformation());
        }
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
