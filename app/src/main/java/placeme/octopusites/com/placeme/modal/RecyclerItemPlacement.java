package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by sunny on 12/21/2017.
 */

public class RecyclerItemPlacement  implements Serializable {
    private String id,companyname,cpackage,post,forwhichcourse,forwhichstream,vacancies,lastdateofregistration,dateofarrival,bond,noofapti,nooftechtest,noofgd,noofti,noofhri,stdx,stdxiiordiploma,ug,pg,uploadtime,lastmodified,uploadedby,noofallowedliveatkt,noofalloweddeadatkt;
    private String signature;
    private String experiences,passingyear;
    private boolean isread;



    public RecyclerItemPlacement() {
    }
    public RecyclerItemPlacement(String id, String companyname, String cpackage, String post, String forwhichcourse, String forwhichstream, String vacancies, String lastdateofregistration, String dateofarrival, String bond, String noofapti, String nooftechtest, String noofgd, String noofti, String noofhri, String stdx, String stdxiiordiploma, String ug, String pg, String uploadtime, String lastmodified, String uploadedby, String noofallowedliveatkt,String noofalloweddeadatkt , String signature, String experiences, String passingyear, boolean isread) {
        this.id = id;
        this.companyname = companyname;
        this.cpackage = cpackage;
        this.post = post;
        this.forwhichcourse = forwhichcourse;
        this.forwhichstream = forwhichstream;
        this.vacancies = vacancies;
        this.lastdateofregistration = lastdateofregistration;
        this.dateofarrival = dateofarrival;
        this.bond = bond;
        this.noofapti = noofapti;
        this.nooftechtest = nooftechtest;
        this.noofgd = noofgd;
        this.noofti = noofti;
        this.noofhri = noofhri;
        this.stdx = stdx;
        this.stdxiiordiploma = stdxiiordiploma;
        this.ug = ug;
        this.pg = pg;
        this.uploadtime = uploadtime;
        this.lastmodified = lastmodified;
        this.uploadedby = uploadedby;
        this.noofallowedliveatkt = noofallowedliveatkt;
        this.noofalloweddeadatkt= noofalloweddeadatkt;

        this.signature = signature;
        this.experiences = experiences;
        this.passingyear = passingyear;
        this.isread = isread;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCpackage() {
        return cpackage;
    }

    public void setCpackage(String cpackage) {
        this.cpackage = cpackage;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getForwhichcourse() {
        return forwhichcourse;
    }

    public void setForwhichcourse(String forwhichcourse) {
        this.forwhichcourse = forwhichcourse;
    }

    public String getForwhichstream() {
        return forwhichstream;
    }

    public void setForwhichstream(String forwhichstream) {
        this.forwhichstream = forwhichstream;
    }

    public String getVacancies() {
        return vacancies;
    }

    public void setVacancies(String vacancies) {
        this.vacancies = vacancies;
    }

    public String getLastdateofregistration() {
        return lastdateofregistration;
    }

    public void setLastdateofregistration(String lastdateofregistration) {
        this.lastdateofregistration = lastdateofregistration;
    }

    public String getDateofarrival() {
        return dateofarrival;
    }

    public void setDateofarrival(String dateofarrival) {
        this.dateofarrival = dateofarrival;
    }

    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond;
    }

    public String getNoofapti() {
        return noofapti;
    }

    public void setNoofapti(String noofapti) {
        this.noofapti = noofapti;
    }

    public String getNooftechtest() {
        return nooftechtest;
    }

    public void setNooftechtest(String nooftechtest) {
        this.nooftechtest = nooftechtest;
    }

    public String getNoofgd() {
        return noofgd;
    }

    public void setNoofgd(String noofgd) {
        this.noofgd = noofgd;
    }

    public String getNoofti() {
        return noofti;
    }

    public void setNoofti(String noofti) {
        this.noofti = noofti;
    }

    public String getNoofhri() {
        return noofhri;
    }

    public void setNoofhri(String noofhri) {
        this.noofhri = noofhri;
    }

    public String getStdx() {
        return stdx;
    }

    public void setStdx(String stdx) {
        this.stdx = stdx;
    }

    public String getStdxiiordiploma() {
        return stdxiiordiploma;
    }

    public void setStdxiiordiploma(String stdxiiordiploma) {
        this.stdxiiordiploma = stdxiiordiploma;
    }

    public String getUg() {
        return ug;
    }

    public void setUg(String ug) {
        this.ug = ug;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getUploadedby() {
        return uploadedby;
    }

    public void setUploadedby(String uploadedby) {
        this.uploadedby = uploadedby;
    }



    public void setNoofallowedliveatkt(String noofallowedliveatkt) {
        this.noofallowedliveatkt = noofallowedliveatkt;
    }

    public String getNoofalloweddeadatkt() {
        return noofalloweddeadatkt;
    }



    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getPassingyear() {
        return passingyear;
    }

    public void setPassingyear(String passingyear) {
        this.passingyear = passingyear;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }
    public String getNoofallowedliveatkt() {
        return noofallowedliveatkt;
    }

    public void setNoofalloweddeadatkt(String noofalloweddeadatkt) {
        this.noofalloweddeadatkt = noofalloweddeadatkt;
    }
}
