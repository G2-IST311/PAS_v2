package pas_v2.Models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class is responsible for persistent storage
 *
 * @author d.mikhaylov
 */
public class Storage {

    private Gson gson;
    private String fileName = "";
    private Type listType;

    public Storage() {
        gson = new Gson();

    }

    public <T> ArrayList<T> read(Class<T> cls) {

        findClass(cls);
        try (Scanner input = new Scanner(new File(fileName))) {
            String stringJSON = input.useDelimiter("\\A").next();
            input.close();
            //System.out.println(stringJSON);
            return gson.fromJson(stringJSON, listType);
            //System.out.println("List is empty " + list.isEmpty());
        } catch (FileNotFoundException ex) {
            System.err.println(fileName + " not found.");  
            return null;
        }

    }

    public void write(ArrayList obj, Class cls) {
        findClass(cls);
        String stringJSON = gson.toJson(obj, listType);
        try (PrintWriter output = new PrintWriter(fileName)) {
            output.println(stringJSON);
            output.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void findClass(Class cls) {

        if (cls == Swimmer.class) {
            listType = new TypeToken<ArrayList<Swimmer>>() {
            }.getType();
            fileName = "swimmers.json";
        } else if (cls == Swimmer.class) {
            listType = new TypeToken<ArrayList<Employee>>() {
            }.getType();
            fileName = "employees.json";
        } else if (cls == Swimmer.class) {
            listType = new TypeToken<ArrayList<Visit>>() {
            }.getType();
            fileName = "visits.json";
        } else {
            System.err.println("Type " + cls.getSimpleName() + " does not exist!");
            return;
        }

    }

}
