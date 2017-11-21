package placeme.octopusites.com.placeme;

import android.content.Context;

/**
 * Created by Lincoln on 15/01/16.
 */
public class RecyclerItemUsers {
    private String name, email,mobile;
    int id;



    public RecyclerItemUsers(int id, String name, String email,String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.id=id;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
