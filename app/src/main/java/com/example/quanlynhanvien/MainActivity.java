package com.example.quanlynhanvien;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<NhanVien> nhanVienArrayList;
    NhanVienAdapter nhanVienAdapter;
    String [] dv_string_xml;
    Spinner spinnerDonVi;
    Button btnthoat, btnThem,btnXoa,btnSua;
    EditText editTextMaSo,editTextHoTen;
    ListView listView;
    RadioGroup radioGroup;
    RadioButton radioButtonNam,radioButtonNu;
    String donVi;
    Database database;
    //SqLite
    ImageView imageViewKhungAnh;
    Button btnUpLoadAnh;
    int REQUEST_THUVIEN=123;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.listViewNhanVien);
        nhanVienArrayList= new ArrayList<>();
        nhanVienAdapter= new NhanVienAdapter(MainActivity.this,R.layout.customize_listview,nhanVienArrayList);
        listView.setAdapter(nhanVienAdapter);
       imageViewKhungAnh= (ImageView) findViewById(R.id.imageViewKhungAnh);
         btnUpLoadAnh= (Button) findViewById(R.id.buttonLoadAnhTuThuVien);

        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        radioButtonNam=(RadioButton) findViewById(R.id.radioButtonNam);
        radioButtonNu=(RadioButton) findViewById(R.id.radioButtonNu);
        btnthoat= (Button) findViewById(R.id.buttonthoat);
        btnThem=(Button) findViewById(R.id.buttonThem);
        btnSua=(Button) findViewById(R.id.buttonSua);
        btnXoa= (Button) findViewById(R.id.buttonXoa);

        editTextMaSo = (EditText) findViewById(R.id.editTextMaSo);
        editTextHoTen=(EditText) findViewById(R.id.editTextHoTen);
        spinnerDonVi=(Spinner) findViewById(R.id.spinner);
        dv_string_xml=getResources().getStringArray(R.array.donvi_list);
        ArrayAdapter<String> adapterDonViList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dv_string_xml);
        spinnerDonVi.setAdapter(adapterDonViList);
        LayGiaTriTrongSpinner();
        //Sqlite
        database = new Database(this,"QuanLyNhanVien.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS NhanVienQL(Id INTEGER PRIMARY KEY AUTOINCREMENT,Maso VARCHAR(150),HoTen VARCHAR(150),GioiTinh VARCHAR(250),DonVi VARCHAR(150),HinhAnh BLOB)");
        //get data
        Cursor cursor = database.GetData("SELECT * FROM NhanVienQL");
        while (cursor.moveToNext()){
            nhanVienArrayList.add(new NhanVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getBlob(5)) );

        }
        nhanVienAdapter.notifyDataSetChanged();

        //
            btnUpLoadAnh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Intent intent =new Intent(Intent.ACTION_PICK);
                   intent.setType("image/*");
                   startActivityForResult(intent,REQUEST_THUVIEN);
                }
            });

        //
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageViewKhungAnh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhAnh = byteArrayOutputStream.toByteArray();
                String gioiTinh= ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                database.INSERT_NhanVien(editTextMaSo.getText().toString().trim(),editTextHoTen.getText().toString().trim(),gioiTinh,donVi,hinhAnh);
                Cursor cursor = database.GetData("SELECT * FROM NhanVienQL");
                while (cursor.moveToNext()){
                    nhanVienArrayList.add(new NhanVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getBlob(5)) );

                }
                nhanVienAdapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                listView.setAdapter(nhanVienAdapter);
            }
        });

//        ThemVaoListView();
//        HienThiKhiAnVaoListView();


        ThoatChuongTrinh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_THUVIEN && resultCode == RESULT_OK && data !=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewKhungAnh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ThoatChuongTrinh(){
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void LayGiaTriTrongSpinner(){
        spinnerDonVi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donVi=dv_string_xml[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void ThemVaoListView (){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                int maSo = Integer.parseInt(editTextMaSo.getText().toString());
//                String hoTen = editTextHoTen.getText().toString();
//                String gioiTinh = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
//
//                NhanVien nv = new NhanVien(maSo,hoTen,gioiTinh,donVi);
//                nhanVienArrayList.add(nv);
//                //đưa nhân viên vào ListView
//                ArrayList<String> listNhanVien = new ArrayList<>();
//                for(NhanVien nv_temp:nhanVienArrayList)
//                    listNhanVien.add(nv_temp.toString());
//                ArrayAdapter<String> adapterDuaNhanVienVaoListView = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,listNhanVien);
//                listView.setAdapter(adapterDuaNhanVienVaoListView);
                //
            }
        });
    }
    private void HienThiKhiAnVaoListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhanVien nv = nhanVienArrayList.get(i);
                editTextMaSo.setText(nv.getMaso()+"");
                editTextHoTen.setText(nv.getHoten());
                //Xử lí giới tính
                if (nv.getGioitinh().equals("Nam"))
                    radioButtonNam.setChecked(true);
                else
                    radioButtonNu.setChecked(true);
                //Xử lí đơn vị
//                for (int j = 0; j < dv_List.length; j++)
//                    if (dv_List[j].equals(nv.getDonvi()))
//                        sp_DonVi.setSelection(j);

            }
        });
    }
}