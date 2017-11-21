package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/20/2017.
 */

public class ModalHrIntro implements Serializable {
    public String firstname,lastname,selectedCountry,selectedState,selectedCity,designationValue ;

    public ModalHrIntro(String firstname,String lastname,String designationValue,String selectedCountry,String selectedState,String selectedCity)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.designationValue=designationValue;
        this.selectedCountry=selectedCountry;
        this.selectedState=selectedState;
        this.selectedCity=selectedCity;
    }
}
