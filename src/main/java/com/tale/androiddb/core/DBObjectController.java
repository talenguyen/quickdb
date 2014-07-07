package com.tale.androiddb.core;

import java.util.List;

/**
 * Created by Giang on 6/28/2014.
 */
interface DBObjectController {

    public long insert(ITable object);

    public int update(ITable object);

    public int update(ITable object, String whereClause, String[] whereArgs);

    public int delete(ITable object);

    public int delete(ITable object, String whereClause, String[] whereArgs);

    public Object queryById(String table, long id, ObjectFactory factory);

    public List<Object> query(String table, String selection, String[] selectionArgs, ObjectFactory factory);
}
