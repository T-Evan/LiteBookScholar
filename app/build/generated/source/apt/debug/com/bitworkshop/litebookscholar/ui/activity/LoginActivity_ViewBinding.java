// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  private View view2131624081;

  @UiThread
  public LoginActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.editUsername = Utils.findRequiredViewAsType(source, R.id.edit_username, "field 'editUsername'", EditText.class);
    target.editLibAccount = Utils.findRequiredViewAsType(source, R.id.edit_lib_account, "field 'editLibAccount'", EditText.class);
    target.editPassword = Utils.findRequiredViewAsType(source, R.id.edit_password, "field 'editPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btu_login, "field 'btuLogin' and method 'onClick'");
    target.btuLogin = Utils.castView(view, R.id.btu_login, "field 'btuLogin'", Button.class);
    view2131624081 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbar = null;
    target.editUsername = null;
    target.editLibAccount = null;
    target.editPassword = null;
    target.btuLogin = null;
    target.progressBar = null;

    view2131624081.setOnClickListener(null);
    view2131624081 = null;

    this.target = null;
  }
}
