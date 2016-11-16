package com.bitworkshop.litebookscholar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.asynctask.GetBookImage;
import com.bitworkshop.litebookscholar.asynctask.ThreadPoolFactory;
import com.bitworkshop.litebookscholar.entity.BrrowInfo;
import com.bitworkshop.litebookscholar.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BrrowInfoFragmentAdapter extends RecyclerView.Adapter<BrrowInfoFragmentAdapter.ViewHolder> {
    private List<BrrowInfo.brrowdata> brrowlist;
    private Context mContext;
    private LayoutInflater inflater;
    private ExecutorService exec;

    private OnItemClickListener listener;

    public BrrowInfoFragmentAdapter(Context mContext, List<BrrowInfo.brrowdata> brrowlist) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.brrowlist = brrowlist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.bookshelf_book_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BrrowInfo.brrowdata listItm = brrowlist.get(position);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    exec = Executors.newCachedThreadPool(new ThreadPoolFactory());
                    final String imageUrl = exec.submit(new GetBookImage(listItm.getBookTitle())).get();
                    listItm.setImge(imageUrl);
                    holder.bookImage.post(new Runnable() {
                        @Override
                        public void run() {
                            if (listItm.getImge() != null) {
                                Glide.with(mContext)
                                        .load(listItm.getImge())
                                        .placeholder(Utils.getRandomColors())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(holder.bookImage);
                            } else {
                                holder.bookImage.setImageResource(Utils.getRandomColors());
                            }

                            exec.shutdown();
                        }
                    });
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        SpannableStringBuilder style=new SpannableStringBuilder(listItm.getBrrowTime());
        style.setSpan(new ForegroundColorSpan(Color.rgb(0,147,255)),4,6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        holder.stateTextView.setText(style);
        holder.nameTextView.setText(listItm.getBookTitle());
    }


    @Override
    public int getItemCount() {
        return brrowlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.book_state)
        TextView stateTextView;
        @BindView(R.id.book_name)
        TextView nameTextView;
        @BindView(R.id.book_image)
        ImageView bookImage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            listener.onItemClick(v, getAdapterPosition());
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View itemview, int position);
    }

    public void setOnItemClickListner(OnItemClickListener listner) {
        this.listener = listner;
    }
}
