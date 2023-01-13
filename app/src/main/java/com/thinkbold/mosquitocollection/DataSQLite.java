package com.thinkbold.mosquitocollection;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DataSQLite extends SQLiteOpenHelper {
    String TABLE_NAME_PREPSDATA = "prepsdata";
    String TABLE_NAME_PARTONE = "PartOne";
    String TABLE_NAME_PARTTWO = "PartTwo";
    String TABLE_NAME_PARTTHREE = "PartThree";
    String TABLE_NAME_PARTFOUR = "PartTfour";

    public DataSQLite(@Nullable Context context){
        super(context, "ecodatabase.db", null, 1);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        String query_prepsdata, query_part1, query_part2, query_part3, query_part4;

        query_prepsdata = " CREATE TABLE " + TABLE_NAME_PREPSDATA + "( entryId INTEGER PRIMARY KEY, " +
                "serialno TEXT, o_name TEXT, h_number TEXT, d_date TEXT, d_dist TEXT, d_ward TEXT, d_hamlet TEXT, d_trap TEXT)";



        db.execSQL(query_prepsdata);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query_prepsdata;
        query_prepsdata = "DROP TABLE IF EXISTS prepsdata";
        db.execSQL(query_prepsdata);
        onCreate(db);
    }
    public ArrayList<HashMap<String, String>> getPrepsData(){
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_PREPSDATA;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("serialno", cursor.getString(1));
                map.put("o_name", cursor.getString(2));
                map.put("d_date", cursor.getString(4));
                map.put("d_trap", cursor.getString(8));

                wordList.add(map);
            }while (cursor.moveToNext());
        }
        database.close();
        return wordList;
    }

    public void insertDataInPreps(HashMap<String, String> querryvalues){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("serialno", querryvalues.get("serialno"));
        values.put("o_name", querryvalues.get("o_name"));
        values.put("h_number", querryvalues.get("h_number"));
        values.put("d_date", querryvalues.get("d_date"));
        values.put("d_dist", querryvalues.get("d_dist"));
        values.put("d_ward", querryvalues.get("d_ward"));
        values.put("d_hamlet", querryvalues.get("d_hamlet"));
        values.put("d_trap", querryvalues.get("d_trap"));

        db.insert(TABLE_NAME_PREPSDATA, null, values);
        db.close();
    }

    public String getSelectedPrepsData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME_PREPSDATA
                + " where serialno" + " = '" + id + "'", null);
        StringBuffer bf = new StringBuffer();
        while (c.moveToNext()) {

            @SuppressLint("Range") String serialno = c.getString(c.getColumnIndex("serialno"));
            @SuppressLint("Range") String o_name = c.getString(c.getColumnIndex("o_name"));
            @SuppressLint("Range") String h_number = c.getString(c.getColumnIndex("h_number"));
            @SuppressLint("Range") String d_date = c.getString(c.getColumnIndex("d_date"));
            @SuppressLint("Range") String d_dist = c.getString(c.getColumnIndex("d_dist"));
            @SuppressLint("Range") String d_ward = c.getString(c.getColumnIndex("d_ward"));
            @SuppressLint("Range") String d_hamlet = c.getString(c.getColumnIndex("d_hamlet"));
            @SuppressLint("Range") String d_trap = c.getString(c.getColumnIndex("d_trap"));

            bf.append(serialno + "#" + o_name + "#" + h_number + "#" + d_date + "#" + d_dist + "#" + d_ward + "#" + d_hamlet + "#" + d_trap + "#");
        }
        return bf.toString();
    }
    public void deleteReportRow(String entryDB) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME_PREPSDATA, "serialno" + "='" + entryDB + "'", null);
        database.delete(TABLE_NAME_PARTONE, "serialno" + "='" + entryDB + "'", null);
        database.delete(TABLE_NAME_PARTTWO, "serialno" + "='" + entryDB + "'", null);
        database.delete(TABLE_NAME_PARTTHREE, "serialno" + "='" + entryDB + "'", null);
        database.delete(TABLE_NAME_PARTFOUR, "serialno" + "='" + entryDB + "'", null);

    }
}
