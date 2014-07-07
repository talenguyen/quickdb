package com.tale.androiddb.core;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.CancellationSignal;

/**
 * Created by TALE on 6/27/2014.
 */
class DBControllerImpl implements DBController {

    private final SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private int connections;

    public DBControllerImpl(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public synchronized void open() {
        connections++;
        if (database != null && database.isOpen()) {
            return;
        }
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public synchronized void close() {
        connections = connections == 0 ? 0 : connections - 1;
        if (connections == 0 && database != null && database.isOpen()) {
            sqLiteOpenHelper.close();
        }
    }

    /**
     * Convenience method for inserting a row into the database.
     *
     * @param table  The name of table to insert
     * @param values ContentValue object to insert.
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insert(String table, ContentValues values) {
        return insert(table, null, values);
    }

    /**
     * Convenience method for inserting a row into the database.
     *
     * @param table  The name of table to insert
     * @param values ContentValue object to insert.
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    @Override
    public long insert(String table, String nullColumnHack, ContentValues values) {
        // Verify pre-define.
        verify();

        return database.insert(table, null, values);
    }

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
    @Override
    public int update(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        // Verify pre-define.
        verify();

        // Update the database, returning the number of rows that affected.
        return database.update(tableName, values, whereClause, whereArgs);
    }

    /**
     * @param tableName   the table to delete from
     * @param whereClause the optional WHERE clause to apply when deleting. Passing null
     *                    will delete all rows.
     * @param whereArgs   You may include ?s in the where clause, which will be replaced
     *                    by the values from whereArgs. The values will be bound as
     *                    Strings.
     * @return the number of rows affected
     */
    @Override
    public int delete(String tableName, String whereClause, String[] whereArgs) {
        verify();

        return database.delete(tableName, whereClause, whereArgs);
    }

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
    @Override
    public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        verify();
        return database.query(distinct, table, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Cursor query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit, CancellationSignal cancellationSignal) {
        verify();
        return database.query(distinct, table, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
    }

    @Override
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return query(table, columns, selection,
                selectionArgs, groupBy, having, orderBy, null);
    }

    @Override
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return query(false, table, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
    }

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
    @Override
    public Cursor quickQuery(String table, String selection, String[] selectionArgs) {
        return query(false, table, null, selection, selectionArgs, null, null, "_id asd", null);
    }

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
    @Override
    public Cursor rawQuery(String sql, String[] selectionArgs) {
        verify();
        return database.rawQuery(sql, selectionArgs);
    }

    /**
     * Verify the connection is open before interact with database.
     */
    private void verify() {
        if (database == null || !database.isOpen()) {
            throw new IllegalStateException(
                    "Must call open() first");
        }
    }

}
