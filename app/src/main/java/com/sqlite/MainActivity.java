package com.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sqlite.netutils.DBHelper;

public class MainActivity extends AppCompatActivity {

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        db.createDatabase();

        try {
            Cursor c = db.getData("select * from " + DBHelper.COLOR_TABLE);
            if (c == null) {
                System.out.print("");
            } else {
                System.out.print("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
