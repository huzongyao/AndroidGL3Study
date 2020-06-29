package com.hzy.gl3.study.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.hzy.gl3.study.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_splash);
        if (!isSupportGlEs3()) {
            ToastUtils.showShort("Not Support GlEs3!");
        } else {
            startActivity(new Intent(this, HomeActivity.class));
        }
        finish();
    }

    private boolean isSupportGlEs3() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            ConfigurationInfo info = am.getDeviceConfigurationInfo();
            return (info.reqGlEsVersion >= 0x30000);
        }
        return false;
    }
}
