// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

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

public class UserInfoActivity_ViewBinding<T extends UserInfoActivity> implements Unbinder {
  protected T target;

  private View view2131624107;

  private View view2131624109;

  @UiThread
  public UserInfoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.imageUserIcon = Utils.findRequiredViewAsType(source, R.id.image_user_icon, "field 'imageUserIcon'", CircleImageView.class);
    view = Utils.findRequiredView(source, R.id.card_view_user_image, "field 'cardViewUserImage' and method 'onClick'");
    target.cardViewUserImage = Utils.castView(view, R.id.card_view_user_image, "field 'cardViewUserImage'", CardView.class);
    view2131624107 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvUserNickname = Utils.findRequiredViewAsType(source, R.id.tv_user_nickname, "field 'tvUserNickname'", TextView.class);
    view = Utils.findRequiredView(source, R.id.card_view_user_nickname, "field 'cardViewUserNickname' and method 'onClick'");
    target.cardViewUserNickname = Utils.castView(view, R.id.card_view_user_nickname, "field 'cardViewUserNickname'", CardView.class);
    view2131624109 = view;
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

    target.toolbar = null;
    target.imageUserIcon = null;
    target.cardViewUserImage = null;
    target.tvUserNickname = null;
    target.cardViewUserNickname = null;

    view2131624107.setOnClickListener(null);
    view2131624107 = null;
    view2131624109.setOnClickListener(null);
    view2131624109 = null;

    this.target = null;
  }
}
