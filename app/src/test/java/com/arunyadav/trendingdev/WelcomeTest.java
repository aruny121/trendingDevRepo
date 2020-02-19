package com.arunyadav.trendingdev;



import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
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

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.support.membermodification.MemberMatcher.methods;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WelcomeTest.class, WelcomeActivity.class, Bundle.class})
public class WelcomeTest {
    public static final String onCreate = "onCreate";
    public static final String onResume = "onResume";

    @Mock
    WelcomeActivity acknowledgementActivity;

    @Before
    public void setUp() {
        acknowledgementActivity = spy(WelcomeActivity.class);
    }

    @Test
    public void testOnCreate()  {

        suppress(methods(WelcomeActivity.class, "setContentView"));
        doReturn(mock(TextView.class)).when(acknowledgementActivity).findViewById(anyInt());

        RepositoryModel repositoryModel = new RepositoryModel("as","as","as","as","as","as","as",1,1,1);
        repositoryModel.setParentid(1);
        DeveloperModel developerModel = null;
        acknowledgementActivity.OnRepositoryFragmentInteractionListener(repositoryModel);
        acknowledgementActivity.OnDeveloperFragmentInteractionListener(developerModel);

    }



}