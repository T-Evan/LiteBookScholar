// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingsActivity_ViewBinding<T extends SettingsActivity> implements Unbinder {
  protected T target;

  private View view2131624097;

  private View view2131624099;

  private View view2131624101;

  private View view2131624103;

  @UiThread
  public SettingsActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.switchMessages = Utils.findRequiredViewAsType(source, R.id.switch_messages, "field 'switchMessages'", Switch.class);
    view = Utils.findRequiredView(source, R.id.card_view_cache_clean_layout, "field 'cardViewCacheCleanLayout' and method 'onClick'");
    target.cardViewCacheCleanLayout = Utils.castView(view, R.id.card_view_cache_clean_layout, "field 'cardViewCacheCleanLayout'", CardView.class);
    view2131624097 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.card_view_feedback_layout, "field 'cardViewFeedbackLayout' and method 'onClick'");
    target.cardViewFeedbackLayout = Utils.castView(view, R.id.card_view_feedback_layout, "field 'cardViewFeedbackLayout'", CardView.class);
    view2131624099 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.card_view_check_update_layout, "field 'cardViewCheckUpdateLayout' and method 'onClick'");
    target.cardViewCheckUpdateLayout = Utils.castView(view, R.id.card_view_check_update_layout, "field 'cardViewCheckUpdateLayout'", CardView.class);
    view2131624101 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.card_view_logout_layout, "field 'cardViewLogoutLayout' and method 'onClick'");
    target.cardViewLogoutLayout = Utils.castView(view, R.id.card_view_logout_layout, "field 'cardViewLogoutLayout'", CardView.class);
    view2131624103 = view;
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
    target.switchMessages = null;
    target.cardViewCacheCleanLayout = null;
    target.cardViewFeedbackLayout = null;
    target.cardViewCheckUpdateLayout = null;
    target.cardViewLogoutLayout = null;

    view2131624097.setOnClickListener(null);
    view2131624097 = null;
    view2131624099.setOnClickListener(null);
    view2131624099 = null;
    view2131624101.setOnClickListener(null);
    view2131624101 = null;
    view2131624103.setOnClickListener(null);
    view2131624103 = null;

    this.target = null;
  }
}
