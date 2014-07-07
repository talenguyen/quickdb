package com.tale.androiddb.robolectric;

import com.tale.androiddb.core.Column;
import com.tale.androiddb.core.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by TALE on 7/7/2014.
 */
@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class ColumnTest {

    @Test
    public void testNormal() throws Exception {
        final String expected = "name INTEGER";
        Column column = Column.with("name", Type.INTEGER);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(false);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(false).autoincrement(false).nullable(true);
        assertEquals(expected, column.build());
    }

    @Test
    public void testPrimaryKey() throws Exception {
        final String expected = "name INTEGER primary key";
        Column column = Column.with("name", Type.INTEGER).primaryKey(true);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).nullable(true);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).nullable(false);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).nullable(false).unique(true);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).nullable(false).unique(false);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(false);
        assertFalse(expected.equals(column.build()));
    }

    @Test
    public void testPrimaryKeyAutoincrement() throws Exception {
        final String expected = "name INTEGER primary key autoincrement";
        Column column = Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true).nullable(true);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true).nullable(false);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true).nullable(false).unique(true);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(true).nullable(false).unique(false);
        assertEquals(expected, column.build());

        column = Column.with("name", Type.INTEGER).primaryKey(true).autoincrement(false).nullable(false).unique(false);
        assertFalse(expected.equals(column.build()));
    }

}
