package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 18-Nov-17.
 */

public class Publications implements Serializable {

    String title,publication,author,publicationdate,url,description;

    public Publications(String title, String publication, String author, String publicationdate, String url, String description)
    {
        this.title=title;
        this.publication=publication;

        this.author=author;
        this.publicationdate=publicationdate;
        this.url=url;
        this.description=description;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
