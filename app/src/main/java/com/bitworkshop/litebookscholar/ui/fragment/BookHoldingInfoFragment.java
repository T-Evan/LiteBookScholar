package com.bitworkshop.litebookscholar.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.entity.BookHoldingInfo;
import com.bitworkshop.litebookscholar.entity.BookInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aidChow on 2016/10/16.
 */

public class BookHoldingInfoFragment extends BaseFragment {

    @BindView(R.id.recycler_book_holding_info)
    RecyclerView recyclerBookHoldingInfo;
    private List<BookHoldingInfo> holdingInfos = new ArrayList<>();
    private BookHoldingInfoAdapter holdingInfoAdapter;

    public static BookHoldingInfoFragment newtInstance() {
        return new BookHoldingInfoFragment();
    }

    public BookHoldingInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_hoding_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        holdingInfoAdapter = new BookHoldingInfoAdapter(holdingInfos);
        recyclerBookHoldingInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBookHoldingInfo.setAdapter(holdingInfoAdapter);
    }

    public void setHodingInfo(List<BookHoldingInfo> hodingInfos) {
        this.holdingInfos.addAll(hodingInfos);
        holdingInfoAdapter.notifyItemRangeInserted(holdingInfoAdapter.getItemCount(), hodingInfos.size());
    }

    static class BookHoldingInfoAdapter extends RecyclerView.Adapter<BookHoldingInfoAdapter.BookHoldingInfoViewHolder> {
        private List<BookHoldingInfo> holdingInfos;

        public BookHoldingInfoAdapter(List<BookHoldingInfo> holdingInfos) {
            this.holdingInfos = holdingInfos;
        }

        @Override
        public BookHoldingInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_holding_info_item, parent, false);
            return new BookHoldingInfoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BookHoldingInfoViewHolder holder, int position) {
            holder.tvBookIndex.setText(holdingInfos.get(position).getIndexBookNum());
            holder.tvBookLocation.setText(holdingInfos.get(position).getBookLocation());
            holder.tvBookStatus.setText(holdingInfos.get(position).getBookStatus());
        }

        @Override
        public int getItemCount() {
            return holdingInfos.size();
        }

        static class BookHoldingInfoViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_book_index)
            TextView tvBookIndex;
            @BindView(R.id.tv_book_location)
            TextView tvBookLocation;
            @BindView(R.id.tv_book_status)
            TextView tvBookStatus;

            public BookHoldingInfoViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}

