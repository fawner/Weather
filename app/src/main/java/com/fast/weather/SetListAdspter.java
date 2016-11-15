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

/**
 * Created by 亲爱的~ on 2016/11/15.
 */
public class SetListAdspter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public SetListAdspter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public TextView set_list_name;
        public TextView set_list_country;
        public TextView set_list_path;
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
            convertView=layoutInflater.inflate(R.layout.set_list, null);
            zujian.set_list_name = (TextView)convertView.findViewById(R.id.set_list_name);
            zujian.set_list_country = (TextView)convertView.findViewById(R.id.set_list_country);
            zujian.set_list_path = (TextView)convertView.findViewById(R.id.set_list_path);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.set_list_name.setText((String)data.get(position).get("set_list_name"));
        zujian.set_list_country.setText((String)data.get(position).get("set_list_country"));
        zujian.set_list_path.setText((String)data.get(position).get("set_list_path"));
        return convertView;
    }
}
