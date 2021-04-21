package com.example.a149_webservicedliulistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(Context context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView tvHoTen,tvNamSinh,tvDiaChi;
        ImageView imgEdit,imgDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            viewHolder = new ViewHolder();

            viewHolder.tvHoTen = convertView.findViewById(R.id.tvTenSV);
            viewHolder.tvNamSinh = convertView.findViewById(R.id.tvNamSinhSV);
            viewHolder.tvDiaChi = convertView.findViewById(R.id.tvDiaChiSV);
            viewHolder.imgEdit = convertView.findViewById(R.id.imgEdit);
            viewHolder.imgDelete = convertView.findViewById(R.id.imgDelete);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SinhVien sinhVien = sinhVienList.get(position);

        viewHolder.tvHoTen.setText(sinhVien.getHoTen());
        viewHolder.tvNamSinh.setText("Nam sinh: " + sinhVien.getNamSinh());
        viewHolder.tvDiaChi.setText(sinhVien.getDiaChi());

        return convertView;
    }
}
