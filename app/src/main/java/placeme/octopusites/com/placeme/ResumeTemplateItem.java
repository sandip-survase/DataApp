package placeme.octopusites.com.placeme;


public class ResumeTemplateItem {

    private int id;
    private String thumbnail,title,status;
    public ResumeTemplateItem() {
    }
    public ResumeTemplateItem(int id,String thumbnail,String title,String status) {
        this.id=id;
        this.thumbnail=thumbnail;
        this.title=title;
        this.status=status;
    }
    public int getId() {
        return id;
    }
    public void setId(int status) {
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
