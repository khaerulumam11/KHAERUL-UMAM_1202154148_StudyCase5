package com.example.khaerulumam.khaerulumam_1202154148_studycase5.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilhamelmujib on 7/29/17.
 */

public class DataHelper2 extends SQLiteOpenHelper{

    public static final String db = "db_crud";
    public static final String tb_user = "crud";


    public static final List<String> create = new ArrayList<String>(){{

        add("create table " + tb_user +
                " (id INTEGER PRIMARY KEY autoincrement,nama TEXT NOT NULL, deskripsi TEXT NOT NULL, jumlah TEXT NOT NULL)");


    }};

    public static final List<String> table = new ArrayList<String>(){{
        add(tb_user);
    }};

    public DataHelper2(Context context) {
        super(context, db, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        for (int i = 0; i < create.size(); i++){
            sqLiteDatabase.execSQL(create.get(i));
        }

        String sql = "INSERT INTO crud (id,nama, deskripsi,jumlah) VALUES " +
                "('1', 'test','test','4')";
        sqLiteDatabase.execSQL(sql);

//        String sql1 = "INSERT INTO tb_user (nama, nis) VALUES " +
//                "('Agnesia', '11506747')";
//        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        for (int j = 0; j < table.size(); j++){
            sqLiteDatabase.execSQL(table.get(j));
        }
    }
}
