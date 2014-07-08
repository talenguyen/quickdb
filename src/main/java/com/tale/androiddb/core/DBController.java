package com.tale.androiddb.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.CancellationSignal;

/**
 * Created by Giang on 6/28/2014.
 */
public interface DBController {

    /**
     * Open connection. Must be called before execute any query.
     */
    public void open();

    /**
     * Close the opened connection. You must plan to close after using.
     */
    public void close();

    /**
     * Convenience method for inserting a row into the database.
     *
     * @param table          The name of table to insert
     * @param nullColumnHack optional; may be null. SQL doesn't allow inserting a completely empty row without naming at least one column name. If your provided values is empty, no column names are known and an empty row can't be inserted. If not set to null, the nullColumnHack parameter provides the name of nullable column name to explicitly insert a NULL into in the case where your values is empty.
     * @param values         ContentValue object to insert.
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insert(String table, String nullColumnHack, ContentValues values);

    /**
     * Convenience method for inserting a row into the database.
     *
     * @param table  The name of table to insert
     * @param values ContentValue object to insert.
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insert(String table, ContentValues values);

    /**
     * Convenience method for updating rows in the database.
     *
     * @param values      a map from column names to new column values. null is a valid
     *                    value that will be translated to NULL.
     * @param whereClause the optional WHERE clause to apply when updating. Passing null
     *                    will update all rows.
     * @param whereArgs   You may include ?s in the where clause, which will be replaced
     *                    by the values from whereArgs. The values will be bound as
     *                    Strings.
     * @return the number of rows affected
     */
    public int update(String table, ContentValues values, String whereClause, String... whereArgs);

    /**
     * @param table       the table to delete from
     * @param whereClause the optional WHERE clause to apply when deleting. Passing null
     *                    will delete all rows.
     * @param whereArgs   You may include ?s in the where clause, which will be replaced
     *                    by the values from whereArgs. The values will be bound as
     *                    Strings.
     * @return the number of rows affected
     */
    public int delete(String table, String whereClause, String... whereArgs);

    /**
     * @param distinct      true if you want each row to be unique, false otherwise.
     * @param table         The table name to compile the query against.
     * @param columns       A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage that isn't going to be used.
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param groupBy       A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause the rows to not be grouped.
     * @param having        A filter declare which row groups to include in the cursor, if row grouping is being used, formatted as an SQL HAVING clause (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being used.
     * @param orderBy       How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
     * @param limit         Limits the number of rows returned by the query, formatted as LIMIT clause. Passing null denotes no LIMIT clause.
     * @return A Cursor object, which is positioned before the first entry. Note
     * that Cursors are not synchronized, see the documentation for more
     * details.
     */
    public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    /**
     * @param distinct           true if you want each row to be unique, false otherwise.
     * @param table              The table name to compile the query against.
     * @param columns            A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage that isn't going to be used.
     * @param selection          A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table.
     * @param selectionArgs      You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param groupBy            A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause the rows to not be grouped.
     * @param having             A filter declare which row groups to include in the cursor, if row grouping is being used, formatted as an SQL HAVING clause (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being used.
     * @param orderBy            How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
     * @param limit              Limits the number of rows returned by the query, formatted as LIMIT clause. Passing null denotes no LIMIT clause.
     * @param cancellationSignal A signal to cancel the operation in progress, or null if none. If the operation is canceled, then OperationCanceledException will be thrown when the query is executed.
     * @return A Cursor object, which is positioned before the first entry. Note
     * that Cursors are not synchronized, see the documentation for more
     * details.
     */
    public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, CancellationSignal cancellationSignal);

    /**
     * @param table         The table name to compile the query against.
     * @param columns       A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage that isn't going to be used.
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param groupBy       A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause the rows to not be grouped.
     * @param having        A filter declare which row groups to include in the cursor, if row grouping is being used, formatted as an SQL HAVING clause (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being used.
     * @param orderBy       How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
     * @return A Cursor object, which is positioned before the first entry. Note
     * that Cursors are not synchronized, see the documentation for more
     * details.
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);

    /**
     * @param table         The table name to compile the query against.
     * @param columns       A list of which columns to return. Passing null will return all columns, which is discouraged to prevent reading data from storage that isn't going to be used.
     * @param selection     A filter declaring which rows to return, formatted as an SQL WHERE clause (excluding the WHERE itself). Passing null will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values from selectionArgs, in order that they appear in the selection. The values will be bound as Strings.
     * @param groupBy       A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself). Passing null will cause the rows to not be grouped.
     * @param having        A filter declare which row groups to include in the cursor, if row grouping is being used, formatted as an SQL HAVING clause (excluding the HAVING itself). Passing null will cause all row groups to be included, and is required when row grouping is not being used.
     * @param orderBy       How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort order, which may be unordered.
     * @param limit         Limits the number of rows returned by the query, formatted as LIMIT clause. Passing null denotes no LIMIT clause.
     * @return A Cursor object, which is positioned before the first entry. Note
     * that Cursors are not synchronized, see the documentation for more
     * details.
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    /**
     * Query the given URL, returning a Cursor over the result set.
     *
     * @param table         The table name to compile the query against.
     * @param selection     A filter declaring which rows to return, formatted as an SQL
     *                      WHERE clause (excluding the WHERE itself). Passing null will
     *                      return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the
     *                      values from selectionArgs, in order that they appear in the
     *                      selection. The values will be bound as Strings.
     * @return A Cursor object, which is positioned before the first entry. Note
     * that Cursors are not synchronized, see the documentation for more
     * details.
     */
    public Cursor quickQuery(String table, String selection, String... selectionArgs);

    /**
     * Runs the provided SQL and returns a Cursor over the result set.
     *
     * @param sql           the SQL query. The SQL string must not be ; terminated
     * @param selectionArgs You may include ?s in where clause in the query, which will be
     *                      replaced by the values from selectionArgs. The values will be
     *                      bound as Strings.
     * @return A Cursor object, which is positioned before the first entry. Note
     * that Cursors are not synchronized, see the documentation for more
     * details.
     */
    public Cursor rawQuery(String sql, String... selectionArgs);

}
