package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/17/2017.
 */

public class AdminContactDetailsModal implements Serializable {

  String  fname,lname,email1,email2,addressline1,addressline2,addressline3,phone,mobile,mobile2;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public AdminContactDetailsModal(String fname, String lname, String email1, String email2, String addressline1, String addressline2, String addressline3, String phone, String mobile, String mobile2) {
        this.fname = fname;
        this.lname = lname;
        this.email1 = email1;
        this.email2 = email2;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.addressline3 = addressline3;
        this.phone = phone;
        this.mobile = mobile;
        this.mobile2 = mobile2;
    }
}
