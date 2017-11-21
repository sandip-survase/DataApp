package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 18-Nov-17.
 */

public class Certificates implements Serializable {

    String title,issuer,license,startdate,enddate,willexpire;

    public Certificates(String title, String issuer, String license, String startdate, String enddate,String willexpire)
    {
        this.title=title;

        this.issuer=issuer;
        this.license=license;
        this.startdate=startdate;
        this.enddate=enddate;
        this.willexpire=willexpire;
    }

    public String getWillexpire() {
        return willexpire;
    }

    public void setWillexpire(String willexpire) {
        this.willexpire = willexpire;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }



}
