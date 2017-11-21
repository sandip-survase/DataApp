package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/20/2017.
 */


public class MyProfileTenthModal implements Serializable {

    public String marksobtained = "", outofmarks = "", percentage = "", schoolname = "", monthandyearofpassing = "", selectedBoard = "";
    public MyProfileTenthModal(String marksobtained,String outofmarks,String percentage ,String schoolname,String monthandyearofpassing,String selectedBoard)
    {
        this. marksobtained = marksobtained;
        this.outofmarks = outofmarks;
        this.percentage = percentage;
        this.schoolname = schoolname;
        this.monthandyearofpassing = monthandyearofpassing;
        this.selectedBoard =selectedBoard;
    }
}
