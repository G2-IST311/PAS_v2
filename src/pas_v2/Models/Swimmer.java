package pas_v2.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author d.mikhaylov, David Ortiz
 */
public class Swimmer implements Serializable{

    private String name, lastname, dob, address, city, zip, state, phone, em_firstname, em_lastname, em_phone, skill, status, note = "";
    private String photoPath;
    
    private String fullName;
    private String fullAddress;
    
    private String checkedStatus = "Checked out";
    private Date CheckedInDate;
    private Date CheckedOutDate;
    
    private ArrayList<Visit> visits;

    private Visit currentVisit;
    
    
    public Swimmer(String name, String lastname, String dob, String address, String city, String zip, String state, String phone, String em_firstname, String em_lastname, String em_phone, String skill, String status, String image) {
        visits = new ArrayList<>();
        
        this.name = name;
        this.lastname = lastname;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.phone = phone;
        this.em_firstname = em_firstname;
        this.em_lastname = em_lastname;
        this.em_phone = em_phone;
        this.skill = skill;
        this.status = status;
        this.photoPath = image;
        
        this.fullName = name +" "+ lastname;
        this.fullAddress = address+" "+city+" "+state+" "+zip+"";
        
        
    }

    public String getCheckedStatus() {
        return this.checkedStatus;
    }

    public void setCheckedStatus(boolean _checkedStatus) {
        Date now = new Date();
        
        if(_checkedStatus){
            this.checkedStatus = "Checked in";

            CheckedInDate = now;
            currentVisit = new Visit(this.CheckedInDate);
            
        } else {
            this.checkedStatus = "Checked out";

            CheckedOutDate = now;
            
            currentVisit.setCheckOut(CheckedOutDate);
            visits.add(currentVisit);
            
        }
        
    }
    
    
    public ArrayList<Visit> getVisits(){
        return this.visits;
    }
    
    public void setVisits(ArrayList<Visit> _visits){
        this.visits = _visits;
    }
    
    
    
    
    public String getFullName() {
        return fullName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    public String getLastname() {
        return lastname;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public String getEm_firstname() {
        return em_firstname;
    }

    public String getEm_lastname() {
        return em_lastname;
    }

    public String getEm_phone() {
        return em_phone;
    }

    public String getSkill() {
        return skill;
    }

    public String getStatus() {
        return status;
    }
    
    public String getNote(){
        return note;
    }
    
    public String getPhotoPath() {
        return photoPath;
    }
    
    public String getSwimmerInformation(){
        String temp = name + " " + lastname + ", " +  dob+ ", " +  address+ ", " +  city+ ", " +  zip+ ", " +  state+ ", " +  phone+ ", " +  skill+ ", " +  status +", " +note +", "+ checkedStatus;
        

        return temp;
    }

    
    
    /**
     * @set the variables
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEm_firstname(String em_firstname) {
        this.em_firstname = em_firstname;
    }

    public void setEm_lastname(String em_lastname) {
        this.em_lastname = em_lastname;
    }

    public void setEm_phone(String em_phone) {
        this.em_phone = em_phone;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setNote(String note){
        this.note = note;
    }

    public void setPhotoPath(String photo) {
        this.photoPath = photo;
    }

    

    

}
