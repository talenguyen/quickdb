package com.tale.androiddb.robolectric.mock;

import android.content.ContentValues;
import android.database.Cursor;

import com.tale.androiddb.core.SQLiteObjectHelper;

/**
 * Created by TALE on 7/8/2014.
 */
public class MockDBObjectHelper implements SQLiteObjectHelper {

    @Override
    public ContentValues buildContentValues(Object object) {
        if (object instanceof Person) {
            Person person = (Person) object;
            ContentValues values = new ContentValues();
            values.put("name", person.name);
            return values;
        }

        return null;
    }

    @Override
    public Object buildObject(String table, Cursor cursor) {
        if (table.equals("Person")) {
            Person person = new Person();
            person.name = cursor.getString(cursor.getColumnIndex("name"));
            person._id = cursor.getLong(cursor.getColumnIndex("_id"));
            return person;
        }
        return null;
    }
}
