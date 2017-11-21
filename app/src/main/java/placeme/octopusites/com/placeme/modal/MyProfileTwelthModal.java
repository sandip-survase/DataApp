package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by sunny on 11/18/2017.
 */

public class MyProfileTwelthModal implements Serializable {

  public  String marksobtained = "", outofmarks = "", percentage = "", schoolnametwelth = "",selectedstreamBytes1="",selectedboardBytes1="",monthandyearofpassing12="";

    public MyProfileTwelthModal(String marksobtained, String outofmarks, String percentage, String schoolnametwelth, String selectedstreamBytes1, String selectedboardBytes1, String monthandyearofpassing12) {
        this.marksobtained = marksobtained;
        this.outofmarks = outofmarks;
        this.percentage = percentage;
        this.schoolnametwelth = schoolnametwelth;
        this.selectedstreamBytes1 = selectedstreamBytes1;
        this.selectedboardBytes1 = selectedboardBytes1;
        this.monthandyearofpassing12 = monthandyearofpassing12;
    }

    public String getMarksobtained() {
        return marksobtained;
    }

    public void setMarksobtained(String marksobtained) {
        this.marksobtained = marksobtained;
    }

    public String getOutofmarks() {
        return outofmarks;
    }

    public void setOutofmarks(String outofmarks) {
        this.outofmarks = outofmarks;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getSchoolnametwelth() {
        return schoolnametwelth;
    }

    public void setSchoolnametwelth(String schoolnametwelth) {
        this.schoolnametwelth = schoolnametwelth;
    }

    public String getSelectedstreamBytes1() {
        return selectedstreamBytes1;
    }

    public void setSelectedstreamBytes1(String selectedstreamBytes1) {
        this.selectedstreamBytes1 = selectedstreamBytes1;
    }

    public String getSelectedboardBytes1() {
        return selectedboardBytes1;
    }

    public void setSelectedboardBytes1(String selectedboardBytes1) {
        this.selectedboardBytes1 = selectedboardBytes1;
    }

    public String getMonthandyearofpassing12() {
        return monthandyearofpassing12;
    }

    public void setMonthandyearofpassing12(String monthandyearofpassing12) {
        this.monthandyearofpassing12 = monthandyearofpassing12;
    }




}
