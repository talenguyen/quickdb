package com.tale.quickdb.core;

/**
 * Created by Giang on 6/29/2014.
 */
public interface DBObjectConverter {

    public ITable fromObject(Object object);

    public Object toObject(ITable table);

}
