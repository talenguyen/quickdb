package com.tale.quickdb.core;

/**
 * Created by Giang on 6/28/2014.
 */
public interface ObjectFactory {
    public ITable newInstance(String className);
}
