package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by sunny on 12/20/2017.
 */

public class RecyclerItemEdit implements Serializable {
    private String id,title,notification,filename1,filename2,filename3,filename4,filename5,uploadtime,lastmodified,uploadedby;
    private boolean isAttachment;
    private String signature;
    private boolean isread;





    public RecyclerItemEdit(String id, String title, String notification, String filename1, String filename2, String filename3, String filename4, String filename5, String uploadtime, String lastmodified, String uploadedby, boolean isAttachment, String signature,boolean isread ) {
        this.id = id;
        this.title = title;
        this.notification = notification;
        this.filename1 = filename1;
        this.filename2 = filename2;
        this.filename3 = filename3;
        this.filename4 = filename4;
        this.filename5 = filename5;
        this.uploadtime = uploadtime;
        this.lastmodified = lastmodified;
        this.uploadedby = uploadedby;
        this.isAttachment = isAttachment;
        this.signature = signature;
        this.isread = isread;


    }

    public RecyclerItemEdit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getFilename1() {
        return filename1;
    }

    public void setFilename1(String filename1) {
        this.filename1 = filename1;
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public String getFilename3() {
        return filename3;
    }

    public void setFilename3(String filename3) {
        this.filename3 = filename3;
    }

    public String getFilename4() {
        return filename4;
    }

    public void setFilename4(String filename4) {
        this.filename4 = filename4;
    }

    public String getFilename5() {
        return filename5;
    }

    public void setFilename5(String filename5) {
        this.filename5 = filename5;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getUploadedby() {
        return uploadedby;
    }

    public void setUploadedby(String uploadedby) {
        this.uploadedby = uploadedby;
    }

    public boolean isAttachment() {
        return isAttachment;
    }

    public void setAttachment(boolean attachment) {
        isAttachment = attachment;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

}

