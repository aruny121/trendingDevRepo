package com.arunyadav.trendingdev.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;

import java.util.List;

/**
 * Author - Arun yadav
 * Description - Dao fil  to handle sqlite query
 */
@Dao
public interface RepositoryAllDao {
    /**
     * @param repositoryModel
     * @return desc - Dao for repository list and build by list
     */
    @Insert
    long[] insertRepositoryAll(List<RepositoryModel> repositoryModel);

    @Query("DELETE FROM repository_all")
    void deleteAllRepository();

    @Query("SELECT * FROM repository_all ORDER BY parentid DESC")
    LiveData<List<RepositoryModel>> getRepositoryAll();


    @Query("SELECT * FROM repository_all WHERE author= :searchText")
    LiveData<List<RepositoryModel>> getRepositoryAllSearch(String searchText);


    @Insert
    void insertRepositoryBuildby(List<BuiltBy> builtBy);

    @Query("SELECT * FROM repository_build_by WHERE parentid= :parent_id ORDER BY idchild DESC")
    LiveData<List<BuiltBy>> getRepositoryBuildBy(long parent_id);

    @Query("DELETE FROM repository_build_by")
    void deleteRepositoryBuildby();


    //*******************************************************************************//

    /**
     * @param developerModels Desc - dao for developer lit
     */
    @Insert
    long[] insertDeveloperAll(List<DeveloperModel> developerModels);

    @Query("DELETE FROM developer_all")
    void deleteAllDeveloper();

    @Query("SELECT * FROM developer_all ORDER BY parentid DESC")
    LiveData<List<DeveloperModel>> getDeveloperAll();

}