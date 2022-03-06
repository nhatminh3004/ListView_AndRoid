package com.example.listview_sinhvien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        Button btnThem, btnHuy;
        EditText edtTen, edtMoTa;
        ImageButton imgButoonCamera, imgButtonThuVien;
        ImageView imgViewHinh;
        DataBase dataBase;
        ListView listView;
        ArrayList<DoVat> arrayList;
        DoVatAdapter adapter;
        int ketQuaTraVeCuaHinhTuCamera=123;
        int ketQuaTraVeCuaThuVien=456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        listView= (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new DoVatAdapter(this,R.layout.layout_dong,arrayList);
        listView.setAdapter(adapter);
        dataBase = new DataBase(this,"QuanLy.sqlite",null,1);
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS DoVat(Id INTEGER PRIMARY KEY AUTOINCREMENT,Ten VARCHAR(150),MoTa VARCHAR(250),HinhAnh BLOB)");
        //get Data
        Cursor cursor = dataBase.GetData("SELECT * FROM DoVat");
        while(cursor.moveToNext()){
            arrayList.add(new DoVat( cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3)));

        }
        adapter.notifyDataSetChanged();
        //
        imgButtonThuVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,ketQuaTraVeCuaThuVien);
            }
        });
        imgButoonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,ketQuaTraVeCuaHinhTuCamera);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyen data ImageView sang kieu byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgViewHinh.getDrawable();
                Bitmap bitmap  =bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhAnh = byteArrayOutputStream.toByteArray();
                //
                dataBase.Insert_DoVat(edtTen.getText().toString().trim(),edtMoTa.getText().toString().trim(),hinhAnh);
                Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == ketQuaTraVeCuaThuVien && resultCode==RESULT_OK &&data !=null){
            Uri uri= data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgViewHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == ketQuaTraVeCuaHinhTuCamera && resultCode==RESULT_OK &&data !=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgViewHinh.setImageBitmap(bitmap);

        }



        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        btnThem = (Button) findViewById(R.id.buttonThem);
        btnHuy = (Button) findViewById(R.id.buttonHuy);
        edtTen = (EditText) findViewById(R.id.editTextTextTenDoVat);
        edtMoTa = (EditText) findViewById(R.id.editTextTextMoTa);
        imgButoonCamera= (ImageButton) findViewById(R.id.imageButtonCamera);
        imgButtonThuVien= (ImageButton) findViewById(R.id.imageButtonThuVien);
        imgViewHinh= (ImageView) findViewById(R.id.imageViewKhungLoadingAnh);
    }


}