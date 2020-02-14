package com.arunyadav.trendingdev.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.R;
import com.arunyadav.trendingdev.adapter.RepositoryRecyclerViewAdapter;
import com.arunyadav.trendingdev.viewModel.RepositoryViewModel;

import java.util.List;


/**
 * Author - Arun yadav
 * Description - Fragments for Repository list
 */
public class RepositoryFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnRepositoryFragmentInteractionListener mListener;
    private RepositoryViewModel viewModel;
    RecyclerView recyclerView;

    public RepositoryFragment() {
    }

    @SuppressWarnings("unused")
    public static RepositoryFragment newInstance(int columnCount) {
        RepositoryFragment fragment = new RepositoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            viewModel = ViewModelProviders.of(this).get(RepositoryViewModel.class);
            viewModel.getAllRepository().observe(this, new Observer<List<RepositoryModel>>() {
                @Override
                public void onChanged(@Nullable List<RepositoryModel> repositoryModels) {
                    if (recyclerView != null && repositoryModels != null) {
                        recyclerView.setAdapter(new RepositoryRecyclerViewAdapter(getContext(), repositoryModels, mListener));
                        System.out.print("****" + repositoryModels.toString());
                    }
                }
            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRepositoryFragmentInteractionListener) {
            mListener = (OnRepositoryFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnRepositoryFragmentInteractionListener {
        void OnRepositoryFragmentInteractionListener(RepositoryModel item);
    }

    public void onSearchRepository(String s) {
        if (s == null || s.equalsIgnoreCase("") || s.length() == 0) {
            viewModel.getAllRepository().observe(this, new Observer<List<RepositoryModel>>() {
                @Override
                public void onChanged(@Nullable List<RepositoryModel> repositoryModels) {
                    if (recyclerView != null && repositoryModels != null) {
                        recyclerView.setAdapter(new RepositoryRecyclerViewAdapter(getContext(), repositoryModels, mListener));
                        System.out.print("****" + repositoryModels.toString());
                    }
                }
            });
        } else {

            viewModel.getAllRepositorySearch(s).observe(this, new Observer<List<RepositoryModel>>() {
                @Override
                public void onChanged(@Nullable List<RepositoryModel> repositoryModels) {
                    if (recyclerView != null && repositoryModels != null) {
                        recyclerView.setAdapter(new RepositoryRecyclerViewAdapter(getContext(), repositoryModels, mListener));
                        System.out.print("****" + repositoryModels.toString());
                    }
                }
            });
        }
    }
}
