package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/20/2017.
 */

public class CompanyDetailsModal implements Serializable {
    public String ComName, ComMail,ComWeb,ComPhone,CompanyNature,ComAlterPhone,ComCIIN,ComAdd1, ComAdd2, ComAdd3;

    public  CompanyDetailsModal(String ComName,String ComMail,String ComWeb,String ComPhone,String ComAlterPhone,String ComCIIN,String ComAdd1,String ComAdd2,String ComAdd3,String CompanyNature){

        this.ComName=ComName;
        this.ComMail=ComMail;
        this.ComWeb=ComWeb;
        this.ComPhone=ComPhone;
        this.ComAlterPhone=ComAlterPhone;
        this.ComCIIN=ComCIIN;
        this.ComAdd1=ComAdd1;
        this.ComAdd2=ComAdd2;
        this.ComAdd3=ComAdd3;
        this.CompanyNature=CompanyNature;

    }
}
