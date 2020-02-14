package com.arunyadav.trendingdev;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.arunyadav.trendingdev.Fragments.DeveloperFragment;
import com.arunyadav.trendingdev.Fragments.RepositoryFragment;
import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.constants.Constants;
import com.arunyadav.trendingdev.constants.MyReceiver;
import com.arunyadav.trendingdev.viewModel.RepositoryViewModel;
/**
 * Author - Arun yadav
 * Description - Dashboard class
 */
public class WelcomeActivity extends AppCompatActivity implements DeveloperFragment.OnDeveloperFragmentInteractionListener, RepositoryFragment.OnRepositoryFragmentInteractionListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    RepositoryViewModel viewModel;
    private ViewPager mViewPager;
    private SearchView searchView;
    public Fragment fragmentVisible;
    private BroadcastReceiver MyReceiver = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        MyReceiver = new MyReceiver();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        searchView = findViewById(R.id.searchRepo);
        searchView.setQueryHint(Constants.SEARCH_SUGGESTION);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (0 == mViewPager.getCurrentItem() && fragmentVisible != null) {
                    ((RepositoryFragment) fragmentVisible).onSearchRepository(s);
                }
                return false;
            }
        });


        viewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnDeveloperFragmentInteractionListener(DeveloperModel item) {

    }

    @Override
    public void OnRepositoryFragmentInteractionListener(RepositoryModel item) {
        item.getParentid();
        Intent intent = new Intent(this, RepositoryDetail.class);
        intent.putExtra(Constants.INTENT_REPO_DETAILS, item);
        startActivity(intent);

    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fm = new Fragment();
            switch (position) {
                case 0:
                    fm = new RepositoryFragment();
                    fragmentVisible = fm;
                    break;
                case 1:
                    fm = new DeveloperFragment();
                    break;
            }
            return fm;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(MyReceiver);

    }
}
