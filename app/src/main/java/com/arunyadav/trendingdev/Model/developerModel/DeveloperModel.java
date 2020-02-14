package com.arunyadav.trendingdev.Model.developerModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import com.arunyadav.trendingdev.constants.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Author - Arun yadav
 * Description - Model for developer
 */
@Entity(tableName = Constants.DATABASE_DEVELOPER_TABLE_NAME)
public class DeveloperModel {
    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    @PrimaryKey(autoGenerate = true)
    private int parentid;


    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("repo")
    @Expose
    @Ignore
    private DeveloperRepoModel repo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public DeveloperRepoModel getRepo() {
        return repo;
    }

    public void setRepo(DeveloperRepoModel repo) {
        this.repo = repo;
    }
}
