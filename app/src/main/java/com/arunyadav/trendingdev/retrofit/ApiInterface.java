package com.arunyadav.trendingdev.retrofit;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.constants.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Author - Arun yadav
 * Description - Api connection Interface
 */
public interface ApiInterface {


    @GET(Constants.API_REPOSITORIES_TAG)
    Call<List<RepositoryModel>> getRespositoryApi();

    @GET(Constants.API_DEVELOPERS_TAG)
    Call<List<DeveloperModel>> getDeveloperApi();


}
