package placeme.octopusites.com.placeme;

import android.content.Context;

/**
 * Created by Lincoln on 15/01/16.
 */
public class RecyclerItemAdminEdit {
    private String title, notification;
    int id;
    Context context;
    public RecyclerItemAdminEdit() {
    }

    public RecyclerItemAdminEdit(int id, String title, String notification) {
        this.title = title;
        this.notification = notification;
        this.id=id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }


    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Context getContext()
    {
        return this.context;
    }
}
