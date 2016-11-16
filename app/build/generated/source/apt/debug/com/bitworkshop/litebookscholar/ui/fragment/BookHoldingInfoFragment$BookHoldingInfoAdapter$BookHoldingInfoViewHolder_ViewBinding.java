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

public class BookHoldingInfoFragment$BookHoldingInfoAdapter$BookHoldingInfoViewHolder_ViewBinding<T extends BookHoldingInfoFragment.BookHoldingInfoAdapter.BookHoldingInfoViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public BookHoldingInfoFragment$BookHoldingInfoAdapter$BookHoldingInfoViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.tvBookIndex = Utils.findRequiredViewAsType(source, R.id.tv_book_index, "field 'tvBookIndex'", TextView.class);
    target.tvBookLocation = Utils.findRequiredViewAsType(source, R.id.tv_book_location, "field 'tvBookLocation'", TextView.class);
    target.tvBookStatus = Utils.findRequiredViewAsType(source, R.id.tv_book_status, "field 'tvBookStatus'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvBookIndex = null;
    target.tvBookLocation = null;
    target.tvBookStatus = null;

    this.target = null;
  }
}
