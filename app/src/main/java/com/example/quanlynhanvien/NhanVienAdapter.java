package com.example.quanlynhanvien;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NhanVien> nhanVienList;

    public NhanVienAdapter(Context context, int layout, List<NhanVien> nhanVienList) {
        this.context = context;
        this.layout = layout;
        this.nhanVienList = nhanVienList;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHoler{
        TextView txtMaSo,txtHoTen,txtGioiTinh,txtDonVi;
        ImageView imageViewHinh_Custome;
}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoler holer;
        if(view==null){
            holer= new ViewHoler();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holer.txtMaSo = (TextView) view.findViewById(R.id.textViewMaSo_Custome);
            holer.txtHoTen=(TextView) view.findViewById(R.id.textViewHoTen_Custome);
            holer.txtGioiTinh=(TextView) view.findViewById(R.id.textViewGioiTinh_Custome);
            holer.txtDonVi=(TextView) view.findViewById(R.id.textViewDonVi_Custome);
            holer.imageViewHinh_Custome=(ImageView)  view.findViewById(R.id.imageViewCustome);
            view.setTag(holer);
        }
        else {
            holer=(ViewHoler) view.getTag();
        }
        NhanVien nhanvien = nhanVienList.get(i);
        holer.txtMaSo.setText("Mã Số:"+nhanvien.getMaso());
        holer.txtHoTen.setText("Họ Tên :"+nhanvien.getHoten());
        holer.txtGioiTinh.setText(nhanvien.getGioitinh());
        holer.txtDonVi.setText("Đơn Vị :"+nhanvien.getDonvi());
        byte[] hinhAnh = nhanvien.getHinh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holer.imageViewHinh_Custome.setImageBitmap(bitmap);
        return view;
    }
}
