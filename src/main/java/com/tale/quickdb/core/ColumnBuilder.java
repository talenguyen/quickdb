package com.example.robolectric;

import com.google.common.base.Joiner;

/**
 * Created by TALE on 7/7/2014.
 */
public class ColumnBuilder {
    private static Joiner joiner = Joiner.on(" ").skipNulls();

    public static String build(String name, Type type, boolean primary, boolean autoincrement, boolean unique, boolean nullable) {
        return joiner.join(name, type, primary ? "primary" : null, autoincrement ? "autoincrement" : null, unique ? "unique": null, (nullable | primary | unique)? null : "not null");
    }

    public static String buildPrimaryAutoincrement(String name, Type type) {
        return build(name, type, true, true, false, false);
    }

    public static String buildPrimary(String name, Type type) {
        return build(name, type, true, false, false, false);
    }

    public static String buildUnique(String name, Type type) {
        return build(name, type, false, false, true, false);
    }

    public static String build(String name, Type type, boolean nullable) {
        return build(name, type, false, false, false, nullable);
    }

}
