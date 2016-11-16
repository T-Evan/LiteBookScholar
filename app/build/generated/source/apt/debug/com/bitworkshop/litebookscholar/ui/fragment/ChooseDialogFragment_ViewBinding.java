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

public class ChooseDialogFragment_ViewBinding<T extends ChooseDialogFragment> implements Unbinder {
  protected T target;

  @UiThread
  public ChooseDialogFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tvChooseFromCamera = Utils.findRequiredViewAsType(source, R.id.tv_choose_from_camera, "field 'tvChooseFromCamera'", TextView.class);
    target.tvChooseFromPhoto = Utils.findRequiredViewAsType(source, R.id.tv_choose_from_photo, "field 'tvChooseFromPhoto'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvChooseFromCamera = null;
    target.tvChooseFromPhoto = null;

    this.target = null;
  }
}
