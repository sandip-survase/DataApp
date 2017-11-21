package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

public class Experiences implements Serializable{

    String post,inst,fromdate,todate;

    public Experiences(String post, String inst, String fromdate, String todate)
    {

        this.post=post;
        this.inst=inst;
        this.fromdate=fromdate;
        this.todate=todate;
    }
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }


}
