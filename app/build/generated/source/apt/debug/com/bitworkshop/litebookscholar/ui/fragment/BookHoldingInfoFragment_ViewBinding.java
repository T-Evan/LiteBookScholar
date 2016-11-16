// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookHoldingInfoFragment_ViewBinding<T extends BookHoldingInfoFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BookHoldingInfoFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.recyclerBookHoldingInfo = Utils.findRequiredViewAsType(source, R.id.recycler_book_holding_info, "field 'recyclerBookHoldingInfo'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.recyclerBookHoldingInfo = null;

    this.target = null;
  }
}
