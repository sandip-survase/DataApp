package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 18-Nov-17.
 */

public class Honors implements Serializable {

    String title,issuer,description,yearofhonor;

    public Honors(String title, String issuer, String description, String yearofhonor)
    {
        this.title=title;
        this.issuer=issuer;
        this.description=description;
        this.yearofhonor=yearofhonor;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYearofhonor() {
        return yearofhonor;
    }

    public void setYearofhonor(String yearofhonor) {
        this.yearofhonor = yearofhonor;
    }


}
