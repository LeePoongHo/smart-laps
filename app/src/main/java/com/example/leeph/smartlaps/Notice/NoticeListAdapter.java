package com.example.leeph.smartlaps.Notice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.leeph.smartlaps.R;
import com.example.leeph.smartlaps.Service.Notice;

import java.util.List;

/**
 * Created by leeph on 2019-11-29.
 */

public class NoticeListAdapter extends BaseAdapter {

    private Context context;
    private List<Notice> noticeList;

    public NoticeListAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.notice, null);
        TextView noticeText = view.findViewById(R.id.noticeText);
        TextView nameText = view.findViewById(R.id.txtName);
        TextView dateText = view.findViewById(R.id.dateText);

        noticeText.setText(noticeList.get(position).getTitle());
        nameText.setText(noticeList.get(position).getMem_name());
        dateText.setText(noticeList.get(position).getWrite_date());

        view.setTag(noticeList.get(position).getTitle());


        return view;
    }
}
