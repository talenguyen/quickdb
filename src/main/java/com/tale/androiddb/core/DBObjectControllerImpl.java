package com.tale.androiddb.core;

import android.database.Cursor;
import android.os.CancellationSignal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giang on 6/28/2014.
 */
class DBObjectControllerImpl implements DBObjectController {

    private final DBController dbController;
    private final SQLiteObjectHelper sqLiteObjectHelper;

    DBObjectControllerImpl(DBController dbController, SQLiteObjectHelper sqLiteObjectHelper) {
        this.dbController = dbController;
        this.sqLiteObjectHelper = sqLiteObjectHelper;
    }

    public long insert(Entry object) {
        try {
            dbController.open();
            return dbController.insert(object.getTable(), sqLiteObjectHelper.buildContentValues(object));
        } finally {
            dbController.close();
        }
    }

    public int update(Entry object) {
        try {
            dbController.open();
            return dbController.update(object.getTable(), sqLiteObjectHelper.buildContentValues(object), "_id LIKE ?", String.valueOf(object.get_id()));
        } finally {
            dbController.close();
        }
    }

    public int update(Entry object, String whereClause, String... whereArgs) {
        try {
            dbController.open();
            return dbController.update(object.getTable(), sqLiteObjectHelper.buildContentValues(object), whereClause, whereArgs);
        } finally {
            dbController.close();
        }
    }

    public int delete(Entry object) {
        try {
            dbController.open();
            return dbController.delete(object.getTable(), "_id LIKE ?", String.valueOf(object.get_id()));
        } finally {
            dbController.close();
        }
    }

    @Override
    public Object queryObjectById(String table, long id) {
        Cursor cursor = null;
        try {
            dbController.open();
            cursor = dbController.quickQuery(table, "_id LIKE ?", String.valueOf(id));
            if (cursor != null && cursor.moveToFirst()) {
                return sqLiteObjectHelper.buildObject(table, cursor);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbController.close();
        }
        return null;
    }

    @Override
    public List<Object> queryObjects(String table, String selection, String... selectionArgs) {
        return queryObjects(false, table, null, selection, selectionArgs, null, null, "_id asc", null);
    }

    @Override
    public List<Object> queryObjects(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return queryObjects(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, null);
    }

    @Override
    public List<Object> queryObjects(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, CancellationSignal cancellationSignal) {
        Cursor cursor = null;
        try {
            dbController.open();
            cursor = dbController.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
            return parseCursor(table, cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbController.close();
        }
    }

    @Override
    public List<Object> queryObjects(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return queryObjects(table, columns, selection, selectionArgs, groupBy, having, orderBy, null);
    }

    @Override
    public List<Object> queryObjects(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return queryObjects(false, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    @Override
    public List<Object> rawQueryObjects(String table, String sql, String... selectionArgs) {
        Cursor cursor = null;
        try {
            dbController.open();
            cursor = dbController.rawQuery(sql, selectionArgs);
            return parseCursor(table, cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbController.close();
        }
    }

    private List<Object> parseCursor(String table, Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            List<Object> result = new ArrayList<Object>(cursor.getCount());
            do {
                Object object = sqLiteObjectHelper.buildObject(table, cursor);
                result.add(object);
            } while (cursor.moveToNext());
            return result;
        }
        return null;
    }

}
