package com.mpersson.myrecipeorganizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.StringViewHolder> {
    private final LinkedList<String> mStringList;
    private LayoutInflater mInflator;

    class StringViewHolder extends RecyclerView.ViewHolder {
        public final TextView stringItemView;
        final StringListAdapter mAdapter;

        public StringViewHolder(View itemView, StringListAdapter adapter) {
            super(itemView);
            stringItemView = itemView.findViewById(R.id.string);
            this.mAdapter = adapter;
        }
    }

    public StringListAdapter(Context context, LinkedList<String> stringList) {
        mInflator = LayoutInflater.from(context);
        this.mStringList = stringList;
    }


    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflator.inflate(R.layout.stringlist_item, parent, false);
        return new StringViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, int position) {
        String mCurrent = mStringList.get(position);
        holder.stringItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }
}
