// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashActivity_ViewBinding<T extends SplashActivity> implements Unbinder {
  protected T target;

  private View view2131624081;

  @UiThread
  public SplashActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
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

    target.btuLogin = null;
    target.progressBar = null;

    view2131624081.setOnClickListener(null);
    view2131624081 = null;

    this.target = null;
  }
}
