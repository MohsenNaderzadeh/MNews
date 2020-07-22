package ir.developer_boy.mnews.categories;

import android.support.annotation.DrawableRes;

/**
 * Created by Saeed Shahini on 4/20/2018.
 */

public class Category {
    private int id;
    private String title;
    @DrawableRes
    private int icon;

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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
