package placeme.octopusites.com.placeme;

/**
 * Created by Octopus ITES on 8/19/2017.
 */

public class VideoItem {
    private int id;
    private String thumbnail,title,description,url;
    public VideoItem() {
    }
    public VideoItem(int id,String thumbnail,String title,String description,String url) {
        super();
        this.id = id;
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.url = url;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
