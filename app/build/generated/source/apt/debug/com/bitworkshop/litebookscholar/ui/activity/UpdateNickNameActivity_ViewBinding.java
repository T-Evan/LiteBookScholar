// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UpdateNickNameActivity_ViewBinding<T extends UpdateNickNameActivity> implements Unbinder {
  protected T target;

  private View view2131624206;

  @UiThread
  public UpdateNickNameActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tvToolbarTitle = Utils.findRequiredViewAsType(source, R.id.tv_toolbar_title, "field 'tvToolbarTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editChangeNickname = Utils.findRequiredViewAsType(source, R.id.edit_change_nickname, "field 'editChangeNickname'", EditText.class);
    target.activityUpdateNickName = Utils.findRequiredViewAsType(source, R.id.activity_update_nick_name, "field 'activityUpdateNickName'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_toolbar_right, "field 'tvToolbarRight' and method 'onClick'");
    target.tvToolbarRight = Utils.castView(view, R.id.tv_toolbar_right, "field 'tvToolbarRight'", TextView.class);
    view2131624206 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
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
    target.editChangeNickname = null;
    target.activityUpdateNickName = null;
    target.tvToolbarRight = null;

    view2131624206.setOnClickListener(null);
    view2131624206 = null;

    this.target = null;
  }
}
