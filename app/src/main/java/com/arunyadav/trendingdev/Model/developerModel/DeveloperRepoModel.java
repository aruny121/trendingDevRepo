package com.arunyadav.trendingdev.Model.developerModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Author - Arun yadav
 * Description - Model for developer list sublist
 */
public class DeveloperRepoModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
