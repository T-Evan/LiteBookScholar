// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookInfoActivity_ViewBinding<T extends BookInfoActivity> implements Unbinder {
  protected T target;

  private View view2131624070;

  @UiThread
  public BookInfoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.iamgeBookLarge = Utils.findRequiredViewAsType(source, R.id.iamge_book_large, "field 'iamgeBookLarge'", ImageView.class);
    target.imageBook = Utils.findRequiredViewAsType(source, R.id.image_book, "field 'imageBook'", ImageView.class);
    target.tvBookTitle = Utils.findRequiredViewAsType(source, R.id.tv_book_title, "field 'tvBookTitle'", TextView.class);
    target.tvBookAuthor = Utils.findRequiredViewAsType(source, R.id.tv_book_author, "field 'tvBookAuthor'", TextView.class);
    target.tvBookPublish = Utils.findRequiredViewAsType(source, R.id.tv_book_publish, "field 'tvBookPublish'", TextView.class);
    target.ratingBarOfBook = Utils.findRequiredViewAsType(source, R.id.rating_bar_of_book, "field 'ratingBarOfBook'", RatingBar.class);
    target.tvBookAverage = Utils.findRequiredViewAsType(source, R.id.tv_book_average, "field 'tvBookAverage'", TextView.class);
    target.relativeBookBaseInfo = Utils.findRequiredViewAsType(source, R.id.relative_book_base_info, "field 'relativeBookBaseInfo'", RelativeLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.collapsToolbarLayout = Utils.findRequiredViewAsType(source, R.id.collaps_toolbar_layout, "field 'collapsToolbarLayout'", CollapsingToolbarLayout.class);
    target.appbar = Utils.findRequiredViewAsType(source, R.id.appbar, "field 'appbar'", AppBarLayout.class);
    target.tableLayout = Utils.findRequiredViewAsType(source, R.id.table_layout, "field 'tableLayout'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.float_add_to_bookshelf, "field 'floatAddToBookshelf' and method 'onClick'");
    target.floatAddToBookshelf = Utils.castView(view, R.id.float_add_to_bookshelf, "field 'floatAddToBookshelf'", FloatingActionButton.class);
    view2131624070 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iamgeBookLarge = null;
    target.imageBook = null;
    target.tvBookTitle = null;
    target.tvBookAuthor = null;
    target.tvBookPublish = null;
    target.ratingBarOfBook = null;
    target.tvBookAverage = null;
    target.relativeBookBaseInfo = null;
    target.toolbar = null;
    target.collapsToolbarLayout = null;
    target.appbar = null;
    target.tableLayout = null;
    target.viewpager = null;
    target.floatAddToBookshelf = null;

    view2131624070.setOnClickListener(null);
    view2131624070 = null;

    this.target = null;
  }
}
