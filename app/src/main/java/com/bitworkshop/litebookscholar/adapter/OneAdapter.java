package com.bitworkshop.litebookscholar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.entity.One;
import com.bitworkshop.litebookscholar.util.Utils;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.concurrent.ExecutorService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 78537 on 2016/10/21.
 */

public class OneAdapter extends RecyclerView.Adapter<OneAdapter.ViewHolder> {


    private List<One.DataBean> onDataBeens;
    private Context mContext;
    private LayoutInflater inflater;

    public OneAdapter(Context mContext, List<One.DataBean> onDataBeens) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.onDataBeens = onDataBeens;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vol_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        One.DataBean dataBean = onDataBeens.get(position);
        String vol = dataBean.getVol();
        Glide.with(mContext).load(dataBean.getUrl())
                .placeholder(Utils.getRandomColors())
                .centerCrop()
                .into(holder.imageOneWords);
        if (vol.length() == 1) {
            vol = "00" + vol;
        }
        if (vol.length() == 2) {
            vol = "0" + vol;
        }
        holder.tvVol.setText("VOL." + vol);
        holder.tvAuthor.setText(dataBean.getAuthor() + " |");
        holder.tvDescription.setText(onDataBeens.get(position).getSentence());
    }

    @Override
    public int getItemCount() {
        return onDataBeens.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_one_words)
        ImageView imageOneWords;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_vol)
        TextView tvVol;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.relative_vol)
        RelativeLayout relativeVol;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void clear() {
        onDataBeens.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<One.DataBean> onDataBeens) {
        onDataBeens.addAll(onDataBeens);
        notifyDataSetChanged();
    }
}
