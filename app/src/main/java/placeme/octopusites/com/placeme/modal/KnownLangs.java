package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;


public class KnownLangs implements Serializable {

    String knownlang,proficiency;

    public String getKnownlang() {
        return knownlang;
    }

    public void setKnownlang(String knownlang) {
        this.knownlang = knownlang;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public KnownLangs(String knownlang, String proficiency)
    {

        this.knownlang=knownlang;
        this.proficiency=proficiency;
    }


}
