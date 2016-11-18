package com.fast.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class NowListAdspter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public NowListAdspter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public TextView now_list_title;
        public TextView now_list_details;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.now_list, null);
            zujian.now_list_title = (TextView)convertView.findViewById(R.id.now_list_title);
            zujian.now_list_details = (TextView)convertView.findViewById(R.id.now_list_details);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.now_list_title.setText((String)data.get(position).get("now_list_title"));
        zujian.now_list_details.setText((String)data.get(position).get("now_list_details"));
        return convertView;
    }
}
