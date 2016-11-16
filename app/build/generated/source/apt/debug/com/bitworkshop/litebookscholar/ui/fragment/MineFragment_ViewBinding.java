// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.ui.view.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding<T extends MineFragment> implements Unbinder {
  protected T target;

  private View view2131624153;

  private View view2131624156;

  private View view2131624159;

  private View view2131624162;

  @UiThread
  public MineFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tvToolbarTitle = Utils.findRequiredViewAsType(source, R.id.tv_toolbar_title, "field 'tvToolbarTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.imageUserIcon = Utils.findRequiredViewAsType(source, R.id.image_user_icon, "field 'imageUserIcon'", CircleImageView.class);
    target.tvUserNickname = Utils.findRequiredViewAsType(source, R.id.tv_user_nickname, "field 'tvUserNickname'", TextView.class);
    view = Utils.findRequiredView(source, R.id.card_view_change_user_info, "field 'cardViewChangeUserInfo' and method 'onClick'");
    target.cardViewChangeUserInfo = Utils.castView(view, R.id.card_view_change_user_info, "field 'cardViewChangeUserInfo'", CardView.class);
    view2131624153 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.card_view_borrow_rule, "field 'cardViewBorrowRule' and method 'onClick'");
    target.cardViewBorrowRule = Utils.castView(view, R.id.card_view_borrow_rule, "field 'cardViewBorrowRule'", CardView.class);
    view2131624156 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.card_view_setting, "field 'cardViewSetting' and method 'onClick'");
    target.cardViewSetting = Utils.castView(view, R.id.card_view_setting, "field 'cardViewSetting'", CardView.class);
    view2131624159 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.card_view_about, "field 'cardViewAbout' and method 'onClick'");
    target.cardViewAbout = Utils.castView(view, R.id.card_view_about, "field 'cardViewAbout'", CardView.class);
    view2131624162 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvToolbarTitle = null;
    target.toolbar = null;
    target.imageUserIcon = null;
    target.tvUserNickname = null;
    target.cardViewChangeUserInfo = null;
    target.cardViewBorrowRule = null;
    target.cardViewSetting = null;
    target.cardViewAbout = null;

    view2131624153.setOnClickListener(null);
    view2131624153 = null;
    view2131624156.setOnClickListener(null);
    view2131624156 = null;
    view2131624159.setOnClickListener(null);
    view2131624159 = null;
    view2131624162.setOnClickListener(null);
    view2131624162 = null;

    this.target = null;
  }
}
