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

import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.R;
import com.arunyadav.trendingdev.adapter.MyDeveloperRecyclerViewAdapter;
import com.arunyadav.trendingdev.viewModel.DeveloperViewModel;

import java.util.List;

/**
 * Author - Arun yadav
 * Description - Fragments for developer list
 */
public class DeveloperFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnDeveloperFragmentInteractionListener mListener;
    private DeveloperViewModel viewModel;


    public DeveloperFragment() {
    }


    public static DeveloperFragment newInstance(int columnCount) {
        DeveloperFragment fragment = new DeveloperFragment();
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
        View view = inflater.inflate(R.layout.fragment_developer_list, container, false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            viewModel = ViewModelProviders.of(this).get(DeveloperViewModel.class);
            viewModel.getAllDeveloper().observe(this, new Observer<List<DeveloperModel>>() {
                @Override
                public void onChanged(@Nullable List<DeveloperModel> developerModels) {
                    recyclerView.setAdapter(new MyDeveloperRecyclerViewAdapter(getContext(), developerModels, mListener));
                    System.out.print("****" + developerModels.toString());
                }
            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDeveloperFragmentInteractionListener) {
            mListener = (OnDeveloperFragmentInteractionListener) context;
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

    public interface OnDeveloperFragmentInteractionListener {
        void OnDeveloperFragmentInteractionListener(DeveloperModel item);
    }
}
