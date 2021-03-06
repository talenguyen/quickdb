package com.tale.androiddb.core;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Giang on 6/28/2014.
 */
@Retention(SOURCE)
@Target(TYPE)
public @interface DBTable {
}
