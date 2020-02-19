package com.arunyadav.trendingdev;



import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Field;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.support.membermodification.MemberMatcher.methods;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RepositoryDetailTest.class, WelcomeActivity.class, Bundle.class})
public class RepositoryDetailTest {
    public static final String onCreate = "onCreate";
    public static final String onResume = "onResume";

    @Mock
    RepositoryDetail repositoryDeatils;
    @Mock
    TextView txtView;

    @Mock
    ImageView imageView;

    @Mock
    RecyclerView recyclerView;

    @Mock
    RepositoryModel repositoryModel;

    @Before
    public void setUp() {
        repositoryDeatils = spy(RepositoryDetail.class);
         txtView = PowerMockito.mock(TextView.class);
        imageView= PowerMockito.mock(ImageView.class);
        recyclerView = PowerMockito.mock(RecyclerView.class);

        repositoryModel = new RepositoryModel("as","as","as","as","as","as","as",1,1,1);
        repositoryModel.setParentid(1);
        DeveloperModel developerModel = null;

        doReturn(mock(TextView.class)).when(repositoryDeatils).findViewById(anyInt());
        when(repositoryDeatils.findViewById(R.id.avatar)).thenReturn(imageView);
        when(repositoryDeatils.findViewById(R.id.memberadapter)).thenReturn(recyclerView);
    }

    @Test
    public void testOnCreate()  {
        suppress(methods(WelcomeActivity.class, "setContentView"));
        repositoryDeatils.setIntialView();
    }


    @Test
    public  void seViewForRepositoryTest(){
        doReturn(txtView).when(repositoryDeatils).findViewById(anyInt());
        repositoryDeatils.seViewForRepository();
    }



}