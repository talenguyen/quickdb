package com.tale.quickdb.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.CancellationSignal;

/**
 * Created by Giang on 6/28/2014.
 */
interface DBController {

    public void open();

    public void close();

    public long insert (String table, String nullColumnHack, ContentValues values);

    public long insert (String table, ContentValues values);

    public int update (String table, ContentValues values, String whereClause, String[] whereArgs);

    public int delete (String table, String whereClause, String[] whereArgs);

    public Cursor query (boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    public Cursor query (boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, CancellationSignal cancellationSignal);

    public Cursor query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);

    public Cursor query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    public Cursor quickQuery(String table, String selection, String[] selectionArgs);

    public Cursor rawQuery (String sql, String[] selectionArgs);

}
