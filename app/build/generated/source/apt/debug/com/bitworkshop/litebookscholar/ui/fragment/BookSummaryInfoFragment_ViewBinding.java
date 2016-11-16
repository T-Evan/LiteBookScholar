// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookSummaryInfoFragment_ViewBinding<T extends BookSummaryInfoFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BookSummaryInfoFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tvSummary = Utils.findRequiredViewAsType(source, R.id.tv_summary, "field 'tvSummary'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvSummary = null;

    this.target = null;
  }
}
