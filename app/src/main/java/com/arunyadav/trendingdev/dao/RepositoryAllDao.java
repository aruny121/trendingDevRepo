package com.arunyadav.trendingdev.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;

import java.util.List;


@Dao
public interface RepositoryAllDao {

    @Insert
    long[] insertRepositoryAll(List<RepositoryModel> repositoryModel);

    @Query("SELECT * FROM repository_all ORDER BY id DESC")
    LiveData<List<RepositoryModel>> getRepositoryAll();

}