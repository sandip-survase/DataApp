package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 18-Nov-17.
 */

public class Patents implements Serializable{

    String title,appno,patoffice,inventor,issuedorpending,issue,filing,url,description;

    public Patents(String title, String appno, String patoffice, String inventor, String issuedorpending, String issue, String filing, String url, String description)
    {
        this.title=title;
        this.appno=appno;
        this.issuedorpending=issuedorpending;
        this.patoffice=patoffice;
        this.inventor=inventor;
        this.issue=issue;
        this.filing=filing;
        this.url=url;
        this.description=description;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppno() {
        return appno;
    }

    public void setAppno(String appno) {
        this.appno = appno;
    }

    public String getInventor() {
        return inventor;
    }

    public String getPatoffice() {
        return patoffice;
    }

    public void setPatoffice(String patoffice) {
        this.patoffice = patoffice;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    public String getIssuedorpending() {
        return issuedorpending;
    }

    public void setIssuedorpending(String issuedorpending) {
        this.issuedorpending = issuedorpending;
    }
    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }


    public String getUrl() {
        return url;
    }

    public String getFiling() {
        return filing;
    }

    public void setFiling(String filing) {
        this.filing = filing;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
