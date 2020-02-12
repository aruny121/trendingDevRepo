package com.arunyadav.trendingdev.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arunyadav.trendingdev.Fragments.RepositoryFragment.OnRepositoryFragmentInteractionListener;
import com.arunyadav.trendingdev.Fragments.dummy.DummyContent.DummyItem;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.R;

import java.util.List;


public class RepositoryRecyclerViewAdapter extends RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder> {

    private final List<RepositoryModel> mValues;
    private final OnRepositoryFragmentInteractionListener mListener;

    public RepositoryRecyclerViewAdapter(List<RepositoryModel> items,OnRepositoryFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_repository, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getAuthor());
        holder.mContentView.setText(mValues.get(position).getAvatar());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnRepositoryFragmentInteractionListener(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public RepositoryModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
