package com.bitworkshop.litebookscholar.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.adapter.BrrowInfoFragmentAdapter;
import com.bitworkshop.litebookscholar.entity.BrrowInfo;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.presenter.BrrowInfoPreSenter;
import com.bitworkshop.litebookscholar.presenter.MinePresenter;
import com.bitworkshop.litebookscholar.ui.activity.BookInfoActivity;
import com.bitworkshop.litebookscholar.ui.view.IBrrowInfoView;
import com.bitworkshop.litebookscholar.ui.view.IMineView;
import com.bitworkshop.litebookscholar.util.MyToastUtils;
import com.bitworkshop.litebookscholar.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookshelfFragment extends BaseFragment implements IBrrowInfoView, IMineView, BrrowInfoFragmentAdapter.OnItemClickListener {
    private final static String BOOK_ID = "bookid";
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.design_bottom_sheet)
    NestedScrollView nestedScrollView;
    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    private BrrowInfoPreSenter presenter;
    private MinePresenter mMinePresenter;
    private String userAccount;
    private String password;
    private TextView mBrrow;
    private TextView mBook;
    private BrrowInfoFragmentAdapter Adapter;
    User user;
    private List<BrrowInfo.brrowdata> brrowdata = new ArrayList<>();

    public static BookshelfFragment getInstance() {
        return new BookshelfFragment();
    }

    public BookshelfFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookshelf, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        presenter = new BrrowInfoPreSenter(this);
        mMinePresenter = new MinePresenter(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setHasFixedSize(true);
        Adapter = new BrrowInfoFragmentAdapter(getContext(), brrowdata);
        mRecyclerView.setAdapter(Adapter);
        Adapter.setOnItemClickListner(this);
        loaddata();

    }

    @Override
    public void setBrrowInfo(List<BrrowInfo.brrowdata> data) {
        int currentSize = Adapter.getItemCount();
        brrowdata.addAll(data);
        Adapter.notifyItemRangeInserted(currentSize, data.size());
    }

    public void loaddata() {
        mMinePresenter.setUserInfo(getUserAccount());
        if (Utils.isOnline(getContext())) {
            presenter.getBrrowInfoFromLib(userAccount, password);
        } else {
            MyToastUtils.showToast(getActivity(), "请检查网络设置");
        }
    }

    private void initToolbar() {
        tvToolbarTitle.setVisibility(View.VISIBLE);
        tvToolbarTitle.setText(R.string.string_shujia);
    }

    public void intro(View view, int position) {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(view.findViewById(R.id.design_bottom_sheet));
        final int p = position;
        mBrrow = (TextView) view.findViewById(R.id.book_brrow);
        mBook = (TextView) view.findViewById(R.id.book_text);
        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookInfoActivity.startActivity(getActivity(), brrowdata.get(p).getUrl());

            }
        });
        mBrrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

    }


    @Override
    public void onItemClick(View itemview, int position) {
        intro(nestedScrollView, position);

    }


    public void setUserInfo(User user) {
        userAccount = user.getUser();
        password = user.getUserPassword();
    }
}

