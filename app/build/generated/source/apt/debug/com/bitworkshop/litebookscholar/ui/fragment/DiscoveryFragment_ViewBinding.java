// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DiscoveryFragment_ViewBinding<T extends DiscoveryFragment> implements Unbinder {
  protected T target;

  private View view2131624165;

  private View view2131624200;

  private View view2131624202;

  @UiThread
  public DiscoveryFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tvToolbarTitle = Utils.findRequiredViewAsType(source, R.id.tv_toolbar_title, "field 'tvToolbarTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerVol = Utils.findRequiredViewAsType(source, R.id.recycler_vol, "field 'recyclerVol'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.cardview_no_borrow_book_layout, "field 'cardviewNoBorrowBookLayout' and method 'onClick'");
    target.cardviewNoBorrowBookLayout = Utils.castView(view, R.id.cardview_no_borrow_book_layout, "field 'cardviewNoBorrowBookLayout'", CardView.class);
    view2131624165 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.recyclerToBorrowList = Utils.findRequiredViewAsType(source, R.id.recycler_to_borrow_list, "field 'recyclerToBorrowList'", RecyclerView.class);
    target.relativeToBorrowBookListLayout = Utils.findRequiredViewAsType(source, R.id.relative_to_borrow_book_list_root, "field 'relativeToBorrowBookListLayout'", RelativeLayout.class);
    target.swipeRefresh = Utils.findRequiredViewAsType(source, R.id.swipe_refresh, "field 'swipeRefresh'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_clear_book_list, "field 'tvClearBookList' and method 'onClick'");
    target.tvClearBookList = Utils.castView(view, R.id.tv_clear_book_list, "field 'tvClearBookList'", TextView.class);
    view2131624200 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.relative_add_continue_layout, "field 'relativeAddContinueLayout' and method 'onClick'");
    target.relativeAddContinueLayout = Utils.castView(view, R.id.relative_add_continue_layout, "field 'relativeAddContinueLayout'", RelativeLayout.class);
    view2131624202 = view;
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

    target.tvToolbarTitle = null;
    target.toolbar = null;
    target.recyclerVol = null;
    target.cardviewNoBorrowBookLayout = null;
    target.recyclerToBorrowList = null;
    target.relativeToBorrowBookListLayout = null;
    target.swipeRefresh = null;
    target.tvClearBookList = null;
    target.relativeAddContinueLayout = null;

    view2131624165.setOnClickListener(null);
    view2131624165 = null;
    view2131624200.setOnClickListener(null);
    view2131624200 = null;
    view2131624202.setOnClickListener(null);
    view2131624202 = null;

    this.target = null;
  }
}
