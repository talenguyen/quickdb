package com.tale.androiddb.core;

import android.database.Cursor;

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
            return dbController.update(object.getTable(), sqLiteObjectHelper.buildContentValues(object), "_id LIKE ?", new String[]{String.valueOf(object.get_id())});
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
            return dbController.delete(object.getTable(), "_id LIKE ?", new String[]{String.valueOf(object.get_id())});
        } finally {
            dbController.close();
        }
    }

    public Object queryById(String table, long id) {
        Cursor cursor = null;
        try {
            dbController.open();
            cursor = dbController.quickQuery(table, "_id LIKE ?", new String[]{String.valueOf(id)});
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

    public List<Object> query(String table, String selection, String... selectionArgs) {
        Cursor cursor = null;
        try {
            dbController.open();
            cursor = dbController.quickQuery(table, selection, selectionArgs);
            if (cursor != null && cursor.moveToFirst()) {
                List<Object> result = new ArrayList<Object>(cursor.getCount());
                do {
                    Object object = sqLiteObjectHelper.buildObject(table, cursor);
                    result.add(object);
                } while (cursor.moveToNext());
                return result;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbController.close();
        }
        return null;
    }
}
