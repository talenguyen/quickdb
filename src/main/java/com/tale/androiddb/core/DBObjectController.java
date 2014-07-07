package com.tale.androiddb.core;

import java.util.List;

/**
 * Created by Giang on 6/28/2014.
 */
interface DBObjectController {

    public long insert(Entry object);

    public int update(Entry object);

    public int update(Entry object, String whereClause, String[] whereArgs);

    public int delete(Entry object);

    public int delete(Entry object, String whereClause, String[] whereArgs);

    public Object queryById(String table, long id);

    public List<Object> query(String table, String selection, String[] selectionArgs);
}
