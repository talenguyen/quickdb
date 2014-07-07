package com.tale.androiddb.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Giang on 6/28/2014.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface DBField {

    boolean primaryKey() default false;

    boolean autoincrement() default false;

    boolean nullable() default true;

    boolean unique() default false;

}
