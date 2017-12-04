package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;


public class AdminPersonal implements Serializable {
    public  String fname, mname, sname, alternateemail, sinst, dob, gender, addrline1c, addrline2c, addrline3c, addrline1p, addrline2p, addrline3p;

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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAlternateemail() {
        return alternateemail;
    }

    public void setAlternateemail(String alternateemail) {
        this.alternateemail = alternateemail;
    }

    public String getSinst() {
        return sinst;
    }

    public void setSinst(String sinst) {
        this.sinst = sinst;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddrline1c() {
        return addrline1c;
    }

    public void setAddrline1c(String addrline1c) {
        this.addrline1c = addrline1c;
    }

    public String getAddrline2c() {
        return addrline2c;
    }

    public void setAddrline2c(String addrline2c) {
        this.addrline2c = addrline2c;
    }

    public String getAddrline3c() {
        return addrline3c;
    }

    public void setAddrline3c(String addrline3c) {
        this.addrline3c = addrline3c;
    }

    public String getAddrline1p() {
        return addrline1p;
    }

    public void setAddrline1p(String addrline1p) {
        this.addrline1p = addrline1p;
    }

    public String getAddrline2p() {
        return addrline2p;
    }

    public void setAddrline2p(String addrline2p) {
        this.addrline2p = addrline2p;
    }

    public String getAddrline3p() {
        return addrline3p;
    }

    public void setAddrline3p(String addrline3p) {
        this.addrline3p = addrline3p;
    }

    public AdminPersonal(String fname, String mname, String sname, String alternateemail, String sinst, String dob, String gender, String addrline1c, String addrline2c, String addrline3c, String addrline1p, String addrline2p, String addrline3p) {

        this.fname = fname;
        this.mname = mname;
        this.sname = sname;
        this.alternateemail = alternateemail;
        this.sinst = sinst;
        this.dob = dob;
        this.gender = gender;
        this.addrline1c = addrline1c;
        this.addrline2c = addrline2c;
        this.addrline3c = addrline3c;
        this.addrline1p = addrline1p;
        this.addrline2p = addrline2p;
        this.addrline3p = addrline3p;

    }
}
