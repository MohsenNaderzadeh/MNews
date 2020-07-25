package ir.developer_boy.mnews.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "tbl_news")
public class News implements Parcelable {
    private String title;
    private String content;
    private String image;
    @PrimaryKey
    private int id;
    private String date;

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
    @ColumnInfo(name = "is_bookmarked")
    private boolean isBookmarked;

    protected News(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.image = in.readString();
        this.id = in.readInt();
        this.date = in.readString();
        this.isBookmarked = in.readByte() != 0;
        this.video = in.readString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String video;

    public News() {
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public boolean isVideoNews(){
        if (video != null && image != null) {
            return video.length() > 0 && image.length() > 0;
        } else {
            return false;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.image);
        dest.writeInt(this.id);
        dest.writeString(this.date);
        dest.writeByte(this.isBookmarked ? (byte) 1 : (byte) 0);
        dest.writeString(this.video);
    }
}
