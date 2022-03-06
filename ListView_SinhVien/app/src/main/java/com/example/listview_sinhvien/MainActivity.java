package com.example.listview_sinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        ListView lvTraiCay;
        ArrayList<TraiCay> arrayTraiCay;
        TraiCayAdapter adapter;
        ImageView  lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ImageView)findViewById(R.id.imageView);

        AnhXa();
        adapter = new TraiCayAdapter(this,R.layout.dong_trai_cay,arrayTraiCay);
        lvTraiCay.setAdapter(adapter);
    }

    private void AnhXa() {
        lvTraiCay=(ListView) findViewById(R.id.listviewTraiCay);
        arrayTraiCay = new ArrayList<>();

        arrayTraiCay.add(new TraiCay("Dâu Tây","Dâu tây đà lat",lv.getImageAlpha()));
        arrayTraiCay.add(new TraiCay("Thịt Tươi","Dâu tây đà lat",R.drawable.ic_launcher_background));
        arrayTraiCay.add(new TraiCay("Dâu Sống","Dâu tây đà lat",R.drawable.ic_launcher_background));
        arrayTraiCay.add(new TraiCay("Dâu Non","Dâu tây đà lat",R.drawable.ic_launcher_background));
        arrayTraiCay.add(new TraiCay("Dâu Chưa Chín","Dâu tây đà lat",R.drawable.ic_launcher_background));
    }
}