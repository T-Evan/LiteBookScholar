// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchBookActivity_ViewBinding<T extends SearchBookActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SearchBookActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerSearchResult = Utils.findRequiredViewAsType(source, R.id.recycler_search_result, "field 'recyclerSearchResult'", RecyclerView.class);
    target.searchView = Utils.findRequiredViewAsType(source, R.id.search_view, "field 'searchView'", SearchView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
    target.relativeContent = Utils.findRequiredViewAsType(source, R.id.relative_content, "field 'relativeContent'", RelativeLayout.class);
    target.activitySearchBook = Utils.findRequiredViewAsType(source, R.id.activity_search_book, "field 'activitySearchBook'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbar = null;
    target.recyclerSearchResult = null;
    target.searchView = null;
    target.progressBar = null;
    target.relativeContent = null;
    target.activitySearchBook = null;

    this.target = null;
  }
}
