package com.arunyadav.trendingdev.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.constants.Constants;
import com.arunyadav.trendingdev.dao.RepositoryAllDao;

/**
 * Author - Arun yadav
 * Description - DB Connection file
 */
@Database(entities = {BuiltBy.class, RepositoryModel.class, DeveloperModel.class}, version = 1, exportSchema = false)
public abstract class TrendingDB extends RoomDatabase {
    private static final String DB_NAME = Constants.DATABASE_NAME;

    private static TrendingDB instance;


    public static synchronized TrendingDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TrendingDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            new PopulateDBAsyncTask(instance).execute();
        }


    };

    public abstract RepositoryAllDao repositoryAllDao();


    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private RepositoryAllDao repositoryAllDao;


        public PopulateDBAsyncTask(TrendingDB instance) {
            this.repositoryAllDao = instance.repositoryAllDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
