package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

public class Courses implements Serializable{

    String name,inst,fromdate,todate;

    public Courses(String name, String inst, String fromdate, String todate)
    {

        this.name=name;
        this.inst=inst;
        this.fromdate=fromdate;
        this.todate=todate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
