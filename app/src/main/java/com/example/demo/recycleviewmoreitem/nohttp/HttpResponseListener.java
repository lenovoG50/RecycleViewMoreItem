package com.example.demo.recycleviewmoreitem.nohttp;

import android.content.Context;
import android.content.DialogInterface;

import com.example.demo.recycleviewmoreitem.nohttp.Interface.HttpListener;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

/**
 * Created by 59246 on 2018/3/12.
 */

public class HttpResponseListener<T> implements OnResponseListener {
    //使用之前自定义的进度条对话框
    private WaitDialog mWaitDialog;
    //之前定义的监听方法的接口类
    private HttpListener<T> callBack;
    //一个标识，看是否加载
    private boolean isLoading;
    //一个NoHttp队列
    private Request<?> mRequest;
    //上下文
    private Context context;
    //一个表示，判断进度条对话框是否可以取消
    private boolean canCancle;

    /**
     * @param callBack  自定义的接口对象，就是复写Nohttp成功失败的那个类
     * @param isLoading 判断进度条对话框是否可以取消
     * @param request   Nohttp的队列对象
     * @param context   上下文
     * @param canCancle
     */
    public HttpResponseListener(Context context, Request<?> request, HttpListener<T> callBack, boolean isLoading, boolean canCancle) {

        this.isLoading = isLoading;
        this.mRequest = request;
        this.callBack = callBack;
        this.context = context;
        this.canCancle = canCancle;

        if (context != null && isLoading) {
            mWaitDialog = new WaitDialog(context);
            mWaitDialog.setCancelable(canCancle);
            //设置监听器
            //当对话框点击外面可以取消，那么就让他取消的逻辑代码
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    HttpResponseListener.this.mRequest.cancel();
                }
            });
        }
    }


    @Override
    public void onStart(int what) {
        //判断进度条对话框是否处于加载状态，进度条对话框对象是否存在，进度条对话框是否存在
        if (isLoading && mWaitDialog != null && !mWaitDialog.isShowing()) {
            mWaitDialog.show();
        }
    }

    @Override
    public void onSucceed(int what, Response response) {
        //使用的是自己定义的接口，先判断接口对象是否为null，不为null时执行
        if (callBack != null) {
            callBack.onSuccesed(what, response);
        }
    }

    @Override
    public void onFailed(int what, Response response) {
        //使用的是自己定义的接口，先判断接口对象是否为null，为null时执行
        if (callBack != null) {
            callBack.onFailed(what, response);
        }
    }

    //判断进度条对话框是否处于夹在状态,进度条对话框对象是否存在,进度条对话框是否存在
    @Override
    public void onFinish(int what) {
        if (isLoading && mWaitDialog != null && mWaitDialog.isShowing()) {
            //隐藏对话框
            mWaitDialog.dismiss();
        }
    }
}
