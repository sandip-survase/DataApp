package placeme.octopusites.com.placeme;

/**
 * Created by Lincoln on 15/01/16.
 */
public class RecyclerItemUsersAdmin {
    private String name, email,role,isactivated,encemail,signatsure;


    public String getSignatsure() {
        return signatsure;
    }

    public void setSignatsure(String signatsure) {
        this.signatsure = signatsure;
    }

    public RecyclerItemUsersAdmin(String encemail, String name, String email, String role, String isactivated, String signatsure) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.isactivated = isactivated;
        this.encemail=encemail;
        this.signatsure=signatsure;


    }

    public String getIsactivated() {
        return isactivated;
    }

    public void setIsactivated(String isactivated) {
        this.isactivated = isactivated;
    }

    public String getEncemail() {
        return encemail;
    }

    public void setEncemail(String encemail) {
        this.encemail = encemail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
