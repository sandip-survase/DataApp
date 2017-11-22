package placeme.octopusites.com.placeme;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by Lincoln on 15/01/16.
 */
public class RecyclerItemUsers implements Serializable {
    private String name, email;
    int id;
    boolean isSelected=false;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    Context mContext;


    public RecyclerItemUsers(int id, String name, String email,boolean isSelected ) {
        this.name = name;
        this.email = email;
        this.id=id;
        this.isSelected=isSelected;
        this.mContext=mContext;



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


}
