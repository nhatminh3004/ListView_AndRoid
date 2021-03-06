package com.example.listview_sinhvien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


public class DataBase extends SQLiteOpenHelper {
    public DataBase( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public void Insert_DoVat(String ten,String mota, byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO DoVat VALUES(null,?, ? , ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,ten);
        statement.bindString(2,mota);
        statement.bindBlob(3,hinh);
        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
