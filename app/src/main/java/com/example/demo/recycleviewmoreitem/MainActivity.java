package com.example.demo.recycleviewmoreitem;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frgment)
    ViewPager fragment;
    @BindView(R.id.bottomLayout)
    PageNavigationView bottomLayout;
    ArrayList<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        list.add(new Home());
        list.add(new News());
        list.add(new KeJi());

        fragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        NavigationController build = bottomLayout.custom()
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, "首页"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, "新闻"))
                .addItem(newItem(R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, "科技"))
                .build();
        build.setupWithViewPager(fragment);
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(0xFF009688);
        return normalItemView;
    }

}
