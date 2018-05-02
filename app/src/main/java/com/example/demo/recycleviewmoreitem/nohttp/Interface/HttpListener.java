package com.example.demo.recycleviewmoreitem.nohttp.Interface;

import com.yolanda.nohttp.rest.Response;

/**
 * Created by 59246 on 2018/3/12.
 */

public interface HttpListener<T> {

    //请求失败的监听方法
    void onFailed(int what, Response<String> response);

    void onSuccesed(int what, Response response);
}
