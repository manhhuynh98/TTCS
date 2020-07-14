package com.example.thuctapcoso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE = "ttcs";
    public static final String TABLE = "highscore";
    public static final String TEN = "ten";
    public static final String DIEM = "diem";

    SQLiteDatabase sqLiteDatabase;

    public DataBase(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " create table "+ TABLE + " ( "+
                TEN + " nvachar(50), "+
                DIEM + " integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = " drop table if exists " + TABLE;
        sqLiteDatabase.execSQL(sql);
    }

    public long insertData(HighScore highScore){
        ContentValues cv = new ContentValues();
        cv.put(TEN,highScore.getTen());
        cv.put(DIEM,highScore.getDiem());
        return sqLiteDatabase.insert(TABLE,null,cv);
    }

    public ArrayList<HighScore> showData(){
        String sql = " SELECT * FROM " + TABLE + " ORDER BY "+ DIEM +" DESC LIMIT 5" ;
        Cursor cs = sqLiteDatabase.rawQuery(sql,null);
        ArrayList<HighScore> arr = new ArrayList<>();

        while (cs.moveToNext()){
            HighScore highScore = new HighScore();
            highScore.Ten = cs.getString(0);
            highScore.Diem = cs.getInt(1);
            arr.add(highScore);
        }
        return arr;
    }
}
