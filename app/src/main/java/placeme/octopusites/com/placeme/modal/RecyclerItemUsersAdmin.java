package placeme.octopusites.com.placeme.modal;


public class RecyclerItemUsersAdmin {
    private String name, email, role, isactivated, encemail, signatsure;
    String isplaceds, isDebars, companyNames, verifys;


    public String getSignatsure() {
        return signatsure;
    }

    public void setSignatsure(String signatsure) {
        this.signatsure = signatsure;
    }

    public RecyclerItemUsersAdmin(String encemail, String name, String email, String role, String isactivated, String signatsure, String isplaceds, String isDebars, String companyNames, String verifys) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.isactivated = isactivated;
        this.encemail = encemail;
        this.signatsure = signatsure;
        this.isplaceds = isplaceds;
        this.isDebars = isDebars;
        this.companyNames = companyNames;
        this.verifys = verifys;


    }

    public String getIsplaceds() {
        return isplaceds;
    }

    public void setIsplaceds(String isplaceds) {
        this.isplaceds = isplaceds;
    }

    public String getIsDebars() {
        return isDebars;
    }

    public void setIsDebars(String isDebars) {
        this.isDebars = isDebars;
    }

    public String getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(String companyNames) {
        this.companyNames = companyNames;
    }

    public String getVerifys() {
        return verifys;
    }

    public void setVerifys(String verifys) {
        this.verifys = verifys;
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
