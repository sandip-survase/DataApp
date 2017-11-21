package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 18-Nov-17.
 */

public class Skills implements Serializable {

    String skill,proficiency;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public Skills(String skill, String proficiency)
    {
        this.skill=skill;
        this.proficiency=proficiency;

    }
}
