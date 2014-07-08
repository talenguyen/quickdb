package com.tale.androiddb.robolectric.mock;

import com.tale.androiddb.core.Entry;

/**
 * Created by TALE on 7/8/2014.
 */
public class Person implements Entry {
    public String name;
    public long _id;

    @Override
    public String getTable() {
        return "Person";
    }

    @Override
    public long get_id() {
        return _id;
    }
}
