package com.sqlite.netutils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nidhi on 13-03-2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static final String database_Name = "sqlite.db";
    public static final int database_Version = 1;
    public static final String TAG = "DATABASE OPERATIONS";

    public static final String COLOR_TABLE = "_color";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COLOR_NAME = "name";
    public static final String COLUMN_COLOR_desc = "description";

    public static final String create_Table = "CREATE TABLE " + COLOR_TABLE +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            COLUMN_COLOR_NAME + " TEXT);";

    public DBHelper(Context context) {
        super(context, database_Name, null, database_Version);
        Log.i(TAG, "Database Created / Opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_Table);
    }

    public void createDatabase() {
        db = getReadableDatabase();
    }

    public Cursor getData(String Query) {
        db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(Query, null);
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteTable(String table_name) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            if (db.delete(table_name, "1", null) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteRecord(String id, String Table) {
        db = getWritableDatabase();
        String value[] = {id};
        int i = db.delete(Table, COLUMN_ID + "=?", value);
        db.close();
    }

    public String rp_getvalue(String table_name, String column_name, String where) {
        try {
            Cursor c2 = getData("select * from " + table_name + " where " + where + "");
            if (c2.getCount() > 0) {
                if (c2.moveToFirst()) {
                    String value = "" + c2.getString(c2.getColumnIndex("" + column_name));
                    c2.close();
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "0";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            String DATABASE_ALTER_TEAM_1 = "ALTER TABLE " + COLOR_TABLE + " ADD COLUMN " + COLUMN_COLOR_desc;
            if (oldVersion < newVersion) {
                Log.e("", "");
                db.execSQL(DATABASE_ALTER_TEAM_1);
            } else {
                Log.e("", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
