package com.example.demo.recycleviewmoreitem;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by 59246 on 2018/4/29.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }
}
