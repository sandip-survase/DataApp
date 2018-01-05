package placeme.octopusites.com.placeme.modal;

import android.content.Context;

/**
 * Created by sunny
 */
public class RecyclerItem {
    private String id,titleToShow,title,notificationToShow,notification,filename1,filename2,filename3,filename4,filename5,uploadtime,lastmodified,uploadedby;
    private boolean isAttachment,isRead;
    private String signature;
    Context context;

    public RecyclerItem() {
    }

    public RecyclerItem(String id,String titleToShow, String title, String notificationToShow,String notification,String filename1,String filename2,String filename3,String filename4,String filename5, String uploadtime,String lastmodified, boolean isAttachment,boolean isRead,String uploadedby, Context context,String signature) {
        this.id=id;
        this.titleToShow = titleToShow;
        this.title = title;
        this.notificationToShow = notificationToShow;
        this.notification = notification;
        this.filename1 = filename1;
        this.filename2 = filename2;
        this.filename3 = filename3;
        this.filename4 = filename4;
        this.filename5 = filename5;
        this.uploadtime = uploadtime;
        this.lastmodified = lastmodified;
        this.isAttachment=isAttachment;
        this.isRead=isRead;
        this.uploadedby = uploadedby;
        this.context=context;
        this.signature=signature;

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitleToShow() {
        return titleToShow;
    }
    public void setTitleToShow(String titleToShow) {
        this.titleToShow = titleToShow;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }
    public String getNotificationToShow() {
        return notificationToShow;
    }
    public void setNotificationToShow(String notificationToShow) { this.notificationToShow = notificationToShow;}
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
    public boolean getisAttachment() {
        return isAttachment;
    }
    public void setisAttachment(boolean isAttachment) {
        this.isAttachment = isAttachment;
    }
    public boolean getisRead() {return isRead; }
    public void setisRead(boolean isRead) { this.isRead = isRead;  }
    public String getUploadedby() {
        return uploadedby;
    }
    public void setUploadedby(String uploadedby) {
        this.uploadedby = uploadedby;
    }
    public Context getContext()
    {
        return this.context;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }

}
