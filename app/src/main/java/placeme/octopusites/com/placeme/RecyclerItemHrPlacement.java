package placeme.octopusites.com.placeme;


import java.io.Serializable;

/**
 * Created by Lincoln on 15/01/16.
 */
public class RecyclerItemHrPlacement implements Serializable {
    private String companyname,lastmodifiedtime,registerednumber="",placednumber="",lastdateofreg;
    int id;
    public RecyclerItemHrPlacement() {
    }

    public RecyclerItemHrPlacement(int id, String companyname, String lastmodifiedtime,String registerednumber,String placednumber,String lastdateofreg) {
        this.companyname = companyname;
        this.lastmodifiedtime = lastmodifiedtime;
        this.registerednumber = registerednumber;
        this.placednumber = placednumber;
        this.lastdateofreg = lastdateofreg;
        this.id=id;

    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }


    public String getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(String lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public String getRegisterednumber() {
        return registerednumber;
    }

    public void setRegisterednumber(String registerednumber) {
        this.registerednumber = registerednumber;
    }
    public String getLastdateofreg() {
        return lastdateofreg;
    }

    public void setLastdateofreg(String lastdateofreg) {
        this.lastdateofreg = lastdateofreg;
    }


    public String getPlacednumber() {
        return placednumber;
    }

    public void setPlacednumber(String placednumber) {
        this.placednumber = placednumber;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
