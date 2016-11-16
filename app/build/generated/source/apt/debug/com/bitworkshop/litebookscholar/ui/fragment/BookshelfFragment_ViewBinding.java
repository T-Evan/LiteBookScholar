// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookshelfFragment_ViewBinding<T extends BookshelfFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BookshelfFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tvToolbarTitle = Utils.findRequiredViewAsType(source, R.id.tv_toolbar_title, "field 'tvToolbarTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.nestedScrollView = Utils.findRequiredViewAsType(source, R.id.design_bottom_sheet, "field 'nestedScrollView'", NestedScrollView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.id_recyclerview, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvToolbarTitle = null;
    target.toolbar = null;
    target.nestedScrollView = null;
    target.mRecyclerView = null;

    this.target = null;
  }
}
