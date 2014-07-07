package com.tale.androiddb.robolectric;

import com.tale.androiddb.core.Column;
import com.tale.androiddb.core.Table;
import com.tale.androiddb.core.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Created by TALE on 7/7/2014.
 */
@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class TableTest {
    @Test
    public void testDelete() {
        final String expected = "drop table if exist table";
        final String deleteStatement = Table.with("table").buildDeleteStatement();
        assertEquals(expected, deleteStatement);
    }

    @Test public void testCreate() {
        String expected = "create table table (name INTEGER primary key autoincrement,value TEXT not null);";
        String createStatement = Table.with("table")
                .columns(
                        Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true),
                        Column.with("value", Type.TEXT).nullable(false))
                .buildCreateStatement();
        assertEquals(expected, createStatement);

        expected = "create table table (name INTEGER primary key autoincrement,value TEXT not null);";
        createStatement = Table.with("table")
                .column(Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true))
                .column(Column.with("value", Type.TEXT).nullable(false))
                .buildCreateStatement();
        assertEquals(expected, createStatement);

        expected = "create table table (name INTEGER primary key autoincrement,value TEXT not null,unique TEXT unique);";
        createStatement = Table.with("table")
                .columns(
                        Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true),
                        Column.with("value", Type.TEXT).nullable(false),
                        Column.with("unique", Type.TEXT).unique(true).nullable(true))
                .buildCreateStatement();
        assertEquals(expected, createStatement);

        expected = "create table table (name INTEGER primary key autoincrement,value TEXT not null,unique TEXT unique);";
        createStatement = Table.with("table")
                .column(Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true))
                .column(Column.with("value", Type.TEXT).nullable(false))
                .column(Column.with("unique", Type.TEXT).unique(true).nullable(true))
                .buildCreateStatement();
        assertEquals(expected, createStatement);
    }
}
