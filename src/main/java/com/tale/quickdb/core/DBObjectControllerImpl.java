package com.tale.quickdb.core;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

/**
 * Created by Giang on 6/28/2014.
 */
class DBObjectControllerImpl implements DBObjectController{

    private final DBController dbController;

    DBObjectControllerImpl(DBController dbController) {
        this.dbController = dbController;
    }

    public long insert(ITable object) {
        try {
            dbController.open();
            return dbController.insert(object.getName(), object.toContentValues());
        } finally {
            dbController.close();
        }
    }

    public int update(ITable object) {
        try {
            dbController.open();
            return dbController.update(object.getName(), object.toContentValues(), "_id LIKE ?", new String[]{String.valueOf(object.get_id())});
        } finally {
            dbController.close();
        }
    }

    public int update(ITable object, String whereClause, String[] whereArgs) {
        try {
            dbController.open();
            return dbController.update(object.getName(), object.toContentValues(), whereClause, whereArgs);
        } finally {
            dbController.close();
        }
    }

    public int delete(ITable object) {
        try {
            dbController.open();
            return dbController.delete(object.getName(), "_id LIKE ?", new String[]{String.valueOf(object.get_id())});
        } finally {
            dbController.close();
        }
    }

    public int delete(ITable object, String whereClause, String[] whereArgs) {
        try {
            dbController.open();
            return dbController.delete(object.getName(), whereClause, whereArgs);
        } finally {
            dbController.close();
        }
    }

    public Object queryById(String table, long id, ObjectFactory factory) {
        Cursor cursor = null;
        try {
            dbController.open();
            cursor = dbController.quickQuery(table, "_id LIKE ?", new String[]{String.valueOf(id)});
            if (cursor != null && cursor.moveToFirst()) {
                ITable object = factory.newInstance(table);
                object.consume(cursor);
                return object;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbController.close();
        }
        return null;
    }

    public List<Object> query(String table, String selection, String[] selectionArgs, ObjectFactory factory) {
        Cursor cursor = null;
        try {
            dbController.open();
            cursor = dbController.quickQuery(table, selection, selectionArgs);
            if (cursor != null && cursor.moveToFirst()) {
                List<Object> result = new ArrayList<Object>(cursor.getCount());
                do {
                    ITable object = factory.newInstance(table);
                    object.consume(cursor);
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
