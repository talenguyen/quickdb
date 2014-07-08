package com.tale.androiddb.core;

import android.os.CancellationSignal;

import java.util.List;

/**
 * Created by Giang on 6/28/2014.
 */
public interface DBObjectController {

    public long insert(Entry object);

    public int update(Entry object);

    public int update(Entry object, String whereClause, String... whereArgs);

    public int delete(Entry object);

    public Object queryObjectById(String table, long id);

    public List<Object> queryObjects(String table, String selection, String... selectionArgs);

    /**
     * @param distinct      true if you want each row to be unique, false otherwise.
     * @param table         The table name to compile the queryObjects against.
     * @param columns       A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage that isn't going to be used.
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param groupBy       A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause the rows to not be grouped.
     * @param having        A filter declare which row groups to include in the List<Object>, if row grouping is being used, formatted as an SQL HAVING clause (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being used.
     * @param orderBy       How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
     * @param limit         Limits the number of rows returned by the queryObjects, formatted as LIMIT clause. Passing null denotes no LIMIT clause.
     * @return A List<Object> object, which is positioned before the first entry. Note
     * that List<Object>s are not synchronized, see the documentation for more
     * details.
     */
    public List<Object> queryObjects(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    /**
     * @param distinct           true if you want each row to be unique, false otherwise.
     * @param table              The table name to compile the queryObjects against.
     * @param columns            A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage that isn't going to be used.
     * @param selection          A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table.
     * @param selectionArgs      You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param groupBy            A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause the rows to not be grouped.
     * @param having             A filter declare which row groups to include in the List<Object>, if row grouping is being used, formatted as an SQL HAVING clause (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being used.
     * @param orderBy            How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
     * @param limit              Limits the number of rows returned by the queryObjects, formatted as LIMIT clause. Passing null denotes no LIMIT clause.
     * @param cancellationSignal A signal to cancel the operation in progress, or null if none. If the operation is canceled, then OperationCanceledException will be thrown when the queryObjects is executed.
     * @return A List<Object> object, which is positioned before the first entry. Note
     * that List<Object>s are not synchronized, see the documentation for more
     * details.
     */
    public List<Object> queryObjects(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, CancellationSignal cancellationSignal);

    /**
     * @param table         The table name to compile the queryObjects against.
     * @param columns       A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage that isn't going to be used.
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param groupBy       A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause the rows to not be grouped.
     * @param having        A filter declare which row groups to include in the List<Object>, if row grouping is being used, formatted as an SQL HAVING clause (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being used.
     * @param orderBy       How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
     * @return A List<Object> object, which is positioned before the first entry. Note
     * that List<Object>s are not synchronized, see the documentation for more
     * details.
     */
    public List<Object> queryObjects(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);

    /**
     * @param table         The table name to compile the queryObjects against.
     * @param columns       A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage that isn't going to be used.
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param groupBy       A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause the rows to not be grouped.
     * @param having        A filter declare which row groups to include in the List<Object>, if row grouping is being used, formatted as an SQL HAVING clause (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being used.
     * @param orderBy       How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
     * @param limit         Limits the number of rows returned by the queryObjects, formatted as LIMIT clause. Passing null denotes no LIMIT clause.
     * @return A List<Object> object, which is positioned before the first entry. Note
     * that List<Object>s are not synchronized, see the documentation for more
     * details.
     */
    public List<Object> queryObjects(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    /**
     * Runs the provided SQL and returns a List<Object> over the result set.
     *
     * @param sql           the SQL queryObjects. The SQL string must not be ; terminated
     * @param selectionArgs You may include ?s in where clause in the queryObjects, which will be
     *                      replaced by the values from selectionArgs. The values will be
     *                      bound as Strings.
     * @return A List<Object> object, which is positioned before the first entry. Note
     * that List<Object>s are not synchronized, see the documentation for more
     * details.
     */
    public List<Object> rawQueryObjects(String table, String sql, String... selectionArgs);
}
