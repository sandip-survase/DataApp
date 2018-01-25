package placeme.octopusites.com.placeme.modal;
import java.io.Serializable;
/**
 * Created by sunny .
 */
public class RecyclerItemUsers implements Serializable {
    private String name, email;
    int id;
    public  boolean isSelected=false;
    public  boolean isregistered=false;
    public  boolean isshortlisted=false;
    public  boolean isPlaced=false;
    public RecyclerItemUsers( int id, String name, String email, boolean isSelected, boolean isregistered, boolean isshortlisted, boolean isPlaced) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isSelected = isSelected;
        this.isregistered = isregistered;
        this.isshortlisted = isshortlisted;
        this.isPlaced = isPlaced;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isIsregistered() {
        return isregistered;
    }
    public void setIsregistered(boolean isregistered) {
        this.isregistered = isregistered;
    }
    public boolean isIsshortlisted() {
        return isshortlisted;
    }
    public void setIsshortlisted(boolean isshortlisted) {
        this.isshortlisted = isshortlisted;
    }
    public boolean isPlaced() {
        return isPlaced;
    }
    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }
}
