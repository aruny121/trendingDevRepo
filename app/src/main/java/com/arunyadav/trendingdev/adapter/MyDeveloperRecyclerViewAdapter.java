package com.arunyadav.trendingdev.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunyadav.trendingdev.Fragments.DeveloperFragment;
import com.arunyadav.trendingdev.Fragments.DeveloperFragment.OnDeveloperFragmentInteractionListener;
import com.arunyadav.trendingdev.Fragments.RepositoryFragment;
import com.arunyadav.trendingdev.Model.developerModel.DeveloperModel;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class MyDeveloperRecyclerViewAdapter extends RecyclerView.Adapter<MyDeveloperRecyclerViewAdapter.ViewHolder> {

    private final List<DeveloperModel> mValues;
    private final DeveloperFragment.OnDeveloperFragmentInteractionListener mListener;
    private Context mContext;
    public MyDeveloperRecyclerViewAdapter(Context context, List<DeveloperModel> items, DeveloperFragment.OnDeveloperFragmentInteractionListener listener) {
        mContext = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public MyDeveloperRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_repository, parent, false);
        return new MyDeveloperRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyDeveloperRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());
        holder.mIdView.setText(mValues.get(position).getUrl());
        holder.description.setText(mValues.get(position).getUsername());

            holder.language.setVisibility(View.GONE);

        Glide.with(mContext).load(mValues.get(position).getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatar);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnDeveloperFragmentInteractionListener(holder.mItem);
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
        public final TextView mIdView, description, language;
        public final TextView mContentView;
        public  final ImageView avatar;

        public DeveloperModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            avatar = (ImageView)view.findViewById(R.id.avatar);
            description = (TextView)view.findViewById(R.id.description);
            language = (TextView)view.findViewById(R.id.language);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
