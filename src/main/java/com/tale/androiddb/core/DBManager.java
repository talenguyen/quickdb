package com.tale.androiddb.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CancellationSignal;

import java.util.List;

/**
 * Created by Giang on 6/28/2014.
 */
public class DBManager {

    private static DBManager ourInstance = new DBManager();
    private DBControllerImpl dbController;
    private DBObjectController dbObjectController;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private DBObjectConverter dbObjectConverter;
    private SQLiteObjectHelper sqLiteObjectHelper;

    private DBManager() {
    }

    public static DBManager getInstance() {
        return ourInstance;
    }

    public void init(Context context, Contract contract) {
        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new SQLiteOpenHelperEx(context, contract);
        }
        if (dbController == null) {
            dbController = new DBControllerImpl(sqLiteOpenHelper);
        }

        // TODO: instance a SQLiteObjectHelper
        if (dbObjectController == null) {
            dbObjectController = new DBObjectControllerImpl(dbController, sqLiteObjectHelper);
        }
    }

    public void setSqLiteObjectHelper(SQLiteObjectHelper sqLiteObjectHelper) {
        this.sqLiteObjectHelper = sqLiteObjectHelper;
    }

    public void setDbObjectConverter(DBObjectConverter dbObjectConverter) {
        this.dbObjectConverter = dbObjectConverter;
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        try {
            dbController.open();
            return dbController.insert(table, nullColumnHack, values);
        } finally {
            dbController.close();
        }
    }

    public long insert(String table, ContentValues values) {
        try {
            dbController.open();
            return dbController.insert(table, null, values);
        } finally {
            dbController.close();
        }
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        try {
            dbController.open();
            return dbController.update(table, values, whereClause, whereArgs);
        } finally {
            dbController.close();
        }
    }

    public int delete(String table, String whereClause, String[] whereArgs) {
        try {
            dbController.open();
            return dbController.delete(table, whereClause, whereArgs);
        } finally {
            dbController.close();
        }
    }

    public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        try {
            dbController.open();
            return dbController.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        } finally {
            dbController.close();
        }
    }

    public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, CancellationSignal cancellationSignal) {
        try {
            dbController.open();
            return dbController.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
        } finally {
            dbController.close();
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        try {
            dbController.open();
            return dbController.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        } finally {
            dbController.close();
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        try {
            dbController.open();
            return dbController.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        } finally {
            dbController.close();
        }
    }

    public Cursor quickQuery(String table, String selection, String[] selectionArgs) {
        try {
            dbController.open();
            return dbController.quickQuery(table, selection, selectionArgs);
        } finally {
            dbController.close();
        }
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        try {
            dbController.open();
            return dbController.rawQuery(sql, selectionArgs);
        } finally {
            dbController.close();
        }
    }

    public long insert(Object object) {
        final Entry table = dbObjectConverter.fromObject(object);
        if (table == null) {
            return 0;
        }
        return dbObjectController.insert(table);
    }

    public int update(Object object) {
        final Entry table = dbObjectConverter.fromObject(object);
        if (table == null) {
            return 0;
        }
        return dbObjectController.update(table);
    }

    public int update(Object object, String whereClause, String[] whereArgs) {
        final Entry table = dbObjectConverter.fromObject(object);
        if (table == null) {
            return 0;
        }
        return dbObjectController.update(table, whereClause, whereArgs);
    }

    public int delete(Object object) {
        final Entry table = dbObjectConverter.fromObject(object);
        if (table == null) {
            return 0;
        }
        return dbObjectController.delete(table);
    }

    public int delete(Object object, String whereClause, String[] whereArgs) {
        final Entry table = dbObjectConverter.fromObject(object);
        if (table == null) {
            return 0;
        }
        return dbObjectController.delete(table, whereClause, whereArgs);
    }

    public Object queryById(String table, long id) {
        return dbObjectController.queryById(table, id);
    }

    public List<Object> query(String table, String selection, String[] selectionArgs) {
        return dbObjectController.query(table, selection, selectionArgs);
    }

}
