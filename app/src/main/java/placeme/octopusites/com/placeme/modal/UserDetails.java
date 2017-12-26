package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 26-Dec-17.
 */

public class UserDetails implements Serializable {

    String user,role,isactivated,fname,lname;

    public UserDetails(String user, String role, String isactivated, String fname, String lname) {
        this.user = user;
        this.role = role;
        this.isactivated = isactivated;
        this.fname = fname;
        this.lname = lname;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIsactivated(String isactivated) {
        this.isactivated = isactivated;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUser() {
        return user;
    }

    public String getRole() {
        return role;
    }

    public String getIsactivated() {
        return isactivated;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }


}