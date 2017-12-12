package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/16/2017.
 */

public class AdminInstituteModal implements Serializable {

    String instname;
    String instemail;
    String instaltrphone;
    String univname;
    String instregno;
    String instweb;
    String instphone;
    String instcaddrline1,instcaddrline2,instcaddrline3;

    public String getInstname() {
        return instname;
    }

    public void setInstname(String instname) {
        this.instname = instname;
    }

    public String getInstcaddrline1() {
        return instcaddrline1;
    }

    public void setInstcaddrline1(String instcaddrline1) {
        this.instcaddrline1 = instcaddrline1;
    }

    public String getInstcaddrline2() {
        return instcaddrline2;
    }

    public void setInstcaddrline2(String instcaddrline2) {
        this.instcaddrline2 = instcaddrline2;
    }

    public String getInstcaddrline3() {
        return instcaddrline3;
    }

    public void setInstcaddrline3(String instcaddrline3) {
        this.instcaddrline3 = instcaddrline3;
    }

    public String getInstemail() {
        return instemail;
    }

    public void setInstemail(String instemail) {
        this.instemail = instemail;
    }

    public String getInstweb() {
        return instweb;
    }

    public void setInstweb(String instweb) {
        this.instweb = instweb;
    }

    public String getInstphone() {
        return instphone;
    }

    public void setInstphone(String instphone) {
        this.instphone = instphone;
    }

    public String getInstaltrphone() {
        return instaltrphone;
    }

    public void setInstaltrphone(String instaltrphone) {
        this.instaltrphone = instaltrphone;
    }

    public String getUnivname() {
        return univname;
    }

    public void setUnivname(String univname) {
        this.univname = univname;
    }

    public String getInstregno() {
        return instregno;
    }

    public void setInstregno(String instregno) {
        this.instregno = instregno;
    }

    public AdminInstituteModal(String instname, String instemail, String instweb, String instphone, String instaltrphone, String univname, String instregno,String instcaddrline1,String instcaddrline2,String instcaddrline3) {
        this.instname = instname;
        this.instemail = instemail;
        this.instweb = instweb;
        this.instphone = instphone;
        this.instaltrphone = instaltrphone;
        this.univname = univname;
        this.instregno = instregno;
        this.instcaddrline1 = instcaddrline1;
        this.instcaddrline2 = instcaddrline2;
        this.instcaddrline3 = instcaddrline3;
    }







}
