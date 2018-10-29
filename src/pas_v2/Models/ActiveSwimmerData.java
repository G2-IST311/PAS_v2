/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Drew Hopkins
 */
public class ActiveSwimmerData {
    private Swimmer swimmer;
    private String fullName;
    private String name;
    private String lastName;
    private String age;
    private String checkIn;
    private String duration;
    private String gender;
    private String dob;
    
    private String address, city, zip, state, phone, em_firstname, em_lastname, em_phone, skill, status, note = "";
    private String photoPath;
    private ArrayList<Visit> visits;

    private GenderEnum genderEnum;
    
    
    public ActiveSwimmerData(Swimmer swimmer) {
        
        this.swimmer = swimmer;
        this.name = swimmer.getName();
        this.lastName = swimmer.getLastname();
        this.fullName = swimmer.getFullName();
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(swimmer.getDob(), formatter);
        LocalDate today = LocalDate.now(); 
        int ageInt = Period.between(localDate, today).getYears();
        this.age = Integer.toString(ageInt);
        //17:33:12

        this.checkIn = swimmer.getCurrentVisit().getCheckInTime();

        
        this.duration = swimmer.getCurrentVisit().getCurrentDuration();

        
        
        this.genderEnum = swimmer.getGender();
        this.dob = swimmer.getDob();
        this.address = swimmer.getAddress();
        this.city = swimmer.getCity();
        this.zip = swimmer.getZip();
        this.state = swimmer.getState();
        this.phone = swimmer.getPhone();
        this.em_firstname = swimmer.getEm_firstname();
        this.em_lastname = swimmer.getEm_lastname();
        this.em_phone = swimmer.getEm_phone();
        this.skill = swimmer.getSkill();
        this.status = swimmer.getStatus();
        this.note = swimmer.getNote();
        
        this.photoPath = swimmer.getPhotoPath();
        this.visits = swimmer.getVisits();
        this.gender = genderEnum.getValue();
        
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setNote(String note) {
        this.note = note;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setVisits(ArrayList<Visit> visits) {
        this.visits = visits;
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }
    
    

    public ArrayList<Visit> getVisits() {
        return visits;
    }
    
    public String getGender() {
        return gender;
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

    public String getNote() {
        return note;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public GenderEnum getGenderEnum() {
        return genderEnum;
    }
   
     /**
     * @return only swimmers in an active status
     */
    
//    public void fillData(Swimmer swimmer){
//        this.swimmer = swimmer;
//        this.fullName = swimmer.getFullName();
//        this.age = "17";
//        this.checkIn = swimmer.getCurrentVisit().getCheckInTime();
//        this.duration = "55";//Long.toString(swimmer.getCurrentVisit().getDuration());
//        this.gender = swimmer.getGender().getValue();
//    }
    public String getDob(){
        return this.dob;
    }
    public Swimmer getSwimmer() {
        return swimmer;
    }

    public void setSwimmer(Swimmer swimmer) {
        this.swimmer = swimmer;
    }

    public String getFullName() {
        return fullName;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }
   
}

