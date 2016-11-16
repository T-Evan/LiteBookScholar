// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import com.roughike.bottombar.BottomBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.fragContent = Utils.findRequiredViewAsType(source, R.id.frag_content, "field 'fragContent'", FrameLayout.class);
    target.bottomBar = Utils.findRequiredViewAsType(source, R.id.bottom_bar, "field 'bottomBar'", BottomBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.fragContent = null;
    target.bottomBar = null;

    this.target = null;
  }
}
