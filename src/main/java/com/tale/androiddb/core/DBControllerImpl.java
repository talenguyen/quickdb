package com.tale.androiddb.core;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.CancellationSignal;

/**
 * Created by TALE on 6/27/2014.
 */
class DBControllerImpl implements DBController {

    private final SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private int connections;

    public DBControllerImpl(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public synchronized void open() {
        connections++;
        if (database != null && database.isOpen()) {
            return;
        }
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public synchronized void close() {
        connections = connections == 0 ? 0 : connections - 1;
        if (connections == 0 && database != null && database.isOpen()) {
            sqLiteOpenHelper.close();
        }
    }

    public long insert(String table, ContentValues values) {
        return insert(table, null, values);
    }


    @Override
    public long insert(String table, String nullColumnHack, ContentValues values) {
        // Verify pre-define.
        verify();

        return database.insert(table, null, values);
    }


    @Override
    public int update(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        // Verify pre-define.
        verify();

        // Update the database, returning the number of rows that affected.
        return database.update(tableName, values, whereClause, whereArgs);
    }

    @Override
    public int delete(String tableName, String whereClause, String[] whereArgs) {
        verify();

        return database.delete(tableName, whereClause, whereArgs);
    }

    @Override
    public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        verify();
        return database.query(distinct, table, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, CancellationSignal cancellationSignal) {
        verify();
        return database.query(distinct, table, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
    }

    @Override
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return query(table, columns, selection,
                selectionArgs, groupBy, having, orderBy, null);
    }

    @Override
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return query(false, table, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
    }

    @Override
    public Cursor quickQuery(String table, String selection, String[] selectionArgs) {
        return query(false, table, null, selection, selectionArgs, null, null, "_id asc", null);
    }

    @Override
    public Cursor rawQuery(String sql, String[] selectionArgs) {
        verify();
        return database.rawQuery(sql, selectionArgs);
    }

    /**
     * Verify the connection is open before interact with database.
     */
    private void verify() {
        if (database == null || !database.isOpen()) {
            throw new IllegalStateException(
                    "Must call open() first");
        }
    }

}
