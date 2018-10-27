/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pas_v2.Models;

import java.util.ArrayList;

/**
 *
 * @author Drew Hopkins
 */
public class ActiveSwimmerData {
    private Swimmer swimmer;
    private String fullName;
    private String age;
    private String checkIn;
    private String duration;
    private String gender;

    public ActiveSwimmerData() {
        swimmer=null;
        fullName="";
        age="";
        checkIn="";
        duration="";
        gender="";
    }
   
     /**
     * @return only swimmers in an active status
     */
    
    public void fillData(Swimmer swimmer){
        this.swimmer = swimmer;
        this.fullName = swimmer.getFullName();
        this.age = "17";
        this.checkIn = swimmer.getCurrentVisit().getCheckInTime();
        this.duration = "55";//Long.toString(swimmer.getCurrentVisit().getDuration());
        this.gender = swimmer.getGender().getValue();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
   
}

