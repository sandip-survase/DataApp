package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 17-Nov-17.
 */

public class Projects implements Serializable{

    String proj1,domain1,team1,duration1;

    public Projects(String proj1, String domain1, String team1, String duration1)
    {
        this.proj1=proj1;
        this.domain1=domain1;
        this.team1=team1;
        this.duration1=duration1;
    }
    public String getProj1() {
        return proj1;
    }

    public void setProj1(String proj1) {
        this.proj1 = proj1;
    }

    public String getDomain1() {
        return domain1;
    }

    public void setDomain1(String domain1) {
        this.domain1 = domain1;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getDuration1() {
        return duration1;
    }

    public void setDuration1(String duration1) {
        this.duration1 = duration1;
    }
}
