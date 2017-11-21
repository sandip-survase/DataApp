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

    public String getInstname() {
        return instname;
    }

    public void setInstname(String instname) {
        this.instname = instname;
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

    public AdminInstituteModal(String instname, String instemail, String instweb, String instphone, String instaltrphone, String univname, String instregno) {
        this.instname = instname;
        this.instemail = instemail;
        this.instweb = instweb;
        this.instphone = instphone;
        this.instaltrphone = instaltrphone;
        this.univname = univname;
        this.instregno = instregno;

    }







}
