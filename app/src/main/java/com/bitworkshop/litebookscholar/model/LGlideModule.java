package com.bitworkshop.litebookscholar.model;

import android.content.Context;

import com.bitworkshop.litebookscholar.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by AidChow on 2016/10/26.
 */

public class LGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
