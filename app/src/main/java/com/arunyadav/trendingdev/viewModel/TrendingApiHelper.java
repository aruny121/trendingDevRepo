package com.arunyadav.trendingdev.viewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.db.TrendingDB;
import com.arunyadav.trendingdev.retrofit.ApiInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendingApiHelper {

    Application application;
    public  TrendingApiHelper(Application application){
        this.application = application;
    }
    private static OkHttpClient providesOkHttpClientBuilder(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS).build();
        }


    List<RepositoryModel> webserviceResponseList = new ArrayList<>();
    public LiveData<List<RepositoryModel>> providesWebService() {
        final MutableLiveData<List<RepositoryModel>> data = new MutableLiveData<>();
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(providesOkHttpClientBuilder())
                    .build();
            ApiInterface service = retrofit.create(ApiInterface.class);
            Call<List<RepositoryModel>> call = service.getRespositoryApi();
            call.enqueue(new Callback<List<RepositoryModel>>() {
                @Override
                public void onResponse(Call<List<RepositoryModel>> call, Response<List<RepositoryModel>> response) {
                    webserviceResponseList = response.body();
                    System.out.print("response is" + webserviceResponseList);
                    Log.d("Repository", "Response::::" + response.body());
                    TrendingViewModelHelper postRoomDBRepository = new TrendingViewModelHelper(application);
                    postRoomDBRepository.insertRepository(webserviceResponseList);
                    data.setValue(webserviceResponseList);
                }
                @Override
                public void onFailure(Call<List<RepositoryModel>> call, Throwable t) {
                    Log.d("Repository", "Response::::gfgdfgdf");

                }
            });
            }catch (Exception e){
            e.printStackTrace();
        }
        return  data;
        }
}
