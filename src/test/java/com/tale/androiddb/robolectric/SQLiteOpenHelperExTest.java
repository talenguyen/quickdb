package com.tale.androiddb.robolectric;

import android.app.Activity;
import android.content.Context;

import com.tale.androiddb.core.Column;
import com.tale.androiddb.core.Contract;
import com.tale.androiddb.core.SQLiteOpenHelperEx;
import com.tale.androiddb.core.Table;
import com.tale.androiddb.core.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

/**
 * Created by TALE on 7/7/2014.
 */
@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class SQLiteOpenHelperExTest {

    @Test
    public void testGetWritableDatabase() throws Exception {
        Context context = new Activity();
        SQLiteOpenHelperEx sqLiteOpenHelperEx = new SQLiteOpenHelperEx(context, new Contract() {
            @Override
            public List<Table> getTables() {
                return Arrays.asList(Table.with("test").column(Column.with("name", Type.INTEGER)));
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
        sqLiteOpenHelperEx.getWritableDatabase();
    }
}
