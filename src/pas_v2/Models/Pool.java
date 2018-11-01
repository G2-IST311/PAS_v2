package pas_v2.Models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pas_v2.Models.Reports.Report;

/**
 *
 * @author d.mikhaylov, David Ortiz, Drew Hopkins
 */
public class Pool {

    private Report report;
    private ArrayList<Swimmer> swimmers;
    ArrayList<Swimmer> activeSwimmers;
    ArrayList<ActiveSwimmerData> activePool;
    private ArrayList<Visit> visits;
    private Gson gson;
    private Type listType;

    //serializeable files
    private String listOfSwimmersFileName = "swimmers";

    public Pool() {
        swimmers = new ArrayList<>();
        visits = new ArrayList<>();
        report = new Report(this);
        activeSwimmers = new ArrayList<>();
        activePool = new ArrayList<>();        
        gson = new Gson();
        listType = new TypeToken<ArrayList<Swimmer>>() {}.getType();
        
        this.readSwimmerListFile();

        if (swimmers.isEmpty() || swimmers == null) {

            this.writeSwimmerListFile();
            this.readSwimmerListFile();

        }

        //printSwimmerList();
    }
    //selectedSwimmer, updatedSwimmer

    public void constructActivePool(ArrayList<Swimmer> activeSwimmers) {
        activePool.clear();
        for (int i = 0; i < activeSwimmers.size(); i++) {
            ActiveSwimmerData data = new ActiveSwimmerData(activeSwimmers.get(i));
            //data.fillData(activeSwimmers.get(i));
            activePool.add(data);
        }

    }

    public void clearActiveSwimmers() {
        this.activeSwimmers.clear();
        this.activePool.clear();
    }

    public void updateSwimmer(Swimmer originalSwimmer, Swimmer updatedSwimmer) {
        swimmers.set(swimmers.indexOf(originalSwimmer), updatedSwimmer);

    }

    public void changeSwimmerStatus(Swimmer tempSwimmer, boolean status) {
        swimmers.get(swimmers.indexOf(tempSwimmer)).setCheckedStatus(status);

        this.writeSwimmerListFile();
    }

    public void deleteSwimmer(Swimmer swimmer) {
        swimmers.remove(swimmer);
        swimmers.trimToSize();
    }

    public ArrayList<Swimmer> searchSwimmer(String keyword) {
        ArrayList<Swimmer> tempList = new ArrayList<>();

        for (Swimmer s : swimmers) {
            if (s.getSwimmerInformation().toLowerCase().contains(keyword.toLowerCase())) {
                tempList.add(s);
            }
        }

        return tempList;
    }

    public ArrayList<ActiveSwimmerData> searchActiveSwimmers(String keyword) {
        ArrayList<ActiveSwimmerData> tempList = new ArrayList<>();

        for (Swimmer tempActive : this.activeSwimmers) {
            if (tempActive.getSwimmerInformation().toLowerCase().contains(keyword.toLowerCase())) {

                ActiveSwimmerData tempSwimmer = new ActiveSwimmerData(tempActive);
                tempSwimmer.setVisits(tempActive.getVisits());
                tempSwimmer.setNote(tempActive.getNote());

                tempList.add(tempSwimmer);
            }
        }

        return tempList;
    }

    public void readSwimmerListFile() {      
        try (Scanner input = new Scanner(new File(listOfSwimmersFileName + ".json"))){           
            String stringJSON = input.useDelimiter("\\A").next();
            swimmers = gson.fromJson(stringJSON, listType); 
        } catch (FileNotFoundException ex) {
            System.out.println(listOfSwimmersFileName + ".json not found, creating.");
        }       
    }

    public void writeSwimmerListFile() {        
        String stringJSON = gson.toJson(swimmers, listType);
        try (PrintWriter output = new PrintWriter(listOfSwimmersFileName + ".json")) {
            output.println(stringJSON);
            output.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void printSwimmerList() {
        System.out.println("The SwimmerList has these swimmers:");
        for (int i = 0; i < swimmers.size(); i++) {
            Swimmer currentUser = (Swimmer) swimmers.get(i);
            System.out.println(currentUser.getSwimmerInformation());
        }
    }

    public ArrayList<ActiveSwimmerData> getActivePool() {
        return activePool;
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
     * @return only swimmers in an active status
     */
    public ArrayList<Swimmer> getActiveSwimmers() {
        activeSwimmers.removeAll(swimmers);
        for (int i = 0; i < swimmers.size(); i++) {
            if (swimmers.get(i).getCheckedStatus().equals("Checked in")) {
                activeSwimmers.add(swimmers.get(i));
            }
        }
        return activeSwimmers;
    }

    /**
     * @return the visits
     */
    public ArrayList<Visit> getVisits() {
        return visits;
    }

    public void addSwimmer(Swimmer swimmer) {
        swimmers.add(swimmer);
    }

    public void addVisit(Swimmer swimmer) {
        visits.add(new Visit(swimmer));
    }

}
