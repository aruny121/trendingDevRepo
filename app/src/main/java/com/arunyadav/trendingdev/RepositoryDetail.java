package com.arunyadav.trendingdev;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.adapter.MyDeveloperRecyclerViewAdapter;
import com.arunyadav.trendingdev.adapter.RepositoryDetailsMemberAdapter;
import com.arunyadav.trendingdev.constants.Constants;
import com.arunyadav.trendingdev.viewModel.DeveloperViewModel;
import com.arunyadav.trendingdev.viewModel.RepositoryDetailViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
/**
 * Author - Arun yadav
 * Description - repository detail screen
 */
public class RepositoryDetail extends AppCompatActivity {

    private ImageView avatar ;
    private TextView content,item_number, language, description;
    RepositoryModel repositoryModel;
     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        repositoryModel = (RepositoryModel) getIntent().getSerializableExtra(Constants.INTENT_REPO_DETAILS);
        if(repositoryModel == null ) {
                finish();
                return ;
            }
        setIntialView();
        seViewForRepository();
        RepositoryDetailViewModel  repositoryDetailViewModel = ViewModelProviders.of(this).get(RepositoryDetailViewModel.class);
        setValueForAdapter(repositoryDetailViewModel);
       }




    public void setValueForAdapter(RepositoryDetailViewModel repositoryDetailViewModel){
        if ( recyclerView ==null && repositoryDetailViewModel ==null){
            Toast.makeText(getApplicationContext(),Constants.ERROR,Toast.LENGTH_LONG).show();
            return;
        }
        repositoryDetailViewModel.getBuildBy(repositoryModel.getParentid()).observe(this, new Observer<List<BuiltBy>>() {
            @Override
            public void onChanged(@Nullable List<BuiltBy> builtByList) {
                System.out.print("****"+builtByList.toString());
                if(builtByList.size()!=0){
                    recyclerView.setAdapter(new RepositoryDetailsMemberAdapter(getApplicationContext(), builtByList));
                }
                }
        });
    }

    public  void setIntialView(){
        avatar = findViewById(R.id.avatar);
        content = findViewById(R.id.content);
        item_number = findViewById(R.id.item_number);
        language = findViewById(R.id.language);
        description = findViewById(R.id.description);
        recyclerView = (RecyclerView) findViewById(R.id.memberadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void seViewForRepository(){
        content.setText(repositoryModel.getName());
        item_number.setText(repositoryModel.getAuthor());
        description.setText(repositoryModel.getDescription());
        if (repositoryModel.getLanguage() != null && repositoryModel.getLanguage().length()!= 0 && repositoryModel.getLanguageColor()!= null
                && repositoryModel.getLanguageColor().equals("")) {
            language.setText(repositoryModel.getLanguage());
            if(repositoryModel.getLanguageColor().startsWith(Constants.START_WITH_COLOR_CHECK))
                language.setTextColor(Color.parseColor(repositoryModel.getLanguageColor()));
        }else {
            language.setVisibility(View.GONE);
        }
        Glide.with(this).load(repositoryModel.getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(avatar);
    }

}
