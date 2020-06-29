package com.hzy.gl3.study.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hzy.gl3.study.R;
import com.hzy.gl3.study.activity.SimpleGlActivity;
import com.hzy.gl3.study.bean.EntryInfo;

import java.util.List;

public class EntryListAdapter extends RecyclerView.Adapter<EntryListAdapter.ViewHolder> {

    private final Context mContext;
    private List<EntryInfo> mEntryData;

    public EntryListAdapter(Context context, List<EntryInfo> data) {
        mContext = context;
        mEntryData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = View.inflate(parent.getContext(), R.layout.layout_sample_item, null);
        rootView.setOnClickListener(v -> {
            EntryInfo item = (EntryInfo) v.getTag();
            Intent intent = new Intent(mContext, SimpleGlActivity.class);
            intent.putExtra(SimpleGlActivity.EXTRA_SAMPLE_INFO, item);
            mContext.startActivity(intent);
        });
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EntryInfo item = mEntryData.get(position);
        holder.itemView.setTag(item);
        holder.mSampleTitle.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return mEntryData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mSampleTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSampleTitle = itemView.findViewById(R.id.sample_title);
        }
    }
}
