package com.example.demo.recycleviewmoreitem;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.recycleviewmoreitem.Adapter.HomeAdapter;
import com.example.demo.recycleviewmoreitem.JsonBean.HomeBean;
import com.example.demo.recycleviewmoreitem.nohttp.CallServer;
import com.example.demo.recycleviewmoreitem.nohttp.Interface.HttpListener;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 59246 on 2018/4/28.
 */

public class Home extends Fragment implements PullToRefreshBase.OnRefreshListener2, HttpListener {


    @BindView(R.id.pull_to_refresh)
    PullToRefreshListView pullToRefresh;
    Unbinder unbinder;
    ArrayList<HomeBean.ResultBean.DataBean> home = new ArrayList<HomeBean.ResultBean.DataBean>();

    Handler handler = new Handler() {

        private HomeAdapter homeAdapter;

        @Override
        public void handleMessage(Message msg) {
            //adapter
            if (homeAdapter == null) {
                homeAdapter = new HomeAdapter(getContext(), home);
                pullToRefresh.setAdapter(homeAdapter);
            } else {
                homeAdapter.notifyDataSetChanged();
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        //初始化view
        initView();
        return view;
    }

    private void initView() {
        //支持下拉刷新上拉加载
        pullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefresh.setOnRefreshListener(this);
        //请求数据
        initData();

    }

    /**
     * 请求数据
     */
    private void initData() {
        Request<String> request = NoHttp.createStringRequest("http://v.juhe.cn/toutiao/index?type=top&key=b0c1a57febbe49da8940dc820c2d8e43", RequestMethod.GET);
        CallServer callServer = CallServer.getRequestInstance();
        callServer.add(getContext(), 0, request, this, true, true);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    /**
     * 加载
     *
     * @param refreshView
     */
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    /**
     * 请求失败
     *
     * @param what
     * @param response
     */
    @Override
    public void onFailed(int what, Response response) {

    }

    /**
     * 请求成功
     *
     * @param what
     * @param response
     */
    @Override
    public void onSuccesed(int what, Response response) {
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(response.get().toString(), HomeBean.class);
        HomeBean.ResultBean homeBeanResult = homeBean.getResult();
        List<HomeBean.ResultBean.DataBean> data = homeBeanResult.getData();
        Log.i("data", data.size() + "");
        for (HomeBean.ResultBean.DataBean dataBean : data) {
            home.add(dataBean);
        }
        handler.sendEmptyMessage(0);
    }
}
