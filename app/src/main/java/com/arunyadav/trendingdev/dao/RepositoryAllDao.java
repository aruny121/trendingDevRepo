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

    @Query("DELETE FROM repository_all")
    void deleteAllRepository();

    @Query("SELECT * FROM repository_all ORDER BY parentid DESC")
    LiveData<List<RepositoryModel>> getRepositoryAll();



    @Insert
    void insertRepositoryBuildby(List<BuiltBy> builtBy);

    @Query("SELECT * FROM repository_build_by WHERE parentid= :parent_id ORDER BY idchild DESC")
    LiveData<List<BuiltBy>> getRepositoryBuildBy(long parent_id);

    @Query("DELETE FROM repository_build_by")
    void deleteRepositoryBuildby();
}