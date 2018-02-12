package placeme.octopusites.com.placeme.modal;


import java.io.Serializable;

public class MainListModal implements Serializable {
    private String name=null, email=null;
    private boolean isShortListed=false,isPlaced=false;
    private boolean isShortlistNotifSent=false,isPlacednotifSent=false;

    public MainListModal(String name, String email, boolean isShortListed, boolean isPlaced, boolean isShortlistNotifSent, boolean isPlacednotifSent) {
        this.name = name;
        this.email = email;
        this.isShortListed = isShortListed;
        this.isPlaced = isPlaced;
        this.isShortlistNotifSent = isShortlistNotifSent;
        this.isPlacednotifSent = isPlacednotifSent;
    }

    public boolean isShortlistNotifSent() {
        return isShortlistNotifSent;
    }

    public void setShortlistNotifSent(boolean shortlistNotifSent) {
        isShortlistNotifSent = shortlistNotifSent;
    }

    public boolean isPlacednotifSent() {
        return isPlacednotifSent;
    }

    public void setPlacednotifSent(boolean placednotifSent) {
        isPlacednotifSent = placednotifSent;
    }
//    public MainListModal(String name, String email, boolean isShortListed, boolean isPlaced) {
//        this.name = name;
//        this.email = email;
//        this.isShortListed = isShortListed;
//        this.isPlaced = isPlaced;
//    }

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

    public boolean isShortListed() {
        return isShortListed;
    }

    public void setShortListed(boolean shortListed) {
        isShortListed = shortListed;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }
}
