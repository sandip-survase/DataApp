package placeme.octopusites.com.placeme.modal;

public class SubListModal {
    private String name=null, email=null;
    private boolean isSelected=false;

    public SubListModal(String name, String email, boolean isSselected) {
        this.name = name;
        this.email = email;
        this.isSelected = isSselected;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean sselected) {
        isSelected = sselected;
    }

}
