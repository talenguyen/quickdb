package com.tale.androiddb.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.util.List;

public class SQLiteOpenHelperEx extends SQLiteOpenHelper {

    private Contract contract;

    public SQLiteOpenHelperEx(Context context, Contract contract) {
        super(context, contract.getDBName(), null, contract.getDBVersion());
        this.contract = contract;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final List<Table> tables = contract.getTables();
        if (tables == null || tables.size() == 0) {
            return;
        }

        for (int i = 0, count = tables.size(); i < count; i++) {
            final Table table= tables.get(i);
            final String createStatement = table.buildCreateStatement();
            if (TextUtils.isEmpty(createStatement)) {
                throw new NullPointerException("The CREATE statement must not be NULL or EMPTY");
            }
            sqLiteDatabase.execSQL(createStatement);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        final List<Table> tables = contract.getTables();
        if (tables == null || tables.size() == 0) {
            return;
        }

        for (int i = 0, count = tables.size(); i < count; i++) {
            final Table table= tables.get(i);
            final String deleteStatement = table.buildDeleteStatement();
            if (TextUtils.isEmpty(deleteStatement)) {
                throw new NullPointerException("The DELETE statement must not be NULL or EMPTY");
            }
            sqLiteDatabase.execSQL(deleteStatement);
        }
        onCreate(sqLiteDatabase);
    }
}