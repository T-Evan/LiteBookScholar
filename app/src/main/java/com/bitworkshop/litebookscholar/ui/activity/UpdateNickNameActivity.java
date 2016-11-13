package com.bitworkshop.litebookscholar.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.entity.User;
import com.bitworkshop.litebookscholar.presenter.EditInfoPresenter;
import com.bitworkshop.litebookscholar.presenter.MinePresenter;
import com.bitworkshop.litebookscholar.ui.view.IEditUserInfoView;
import com.bitworkshop.litebookscholar.ui.view.IMineView;
import com.bitworkshop.litebookscholar.util.MyToastUtils;
import com.bitworkshop.litebookscholar.util.ProgressDialogUtil;
import com.bitworkshop.litebookscholar.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNickNameActivity extends BaseActivity implements IMineView, IEditUserInfoView {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_change_nickname)
    EditText editChangeNickname;
    @BindView(R.id.activity_update_nick_name)
    LinearLayout activityUpdateNickName;
    @BindView(R.id.tv_toolbar_right)
    TextView tvToolbarRight;
    EditInfoPresenter editInfoPresenter;
    MinePresenter minePresenter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        ButterKnife.bind(this);
        setupToolbar(toolbar, "编辑昵称", true);
        minePresenter = new MinePresenter(this);
        editInfoPresenter = new EditInfoPresenter(this);
        tvToolbarRight.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_toolbar_right)
    public void onClick() {
        if (Utils.isOnline(this)) {
            if (getUserNickname().equals("")) {
                MyToastUtils.showToast(this, "想造反是不是,什么都不填就想更新?");
                return;
            }
            if (!getUserNickname().equals(user.getPetname())) {
                editInfoPresenter.updateUserInfo(user.getUser(), user.getUserPassword(), user.getUrl(), getUserNickname());
            } else {
                MyToastUtils.showToast(this, "傻逼,昵称不能和原来的一样!");
            }

        } else {
            MyToastUtils.showToast(this, "哎呀，网络有问题");
        }
    }

    @Override
    public void setUserInfo(User user) {
        this.user = user;
        //设置用户昵称
        editChangeNickname.setText(user.getPetname());
        editChangeNickname.setSelection(user.getPetname().length());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getUserAccount() != null) {
            minePresenter.setUserInfo(getUserAccount());
        }
    }

    @Override
    public String getUserNickname() {
        return Utils.editextUtiils(editChangeNickname);
    }

    @Override
    public void seccess() {
        finish();
    }

    @Override
    public void showLoading() {
        ProgressDialogUtil.showProgressBar(this, "更新中");
    }

    @Override
    public void hideLoading() {
        ProgressDialogUtil.hideProgressDiaglog();
    }

    @Override
    public void showError(String errorMsg) {
        MyToastUtils.showToast(this, errorMsg);
    }

    @Override
    public void setImageUrl(String imageUrl) {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
