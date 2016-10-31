package com.bitworkshop.litebookscholar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bitworkshop.litebookscholar.R;
import com.paginate.recycler.LoadingListItemCreator;

/**
 * Created by 78537 on 2016/10/29.
 */

public class CustomLoadingListItemCreator implements LoadingListItemCreator {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.progressbar_item, parent, false);
        return new ProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProgressViewHolder progressViewHolder = (ProgressViewHolder) holder;
        progressViewHolder.progressBar.setIndeterminate(true);
        progressViewHolder.progressBar.setVisibility(View.VISIBLE);
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }
}
