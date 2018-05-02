package com.example.demo.recycleviewmoreitem.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo.recycleviewmoreitem.JsonBean.HomeBean;
import com.example.demo.recycleviewmoreitem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 59246 on 2018/4/29.
 */

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<HomeBean.ResultBean.DataBean> list;
    private static final int ITEM_COUNT = 1;
    private static final int ITEM_COUNT1 = 2;
    private static final int ITEM_COUNT2 = 3;
    private ViewHolder holder;
    private ViewHolder1 holder1;
    private ViewHolder2 holder2;

    public HomeAdapter(Context home, List<HomeBean.ResultBean.DataBean> dataBeans) {
        context = home;
        list = dataBeans;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HomeBean.ResultBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            switch (getItemViewType(position)) {
                case ITEM_COUNT:
                    convertView = View.inflate(context, R.layout.home_item1, null);
                    holder = new ViewHolder();
                    holder.title = convertView.findViewById(R.id.title);
                    holder.author_name = convertView.findViewById(R.id.author_name);
                    holder.img = convertView.findViewById(R.id.img);
                    convertView.setTag(holder);
                    break;
                case ITEM_COUNT1:
                    convertView = View.inflate(context, R.layout.home_item2, null);
                    holder1 = new ViewHolder1();
                    holder1.title = convertView.findViewById(R.id.title);
                    holder1.author_name = convertView.findViewById(R.id.author_name);
                    holder1.img1 = convertView.findViewById(R.id.img1);
                    holder1.img2 = convertView.findViewById(R.id.img2);
                    convertView.setTag(holder1);
                case ITEM_COUNT2:
                    convertView = View.inflate(context, R.layout.home_item3, null);
                    holder2 = new ViewHolder2();
                    holder2.title = convertView.findViewById(R.id.title);
                    holder2.author_name = convertView.findViewById(R.id.author_name);
                    holder2.img1 = convertView.findViewById(R.id.img1);
                    holder2.img2 = convertView.findViewById(R.id.img2);
                    holder2.img3 = convertView.findViewById(R.id.img3);
                    convertView.setTag(holder2);
                    break;

            }
        } else {
            switch (getItemViewType(position)) {
                case ITEM_COUNT:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case ITEM_COUNT1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case ITEM_COUNT2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
            }
        }
        /**
         * 赋值
         */
        switch (getItemViewType(position)) {
            case ITEM_COUNT:
                holder.title.setText(list.get(position).getTitle());
                holder.author_name.setText(list.get(position).getAuthor_name());
                Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder.img);
                break;
            case ITEM_COUNT1:
                holder1.title.setText(list.get(position).getTitle());
                holder1.author_name.setText(list.get(position).getAuthor_name());
                Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder1.img1);
                Glide.with(context).load(list.get(position).getThumbnail_pic_s02()).into(holder1.img2);
                break;
            case ITEM_COUNT2:
                holder2.title.setText(list.get(position).getTitle());
                holder2.author_name.setText(list.get(position).getAuthor_name());
                Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder2.img1);
                Glide.with(context).load(list.get(position).getThumbnail_pic_s02()).into(holder2.img2);
                Glide.with(context).load(list.get(position).getThumbnail_pic_s03()).into(holder2.img3);
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s02() == null && list.get(position).getThumbnail_pic_s03() == null) {
            return ITEM_COUNT;
        } else if (list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s02() != null && list.get(position).getThumbnail_pic_s03() == null) {
            return ITEM_COUNT1;
        } else {
            return ITEM_COUNT2;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    /*public void getData(ArrayList<HomeBean.ResultBean.DataBean> dataBeans) {
        list = dataBeans;
    }*/

    class ViewHolder {
        TextView title, author_name;
        ImageView img;
    }

    class ViewHolder1 {
        TextView title, author_name;
        ImageView img1, img2;
    }

    class ViewHolder2 {
        TextView title, author_name;
        ImageView img1, img2, img3;
    }
}
