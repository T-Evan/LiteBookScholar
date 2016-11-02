package com.bitworkshop.litebookscholar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitworkshop.litebookscholar.R;
import com.bitworkshop.litebookscholar.asynctask.GetBookImage;
import com.bitworkshop.litebookscholar.asynctask.ThreadPoolFactory;
import com.bitworkshop.litebookscholar.entity.BookInfo;
import com.bitworkshop.litebookscholar.entity.LibraryQueryListItm;
import com.bitworkshop.litebookscholar.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AidChow on 2016/10/21.
 */

public class BorrowBookListAdapter extends RecyclerView.Adapter<BorrowBookListAdapter.ViewHolder> {


    private List<BookInfo> bookInfos;
    private Context mContext;
    private LayoutInflater inflater;
    private ExecutorService exec;
    private OnItemClickListener listener;

    public BorrowBookListAdapter(Context mContext, List<BookInfo> bookInfos) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.bookInfos = bookInfos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.seach_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BookInfo listItm = bookInfos.get(position);
        Glide.with(mContext).load(listItm.getMidImge())
                .placeholder(Utils.getRandomColors())
                .into(holder.imageBook);
        holder.tvBookTitle.setText(listItm.getBookTitle());
        holder.tvBookAuthor.setText(listItm.getAuthor());
        holder.tvBookIndexNum.setText(listItm.getBookHoldingInfos(listItm.getId()).get(0).getIndexBookNum());
        holder.tvBookPublish.setText(listItm.getBookHoldingInfos(listItm.getId()).get(0).getBookLocation());

    }

    @Override
    public int getItemCount() {
        return bookInfos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_book)
        ImageView imageBook;
        @BindView(R.id.tv_book_title)
        TextView tvBookTitle;
        @BindView(R.id.tv_book_author)
        TextView tvBookAuthor;
        @BindView(R.id.tv_book_publish)
        TextView tvBookPublish;
        @BindView(R.id.tv_book_index_num)
        TextView tvBookIndexNum;
        @BindView(R.id.tv_book_can_borrow)
        TextView tvBookCanBorrow;

        ViewHolder(View itemView) {
            super(itemView);
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
