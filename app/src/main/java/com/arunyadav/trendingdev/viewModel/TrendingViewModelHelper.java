package com.arunyadav.trendingdev.viewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.dao.RepositoryAllDao;
import com.arunyadav.trendingdev.db.TrendingDB;

import java.util.List;

public class TrendingViewModelHelper {
    private RepositoryAllDao repositoryAllDao;
    private LiveData<List<RepositoryModel>> allRepository;

    public TrendingViewModelHelper(Application application) {
        TrendingDB db = TrendingDB.getInstance(application);
        repositoryAllDao = db.repositoryAllDao();
        allRepository = repositoryAllDao.getRepositoryAll();
    }

    public void insertRepository(List<RepositoryModel> repositoryModel) {
        new InsertMovieAsyncTask(repositoryAllDao).execute(repositoryModel);
    }


    public LiveData<List<RepositoryModel>> getAllRepository() {
        return allRepository;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<List<RepositoryModel>, Void, Void> {
        private RepositoryAllDao repositoryAllDao;
        private InsertMovieAsyncTask(RepositoryAllDao repositoryAllDao) {
            this.repositoryAllDao = repositoryAllDao;
        }

        @Override
        protected Void doInBackground(List<RepositoryModel>... repository) {
            repositoryAllDao.insertRepositoryAll(repository[0]);
            return null;
        }
    }
}
