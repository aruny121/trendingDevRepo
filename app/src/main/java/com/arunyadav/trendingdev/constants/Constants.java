package com.arunyadav.trendingdev.constants;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Author - Arun yadav
 * Description - Constants file to handle hard code values
 */
public class Constants {
    public  static final String SEARCH_SUGGESTION ="Full Author name";
    public static  final String INTENT_REPO_DETAILS = "RepoDetails";
    public static  final String ERROR = "Error .. please try again";
    public static  final String START_WITH_COLOR_CHECK = "#";

    public static  final String DATABASE_NAME = "TRENDING_DB";
    public static  final String DATABASE_DEVELOPER_TABLE_NAME = "developer_all";
    public static  final String DATABASE_REPOSITORY_SUB_MEMBER_TABLE_NAME = "repository_build_by";
    public static  final String DATABASE_REPOSITORY_TABLE_NAME = "repository_all";

    public static  final String API_BASE_URL = "https://github-trending-api.now.sh/";
    public static  final String API_REPOSITORIES_TAG = "repositories";
    public static  final String API_DEVELOPERS_TAG = "developers";


    public static  final String NO_INTERNET = "No internet is available";

}
