// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchResultAdapter$ViewHolder_ViewBinding<T extends SearchResultAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public SearchResultAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.imageBook = Utils.findRequiredViewAsType(source, R.id.image_book, "field 'imageBook'", ImageView.class);
    target.tvBookTitle = Utils.findRequiredViewAsType(source, R.id.tv_book_title, "field 'tvBookTitle'", TextView.class);
    target.tvBookAuthor = Utils.findRequiredViewAsType(source, R.id.tv_book_author, "field 'tvBookAuthor'", TextView.class);
    target.tvBookPublish = Utils.findRequiredViewAsType(source, R.id.tv_book_publish, "field 'tvBookPublish'", TextView.class);
    target.tvBookIndexNum = Utils.findRequiredViewAsType(source, R.id.tv_book_index_num, "field 'tvBookIndexNum'", TextView.class);
    target.tvBookCanBorrow = Utils.findRequiredViewAsType(source, R.id.tv_book_can_borrow, "field 'tvBookCanBorrow'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imageBook = null;
    target.tvBookTitle = null;
    target.tvBookAuthor = null;
    target.tvBookPublish = null;
    target.tvBookIndexNum = null;
    target.tvBookCanBorrow = null;

    this.target = null;
  }
}
