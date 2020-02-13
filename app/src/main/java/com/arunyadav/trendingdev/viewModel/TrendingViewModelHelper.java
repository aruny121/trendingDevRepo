package com.arunyadav.trendingdev.viewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.dao.RepositoryAllDao;
import com.arunyadav.trendingdev.db.TrendingDB;

import java.util.ArrayList;
import java.util.List;

public class TrendingViewModelHelper {
    private RepositoryAllDao repositoryAllDao;
    private LiveData<List<RepositoryModel>> allRepository;
    private LiveData<List<BuiltBy>> allRepositoryMember;
    private LiveData<List<DeveloperModel>> allDeveloper;



    public TrendingViewModelHelper(Application application) {
        TrendingDB db = TrendingDB.getInstance(application);
        repositoryAllDao = db.repositoryAllDao();
        allRepository = repositoryAllDao.getRepositoryAll();
        allDeveloper = repositoryAllDao.getDeveloperAll();

        //  allRepositoryMember = repositoryAllDao.getRepositoryBuildBy();

    }

    public void insertRepository(List<RepositoryModel> repositoryModel) {
        new InsertRepositoryAsyncTask(repositoryAllDao).execute(repositoryModel);
    }

    public void insertDeveloper(List<DeveloperModel> developerModels) {
        new InsertDeveloperAsyncTask(repositoryAllDao).execute(developerModels);
    }


    public LiveData<List<RepositoryModel>> getAllRepository() {
        return allRepository;
    }


    public LiveData<List<DeveloperModel>> getAllDeveloper() {
        return allDeveloper;
    }


//    public LiveData<List<BuiltBy>> getAllRepositoryMember() {
//        return allRepository;
//    }

    private static class InsertDeveloperAsyncTask extends AsyncTask<List<DeveloperModel>, Void, Void> {
        private RepositoryAllDao repositoryAllDao;
        private InsertDeveloperAsyncTask(RepositoryAllDao repositoryAllDao) {
            this.repositoryAllDao = repositoryAllDao;
        }

        @Override
        protected Void doInBackground(List<DeveloperModel>... repository) {
            repositoryAllDao.deleteAllDeveloper();
           repositoryAllDao.insertDeveloperAll(repository[0]);
            return null;
        }
    }



    private static class InsertRepositoryAsyncTask extends AsyncTask<List<RepositoryModel>, Void, Void> {
        private RepositoryAllDao repositoryAllDao;
        private InsertRepositoryAsyncTask(RepositoryAllDao repositoryAllDao) {
            this.repositoryAllDao = repositoryAllDao;
        }

        @Override
        protected Void doInBackground(List<RepositoryModel>... repository) {
            repositoryAllDao.deleteAllRepository();
            repositoryAllDao.deleteRepositoryBuildby();
            long[] primary_key = repositoryAllDao.insertRepositoryAll(repository[0]);
            List<BuiltBy> buildbymodel = new ArrayList<BuiltBy>();

            for(int i=0;i<repository[0].size();i++)
            {

                buildbymodel = repository[0].get(i).getBuiltBy();
                for(int j=0;j<buildbymodel.size();j++){
                    buildbymodel.get(j).setParentid(primary_key[i]);
                }
                repositoryAllDao.insertRepositoryBuildby(buildbymodel);

            }
            return null;
        }
    }
}
