package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/20/2017.
 */

public class ModalHrIntro implements Serializable {
    public String firstname,lastname,selectedCountry,selectedState,selectedCity,designationValue ;

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

    public String getDesignationValue() {
        return designationValue;
    }

    public void setDesignationValue(String designationValue) {
        this.designationValue = designationValue;
    }

    public ModalHrIntro(String firstname, String lastname, String designationValue, String selectedCountry, String selectedState, String selectedCity)
    {

        this.firstname=firstname;
        this.lastname=lastname;
        this.designationValue=designationValue;
        this.selectedCountry=selectedCountry;
        this.selectedState=selectedState;
        this.selectedCity=selectedCity;
    }
}
