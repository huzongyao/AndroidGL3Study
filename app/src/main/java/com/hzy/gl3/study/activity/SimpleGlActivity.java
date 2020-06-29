package com.hzy.gl3.study.activity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.hzy.gl3.study.bean.EntryInfo;
import com.hzy.gl3.study.constant.SampleId;
import com.hzy.gl3.study.renderer.ColorShapeRenderer;
import com.hzy.gl3.study.renderer.SimpleColorRenderer;
import com.hzy.gl3.study.renderer.SimpleShapeRenderer;
import com.hzy.gl3.study.view.SimpleGlView;

public class SimpleGlActivity extends AppCompatActivity {

    public static final String EXTRA_SAMPLE_INFO = "EXTRA_SAMPLE_INFO";
    private EntryInfo mSampleInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mSampleInfo = (EntryInfo) getIntent().getSerializableExtra(EXTRA_SAMPLE_INFO);
        if (mSampleInfo != null) {
            setTitle(mSampleInfo.title);
            GLSurfaceView glView = new SimpleGlView(this);
            glView.setEGLContextClientVersion(3);
            glView.setRenderer(getGlRenderer());
            setContentView(glView);
        } else {
            finish();
        }
    }

    private GLSurfaceView.Renderer getGlRenderer() {
        switch (mSampleInfo.id) {
            case SampleId.SAMPLE_SIMPLE_SHAPE:
                return new SimpleShapeRenderer();
            case SampleId.SAMPLE_COLOR_SHAPE:
                return new ColorShapeRenderer();
            default:
                return new SimpleColorRenderer();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
