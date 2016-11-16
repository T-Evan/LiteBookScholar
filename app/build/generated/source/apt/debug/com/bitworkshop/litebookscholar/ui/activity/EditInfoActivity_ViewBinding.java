// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.ui.view.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditInfoActivity_ViewBinding<T extends EditInfoActivity> implements Unbinder {
  protected T target;

  private View view2131624074;

  private View view2131624076;

  @UiThread
  public EditInfoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tvToolbarTitle = Utils.findRequiredViewAsType(source, R.id.tv_toolbar_title, "field 'tvToolbarTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.image_choose_image, "field 'imageChooseImage' and method 'onClick'");
    target.imageChooseImage = Utils.castView(view, R.id.image_choose_image, "field 'imageChooseImage'", CircleImageView.class);
    view2131624074 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.editChangeNickname = Utils.findRequiredViewAsType(source, R.id.edit_change_nickname, "field 'editChangeNickname'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btu_finish, "field 'btuFinish' and method 'onClick'");
    target.btuFinish = Utils.castView(view, R.id.btu_finish, "field 'btuFinish'", Button.class);
    view2131624076 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.activityEditInfo = Utils.findRequiredViewAsType(source, R.id.activity_edit_info, "field 'activityEditInfo'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvToolbarTitle = null;
    target.toolbar = null;
    target.imageChooseImage = null;
    target.editChangeNickname = null;
    target.btuFinish = null;
    target.activityEditInfo = null;

    view2131624074.setOnClickListener(null);
    view2131624074 = null;
    view2131624076.setOnClickListener(null);
    view2131624076 = null;

    this.target = null;
  }
}
