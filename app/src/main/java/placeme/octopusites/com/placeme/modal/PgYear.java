package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 20-Nov-17.
 */

public class PgYear implements Serializable {

    String marksyear1,outofyear1,percentyear1,marksyear2,outofyear2,percentyear2,marksyear3,outofyear3,percentyear3,aggregatepgyear,schoolnamepgyears,monthandyearofpassingpgyear;
    String selectedCoursepgyear,selectedStreampgyear,selectedUniversitypgyear;

    public PgYear(String marksyear1, String outofyear1, String percentyear1, String marksyear2, String outofyear2, String percentyear2, String marksyear3, String outofyear3, String percentyear3, String aggregatepgyear, String schoolnamepgyears, String monthandyearofpassingpgyear, String selectedCoursepgyear, String selectedStreampgyear, String selectedUniversitypgyear)
    {
        this.marksyear1=marksyear1;
        this.outofyear1=outofyear1;
        this.percentyear1=percentyear1;
        this.marksyear2=marksyear2;

        this.outofyear2=outofyear2;
        this.percentyear2=percentyear2;
        this.marksyear3=marksyear3;
        this.outofyear3=outofyear3;
        this.percentyear3=percentyear3;
        this.aggregatepgyear=aggregatepgyear;
        this.schoolnamepgyears=schoolnamepgyears;
        this.monthandyearofpassingpgyear=monthandyearofpassingpgyear;
        this.selectedCoursepgyear=selectedCoursepgyear;
        this.selectedStreampgyear=selectedStreampgyear;
        this.selectedUniversitypgyear=selectedUniversitypgyear;

    }

    public String getMarksyear1() {
        return marksyear1;
    }

    public void setMarksyear1(String marksyear1) {
        this.marksyear1 = marksyear1;
    }

    public String getOutofyear1() {
        return outofyear1;
    }

    public void setOutofyear1(String outofyear1) {
        this.outofyear1 = outofyear1;
    }

    public String getPercentyear1() {
        return percentyear1;
    }

    public void setPercentyear1(String percentyear1) {
        this.percentyear1 = percentyear1;
    }

    public String getMarksyear2() {
        return marksyear2;
    }

    public void setMarksyear2(String marksyear2) {
        this.marksyear2 = marksyear2;
    }

    public String getOutofyear2() {
        return outofyear2;
    }

    public void setOutofyear2(String outofyear2) {
        this.outofyear2 = outofyear2;
    }

    public String getPercentyear2() {
        return percentyear2;
    }

    public void setPercentyear2(String percentyear2) {
        this.percentyear2 = percentyear2;
    }

    public String getMarksyear3() {
        return marksyear3;
    }

    public void setMarksyear3(String marksyear3) {
        this.marksyear3 = marksyear3;
    }

    public String getOutofyear3() {
        return outofyear3;
    }

    public void setOutofyear3(String outofyear3) {
        this.outofyear3 = outofyear3;
    }

    public String getPercentyear3() {
        return percentyear3;
    }

    public void setPercentyear3(String percentyear3) {
        this.percentyear3 = percentyear3;
    }

    public String getAggregatepgyear() {
        return aggregatepgyear;
    }

    public void setAggregatepgyear(String aggregatepgyear) {
        this.aggregatepgyear = aggregatepgyear;
    }

    public String getSchoolnamepgyears() {
        return schoolnamepgyears;
    }

    public void setSchoolnamepgyears(String schoolnamepgyears) {
        this.schoolnamepgyears = schoolnamepgyears;
    }

    public String getMonthandyearofpassingpgyear() {
        return monthandyearofpassingpgyear;
    }

    public void setMonthandyearofpassingpgyear(String monthandyearofpassingpgyear) {
        this.monthandyearofpassingpgyear = monthandyearofpassingpgyear;
    }

    public String getSelectedCoursepgyear() {
        return selectedCoursepgyear;
    }

    public void setSelectedCoursepgyear(String selectedCoursepgyear) {
        this.selectedCoursepgyear = selectedCoursepgyear;
    }

    public String getSelectedStreampgyear() {
        return selectedStreampgyear;
    }

    public void setSelectedStreampgyear(String selectedStreampgyear) {
        this.selectedStreampgyear = selectedStreampgyear;
    }

    public String getSelectedUniversitypgyear() {
        return selectedUniversitypgyear;
    }

    public void setSelectedUniversitypgyear(String selectedUniversitypgyear) {
        this.selectedUniversitypgyear = selectedUniversitypgyear;
    }


}
