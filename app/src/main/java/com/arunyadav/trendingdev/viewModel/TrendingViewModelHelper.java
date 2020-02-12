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

    public void insertMovie(RepositoryModel repositoryModel) {
        new InsertMovieAsyncTask(repositoryAllDao).execute(repositoryModel);
    }

//    public void updateMovie(Movie movie) {
//        new UpdateMovieAsyncTask(movieDao).execute(movie);
//    }
//
//    public void deleteMovie(Movie movie) {
//        new DeleteMovieAsyncTask(movieDao).execute(movie);
//    }
//
//    public void deleteAllMovies() {
//        new DeleteAllMovieAsyncTask(movieDao).execute();
//    }

    public LiveData<List<RepositoryModel>> getAllRepository() {
        return allRepository;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<RepositoryModel, Void, Void> {
        private RepositoryAllDao repositoryAllDao;

        private InsertMovieAsyncTask(RepositoryAllDao repositoryAllDao) {
            this.repositoryAllDao = repositoryAllDao;
        }

        @Override
        protected Void doInBackground(RepositoryModel... repository) {
            repositoryAllDao.insertRepositoryAll(repository[0]);
            return null;
        }
    }

//    private static class UpdateMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
//        private MovieDao movieDao;
//
//        private UpdateMovieAsyncTask(MovieDao movieDao) {
//            this.movieDao = movieDao;
//        }
//
//        @Override
//        protected Void doInBackground(Movie... movies) {
//            movieDao.updateMovie(movies[0]);
//            return null;
//        }
//    }

//    private static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
//        private MovieDao movieDao;
//
//        private DeleteMovieAsyncTask(MovieDao movieDao) {
//            this.movieDao = movieDao;
//        }
//
//        @Override
//        protected Void doInBackground(Movie... movies) {
//            movieDao.deleteMovie(movies[0]);
//            return null;
//        }
//    }
//
//    private static class DeleteAllMovieAsyncTask extends AsyncTask<Void, Void, Void> {
//        private MovieDao movieDao;
//
//        private DeleteAllMovieAsyncTask(MovieDao movieDao) {
//            this.movieDao = movieDao;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            movieDao.deleteAllMovies();
//            return null;
//        }
//    }


}
