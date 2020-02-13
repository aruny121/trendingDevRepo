package com.arunyadav.trendingdev.retrofit;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String BASE_URL = "https://github-trending-api.now.sh/";

    @GET("repositories")
    Call<List<RepositoryModel>> getRespositoryApi();

    @GET("developers")
    Call<List<DeveloperModel>> getDeveloperApi();


}
