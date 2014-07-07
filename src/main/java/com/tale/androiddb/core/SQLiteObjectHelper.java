package com.tale.androiddb.core;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by TALE on 7/7/2014.
 */
public interface SQLiteObjectHelper {

    ContentValues buildContentValues(Object object);

    Object buildObject(Cursor cursor);

}
