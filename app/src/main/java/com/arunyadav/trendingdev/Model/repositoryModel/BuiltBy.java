
package com.arunyadav.trendingdev.Model.repositoryModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "repository_build_by")
public class BuiltBy {


    public int getIdchild() {
        return idchild;
    }

    public void setIdchild(int idchild) {
        this.idchild = idchild;
    }

    @PrimaryKey(autoGenerate = true)
    private int idchild;






    public long getParentid() {
        return parentid;
    }

    public void setParentid(long parentid) {
        this.parentid = parentid;
    }

    private  long parentid;



    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("href")
    @Expose
    private String href;

    public BuiltBy(String username, String href, String avatar, long parentid) {
        this.username = username;
        this.href = href;
        this.avatar = avatar;
        this.parentid = parentid;

    }

    @SerializedName("avatar")
    @Expose
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
