package com.bitworkshop.litebookscholar.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 书本简介
 * Created by aidChow on 2016/10/16.
 */

public class BookSummaryInfoFragment extends BaseFragment {

    @BindView(R.id.tv_summary)
    TextView tvSummary;

    public static BookSummaryInfoFragment newtInstance() {
        return new BookSummaryInfoFragment();
    }

    public BookSummaryInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_summary_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setSummary(String summary) {
        tvSummary.setText(summary);
    }

}

