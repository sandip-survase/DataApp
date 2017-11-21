package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/17/2017.
 */

public class AdminIntroModal implements Serializable {

    String fname,mname,lname,country,state,city,phone,institute;



    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }


    public AdminIntroModal(String fname, String mname, String lname, String country, String state, String city, String phone, String institute) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.country = country;
        this.state = state;
        this.city = city;
        this.phone = phone;
        this.institute = institute;
    }


}
