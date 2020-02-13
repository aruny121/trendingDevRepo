package com.arunyadav.trendingdev.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunyadav.trendingdev.Fragments.RepositoryFragment.OnRepositoryFragmentInteractionListener;
import com.arunyadav.trendingdev.Model.repositoryModel.RepositoryModel;
import com.arunyadav.trendingdev.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


public class RepositoryRecyclerViewAdapter extends RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder> {

    private final List<RepositoryModel> mValues;
    private final OnRepositoryFragmentInteractionListener mListener;
    private Context mContext;
    public RepositoryRecyclerViewAdapter(Context context,List<RepositoryModel> items,OnRepositoryFragmentInteractionListener listener) {
        mContext = context;
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
        holder.mContentView.setText(mValues.get(position).getName());
        holder.mIdView.setText(mValues.get(position).getAuthor());
        holder.description.setText(mValues.get(position).getDescription());
        if (mValues.get(position).getLanguage() != null && mValues.get(position).getLanguage().length()!= 0) {
            holder.language.setText(mValues.get(position).getLanguage());
            holder.language.setTextColor(Color.parseColor(mValues.get(position).getLanguageColor()));
        }else {
            holder.language.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(mValues.get(position).getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatar);
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
        public final TextView mIdView, description, language;
        public final TextView mContentView;
        public  final ImageView avatar;

        public RepositoryModel mItem;

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
