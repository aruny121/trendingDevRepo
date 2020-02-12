package com.arunyadav.trendingdev.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;

import java.util.List;
@Dao
public interface RespositoryBuildByDao {

    @Insert
    void insertMovie(BuiltBy builtBy);

    @Update
    void updateMovie(BuiltBy builtBy);

    @Delete
    void deleteMovie(BuiltBy builtBy);

    @Query("DELETE FROM repository_build_by")
    void deleteAllMovies();

    @Query("SELECT * FROM repository_build_by ORDER BY idchild DESC")
    LiveData<List<BuiltBy>> getRepositoryBuildBy();

}