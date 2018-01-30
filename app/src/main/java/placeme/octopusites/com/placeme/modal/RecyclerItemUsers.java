package placeme.octopusites.com.placeme.modal;
import java.io.Serializable;
/**
 * Created by sunny .
 */
public class RecyclerItemUsers implements Serializable {
    private String name, email;
    int id;
    public  boolean isSelected=false;
    public  boolean isSelected2=false;
    public  boolean isSelected3=false;

    public  boolean isregistered=false;
    public  boolean isshortlisted=false;
    public  boolean isPlaced=false;

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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected2() {
        return isSelected2;
    }

    public void setSelected2(boolean selected2) {
        isSelected2 = selected2;
    }

    public boolean isSelected3() {
        return isSelected3;
    }

    public void setSelected3(boolean selected3) {
        isSelected3 = selected3;
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

    public RecyclerItemUsers( int id, String name, String email, boolean isSelected, boolean isSelected2, boolean isSelected3, boolean isregistered, boolean isshortlisted, boolean isPlaced) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.isSelected = isSelected;
        this.isSelected2 = isSelected2;
        this.isSelected3 = isSelected3;
        this.isregistered = isregistered;
        this.isshortlisted = isshortlisted;
        this.isPlaced = isPlaced;
    }


}
