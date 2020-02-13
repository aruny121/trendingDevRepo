package com.arunyadav.trendingdev.viewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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


    public TrendingViewModelHelper(Application application) {
        TrendingDB db = TrendingDB.getInstance(application);
        repositoryAllDao = db.repositoryAllDao();
        allRepository = repositoryAllDao.getRepositoryAll();
      //  allRepositoryMember = repositoryAllDao.getRepositoryBuildBy();

    }

    public void insertRepository(List<RepositoryModel> repositoryModel) {
        new InsertMovieAsyncTask(repositoryAllDao).execute(repositoryModel);
    }


    public LiveData<List<RepositoryModel>> getAllRepository() {
        return allRepository;
    }


//    public LiveData<List<BuiltBy>> getAllRepositoryMember() {
//        return allRepository;
//    }

    private static class InsertMovieAsyncTask extends AsyncTask<List<RepositoryModel>, Void, Void> {
        private RepositoryAllDao repositoryAllDao;
        private InsertMovieAsyncTask(RepositoryAllDao repositoryAllDao) {
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
