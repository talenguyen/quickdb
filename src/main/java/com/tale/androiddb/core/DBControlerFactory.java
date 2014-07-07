package com.tale.androiddb.core;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TALE on 7/7/2014.
 */
public class DBControlerFactory {
    public static DBController newDBController(SQLiteOpenHelper sqLiteOpenHelper) {
        return new DBControllerImpl(sqLiteOpenHelper);
    }

    public static DBObjectController newDBObjectController(SQLiteOpenHelper sqLiteOpenHelper, SQLiteObjectHelper sqLiteObjectHelper) {
        return new DBObjectControllerImpl(newDBController(sqLiteOpenHelper), sqLiteObjectHelper);
    }
}
