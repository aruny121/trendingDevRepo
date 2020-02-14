package com.arunyadav.trendingdev.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;

import java.util.List;

/**
 * Author - Arun yadav
 * Description - vIEW Model for developers list
 */
public class DeveloperViewModel extends AndroidViewModel {

    private TrendingViewModelHelper trendingViewModelHelper;
    private TrendingApiHelper trendingApiHelper;


    private LiveData<List<DeveloperModel>> allDeveloper;
    private LiveData<List<DeveloperModel>> retroObservableDeveloper;

    public DeveloperViewModel(@NonNull Application application) {
        super(application);
        trendingViewModelHelper = new TrendingViewModelHelper(application);
        trendingApiHelper = new TrendingApiHelper(application);
        retroObservableDeveloper = trendingApiHelper.providesWebServiceDeveloper();
        allDeveloper = trendingViewModelHelper.getAllDeveloper();
    }

    public LiveData<List<DeveloperModel>> getAllDeveloper() {
        return allDeveloper;
    }


}
