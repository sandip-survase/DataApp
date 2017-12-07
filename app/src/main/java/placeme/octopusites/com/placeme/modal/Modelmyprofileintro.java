package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/20/2017.
 */

public class Modelmyprofileintro implements Serializable {
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String firstname,lastname,selectedCountry,selectedState,selectedCity ;

    public Modelmyprofileintro(String firstname,String lastname,String selectedCity ,String selectedState,String selectedCountry)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.selectedCountry=selectedCountry;
        this.selectedState=selectedState;
        this.selectedCity=selectedCity;
    }
}
