// Generated code from Butter Knife. Do not modify!
package com.bitworkshop.litebookscholar.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bitworkshop.litebookscholar.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OneAdapter$ViewHolder_ViewBinding<T extends OneAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public OneAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.imageOneWords = Utils.findRequiredViewAsType(source, R.id.image_one_words, "field 'imageOneWords'", ImageView.class);
    target.tvDescription = Utils.findRequiredViewAsType(source, R.id.tv_description, "field 'tvDescription'", TextView.class);
    target.tvVol = Utils.findRequiredViewAsType(source, R.id.tv_vol, "field 'tvVol'", TextView.class);
    target.tvAuthor = Utils.findRequiredViewAsType(source, R.id.tv_author, "field 'tvAuthor'", TextView.class);
    target.relativeVol = Utils.findRequiredViewAsType(source, R.id.relative_vol, "field 'relativeVol'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imageOneWords = null;
    target.tvDescription = null;
    target.tvVol = null;
    target.tvAuthor = null;
    target.relativeVol = null;

    this.target = null;
  }
}
