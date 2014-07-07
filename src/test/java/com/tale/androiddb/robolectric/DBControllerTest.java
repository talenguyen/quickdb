package com.tale.androiddb.robolectric;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.tale.androiddb.core.Column;
import com.tale.androiddb.core.Contract;
import com.tale.androiddb.core.DBControlerFactory;
import com.tale.androiddb.core.DBController;
import com.tale.androiddb.core.SQLiteOpenHelperEx;
import com.tale.androiddb.core.Table;
import com.tale.androiddb.core.Type;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Giang on 7/7/2014.
 */
@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class DBControllerTest {

    DBController dbController;

    @Before
    public void setUp() {
        Context context = new Activity();
        SQLiteOpenHelperEx sqLiteOpenHelperEx = new SQLiteOpenHelperEx(context, new Contract() {
            @Override
            public List<Table> getTables() {
                return Arrays.asList(Table.with("test")
                        .column(Column.with("name", Type.TEXT))
                        .column(Column.with("_id", Type.INTEGER).primaryKey(true).autoincrement(true)));
            }

            @Override
            public int getDBVersion() {
                return 1;
            }

            @Override
            public String getDBName() {
                return "test";
            }
        });
        dbController = DBControlerFactory.newDBController(sqLiteOpenHelperEx);
        dbController.open();
    }

    @Test
    public void testInsert() throws Exception {
        ContentValues values = new ContentValues();
        values.put("name", "Giang");
        long id = dbController.insert("test", values);
        assertTrue(id > 0);
    }

    @Test
    public void testQuery() throws Exception {
        // Insert
        ContentValues values = new ContentValues();
        values.put("name", "Giang");
        long id = dbController.insert("test", values);
        assertTrue(id > 0);

        // Query the inserted
        Cursor cursor = dbController.quickQuery("test", "name LIKE ?", new String[]{"Giang"});
        assertNotNull(cursor);

        assertTrue(cursor.moveToFirst());

        String name = cursor.getString(cursor.getColumnIndex("name"));
        assertEquals(name, "Giang");

        // Delete the inserted
        dbController.delete("test", "name LIKE ?", new String[]{"Giang"});

        // Query to check delete
        cursor = dbController.quickQuery("test", "name LIKE ?", new String[]{"Giang"});
//        assertNull(cursor);

        assertFalse(cursor.moveToFirst());
//
//        name = cursor.getString(cursor.getColumnIndex("name"));
//        assertEquals(name, "Giang");
    }

    @After
    public void tearDown() {
        dbController.close();
    }
}
