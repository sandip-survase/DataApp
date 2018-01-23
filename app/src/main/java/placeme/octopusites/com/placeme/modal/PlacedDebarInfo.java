/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * @author root
 */
public class PlacedDebarInfo implements Serializable {

    String username, isplaced, companyname, isdebar, verify;

    public PlacedDebarInfo(String username, String isplaced, String companyname, String isdebar, String verify) {
        this.username = username;
        this.isplaced = isplaced;
        this.companyname = companyname;
        this.isdebar = isdebar;
        this.verify = verify;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsplaced() {
        return isplaced;
    }

    public void setIsplaced(String isplaced) {
        this.isplaced = isplaced;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getIsdebar() {
        return isdebar;
    }

    public void setIsdebar(String isdebar) {
        this.isdebar = isdebar;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

}
