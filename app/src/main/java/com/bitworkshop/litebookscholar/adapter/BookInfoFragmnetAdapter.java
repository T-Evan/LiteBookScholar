package com.bitworkshop.litebookscholar.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.bitworkshop.litebookscholar.ui.fragment.BookHoldingInfoFragment;
import com.bitworkshop.litebookscholar.ui.fragment.BookSummaryInfoFragment;

/**
 * Created by AidChow on 2016/10/31.
 */

public class BookInfoFragmnetAdapter extends SmartFragmentStatePagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"馆藏信息", "简介"};
    private Context context;

    public BookInfoFragmnetAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BookHoldingInfoFragment.newtInstance();
            case 1:
                return BookSummaryInfoFragment.newtInstance();
            default:
                return BookHoldingInfoFragment.newtInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
