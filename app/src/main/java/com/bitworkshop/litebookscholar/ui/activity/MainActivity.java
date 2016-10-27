package com.bitworkshop.litebookscholar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.ui.fragment.BookshelfFragment;
import com.bitworkshop.litebookscholar.ui.fragment.DiscoveryFragment;
import com.bitworkshop.litebookscholar.ui.fragment.MineFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements OnTabSelectListener {

    @BindView(R.id.frag_content)
    FrameLayout fragContent;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;
    private int currentPosition = 0;
    private boolean isUpdate = false;

    public static void startActiviyForResult(Context context, boolean updateInfo) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("update", updateInfo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomBar.setOnTabSelectListener(this);
        bottomBar.selectTabAtPosition(currentPosition);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void controlFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment oldFragment = fm.findFragmentByTag(makeTag(currentPosition));
        if (oldFragment != null) {
            ft.hide(oldFragment);
        }
        currentPosition = position;
        Fragment currentFragment = fm.findFragmentByTag(makeTag(position));
        if (currentFragment != null) {
            ft.show(currentFragment);
        } else {
            ft.add(R.id.frag_content, getFragment(position), makeTag(position));
        }
        ft.commitAllowingStateLoss();
    }

    private Fragment getFragment(int position) {
        switch (position) {
            case 0:
                return DiscoveryFragment.getInstance();
            case 1:
                return BookshelfFragment.getInstance();
            case 2:
                return MineFragment.getInstance();
            default:
                return DiscoveryFragment.getInstance();
        }
    }

    private String makeTag(int position) {
        return R.id.frag_content + position + "";
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_discovery:
                controlFragment(0);
                break;
            case R.id.tab_bookshelf:
                controlFragment(1);
                break;
            case R.id.tab_mine:
                controlFragment(2);
                break;
            default:
                controlFragment(0);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        System.out.println(getIntent().getStringExtra("update"));
        isUpdate = getIntent().getBooleanExtra("update", false);
    }


}
