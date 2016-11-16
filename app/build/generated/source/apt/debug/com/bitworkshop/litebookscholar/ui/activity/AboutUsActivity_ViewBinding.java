// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AboutUsActivity_ViewBinding<T extends AboutUsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public AboutUsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.tvToolbarTitle = Utils.findRequiredViewAsType(source, R.id.tv_toolbar_title, "field 'tvToolbarTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.webView = Utils.findRequiredViewAsType(source, R.id.web_view, "field 'webView'", WebView.class);
    target.activityAboutUs = Utils.findRequiredViewAsType(source, R.id.activity_about_us, "field 'activityAboutUs'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvToolbarTitle = null;
    target.toolbar = null;
    target.webView = null;
    target.activityAboutUs = null;

    this.target = null;
  }
}
