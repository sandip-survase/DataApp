package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/20/2017.
 */
public class AlumniIntroModal implements Serializable {

    public String firstname,lastname,selectedCountry,selectedState,selectedCity ;

    public AlumniIntroModal(String firstname,String lastname,String selectedCountry,String selectedState,String selectedCity)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.selectedCountry=selectedCountry;
        this.selectedState=selectedState;
        this.selectedCity=selectedCity;
    }
}
