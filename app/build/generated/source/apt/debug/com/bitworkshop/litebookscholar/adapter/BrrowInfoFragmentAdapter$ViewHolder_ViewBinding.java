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

public class BrrowInfoFragmentAdapter$ViewHolder_ViewBinding<T extends BrrowInfoFragmentAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public BrrowInfoFragmentAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.stateTextView = Utils.findRequiredViewAsType(source, R.id.book_state, "field 'stateTextView'", TextView.class);
    target.nameTextView = Utils.findRequiredViewAsType(source, R.id.book_name, "field 'nameTextView'", TextView.class);
    target.bookImage = Utils.findRequiredViewAsType(source, R.id.book_image, "field 'bookImage'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.stateTextView = null;
    target.nameTextView = null;
    target.bookImage = null;

    this.target = null;
  }
}
