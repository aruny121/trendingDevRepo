package com.arunyadav.trendingdev.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;

import java.util.List;

public class TrendingViewModel extends AndroidViewModel {

    private TrendingViewModelHelper trendingViewModelHelper;
    private TrendingApiHelper trendingApiHelper;
    private LiveData<List<RepositoryModel>> allRepository;
    private final LiveData<List<RepositoryModel>>  retroObservable;

    public TrendingViewModel(@NonNull Application application) {
        super(application);
        trendingViewModelHelper = new TrendingViewModelHelper(application);
        trendingApiHelper = new TrendingApiHelper(application);
        retroObservable = trendingApiHelper.providesWebService();
        allRepository = trendingViewModelHelper.getAllRepository();
    }

    public LiveData<List<RepositoryModel>> getAllRepository() {
        return allRepository;
    }
}
