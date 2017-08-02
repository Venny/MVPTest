package com.example.vdimitrova.mvptest.features.books_list.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vdimitrova.mvptest.R;
import com.example.vdimitrova.mvptest.database.model_tables.BookDbModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

/**
 * Created by vdimitrova on 15.05.17.
 */

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.BooksViewHolder> {
    private List<BookDbModel> data;
    private ItemListener itemListener;

    public UpcomingEventsAdapter(ItemListener itemListener) {
        this.itemListener = itemListener;

        Log.d(TAG, "onBindViewHolder: ");
    }

    @Override
    public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming_event, parent, false);
        return new BooksViewHolder(vHolder);
    }

    @Override
    public void onBindViewHolder(BooksViewHolder holder, int position) {
        if (data != null && !data.isEmpty()) {
            BookDbModel book = data.get(position);
            holder.getBookTitle().setText(book.getTitle());
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.book_title)
        TextView bookTitle;

        public BooksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getBookTitle() {
            return bookTitle;
        }

        @OnClick(R.id.list_item_event)
        public void onClick() {
            if (itemListener != null) {
                Log.d(TAG, "onClick: " + "here");
                itemListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public void setData(List<BookDbModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public BookDbModel getItem(int id) {
        return data.get(id);
    }

    /**
     * Interface for item click support
     */
    public interface ItemListener {
        void onItemClick(int position);
    }
}
