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

    private LiveData<List<RepositoryModel>> allRepository;

    public TrendingViewModel(@NonNull Application application) {
        super(application);
        trendingViewModelHelper = new TrendingViewModelHelper(application);
        allRepository = trendingViewModelHelper.getAllRepository();
    }

    public LiveData<List<RepositoryModel>> getAllRepository() {
        return allRepository;
    }

    public void insertMovie(RepositoryModel movie) {
       trendingViewModelHelper.insertMovie(movie);
    }
}
