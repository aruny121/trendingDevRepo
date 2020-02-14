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
 * Description - VIEW Model for repository details
 */
public class RepositoryDetailViewModel extends AndroidViewModel {
    private TrendingViewModelHelper trendingViewModelHelper;

    private LiveData<List<BuiltBy>> allBuildBy;

    int repo_parent_id = 0;

    public RepositoryDetailViewModel(@NonNull Application application) {
        super(application);
        trendingViewModelHelper = new TrendingViewModelHelper(application);


    }

    public LiveData<List<BuiltBy>> getBuildBy(int repo_parent_id) {
        this.repo_parent_id = repo_parent_id;
        allBuildBy = trendingViewModelHelper.getAllBuildBy(repo_parent_id);
        return allBuildBy;
    }
}
