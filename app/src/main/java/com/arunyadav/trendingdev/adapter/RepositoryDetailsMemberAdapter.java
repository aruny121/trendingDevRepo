package com.arunyadav.trendingdev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arunyadav.trendingdev.Model.repositoryModel.BuiltBy;
import com.arunyadav.trendingdev.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Author - Arun yadav
 * Description - Repository Details Member Adapter list
 */
public class RepositoryDetailsMemberAdapter extends RecyclerView.Adapter<RepositoryDetailsMemberAdapter.ViewHolder> {
    private final List<BuiltBy> mValues;
    private Context mContext;

    public RepositoryDetailsMemberAdapter(Context context, List<BuiltBy> items) {
        mContext = context;
        mValues = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_list_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getUsername());
        holder.mIdView.setText(mValues.get(position).getHref());
        holder.description.setText(mValues.get(position).getUsername());
        holder.language.setVisibility(View.GONE);
        Glide.with(mContext).load(mValues.get(position).getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView, description, language;
        public final TextView mContentView;
        public final ImageView avatar;

        public BuiltBy mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            description = (TextView) view.findViewById(R.id.description);
            language = (TextView) view.findViewById(R.id.language);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
