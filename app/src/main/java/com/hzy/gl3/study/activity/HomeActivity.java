package com.hzy.gl3.study.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzy.gl3.study.R;
import com.hzy.gl3.study.adapter.EntryListAdapter;
import com.hzy.gl3.study.bean.EntryInfo;
import com.hzy.gl3.study.constant.SampleId;

import java.util.LinkedList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerList;
    private List<EntryInfo> mEntryData = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerList = findViewById(R.id.menu_list);
        mEntryData.add(new EntryInfo("Simple Color", SampleId.SAMPLE_SIMPLE_COLOR));
        mEntryData.add(new EntryInfo("Simple Shape", SampleId.SAMPLE_SIMPLE_SHAPE));
        mEntryData.add(new EntryInfo("Colorful Shape", SampleId.SAMPLE_COLOR_SHAPE));
        mEntryData.add(new EntryInfo("Line Cube", SampleId.SAMPLE_LINE_CUBE));
        mEntryData.add(new EntryInfo("Color Cube", SampleId.SAMPLE_COLOR_CUBE));
        mEntryData.add(new EntryInfo("Bitmap Texture", SampleId.SAMPLE_BITMAP_TEXTURE));
        mRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerList.setAdapter(new EntryListAdapter(this, mEntryData));
    }
}
