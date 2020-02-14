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
 * Description - VIEW Model for repository list
 */
public class RepositoryViewModel extends AndroidViewModel {

    private TrendingViewModelHelper trendingViewModelHelper;
    private TrendingApiHelper trendingApiHelper;
    private LiveData<List<RepositoryModel>> allRepository;
    private LiveData<List<RepositoryModel>> retroObservable;
    private LiveData<List<RepositoryModel>> allRepositorySearch;


    public RepositoryViewModel(@NonNull Application application) {
        super(application);
        trendingViewModelHelper = new TrendingViewModelHelper(application);
        trendingApiHelper = new TrendingApiHelper(application);
        retroObservable = trendingApiHelper.providesWebService();
        allRepository = trendingViewModelHelper.getAllRepository();

    }

    public LiveData<List<RepositoryModel>> getAllRepository() {
        return allRepository;
    }

    public LiveData<List<RepositoryModel>> getAllRepositorySearch(String searchText) {
        allRepositorySearch = trendingViewModelHelper.getAllRepositorySearch(searchText);
        return allRepositorySearch;
    }

}
