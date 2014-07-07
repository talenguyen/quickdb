package com.tale.androiddb.core;

/**
 * Created by Giang on 6/29/2014.
 */
public interface DBObjectConverter {

    public Entry fromObject(Object object);

    public Object toObject(Entry table);

}
