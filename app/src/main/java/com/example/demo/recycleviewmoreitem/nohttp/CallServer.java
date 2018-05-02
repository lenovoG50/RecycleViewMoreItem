package com.example.demo.recycleviewmoreitem.nohttp;

import android.content.Context;

import com.example.demo.recycleviewmoreitem.nohttp.Interface.HttpListener;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * Created by 59246 on 2018/3/12.
 */

public class CallServer {
    private static CallServer sCallSever;

    //通过类名调用，获取callServer对象
    public synchronized static CallServer getRequestInstance() {
        if (sCallSever == null) {
            sCallSever = new CallServer();
        }
        return sCallSever;
    }

    /**
     * 创建一个NoHttp请求队列的对象
     */
    private RequestQueue mRequestQueue;

    public CallServer() {
        this.mRequestQueue = NoHttp.newRequestQueue();
    }

    /**
     * @param context   上下文 用来实例化我们自定义的对话框
     * @param what      标识,用来标志请求,当多个请求使用同一个
     * @param request   请求对象
     * @param callback  网络请求的监听器
     * @param isLoading 是否显示进度条对话框
     * @param canCancle 是否允许用户取消请求
     */
    public <T> void add(Context context, int what, com.yolanda.nohttp.rest.Request request, HttpListener callback, boolean isLoading, boolean canCancle) {
        mRequestQueue.add(what, request, new HttpResponseListener<T>(context, request, callback, isLoading, canCancle));
    }

    /**
     * 取消这个sign标记的所有请求
     */
    public void cancelBySign(Object sign) {
        mRequestQueue.cancelBySign(sign);
    }

    /**
     * 取消队列中的所有请求
     */
    public void cancelAll() {
        mRequestQueue.cancelAll();
    }

    /**
     * 退出app时停止所有请求
     */
    public void stopAll() {
        mRequestQueue.stop();
    }
}
